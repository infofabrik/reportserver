package net.datenwerke.gf.client.treedb;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.helper.menu.InfoMenuItem;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;

public class DwGwtTreeDbUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(DwGwtTreeDbUiStartup.class).asEagerSingleton();

      /* bind service */
      bind(TreeDBUIService.class).to(TreeDBUIServiceImpl.class).in(Singleton.class);

      install(new GinFactoryModuleBuilder().build(UiTreeFactory.class));

      /* static injection */
      requestStaticInjection(InfoMenuItem.class, TreeSelectionPopup.class, SingleTreeSelectionField.class);
   }

}
