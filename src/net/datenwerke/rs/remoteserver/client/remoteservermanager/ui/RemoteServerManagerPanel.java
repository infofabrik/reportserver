package net.datenwerke.rs.remoteserver.client.remoteservermanager.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerManagerMessages;

@Singleton
public class RemoteServerManagerPanel extends AbstractTreeManagerPanel {

   @Inject
   public RemoteServerManagerPanel(RemoteServerManagerMainPanel mainPanel, RemoteServerManagerTreePanel treePanel) {

      super(mainPanel, treePanel);
   }

   @Override
   protected String getHeadingText() {
      return RemoteServerManagerMessages.INSTANCE.remoteServers();
   }

}
