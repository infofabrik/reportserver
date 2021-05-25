package net.datenwerke.rs.samba.service.samba;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import com.google.inject.Provider;

import jcifs.CIFSContext;
import jcifs.context.SingletonContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;

public class SambaServiceImpl implements SambaService {

   private static final String PROPERTY_SAMBA_DISABLED = "samba[@disabled]";
   private static final String PROPERTY_SAMBA_SCHEDULER_ENABLED = "samba[@supportsScheduling]";

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<ReportService> reportServiceProvider;
   
   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   @Inject
   public SambaServiceImpl(Provider<ConfigService> configServiceProvider,
         Provider<ReportService> reportServiceProvider) {
      this.configServiceProvider = configServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
   }

   @Override
   public void sendToSambaServer(Object report, SambaDatasink sambaDatasink, String filename, String folder)
         throws IOException {
      if (!isSambaEnabled())
         throw new IllegalStateException("samba is disabled");

      Objects.requireNonNull(sambaDatasink, "sambaDatasink is null!");
      Objects.requireNonNull(folder);
      Objects.requireNonNull(filename);

      final String host = Objects.requireNonNull(sambaDatasink.getHost());
      final int port = sambaDatasink.getPort();
      final String domain = (null == sambaDatasink.getDomain() ? "" : sambaDatasink.getDomain());
      final String username = sambaDatasink.getUsername();

      if (null == host || host.trim().contentEquals("") || null == username || username.trim().contentEquals(""))
         throw new IllegalArgumentException("Samba server is not configured correctly");

      try (InputStream bis = reportServiceProvider.get().createInputStream(report)) {

         NtlmPasswordAuthenticator ntlmPasswordAuthenticator = new NtlmPasswordAuthenticator(domain, username,
               sambaDatasink.getPassword());
         CIFSContext context = SingletonContext.getInstance().withCredentials(ntlmPasswordAuthenticator);
         String path = host + ":" + port + "/" + folder + "/" + filename;
         SmbFile resource = (SmbFile) context.get(path);

         try (OutputStream ostream = resource.getOutputStream()) {
            IOUtils.copy(bis, ostream);
         }

         if (!resource.exists())
            throw new IOException("Samba file was not uploaded successfully");
      }

   }

   @Override
   public boolean isSambaEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE).getBoolean(PROPERTY_SAMBA_DISABLED, false);
   }

   @Override
   public boolean isSambaSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE).getBoolean(PROPERTY_SAMBA_SCHEDULER_ENABLED,
            true);
   }
   
   @Override
   public void testSambaDataSink(SambaDatasink sambaDatasink) throws IOException {
      if (!isSambaEnabled())
         throw new IllegalStateException("samba is disabled");

      sendToSambaServer("ReportServer Samba Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            sambaDatasink, "reportserver-samba-test.txt", sambaDatasink.getFolder());
   }

   @Override
   public Map<StorageType, Boolean> getSambaEnabledConfigs() {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(StorageType.SAMBA, isSambaEnabled());
      configs.put(StorageType.SAMBA_SCHEDULING, isSambaSchedulingEnabled());
      return configs;
   }

}