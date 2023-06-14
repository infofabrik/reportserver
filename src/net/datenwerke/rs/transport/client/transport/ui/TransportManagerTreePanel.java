package net.datenwerke.rs.transport.client.transport.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.transport.client.transport.provider.annotations.TransportManagerAdminViewTree;

/**
 * 
 *
 */
public class TransportManagerTreePanel extends AbstractTreeNavigationPanel {

   @Inject
   public TransportManagerTreePanel(HookHandlerService hookHandler, @TransportManagerAdminViewTree UITree tree) {
      super(hookHandler, tree);
   }

}
