package net.datenwerke.rs.dropbox.client.dropbox;

import com.google.gwt.inject.client.AbstractGinModule;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.dropbox.client.dropbox.provider.DropboxTreeProvider;
import net.datenwerke.rs.dropbox.client.dropbox.provider.annotations.DatasinkTreeDropbox;

public class DropboxUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeDropbox.class).toProvider(DropboxTreeProvider.class);
      bind(DropboxUiStartup.class).asEagerSingleton();

   }

}
