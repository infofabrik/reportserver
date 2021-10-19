package net.datenwerke.eximport.ex;

import java.util.Collection;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.obj.ExportedItem;

/**
 * 
 *
 */
public interface Exporter {

   public String getExporterId();

   /**
    * Tells the exportservice whether this exporter can handle the given config
    * item.
    * 
    * @param config
    */
   public boolean consumes(ExportItemConfig<?> config);

   /**
    * Tells the exportservice if this exporter can export the given object.
    * 
    * <p>
    * This case occurs if an enclosed entity is encountered and the exporter asks
    * the supervisor for help
    * </p>
    */
   public boolean consumesEnclosedObject(EnclosedObjectConfig config);

   /**
    * Starts the export of the given configuration.
    * 
    * @param exportSupervisor
    * @throws XMLStreamException
    */
   public void export(ExportSupervisor exportSupervisor) throws XMLStreamException;

   /**
    * Exports an enclosed entity.
    */
   public void exportEnclosed(ExportSupervisor exportSupervisor, EnclosedObjectConfig config) throws XMLStreamException;

   /**
    * Configures the exporter and tells it what items it should export.
    * 
    * <p>
    * Exporter should throw Exception in case the configuration is invalid.
    * </p>
    */
   public void configure(Collection<ExporterSpecificExportConfig> specificConfigs,
         Collection<ExportItemConfig<?>> configItems);

   public Collection<ExportItemConfig<?>> addReferences(ExportSupervisor exportSupervisor,
         Collection<ExportItemConfig<?>> configItems);

   public Collection<EnclosedObjectConfig> addEnclosed(ExportSupervisor exportSupervisor,
         Collection<ExportItemConfig<?>> configItems);

   /**
    * Asks the exporter to generate an export config for the given object.
    * 
    * <p>
    * If the exporter does not know the item, it should return null
    * </p>
    * 
    * @param object
    */
   public ExportItemConfig<?> generateExportConfig(Object object);

   public boolean hasConfigFor(Object value);

   public ExportItemConfig<?> getConfigFor(Object value);

   public Collection<ExportItemConfig<?>> addReferences(ExportSupervisor exportSupervisor,
         EnclosedObjectConfig enclosedCon);

   public Collection<EnclosedObjectConfig> addEnclosed(ExportSupervisor exportSupervisor,
         EnclosedObjectConfig enclosedCon);

   /**
    * Generates a name for the ExportedItem to display to the user
    * 
    * @param exportedItem
    */
   public String getDisplayNameFor(ExportedItem exportedItem);

   public boolean consumes(Object object);

}
