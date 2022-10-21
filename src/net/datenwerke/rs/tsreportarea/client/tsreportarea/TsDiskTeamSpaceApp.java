package net.datenwerke.rs.tsreportarea.client.tsreportarea;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceApp;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

/**
 * 
 *
 */
public class TsDiskTeamSpaceApp implements TeamSpaceApp {

   public static final String APP_ID = "tsApp-favoriteReports";

   private final Provider<TsDiskMainComponent> mainComponentProvider;

   private TsDiskMainComponent appComponent;

   @Inject
   public TsDiskTeamSpaceApp(Provider<TsDiskMainComponent> mainComponentProvider) {

      /* store objects */
      this.mainComponentProvider = mainComponentProvider;
   }

   @Override
   public String getAppId() {
      return APP_ID;
   }

   @Override
   public String getName() {
      return TsFavoriteMessages.INSTANCE.appName();
   }

   @Override
   public String getDescription() {
      return TsFavoriteMessages.INSTANCE.appDescription();
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.NEWSPAPER_O.toImageResource();
   }

   @Override
   public Component getAppComponent() {
      if (null == appComponent)
         appComponent = mainComponentProvider.get();
      return appComponent;
   }

   @Override
   public void displaySpace(TeamSpaceDto currentSpace) {
      appComponent.setCurrentSpace(currentSpace);
   }

}
