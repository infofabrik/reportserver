package net.datenwerke.eximport.im;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.ImportService;
import net.datenwerke.eximport.exceptions.ImportException;
import net.datenwerke.eximport.obj.EnclosedItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;

/**
 * 
 *
 */
public class ImportSupervisor {

	private final ExportDataAnalyzerService analizerService;
	private final ImportService importService;
	
	private final ImportConfig config;
	private final List<Importer> importers;
	
	private Map<String, Object> externalReferences = new HashMap<String, Object>();
	private Map<String, Object> importedObjects = new HashMap<String, Object>();
	private Map<Object, String> importedObjectsImportIdLookup = new HashMap<Object, String>();
	private Set<String> enclosedObjects = new LinkedHashSet<String>();
	
	private Set<String> successfullyImported = new LinkedHashSet<String>();
	
	private Set<String> ignoredReferencesWithNoConfig = new HashSet<String>();
	
	@Inject
	public ImportSupervisor(
		ExportDataAnalyzerService analizerService,
		ImportService importService,
		@Assisted ImportConfig config,
		@Assisted List<Importer> importers
		){
		
		/* store objects */
		this.analizerService = analizerService;
		this.importService = importService;
		
		this.config = config;
		this.importers = importers;
	}
	
	public ImportResult importData() throws ClassNotFoundException{
		/* configure importers */
		configureImporters();

		/* gather enclosed objects */
		for(ImportItemConfig itemConfig : config.getItemConfigs()){
			ExportedItem exportedItem = getExportedItemFor(itemConfig);
			enclosedObjects.addAll(exportedItem.getEnclosedObjectIds());
		}
		
		for(Importer importer : importers)
			importer.importData();
		
		/* post process */
		Set<String> processedItems = new HashSet<String>();
		
		boolean addedToProcessing = true;
		while(addedToProcessing){
			addedToProcessing = false;
			
			for(String id : successfullyImported ){
				if(processedItems.contains(id))
					continue;
				
				boolean success = false;
				if(enclosedObjects.contains(id)){
					Class<?> exporterType = getExporterForEnclosed(id);
					Importer importer = getResponsibleImporterFor(exporterType);
					success = importer.postProcess(id, importedObjects.get(id), true);
				} else {
					Importer importer = getResponsibleImporterFor(getItemConfigFor(id));
					success = importer.postProcess(id, importedObjects.get(id), false);
				}
				
				if(success){
					addedToProcessing = true;
					processedItems.add(id);
				}
			}
		}
		
		if(processedItems.size() != successfullyImported.size())
			throw new ImportException("Could not properly post process all items");
		
		return constructResult();
	}

	public Class<?> getExporterForEnclosed(String id) throws ClassNotFoundException {
		return analizerService.getExporterForEnclosed(config.getExportDataProvider(), id);
	}

	private ImportResult constructResult() {
		String name = analizerService.getExportName(config.getExportDataProvider());
		String description = analizerService.getExportDescription(config.getExportDataProvider());
		Date date = analizerService.getExportDate(config.getExportDataProvider());
		
		Map<String, Object> objects = new HashMap<String, Object>();
		for(Entry<String, Object> entry : importedObjects.entrySet())
			if(! enclosedObjects.contains(entry.getKey()))
				objects.put(entry.getKey(), entry.getValue());
		
		return new ImportResult(name, description, date, objects, ignoredReferencesWithNoConfig);
	}

	private void configureImporters() {
		Map<Importer, Queue<ImportItemConfig>> configMap = createConfigMap();
		
		for(Importer importer : importers)
			importer.configure(this, config.getSpecificImporterConfigs(), configMap.get(importer));
	}
	
	private Map<Importer, Queue<ImportItemConfig>> createConfigMap() {
		Map<Importer, Queue<ImportItemConfig>> configMap = new HashMap<Importer, Queue<ImportItemConfig>>();
		for(Importer importer : importers)
			configMap.put(importer, new LinkedList<ImportItemConfig>());
		
		for(ImportItemConfig itemConfig : config.getItemConfigs()){
			Importer importer = getResponsibleImporterFor(itemConfig);
			configMap.get(importer).add(itemConfig);
		}
		
		return configMap;
	}
	
	public void registerImportedObject(String id, Object object){
		if(null == id || "".equals(id))
			throw new IllegalStateException("ID may not be null.");
		if(importedObjects.containsKey(id))
			throw new IllegalStateException("An object " + object.getClass() + " with id " + id + " was registered a second time.");
		importedObjects.put(id, object);
		importedObjectsImportIdLookup.put(object, id);
	}
	
	public void notifyImportDone(String id, Object obj) {
		if(successfullyImported.contains(id))
			throw new IllegalStateException(id + " was already imported.");
		successfullyImported.add(id);
	}
	
	public String getImportIdForImportedObject(Object object){
		return importedObjectsImportIdLookup.get(object);
	}
	
	public Object getReferencedObject(String id, boolean registerImportedObject) {
		if(isExternalReference(id))
			return getExternalReference(id);
		if(importedObjects.containsKey(id))
			return importedObjects.get(id);
		
		ImportItemConfig itemConfig = getItemConfigFor(id);
		if(null == itemConfig){
			if(enclosedObjects.contains(id)){
				try {
					EnclosedItemProperty enclosedProperty = analizerService.getEnclosedPropertyFor(config.getExportDataProvider(), id);
					Object enclosed = getEnclosedObject(enclosedProperty, registerImportedObject);
					if(! registerImportedObject)
						return enclosed;
					
					if(importedObjects.containsKey(id))
						return importedObjects.get(id);
					
					throw new ImportException("Could not find reference and seems not to be null ... " + id);
				} catch (ClassNotFoundException e) {
					throw new ImportException("Could not find exportedItem with enclosed... " + id);
				}
			} else { 
				/* no config exists .. ignore -> */
				ignoredReferencesWithNoConfig.add(id);
				return null;
			}
		} else {
			Importer importer = getResponsibleImporterFor(itemConfig);
			importer.importReference(itemConfig);
			
			if(importedObjects.containsKey(id))
				return importedObjects.get(id);
			if(isExternalReference(id))
				return getExternalReference(id);

			throw new ImportException("Could not find reference and seems not to be null ... " + id);
		}
	}



	public Importer getResponsibleImporterFor(ImportItemConfig itemConfig) {
		ExportedItem exportedItem = getExportedItemFor(itemConfig);
		for(Importer importer : importers)
			if(importer.consumes(exportedItem, itemConfig))
				return importer;
		throw new ImportException("Could not find importer for item config " + itemConfig.getId());
	}

	public ImportItemConfig getItemConfigFor(String id) {
		return config.getItemConfigFor(id);
	}
	
	public ExportedItem getExportedItemFor(String id){
		return importService.getExportedItemById(config.getExportDataProvider(), id);
	}
	
	public EnclosedItemProperty getEnclosedItemPropertyForId(String id){
		return importService.getEnclosedItemPropertyForId(config.getExportDataProvider(), id);
	}
	
	public ExportedItem getExportedItemFor(ImportItemConfig config){
		return getExportedItemFor(config.getId());
	}

	public Object getEnclosedObject(EnclosedItemProperty property, boolean registerImportedObject) {
		if(importedObjects.containsKey(property.getId()))
			return importedObjects.get(property.getId());
		
		Class<?> exporterType = property.getExporterType();
		Importer importer = getResponsibleImporterFor(exporterType);
		Object value = importer.importEnclosedObject(property, registerImportedObject);
		return value;
	}
	
	public Importer getResponsibleImporterFor(Class<?> type){
		for(Importer importer : importers)
			for(Class<?> recognizedExporter : importer.getRecognizedExporters())
				if(recognizedExporter.isAssignableFrom(type))
					return importer;
		throw new IllegalStateException("Could not find importer for enclosed item: " + type);
	}

	public void registerExternalReference(String id, Object referenceObject) {
		externalReferences.put(id, referenceObject);
	}
	
	public boolean isExternalReference(String id) {
		return externalReferences.containsKey(id);
	}

	public Object getExternalReference(String id) {
		return externalReferences.get(id);
	}
}
