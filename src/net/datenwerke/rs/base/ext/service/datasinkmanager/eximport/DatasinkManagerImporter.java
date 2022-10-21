package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public class DatasinkManagerImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "DatasinkManagerImporter";

   private final DatasinkTreeService datasinkService;

   @Inject
   public DatasinkManagerImporter(DatasinkTreeService datasinkService) {

      /* store objects */
      this.datasinkService = datasinkService;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { DatasinkManagerExporter.class };
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

   @Override
   protected TreeDBManager getTreeDBManager() {
      return datasinkService;
   }

}