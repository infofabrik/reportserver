package net.datenwerke.rs.core.client.reportmanager.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.provider.annotations.ReportManagerAdminViewTree;

/**
 * 
 *
 */
public class ReportManagerTreePanel extends AbstractTreeNavigationPanel {

   @Inject
   public ReportManagerTreePanel(HookHandlerService hookHandler, @ReportManagerAdminViewTree UITree tree) {

      super(hookHandler, tree);
   }

}
