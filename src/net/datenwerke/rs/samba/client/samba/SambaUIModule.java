package net.datenwerke.rs.samba.client.samba;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.samba.client.samba.provider.SambaTreeProvider;
import net.datenwerke.rs.samba.client.samba.provider.annotations.DatasinkTreeSamba;

/**
 * 
 *
 */
public class SambaUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(SambaUiService.class).to(SambaUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeSamba.class).toProvider(SambaTreeProvider.class);

      bind(SambaUiStartup.class).asEagerSingleton();
   }

}