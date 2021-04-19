package net.datenwerke.rs.scp.client.scp;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.scp.client.scp.provider.ScpTreeProvider;
import net.datenwerke.rs.scp.client.scp.provider.annotations.DatasinkTreeScp;

public class ScpUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeScp.class).toProvider(ScpTreeProvider.class);

      bind(ScpUiStartup.class).asEagerSingleton();
   }

}
