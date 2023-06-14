package net.datenwerke.rs.transport.client.transport.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;

@Singleton
public class TransportManagerPanel extends AbstractTreeManagerPanel {

   @Inject
   public TransportManagerPanel(TransportManagerMainPanel mainPanel, TransportManagerTreePanel treePanel) {

      super(mainPanel, treePanel);
   }

   @Override
   protected String getHeadingText() {
      return TransportMessages.INSTANCE.transports();
   }

}
