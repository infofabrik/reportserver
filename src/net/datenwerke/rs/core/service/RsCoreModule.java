package net.datenwerke.rs.core.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.core.service.contexthelp.ContextHelpModule;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsServiceImpl;
import net.datenwerke.rs.core.service.i18ntools.RemoteMessageModule;
import net.datenwerke.rs.core.service.jarextension.ReportServerExtenderModule;
import net.datenwerke.rs.core.service.urlview.UrlViewModule;
import net.datenwerke.rs.core.service.urlview.UrlViewRestrictor;

public class RsCoreModule extends AbstractModule {

   public static final String TEMPLATES_CONFIG_FILE = "main/templates.cf";
   public static final String ERROR_TEMPLATE_PROPERTY = "errors.html";

   public static final String UNNAMED_FIELD = "unnamed";

   @Override
   protected void configure() {
      bind(I18nToolsService.class).to(I18nToolsServiceImpl.class);

      install(new ContextHelpModule());
      install(new RemoteMessageModule());

      install(new ReportServerExtenderModule());

      install(new UrlViewModule());

      bind(RsCoreStartup.class).asEagerSingleton();

      requestStaticInjection(UrlViewRestrictor.class);
   }

}
