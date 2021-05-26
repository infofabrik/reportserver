/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/*
Copyright (c) 2002-2018 ymnk, JCraft,Inc. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

  1. Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.

  2. Redistributions in binary form must reproduce the above copyright 
     notice, this list of conditions and the following disclaimer in 
     the documentation and/or other materials provided with the distribution.

  3. The names of the authors may not be used to endorse or promote products
     derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JCRAFT,
INC. OR ANY CONTRIBUTORS TO THIS SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package net.datenwerke.rs.scp.service.scp;

import static net.datenwerke.rs.scp.service.scp.ScpModule.PROPERTY_SCP_DISABLED;
import static net.datenwerke.rs.scp.service.scp.ScpModule.PROPERTY_SCP_SCHEDULING_ENABLED;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import com.google.inject.Provider;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scp.client.scp.hookers.ScpPublicKeyAuthenticatorHooker;
import net.datenwerke.rs.scp.service.scp.annotations.DefaultScpDatasink;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;
import net.datenwerke.rs.utils.config.ConfigService;

public class ScpServiceImpl implements ScpService {

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<ReportService> reportServiceProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
   private final Provider<Optional<ScpDatasink>> defaultDatasinkProvider;

   @Inject
   public ScpServiceImpl(
         Provider<ConfigService> configServiceProvider, 
         Provider<ReportService> reportServiceProvider,
         Provider<DatasinkService> datasinkServiceProvider,
         @DefaultScpDatasink Provider<Optional<ScpDatasink>> defaultDatasinkProvider
         ) {
      this.configServiceProvider = configServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
      this.defaultDatasinkProvider = defaultDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   // adapted from ScpTo.java (jsch-0.1.55)
   @Override
   public void sendToScpServer(Object report, ScpDatasink scpDatasink, String filename, String folder)
         throws IOException, JSchException {
      if (!isScpEnabled())
         throw new IllegalStateException("scp is disabled");

      Objects.requireNonNull(scpDatasink, "scpDatasink is null!");
      Objects.requireNonNull(folder);
      Objects.requireNonNull(scpDatasink.getAuthenticationType());

      final String host = Objects.requireNonNull(scpDatasink.getHost()).replace("scp://", "");
      final int port = scpDatasink.getPort();
      final String username = scpDatasink.getUsername();

      if (null == host || host.trim().contentEquals("") || null == username || username.trim().contentEquals(""))
         throw new IllegalArgumentException("SCP server is not configured correctly");

      Path knownHostsFile = Paths.get(configServiceProvider.get().getConfigFailsafe("security/misc.cf")
            .getString("knownHosts", System.getProperty("user.home") + "/.ssh/known_hosts"));
      if (!Files.exists(knownHostsFile))
         throw new IllegalArgumentException("known_hosts file does not exist");

      // remote folder must exist
      Path to = Paths.get(folder, filename);

      Channel channel = null;
      Session session = null;

      try (InputStream bis = reportServiceProvider.get().createInputStream(report)) {

         byte[] data = IOUtils.toByteArray(bis);

         JSch jsch = new JSch();
         jsch.setKnownHosts(knownHostsFile.toAbsolutePath().toString());
         session = jsch.getSession(username, host, port);
         if (ScpPublicKeyAuthenticatorHooker.AUTHENTICATION_TYPE.equals(scpDatasink.getAuthenticationType())) {
            jsch.addIdentity("scpIdentityKey", scpDatasink.getPrivateKey(), (byte[]) null,
                  (null != scpDatasink.getPrivateKeyPassphrase()
                        ? scpDatasink.getPrivateKeyPassphrase().getBytes(StandardCharsets.UTF_8)
                        : null));
         } else {
            session.setPassword(scpDatasink.getPassword());
         }

         session.setConfig("StrictHostKeyChecking", "yes");

         session.connect();

         // exec 'scp -t rfile' remotely
         String rfile = to.toAbsolutePath().toString().replace("'", "'\"'\"'");
         rfile = "'" + rfile + "'";
         String command = "scp " + " -t " + rfile;
         channel = session.openChannel("exec");
         ((ChannelExec) channel).setCommand(command);

         // get I/O streams for remote scp
         try (OutputStream out = channel.getOutputStream(); InputStream in = channel.getInputStream()) {

            channel.connect();

            checkAck(in);

            // send "C0644 filesize filename", where filename should not include '/'
            long filesize = data.length;
            command = "C0644 " + filesize + " ";
            command += filename;

            command += "\n";
            out.write(command.getBytes());
            out.flush();
            checkAck(in);

            // send the data
            out.write(data, 0, data.length); // out.flush();
            // send '\0'
            out.write(0);
            out.flush();
            checkAck(in);

         }
      } finally {
         if (null != channel)
            channel.disconnect();
         if (null != session)
            session.disconnect();
      }
   }

   // adapted from ScpTo.java (jsch-0.1.55)
   private void checkAck(InputStream in) throws IOException {
      StringBuffer sb = new StringBuffer();
      int b = in.read();
      // b may be 0 for success,
      // 1 for error,
      // 2 for fatal error,
      // -1
      if (b == 0) {
         // OK
      }
      if (b == -1)
         throw new IOException("Ack: -1");

      if (b == 1 || b == 2) {
         int c;
         do {
            c = in.read();
            sb.append((char) c);
         } while (c != '\n');
         if (b == 1) { // error
            throw new IOException(sb.toString());
         }
         if (b == 2) { // fatal error
            throw new IOException(sb.toString());
         }
      }
   }

   @Override
   public Map<StorageType, Boolean> getScpEnabledConfigs() {
      return datasinkServiceProvider.get().getEnabledConfigs(StorageType.SCP, PROPERTY_SCP_DISABLED,
            StorageType.SCP_SCHEDULING, PROPERTY_SCP_SCHEDULING_ENABLED);
   }

   @Override
   public boolean isScpEnabled() {
      return datasinkServiceProvider.get().isEnabled(PROPERTY_SCP_DISABLED);
   }

   @Override
   public boolean isScpSchedulingEnabled() {
      return datasinkServiceProvider.get().isSchedulingEnabled(PROPERTY_SCP_SCHEDULING_ENABLED);
   }

   @Override
   public void testScpDatasink(ScpDatasink scpDatasink) throws IOException, JSchException {
      if (!isScpEnabled())
         throw new IllegalStateException("scp is disabled");

      sendToScpServer("ReportServer SCP Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            scpDatasink, "reportserver-scp-test.txt", scpDatasink.getFolder());

   }
   
   @Override
   public Optional<ScpDatasink> getDefaultDatasink() {
      return defaultDatasinkProvider.get();
   }

}
