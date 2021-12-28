package net.datenwerke.rs.core.client.reportmanager;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.core.client.reportmanager.ui.ReportManagerPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class ReportManagerAdminModule implements AdminModule {

   final private Provider<ReportManagerPanel> reportManagerPanelProvider;

   @Inject
   public ReportManagerAdminModule(Provider<ReportManagerPanel> reportManagerPanel) {

      /* store objects */
      this.reportManagerPanelProvider = reportManagerPanel;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.FILE.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return ReportmanagerMessages.INSTANCE.reportManagement();
   }

   @Override
   public Widget getMainWidget() {
      return reportManagerPanelProvider.get();
   }

   @Override
   public void notifyOfSelection() {

   }
}
