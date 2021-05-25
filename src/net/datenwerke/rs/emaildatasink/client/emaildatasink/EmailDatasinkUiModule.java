package net.datenwerke.rs.emaildatasink.client.emaildatasink;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.EmailDatasinkTreeProvider;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.annotations.DatasinkTreeEmail;

public class EmailDatasinkUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeEmail.class).toProvider(EmailDatasinkTreeProvider.class);
      bind(EmailDatasinkUiStartup.class).asEagerSingleton();
   }

}