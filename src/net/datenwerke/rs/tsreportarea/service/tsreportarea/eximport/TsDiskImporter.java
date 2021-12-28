package net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport;

import com.google.inject.Inject;

import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 */
public class TsDiskImporter extends TreeNodeImporter {

   public static final String IMPORTER_ID = "TsDiskImporter";

   private final TsDiskService diskService;

   @Inject
   public TsDiskImporter(TsDiskService diskService) {

      /* store objects */
      this.diskService = diskService;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { TsDiskExporter.class };
   }

   @Override
   protected TreeDBManager getTreeDBManager() {
      return diskService;
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

}
