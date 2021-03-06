package net.datenwerke.rs.core.client.datasinkmanager.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;

/**
 * The actual implementation of the user managers main component.
 * 
 *
 */
@Singleton
public class DatasinkManagerMainPanel extends AbstractTreeMainPanel {

   @Inject
   public DatasinkManagerMainPanel(DatasinkTreeManagerDao datasourceTreeManager) {

      super(datasourceTreeManager);
   }

   @Override
   protected String getToolbarName() {
      return "datasink:admin:view:toolbar";
   }
}
