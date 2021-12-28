package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.core.client.datasinkmanager.ui.DatasinkManagerPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DatasinkAdminModule implements AdminModule {

   final private Provider<DatasinkManagerPanel> datasinkManagerPanelProvider;

   @Inject
   public DatasinkAdminModule(Provider<DatasinkManagerPanel> datasinkManagerPanel) {

      /* store objects */
      this.datasinkManagerPanelProvider = datasinkManagerPanel;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.SERVER.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return DatasinksMessages.INSTANCE.datasinks();
   }

   @Override
   public Widget getMainWidget() {
      return datasinkManagerPanelProvider.get();
   }

   @Override
   public void notifyOfSelection() {
      // ignore
   }
}
