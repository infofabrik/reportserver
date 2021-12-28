package net.datenwerke.gf.service.download;

import com.google.inject.AbstractModule;

public class FileDownloadModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(FileDownloadService.class).to(FileDownloadServiceImpl.class);
   }

}
