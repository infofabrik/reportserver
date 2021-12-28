package net.datenwerke.gf.client.managerhelper;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;

public class ManagerHelperUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ManagerHelperUiStartup.class).asEagerSingleton();

      install(new GinFactoryModuleBuilder().build(ManagerHelperTreeFactory.class));

      /* request static injection */
      requestStaticInjection(AbstractTreeMainPanel.class);
   }

}
