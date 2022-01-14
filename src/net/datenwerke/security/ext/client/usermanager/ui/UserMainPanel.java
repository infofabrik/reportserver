package net.datenwerke.security.ext.client.usermanager.ui;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeManagerDao;

/**
 * The actual implementation of the user managers main component.
 * 
 *
 */
@Singleton
public class UserMainPanel extends AbstractTreeMainPanel {

   @Inject
   public UserMainPanel(UserManagerTreeManagerDao userManagerTreeHandler) {

      super(userManagerTreeHandler);
   }

   @Override
   protected String getToolbarName() {
      return "usermanager:admin:view:toolbar";
   }
}
