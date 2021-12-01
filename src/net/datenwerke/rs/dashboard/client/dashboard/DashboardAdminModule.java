package net.datenwerke.rs.dashboard.client.dashboard;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.admin.DashboardManagerPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DashboardAdminModule implements AdminModule {

   final private Provider<DashboardManagerPanel> managerPanelanelProvider;

   @Inject
   public DashboardAdminModule(Provider<DashboardManagerPanel> managerPanelanelProvider) {

      /* store objects */
      this.managerPanelanelProvider = managerPanelanelProvider;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.TACHOMETER.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return DashboardMessages.INSTANCE.adminLabel();
   }

   @Override
   public Widget getMainWidget() {
      return managerPanelanelProvider.get();
   }

   @Override
   public void notifyOfSelection() {

   }
}
