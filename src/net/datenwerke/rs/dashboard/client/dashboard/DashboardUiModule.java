package net.datenwerke.rs.dashboard.client.dashboard;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.dashboard.client.dashboard.provider.BasicTreeProvider;
import net.datenwerke.rs.dashboard.client.dashboard.provider.DadgetsTreeProvider;
import net.datenwerke.rs.dashboard.client.dashboard.provider.DashboardsTreeProvider;
import net.datenwerke.rs.dashboard.client.dashboard.provider.FolderTreeProvider;
import net.datenwerke.rs.dashboard.client.dashboard.provider.FullTreeProvider;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardManagerAdminViewTree;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardTreeBasic;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardTreeDadgets;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardTreeDashboards;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardTreeFolders;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardTreeFull;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetCatalogFactory;

public class DashboardUiModule extends AbstractGinModule {

   public static final String ADMIN_TREE_MENU_NAME = "dashboard:admin:tree:menu";

   public static final String DASHBOARD_HISTORY_TOKEN = "dashboardmgr";

   @Override
   protected void configure() {
      /* bind trees */
      bind(UITree.class).annotatedWith(DashboardTreeBasic.class).toProvider(BasicTreeProvider.class);
      bind(UITree.class).annotatedWith(DashboardTreeFull.class).toProvider(FullTreeProvider.class);
      bind(UITree.class).annotatedWith(DashboardTreeFolders.class).toProvider(FolderTreeProvider.class);
      bind(UITree.class).annotatedWith(DashboardTreeDashboards.class).toProvider(DashboardsTreeProvider.class);
      bind(UITree.class).annotatedWith(DashboardTreeDadgets.class).toProvider(DadgetsTreeProvider.class);
      bind(UITree.class).annotatedWith(DashboardManagerAdminViewTree.class).toProvider(FullTreeProvider.class)
            .in(Singleton.class);

      install(new GinFactoryModuleBuilder().build(DadgetCatalogFactory.class));

      bind(DashboardUiStartup.class).asEagerSingleton();
   }

}
