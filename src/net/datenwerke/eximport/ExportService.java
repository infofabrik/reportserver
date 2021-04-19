package net.datenwerke.eximport;

import java.io.OutputStream;
import java.util.Collection;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.eximport.ex.Exporter;

/**
 * A service that provides access to exporting capabilities. 
 * 
 * @see ImportService
 *
 */
public interface ExportService {

	/**
	 * Exports the given {@link ExportConfig} as XML and returns its content as a {@link String}
	 * @param config The {@link ExportConfig}
	 * @return A {@link String} containing the XML data of the exported {@link ExportConfig}
	 */
	public String export(ExportConfig config);

	/**
	 * Exports the given {@link ExportConfig} as XML and writes the result to the supplied output stream.
	 * 
	 * @param config The configuration
	 * @param os The output stream
	 * 
	 * @throws XMLStreamException
	 */
	void exportAsStream(ExportConfig config, OutputStream os)
			throws XMLStreamException;
	
	/**
	 * Returns a {@link Collection} of {@link String}s holding the IDs of the given exporters
	 * 
	 * @param exporters A {@link Collection} of {@link Class}es holding the exporters
	 * @return A {@link Collection} containing the exporters IDs
	 */
	public Collection<String> getExporterIds(Collection<Class<?>> exporters);
	
	/**
	 * Returns the {@link Exporter} for the given exporter type
	 * 
	 * @param exporterType The {@link Class} of the exporter type
	 * @return The {@link Exporter} for the given type
	 */
	public Exporter getExporterFor(Class<?> exporterType);

	/**
	 * Exports the given {@link ExportConfig} as XML and returns its content as a {@link String}
	 * properly indendent.
	 * 
	 * @see OutputKeys#INDENT
	 * 
	 * @param config The {@link ExportConfig}
	 * @return A {@link String} containing the XML data of the exported {@link ExportConfig}
	 */
	String exportIndent(ExportConfig config);

	/**
	 * Returns the correct exporter for the given object or null if none was found.
	 * 
	 * @param object The object to be exported
	 * @return The exporter 
	 */
	Exporter getExporterFor(Object object);

	
}
