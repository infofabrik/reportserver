package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.sshd.common.NamedResource;
import org.apache.sshd.common.file.FileSystemFactory;
import org.apache.sshd.common.keyprovider.FileKeyPairProvider;
import org.apache.sshd.common.keyprovider.KeyPairProvider;
import org.apache.sshd.common.session.SessionContext;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.AbstractGeneratorHostKeyProvider;
import org.apache.sshd.server.subsystem.SubsystemFactory;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

import net.datenwerke.gf.service.properties.PropertiesService;
import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.configservice.service.configservice.hooks.adapter.ReloadConfigNotificationHookAdapter;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.KeyLocation;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpEnabled;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpPort;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.vfs.rsvfs.RSVFSFileSystem;
import net.datenwerke.rs.terminal.service.terminal.vfs.rsvfs.RSVFSFileSystemProvider;
import net.datenwerke.rs.utils.misc.Nullable;

public class SftpServiceImpl implements SftpService {

   private final static String PROP_HOST_KEY = "rs:sftp:hostkey";

   private final Provider<String> keyLocation;
   private final Provider<Boolean> sftpEnabled;
   private final Provider<SftpAuthenticator> authenticatorProvider;
   private final Provider<PropertiesService> propertiesService;
   private final Provider<TerminalService> terminalServiceProvider;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private Provider<Integer> port;
   private SshServer sshd;

   private final Provider<EntityManager> entityManagerProvider;
   private final Provider<UnitOfWork> unitOfWorkProvider;
   private final Provider<RsSftpSubSystemFactory> rsSftpSubSystemFactory;
   private final Provider<TempFileService> tempFileServiceProvider;

   @Inject
   public SftpServiceImpl(Provider<SftpAuthenticator> authenticatorProvider,
         Provider<PropertiesService> propertiesService, Provider<EntityManager> entityManagerProvider,
         Provider<UnitOfWork> unitOfWorkProvider, HookHandlerService hookhandler,
         Provider<TerminalService> terminalServiceProvider, Provider<RsSftpSubSystemFactory> rsSftpSubSystemFactory,
         Provider<TempFileService> tempFileService, @Nullable @KeyLocation Provider<String> keyLocation,
         @SftpPort Provider<Integer> port, @SftpEnabled Provider<Boolean> sftpEnabled) {
      this.authenticatorProvider = authenticatorProvider;
      this.propertiesService = propertiesService;
      this.entityManagerProvider = entityManagerProvider;
      this.unitOfWorkProvider = unitOfWorkProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.rsSftpSubSystemFactory = rsSftpSubSystemFactory;
      this.tempFileServiceProvider = tempFileService;

      this.keyLocation = keyLocation;
      this.port = port;
      this.sftpEnabled = sftpEnabled;

      hookhandler.attachHooker(ReloadConfigNotificationHook.class, new ReloadConfigNotificationHookAdapter() {

         @Override
         public void reloadConfig() {
            try {
               shutdown();
               start();
            } catch (Exception e) {
               logger.warn("Failed to restart sftp server", e);
            }
         }
      });
   }

   @Override
   public void start() throws IOException {
      if (!sftpEnabled.get()) {
         logger.info("SFTP server disabled. Internal SFTP remains inactive.");
         return;
      }

      if (null == keyLocation.get()) {
         logger.info("No key location given for internal SFTP server. Internal SFTP remains inactive.");
         return;
      }

      sshd = SshServer.setUpDefaultServer();
      sshd.setPort(port.get());

      String keylo = keyLocation.get();
      if (keylo.contains(":")) {
         sshd.setKeyPairProvider(new UrlKeyPairProvider(new URL[] { new URL(keyLocation.get()) }));
      } else if (keylo.equals("$generated")) {
         sshd.setKeyPairProvider(getGeneratorKeyPairProvider());
      } else {
         sshd.setKeyPairProvider(new FileKeyPairProvider(Paths.get(keyLocation.get())));
      }

      final SftpAuthenticator authenticator = authenticatorProvider.get();
      sshd.setPasswordAuthenticator(authenticator);

      sshd.setSubsystemFactories(Arrays.<SubsystemFactory>asList(rsSftpSubSystemFactory.get()));

      sshd.setFileSystemFactory(new FileSystemFactory() {

         @Override
         public FileSystem createFileSystem(SessionContext sessionContext) throws IOException {
            URI vfsUri = URI.create("rs-vfs:/");
            Map<String, Object> env = new HashMap<>();
            env.put(RSVFSFileSystem.VFS, terminalServiceProvider);
            env.put(RSVFSFileSystem.SESSION_CONTEXT, sessionContext);
            env.put(RSVFSFileSystem.TEMPFILESERVICE_PROVIDER, tempFileServiceProvider);
            RSVFSFileSystem fs = new RSVFSFileSystemProvider().newFileSystem(vfsUri, env);
            return fs;
         }

         @Override
         public Path getUserHomeDir(SessionContext sessionContext) throws IOException {
            return ((RSVFSFileSystem) createFileSystem(sessionContext)).getDefaultDir();
         }

      });

      sshd.start();
   }

   /**
    * Generates a private-public key pair for the local SSH server and saves it as
    * a property in the ReportServer database. This should only happen once: the
    * first time the server loads and detects that no key pair is available.
    * 
    * @return the generated private-public key pair
    * @throws IOException if an I/O error occurs
    */
   private KeyPairProvider getGeneratorKeyPairProvider() throws IOException {
      final File tmpfile = File.createTempFile("rssftptmp", "");

      return new AbstractGeneratorHostKeyProvider() {

         @Override
         protected Iterable<KeyPair> loadFromFile(SessionContext session, String alg, Path keyPath) {
            /*
             * instead of loading from a file we load the private-public key pair from the
             * database
             */
            String pem = propertiesService.get().get(PROP_HOST_KEY);
            if (null == pem)
               return null;
            PEMParser r = new PEMParser(new StringReader(pem));
            try {
               PEMKeyPair pemKeyPair = (PEMKeyPair) r.readObject();
               KeyPair kp = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
               return Collections.singletonList(kp);
            } catch (IOException e) {
               logger.warn("An exception occured while loading the key pair", e);
               throw new RuntimeException(e);
            }
         }

         @Override
         public Path getPath() {
            /*
             * the tmpfile is not really used by the ssh-library because we use the
             * ReportServer database instead. Still, the library needs this path.
             */
            return tmpfile.toPath();
         }

         @Override
         protected void doWriteKeyPair(NamedResource resourceKey, KeyPair kp, OutputStream outputStream)
               throws IOException, GeneralSecurityException {
            UnitOfWork unitOfWork = unitOfWorkProvider.get();
            EntityManager em = null;
            em = entityManagerProvider.get();
            em.getTransaction().begin();
            boolean success = false;
            try {
               StringWriter sw = new StringWriter();
               JcaPEMWriter w = new JcaPEMWriter(sw);
               w.writeObject(kp);
               w.flush();
               /* write the private-public key pair into the database */
               propertiesService.get().setProperty(PROP_HOST_KEY, sw.toString());
               success = true;
            } finally {
               try {
                  if (null != em && success)
                     em.getTransaction().commit();
                  else if (null != em)
                     em.getTransaction().rollback();
               } finally {
                  unitOfWork.end();
               }
            }

         }

      };
   }

   @Override
   public boolean isActive() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean isShutdown() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean isTerminated() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void shutdown() {
      try {
         if (null != sshd)
            sshd.stop();
      } catch (IOException e) {
         logger.warn("An exception occured while stopping the sftp service", e);
      }
   }

}
