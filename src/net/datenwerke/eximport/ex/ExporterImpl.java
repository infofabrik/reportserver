package net.datenwerke.eximport.ex;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.obj.ComplexItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.rs.utils.reflection.ReflectionService;

import com.google.inject.Inject;

/**
 * Provides a base implementation for simple exporters.
 * 
 *
 * @param <C>
 */
public abstract class ExporterImpl<C extends ExportItemConfig<?>> implements Exporter {

	protected Collection<ExportItemConfig<?>> configItems;
	protected Collection<ExporterSpecificExportConfig> specificConfigs;
	
	protected ReflectionService reflectionServices;
	
	@Inject
	public void setReflectionServices(ReflectionService reflectionServices){
		this.reflectionServices = reflectionServices;
	}
	
	@Override
	public boolean consumes(ExportItemConfig<?> config) {
		if(null == config) 
			return false;
		
		return reflectionServices.getGenericType(getClass()).isAssignableFrom(config.getClass());
	}
	
	@Override
	public final void configure(Collection<ExporterSpecificExportConfig> specificConfigs, Collection<ExportItemConfig<?>> configItems){
		this.specificConfigs = specificConfigs;
		this.configItems = configItems;
	}
	
	protected <T extends ExporterSpecificExportConfig> T getSpecificConfig(Class<T> type){
		for(ExporterSpecificExportConfig config : specificConfigs)
			if(type.isAssignableFrom(config.getClass()))
				return (T)config;
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final void export(ExportSupervisor exportSupervisor) throws XMLStreamException {
		Iterator<ExportItemConfig<?>> iterator = configItems.iterator();
		if(! iterator.hasNext())
			return;

		exportSupervisor.beginExporterElement(this);
		while(iterator.hasNext()){
			C configItem = (C) iterator.next();
			doExport(exportSupervisor, configItem);
		}
		exportSupervisor.endElement();
	}

	@Override
	public boolean consumesEnclosedObject(EnclosedObjectConfig config){
		return false;
	}
	
	@Override
	public void exportEnclosed(ExportSupervisor supervisor, EnclosedObjectConfig config){
		throw new IllegalStateException("Should not be called.");
	}
	
	protected abstract void doExport(ExportSupervisor exportSupervisor, C item) throws XMLStreamException;

	public final Collection<ExportItemConfig<?>> addReferences(
			ExportSupervisor exportSupervisor, Collection<ExportItemConfig<?>> configItems){
		Collection<ExportItemConfig<?>> references = new HashSet<ExportItemConfig<?>>();
			
		for(ExportItemConfig<?> configItem : configItems){
			doAddReferences(exportSupervisor, (C) configItem, references);
		}
			
		return references;
	}

	protected void doAddReferences(ExportSupervisor exportSupervisor,
			C configItem, Collection<ExportItemConfig<?>> references) {
	}
	
	public final Collection<EnclosedObjectConfig> addEnclosed(
			ExportSupervisor exportSupervisor, Collection<ExportItemConfig<?>> configItems){
		Collection<EnclosedObjectConfig> enclosed = new HashSet<EnclosedObjectConfig>();
			
		for(ExportItemConfig<?> configItem : configItems){
			doAddEnclosed(exportSupervisor, (C) configItem, enclosed);
		}
			
		return enclosed;
	}
	
	protected void doAddEnclosed(ExportSupervisor exportSupervisor,
			C configItem, Collection<EnclosedObjectConfig> enclosed) {
	}
	
	protected Collection<C> getConfigItems(){
		return (Collection<C>) configItems;
	}
	
	public Collection<ExportItemConfig<?>> addReferences(
			ExportSupervisor exportSupervisor, EnclosedObjectConfig enclosedCon){
		return Collections.emptySet();
	}

	public Collection<EnclosedObjectConfig> addEnclosed(
			ExportSupervisor exportSupervisor, EnclosedObjectConfig enclosedCon){
		return Collections.emptySet();
	}
	
	@Override
	public boolean hasConfigFor(Object value){
		return null != getConfigFor(value);
	}
	
	@Override
	public ExportItemConfig<?> getConfigFor(Object value) {
		return null;
	}
	
	@Override
	public String getDisplayNameFor(ExportedItem exportedItem) {
		ComplexItemProperty nameProperty = (ComplexItemProperty) exportedItem.getPropertyByName("name");
		if(null != nameProperty)
			return nameProperty.getElement().getValue();
		
		else return "";
	}
}
