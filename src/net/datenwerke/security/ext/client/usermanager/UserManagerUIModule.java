package net.datenwerke.security.ext.client.usermanager;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.security.ext.client.usermanager.provider.BasicTreeProvider;
import net.datenwerke.security.ext.client.usermanager.provider.FolderTreeProvider;
import net.datenwerke.security.ext.client.usermanager.provider.FullTreeProvider;
import net.datenwerke.security.ext.client.usermanager.provider.GroupTreeProvider;
import net.datenwerke.security.ext.client.usermanager.provider.UserTreeProvider;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerAdminViewTree;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeBasic;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeBasicSingleton;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeFolders;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeFull;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeGroups;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerTreeUsers;
import net.datenwerke.security.ext.client.usermanager.utils.UserIconMapping;

public class UserManagerUIModule extends AbstractGinModule {

   public static final String USERMANAGER_FAV_HISTORY_TOKEN = "usermgr";

   public static final String ADMIN_TREE_MENU_NAME = "usermanager:admin:tree:menu";

   @Override
   protected void configure() {
      /* bind service */
      bind(UserManagerUIService.class).to(UserManagerUIServiceImpl.class).in(Singleton.class);

      /* bind trees */
      bind(UITree.class).annotatedWith(UserManagerTreeBasic.class).toProvider(BasicTreeProvider.class);
      bind(UITree.class).annotatedWith(UserManagerTreeBasicSingleton.class).toProvider(BasicTreeProvider.class)
            .in(Singleton.class);
      bind(UITree.class).annotatedWith(UserManagerTreeFull.class).toProvider(FullTreeProvider.class);
      bind(UITree.class).annotatedWith(UserManagerTreeFolders.class).toProvider(FolderTreeProvider.class);
      bind(UITree.class).annotatedWith(UserManagerTreeUsers.class).toProvider(UserTreeProvider.class);
      bind(UITree.class).annotatedWith(UserManagerTreeGroups.class).toProvider(GroupTreeProvider.class);
      bind(UITree.class).annotatedWith(UserManagerAdminViewTree.class).toProvider(FullTreeProvider.class)
            .in(Singleton.class);

      /* bind service */
      bind(UserManagerUIStartup.class).asEagerSingleton();

      /* request static injection */
      requestStaticInjection(UserIconMapping.class);
   }

}
