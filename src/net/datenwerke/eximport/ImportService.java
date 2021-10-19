package net.datenwerke.eximport;

import java.util.List;

import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;

/**
 * A service that allows to import previously exported objects.
 * 
 * @see ExportService
 *
 */
public interface ImportService {

   /**
    * Returns a {@link List} of all {@link ExportedItem}s exported by the
    * {@link ImportConfig} which needs to be configured.
    * 
    * @param config The {@link ImportConfig}
    * @return A {@link List} of {@link ExportedItem}s
    */
   public List<ExportedItem> getToBeConfigured(ImportConfig config);

   /**
    * Imports the data from the given {@link ImportConfig}
    * 
    * @param config The {@link ImportConfig}
    * @return The {@link ImportResult}
    */
   public ImportResult importData(ImportConfig config);

   /**
    * Returns the exported item identified by ID
    * 
    * @param id The ID identifying the desired item
    * @return The {@link ExportedItem}
    */
   public ExportedItem getExportedItemById(ExportDataProvider dataProvider, String id);

   /**
    * Returns the {@link EnclosedItemProperty} for the element identified by the
    * given ID
    * 
    * @param id The ID of the element
    * @return The {@link EnclosedItemProperty}
    */
   public EnclosedItemProperty getEnclosedItemPropertyForId(ExportDataProvider dataProvider, String id);

}
