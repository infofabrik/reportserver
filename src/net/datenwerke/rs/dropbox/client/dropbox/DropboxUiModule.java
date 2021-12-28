package net.datenwerke.rs.dropbox.client.dropbox;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.client.dropbox.provider.DropboxTreeProvider;
import net.datenwerke.rs.dropbox.client.dropbox.provider.annotations.DatasinkTreeDropbox;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DropboxUiModule extends AbstractGinModule {

   public final static String NAME = "Dropbox";
   public final static BaseIcon ICON = BaseIcon.DROPBOX;
   public final static Class<? extends DatasinkDefinitionDto> TYPE = DropboxDatasinkDto.class;

   @Override
   protected void configure() {
      bind(DropboxUiService.class).to(DropboxUiServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(DatasinkTreeDropbox.class).toProvider(DropboxTreeProvider.class);
      bind(DropboxUiStartup.class).asEagerSingleton();

   }

}
