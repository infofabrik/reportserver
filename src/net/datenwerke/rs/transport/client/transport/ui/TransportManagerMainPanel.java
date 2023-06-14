package net.datenwerke.rs.transport.client.transport.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.rs.transport.client.transport.TransportTreeManagerDao;

/**
 * The actual implementation of the transports main component.
 * 
 *
 */
@Singleton
public class TransportManagerMainPanel extends AbstractTreeMainPanel {

   @Inject
   public TransportManagerMainPanel(TransportTreeManagerDao treeManager) {

      super(treeManager);
   }

   @Override
   protected String getToolbarName() {
      return "transport:admin:view:toolbar";
   }
}
