package net.datenwerke.rs.core.service.reportmanager.metadata;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Provider;

/**
 * 
 *
 */
abstract public class AbstractReportMetadataExporterManager<E extends ReportMetadataExporter> {

	protected final Provider<Set<E>> exporters;
	
	public AbstractReportMetadataExporterManager(
		Provider<Set<E>> exporters	
		){
		this.exporters = exporters;
	}
	
	/**
	 * Gets a specific output generator that generates the specified format.
	 * 
	 * @return The corresponding output generator
	 */
	public E getMetadataExporter(String format){
		if(null == format)
			throw new IllegalArgumentException("No format specified"); //$NON-NLS-1$
		
		for(E g : getRegisteredMetadataExporters())
			for(String f: g.getFormats())
				if(format.equals(f))
					return g;
		
		return null;
	}
	
	/**
	 * Returns all registered generators
	 */
	public Set<E> getRegisteredMetadataExporters(){
		return exporters.get();
	}
	
	/**
	 * Returns an array with all registered output formats
	 * 
	 */
	public String[] getRegisteredExporterFormats(){
		Set<String> formats = new HashSet<String>();

		for(E g : getRegisteredMetadataExporters())
			for(String format : g.getFormats())
				formats.add(format);
		
		return formats.toArray(new String[]{});
	}
}
