package net.datenwerke.rs.core.client.datasourcemanager;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.core.client.datasourcemanager.ui.DatasourceManagerPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DatasourceAdminModule implements AdminModule {

   final private Provider<DatasourceManagerPanel> datasourceManagerPanelProvider;

   @Inject
   public DatasourceAdminModule(Provider<DatasourceManagerPanel> datasourceManagerPanel) {

      /* store objects */
      this.datasourceManagerPanelProvider = datasourceManagerPanel;
   }

   @Override
   public ImageResource getNavigationIcon() {
      return BaseIcon.DATABASE.toImageResource();
   }

   @Override
   public String getNavigationText() {
      return DatasourcesMessages.INSTANCE.adminModuleName();
   }

   @Override
   public Widget getMainWidget() {
      return datasourceManagerPanelProvider.get();
   }

   @Override
   public void notifyOfSelection() {
      // ignore
   }
}
