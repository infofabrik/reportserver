package net.datenwerke.eximport;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Optional;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * A service that provides access to exporting capabilities.
 * 
 * @see ImportService
 *
 */
public interface ExportService {

   /**
    * Exports the given {@link ExportConfig} as XML and returns its content as a
    * {@link String}
    * 
    * @param config The {@link ExportConfig}
    * @return A {@link String} containing the XML data of the exported
    *         {@link ExportConfig}
    */
   public String export(ExportConfig config);

   /**
    * Exports the given {@link ExportConfig} as XML and writes the result to the
    * supplied output stream.
    * 
    * @param config The configuration
    * @param os     The output stream
    * 
    * @throws XMLStreamException
    */
   void exportAsStream(ExportConfig config, OutputStream os) throws XMLStreamException;

   /**
    * Returns a {@link Collection} of {@link String}s holding the IDs of the given
    * exporters
    * 
    * @param exporters A {@link Collection} of {@link Class}es holding the
    *                  exporters
    * @return A {@link Collection} containing the exporters IDs
    */
   public Collection<String> getExporterIds(Collection<Class<? extends Exporter>> exporters);

   /**
    * Returns the {@link Exporter} for the given exporter type
    * 
    * @param exporterType The {@link Class} of the exporter type
    * @return The {@link Exporter} for the given type
    */
   public Exporter getExporterFor(Class<? extends Exporter> exporterType);

   /**
    * Exports the given {@link ExportConfig} as XML and returns its content as a
    * {@link String} properly indendent.
    * 
    * @see OutputKeys#INDENT
    * 
    * @param config The {@link ExportConfig}
    * @return A {@link String} containing the XML data of the exported
    *         {@link ExportConfig}
    */
   String exportIndent(ExportConfig config);

   /**
    * Returns the correct exporter for the given object or null if none was found.
    * 
    * @param object The object to be exported
    * @return The exporter
    */
   Exporter getExporterFor(Object object);

   /**
    * Gets the {@link ExportConfig} for the given node. If the given node is a
    * report, the includeVariants parameter is taken into account. If not, it is
    * ignored.
    * 
    * @param node            the node to export
    * @param includeVariants if the node to export is a report and includeVariants
    *                        is true, the report variants are exported as well.
    * @param flatten         if the tree should be flattened, i.e. all folders
    *                        should be stripped away.
    * @return the export configuration
    */
   Optional<ExportConfig> configureExport(AbstractNode<?> node, boolean includeVariants, boolean flatten);

}
