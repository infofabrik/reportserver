package net.datenwerke.gf.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.download.FileDownloadUiModule;
import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gf.client.uiutils.date.DateFormulaPicker;
import net.datenwerke.gf.client.upload.FileUploadUIModule;

public class DwGwtFrameworkUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      install(new FileUploadUIModule());
      install(new FileDownloadUiModule());

      bind(DwGwtFrameworkUIStartup.class).asEagerSingleton();

      requestStaticInjection(DateFormulaPicker.class);
      requestStaticInjection(ClientDownloadHelper.class);
   }

}
