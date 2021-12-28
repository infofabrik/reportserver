package net.datenwerke.rs.base.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.base.client.datasources.DatasourceExtensionUiModule;
import net.datenwerke.rs.base.client.reportengines.BaseReportEngineUiModule;

public class RsBaseUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      install(new DatasourceExtensionUiModule());
      install(new BaseReportEngineUiModule());
   }

}
