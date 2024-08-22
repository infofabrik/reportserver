package net.datenwerke.rs.remoteserver.client.remoteservermanager.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.annotations.RemoteServerManagerAdminViewTree;

/**
 * 
 *
 */
public class RemoteServerManagerTreePanel extends AbstractTreeNavigationPanel {

   @Inject
   public RemoteServerManagerTreePanel(HookHandlerService hookHandler, @RemoteServerManagerAdminViewTree UITree tree) {
      super(hookHandler, tree);
   }

}
