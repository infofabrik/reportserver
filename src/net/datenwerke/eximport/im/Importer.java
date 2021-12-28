package net.datenwerke.eximport.im;

import java.util.Collection;
import java.util.Queue;

import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;

/**
 * 
 *
 */
public interface Importer {

   public String getId();

   public Class<?>[] getRecognizedExporters();

   public boolean consumes(ExportedItem exportedItem, ImportItemConfig itemConfig);

   public void configure(ImportSupervisor importSupervisor, Collection<ImporterSpecificConfig> specificConfigs,
         Queue<ImportItemConfig> collection);

   public void importData();

   public void importReference(ImportItemConfig itemConfig);

   public Object importEnclosedObject(EnclosedItemProperty property, boolean registerImportedObject);

   public boolean postProcess(String id, Object object, boolean enclosed);

}
