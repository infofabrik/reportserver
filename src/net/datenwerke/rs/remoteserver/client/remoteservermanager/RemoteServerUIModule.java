package net.datenwerke.rs.remoteserver.client.remoteservermanager;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.BasicTreeProvider;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.FolderTreeProvider;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.FullTreeProvider;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.annotations.RemoteServerManagerAdminViewTree;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.annotations.RemoteServerTreeBasic;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.annotations.RemoteServerTreeFolders;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class RemoteServerUIModule extends AbstractGinModule {

   public static final String REMOTE_SERVER_FAV_HISTORY_TOKEN = "remoteservermgr";
   public static final String ADMIN_TREE_MENU_NAME = "remoteserver:admin:tree:menu";
   public final static BaseIcon ICON = BaseIcon.ARROW_UP;
   public final static Class<? extends RemoteServerDefinitionDto> TYPE = RemoteServerDto.class;


   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(RemoteServerTreeBasic.class).toProvider(BasicTreeProvider.class);
      bind(UITree.class).annotatedWith(RemoteServerManagerAdminViewTree.class).toProvider(FullTreeProvider.class)
            .in(Singleton.class);
      bind(UITree.class).annotatedWith(RemoteServerTreeFolders.class).toProvider(FolderTreeProvider.class);

      /* bind service */
      bind(RemoteServerUIService.class).to(RemoteServerUIServiceImpl.class).in(Singleton.class);

      /* bind startup */
      bind(RemoteServerUIStartup.class).asEagerSingleton();
   }

}
