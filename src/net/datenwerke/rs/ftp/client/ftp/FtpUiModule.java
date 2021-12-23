package net.datenwerke.rs.ftp.client.ftp;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.provider.FtpTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.FtpsTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.SftpTreeProvider;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeFtp;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeFtps;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeSftp;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.hooker.SftpPrivateKeyUploadHooker;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class FtpUiModule extends AbstractGinModule {

   public final static String FTP_NAME = "FTP";
   public final static BaseIcon FTP_ICON = BaseIcon.UPLOAD;
   public final static Class<? extends DatasinkDefinitionDto> FTP_TYPE = FtpDatasinkDto.class;
   
   public final static String SFTP_NAME = "SFTP";
   public final static BaseIcon SFTP_ICON = BaseIcon.ARROW_CIRCLE_UP;
   public final static Class<? extends DatasinkDefinitionDto> SFTP_TYPE = SftpDatasinkDto.class;
   
   public final static String FTPS_NAME = "FTPS";
   public final static BaseIcon FTPS_ICON = BaseIcon.ARROW_CIRCLE_O_UP;
   public final static Class<? extends DatasinkDefinitionDto> FTPS_TYPE = FtpsDatasinkDto.class;
   
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
