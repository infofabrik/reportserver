package net.datenwerke.rs.tsreportarea.service.tsreportarea.hookers;

import com.google.inject.Inject;

import net.datenwerke.rs.search.service.search.hooks.SearchResultAllowerHook;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TeamSpaceNodeSearchResultCheckHooker implements SearchResultAllowerHook {

   private final TsDiskService tsDiskService;
   private final TeamSpaceService teamSpaceService;
   
   @Inject
   public TeamSpaceNodeSearchResultCheckHooker(
         TeamSpaceService teamSpaceService,
         TsDiskService tsDiskService
         ) {
      this.teamSpaceService = teamSpaceService;
      this.tsDiskService = tsDiskService;
   }
   @Override
   public boolean allow(AbstractNode<? extends AbstractNode<?>> node) {
      if (node instanceof AbstractTsDiskNode) {
         TeamSpace teamSpace = tsDiskService.getTeamSpaceFor((AbstractTsDiskNode) node);
         return teamSpaceService.mayAccess(teamSpace);
      }
      
      return true;
   }

}
