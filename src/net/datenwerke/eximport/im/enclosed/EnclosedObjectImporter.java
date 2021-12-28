package net.datenwerke.eximport.im.enclosed;

import java.util.Collection;
import java.util.Queue;

import com.google.inject.Inject;

import net.datenwerke.eximport.ex.enclosed.EnclosedObjectExporter;
import net.datenwerke.eximport.im.ImportItemConfig;
import net.datenwerke.eximport.im.ImportSupervisor;
import net.datenwerke.eximport.im.Importer;
import net.datenwerke.eximport.im.ImporterSpecificConfig;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporter;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporterFactory;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;

/**
 * 
 *
 */
public class EnclosedObjectImporter implements Importer {

   public static final String IMPORTER_ID = "EnclosedObjectImporter";

   protected BasicObjectImporterFactory objectImporterFactory;
   protected ImportSupervisor importSupervisor;

   @Inject
   public void setObjectImporterFactory(BasicObjectImporterFactory objectImporterFactory) {
      this.objectImporterFactory = objectImporterFactory;
   }

   @Override
   public void configure(ImportSupervisor importSupervisor, Collection<ImporterSpecificConfig> specificConfigs,
         Queue<ImportItemConfig> collection) {
      this.importSupervisor = importSupervisor;
   }

   @Override
   public boolean consumes(ExportedItem exportedItem, ImportItemConfig itemConfig) {
      return false;
   }

   @Override
   public Class<?>[] getRecognizedExporters() {
      return new Class<?>[] { EnclosedObjectExporter.class };
   }

   @Override
   public void importData() {
   }

   @Override
   public Object importEnclosedObject(EnclosedItemProperty property, boolean registerImportedObject) {
      BasicObjectImporter importer = objectImporterFactory.create(importSupervisor, property);
      Object imported = importer.importObject();
      return imported;
   }

   @Override
   public void importReference(ImportItemConfig itemConfig) {
   }

   @Override
   public boolean postProcess(String id, Object object, boolean enclosed) {
      return true;
   }

   @Override
   public String getId() {
      return IMPORTER_ID;
   }

}
