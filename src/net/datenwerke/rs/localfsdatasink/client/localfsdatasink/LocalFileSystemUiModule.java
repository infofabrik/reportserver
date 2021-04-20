package net.datenwerke.rs.localfsdatasink.client.localfsdatasink;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.LocalFileSystemTreeProvider;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.annotations.DatasinkTreeLocalFileSystem;

public class LocalFileSystemUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeLocalFileSystem.class)
            .toProvider(LocalFileSystemTreeProvider.class);
      bind(LocalFileSystemUiStartup.class).asEagerSingleton();
   }

}