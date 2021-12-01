package net.datenwerke.rs.localfsdatasink.client.localfsdatasink;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.LocalFileSystemTreeProvider;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.annotations.DatasinkTreeLocalFileSystem;

public class LocalFileSystemUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(LocalFileSystemUiService.class).to(LocalFileSystemUiServiceImpl.class).in(Singleton.class);
      
      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeLocalFileSystem.class)
            .toProvider(LocalFileSystemTreeProvider.class);
      bind(LocalFileSystemUiStartup.class).asEagerSingleton();
   }

}