package net.datenwerke.rs.box.client.box;

import com.google.gwt.inject.client.AbstractGinModule;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.box.client.box.provider.BoxTreeProvider;
import net.datenwerke.rs.box.client.box.provider.annotations.DatasinkTreeBox;

public class BoxUiModule extends AbstractGinModule {
   
   public final static String BOX_NAME = "Box";

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeBox.class).toProvider(BoxTreeProvider.class);
      bind(BoxUiStartup.class).asEagerSingleton();

   }

}
