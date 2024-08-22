package net.datenwerke.rs.remoteserver.client.remoteservermanager.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerTreeManagerDao;

/**
 * The actual implementation of the user managers main component.
 * 
 *
 */
@Singleton
public class RemoteServerManagerMainPanel extends AbstractTreeMainPanel {

   @Inject
   public RemoteServerManagerMainPanel(RemoteServerTreeManagerDao treeManager) {

      super(treeManager);
   }

   @Override
   protected String getToolbarName() {
      return "remoteserver:admin:view:toolbar";
   }
}
