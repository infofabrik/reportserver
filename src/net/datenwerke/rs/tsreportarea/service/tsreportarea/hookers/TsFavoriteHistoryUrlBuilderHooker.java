package net.datenwerke.rs.tsreportarea.service.tsreportarea.hookers;

import com.google.inject.Inject;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIModule;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.locale.TsDiskMessages;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

public class TsFavoriteHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

   private final TsDiskMessages messages = LocalizationServiceImpl.getMessages(TsDiskMessages.class);

   private final static String HISTORY_BUILDER_NAME = "TsFavoritesUrlBuilder";

   private final TsDiskService favoriteService;
   private final TeamSpaceService teamSpaceService;

   @Inject
   public TsFavoriteHistoryUrlBuilderHooker(TsDiskService favoriteService, TeamSpaceService teamSpaceService) {

      /* store object */
      this.favoriteService = favoriteService;
      this.teamSpaceService = teamSpaceService;
   }

   @Override
   public boolean consumes(Object o) {
      if (!(o instanceof AbstractTsDiskNode))
         return false;

      AbstractTsDiskNode node = (AbstractTsDiskNode) o;
      TeamSpace teamSpace = favoriteService.getTeamSpaceFor(node);

      return teamSpaceService.mayAccess(teamSpace);
   }

   @Override
   protected void adjustLocation(Object o, HistoryLocation location) {
      AbstractTsDiskNode node = (AbstractTsDiskNode) o;

      TeamSpace teamSpace = favoriteService.getTeamSpaceFor(node);
      location.addParameter("ts", teamSpace.getId().toString());

      if (!(node instanceof TsDiskRoot)) {
         location.addParameter("sel", node.getId().toString());
         location.addParameter("id", node.getParent().getId().toString());
      }
   }

   @Override
   protected String getTokenName() {
      return TsDiskUIModule.TEAMSPACE_SELECT_ITEM_HISTORY_TOKEN;
   }

   @Override
   protected String getBuilderId() {
      return HISTORY_BUILDER_NAME;
   }

   @Override
   protected String getNameFor(Object o) {
      AbstractTsDiskNode node = (AbstractTsDiskNode) o;
      TeamSpace teamSpace = favoriteService.getTeamSpaceFor(node);

      return messages.historyUrlBuilderName(teamSpace.getName());
   }

   @Override
   protected String getIconFor(Object o) {
      return "file";
   }

}
