package net.datenwerke.rs.remoteserver.client.remoteservermanager;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.ui.RemoteServerManagerPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class RemoteServerAdminModule implements AdminModule {

   final private Provider<RemoteServerManagerPanel> remoteserverManagerPanelProvider;

   @Inject
   public RemoteServerAdminModule(Provider<RemoteServerManagerPanel> remoteserverManagerPanel) {

      /* store objects */
      this.remoteserverManagerPanelProvider = remoteserverManagerPanel;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.ALIGN_CENTER.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return RemoteServerMessages.INSTANCE.remoteServers();
   }

   @Override
   public Widget getMainWidget() {
      return remoteserverManagerPanelProvider.get();
   }

   @Override
   public void notifyOfSelection() {
      // ignore
   }
}
