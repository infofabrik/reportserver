package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers;

import com.google.inject.Inject;

import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.DatasinkManagerExporter;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.ImportAllNodesHooker;

public class ImportAllDatasinksHooker extends ImportAllNodesHooker<AbstractDatasinkManagerNode>{

   @Inject
   public ImportAllDatasinksHooker(
      DatasinkTreeService treeDbManager
      ) {
      super(treeDbManager, DatasinkManagerExporter.class);
   }
}