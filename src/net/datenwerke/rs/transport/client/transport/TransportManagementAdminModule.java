package net.datenwerke.rs.transport.client.transport;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.rs.transport.client.transport.ui.TransportManagerAdminPanel;

/**
 * 
 *
 */
public class TransportManagementAdminModule implements AdminModule {

   private final Provider<TransportManagerAdminPanel> mainWidgetProvider;

   @Inject
   public TransportManagementAdminModule(Provider<TransportManagerAdminPanel> mainWidgetProvider) {
      this.mainWidgetProvider = mainWidgetProvider;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.ARCHIVE.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return TransportMessages.INSTANCE.viewNavigationTitle();
   }

   @Override
   public void notifyOfSelection() {
      mainWidgetProvider.get().notifyOfSelection();
   }

   @Override
   public Widget getMainWidget() {
      return mainWidgetProvider.get();
   }
}
