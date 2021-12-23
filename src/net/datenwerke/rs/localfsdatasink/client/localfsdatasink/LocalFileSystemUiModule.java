package net.datenwerke.rs.localfsdatasink.client.localfsdatasink;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.LocalFileSystemTreeProvider;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.annotations.DatasinkTreeLocalFileSystem;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class LocalFileSystemUiModule extends AbstractGinModule {

   public final static String NAME = "Local Filesystem";
   public final static BaseIcon ICON = BaseIcon.SERVER;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = LocalFileSystemDatasinkDto.class;

   @Override
   protected void configure() {
      bind(LocalFileSystemUiService.class).to(LocalFileSystemUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeLocalFileSystem.class).toProvider(LocalFileSystemTreeProvider.class);
      bind(LocalFileSystemUiStartup.class).asEagerSingleton();
   }

}