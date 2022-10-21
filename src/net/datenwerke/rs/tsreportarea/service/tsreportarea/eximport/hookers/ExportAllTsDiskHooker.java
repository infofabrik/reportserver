package net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.hookers;

import com.google.inject.Inject;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;

public class ExportAllTsDiskHooker implements ExportAllHook {

   private final TsDiskService diskService;
   private final TeamSpaceService tsService;

   @Inject
   public ExportAllTsDiskHooker(TsDiskService diskService, TeamSpaceService tsService) {

      this.diskService = diskService;
      this.tsService = tsService;
   }

   @Override
   public void configure(ExportConfig config) {
      if (!tsService.isGlobalTsAdmin())
         throw new ViolatedSecurityException();

      for (AbstractTsDiskNode node : diskService.getAllNodes())
         config.addItemConfig(new TreeNodeExportItemConfig(node));
   }

}
