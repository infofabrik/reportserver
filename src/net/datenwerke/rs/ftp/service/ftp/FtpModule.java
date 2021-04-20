package net.datenwerke.rs.ftp.service.ftp;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;

public class FtpModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(FtpService.class).to(FtpServiceImpl.class);

      requestStaticInjection(FtpDatasink.class);
      requestStaticInjection(SftpDatasink.class);
      requestStaticInjection(FtpsDatasink.class);

      bind(FtpStartup.class).asEagerSingleton();
   }

}
