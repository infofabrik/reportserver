package net.datenwerke.rs.dashboard.client.dashboard;

import net.datenwerke.gf.client.homepage.modules.ClientMainModuleImpl;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardMainComponent;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * 
 *
 */
@Singleton
public class DashboardClientMainModule extends ClientMainModuleImpl {

   private final Provider<DashboardMainComponent> mainComponentProvider;
   private DashboardMainComponent mainComponent;

   @Inject
   public DashboardClientMainModule(Provider<DashboardMainComponent> mainComponentProvider) {

      /* store objects */
      this.mainComponentProvider = mainComponentProvider;

   }

   @Override
   public String getModuleName() {
      return DashboardMessages.INSTANCE.clientModuleName();
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.DASHBOARD.toImageResource();
   }

   @Override
   public Widget getMainWidget() {
      if (null == mainComponent)
         mainComponent = mainComponentProvider.get();
      return mainComponent;
   }

   @Override
   public void selected() {
      if (null != mainComponent)
         mainComponent.selected();
   }

   @Override
   public boolean isRecyclable() {
      return true;
   }

}
