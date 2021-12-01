package net.datenwerke.rs.ftp.client.ftp;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.ftp.client.ftp.provider.FtpTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.FtpsTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.SftpTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeFtp;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeFtps;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeSftp;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.hooker.SftpPrivateKeyUploadHooker;

/**
 * 
 *
 */
public class FtpUiModule extends AbstractGinModule {

   /**
    * Identifies the {@link FileUploadHandlerHook} to use
    * {@link SftpPrivateKeyUploadHooker} in case of SFTP private key upload
    * ({@link SftpDatasink}).
    */
   public static final String SFTP_PRIVATE_KEY_UPLOAD_HANDLER_ID = "sftp_private_key_upload_handler";

   /**
    * Field containing the datasink id used while uploading a SFTP datasink
    * ({@link SftpDatasink}) private key.
    */
   public static final String SFTP_UPLOAD_DATASINK_ID_FIELD = "sftp_datasinkID";

   @Override
   protected void configure() {
      bind(FtpUiService.class).to(FtpUiServiceImpl.class).in(Singleton.class);
      
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeFtp.class).toProvider(FtpTreeProvider.class);
      bind(UITree.class).annotatedWith(DatasinkTreeSftp.class).toProvider(SftpTreeProvider.class);
      bind(UITree.class).annotatedWith(DatasinkTreeFtps.class).toProvider(FtpsTreeProvider.class);

      bind(FtpUiStartup.class).asEagerSingleton();
   }

}
