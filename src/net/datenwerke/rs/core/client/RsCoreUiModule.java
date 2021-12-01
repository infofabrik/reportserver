package net.datenwerke.rs.core.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.core.client.contexthelp.ContextHelpUiModule;
import net.datenwerke.rs.core.client.i18tools.I18nToolsUiModule;
import net.datenwerke.rs.core.client.parameters.helper.ParameterFieldWrapperForFrontend;
import net.datenwerke.rs.core.client.reportproperties.ReportPropertiesUiModule;
import net.datenwerke.rs.core.client.urlview.UrlViewUiModule;
import net.datenwerke.rs.core.client.userprofile.CoreUserProfileModule;

public class RsCoreUiModule extends AbstractGinModule {
   
   public final static String EMAIL_ENCRYPTION_MIXED = "allow_mixed";
   public final static String EMAIL_ENCRYPTION_STRICT = "strict";

   @Override
   protected void configure() {
      install(new I18nToolsUiModule());
      install(new ContextHelpUiModule());
      install(new CoreUserProfileModule());
      install(new UrlViewUiModule());
      install(new ReportPropertiesUiModule());

      requestStaticInjection(ParameterFieldWrapperForFrontend.class);

      bind(RsCoreUiStartup.class).asEagerSingleton();
   }

}
