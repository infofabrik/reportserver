package net.datenwerke.rs.transport.client.transport;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.rs.transport.client.transport.ui.TransportManagerPanel;

/**
 * 
 *
 */
public class TransportAdminModule implements AdminModule {

   final private Provider<TransportManagerPanel> transportManagerPanelProvider;

   @Inject
   public TransportAdminModule(
         Provider<TransportManagerPanel> transportManagerPanelProvider
         ) {
      this.transportManagerPanelProvider = transportManagerPanelProvider;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.ARCHIVE.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return TransportMessages.INSTANCE.transports();
   }

   @Override
   public Widget getMainWidget() {
      return transportManagerPanelProvider.get();
   }

   @Override
   public void notifyOfSelection() {
      // ignore
   }
}
