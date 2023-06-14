package net.datenwerke.rs.transport.client.transport;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.transport.client.transport.eximport.ex.TransportManagerExportUIModule;
import net.datenwerke.rs.transport.client.transport.provider.BasicTreeProvider;
import net.datenwerke.rs.transport.client.transport.provider.FolderTreeProvider;
import net.datenwerke.rs.transport.client.transport.provider.FullTreeProvider;
import net.datenwerke.rs.transport.client.transport.provider.annotations.TransportManagerAdminViewTree;
import net.datenwerke.rs.transport.client.transport.provider.annotations.TransportTreeBasic;
import net.datenwerke.rs.transport.client.transport.provider.annotations.TransportTreeFolders;

/**
 * 
 *
 */
public class TransportUIModule extends AbstractGinModule {

   public static final String TRANSPORT_FAV_HISTORY_TOKEN = "transportmgr";
   public static final String ADMIN_TREE_MENU_NAME = "transport:admin:tree:menu";

   @Override
   protected void configure() {
      install(new TransportManagerExportUIModule());
      
      /* bind trees */
      bind(UITree.class).annotatedWith(TransportTreeBasic.class).toProvider(BasicTreeProvider.class);
      bind(UITree.class).annotatedWith(TransportManagerAdminViewTree.class).toProvider(FullTreeProvider.class)
            .in(Singleton.class);
      bind(UITree.class).annotatedWith(TransportTreeFolders.class).toProvider(FolderTreeProvider.class);

      /* bind startup */
      bind(TransportUIStartup.class).asEagerSingleton();
   }
}
