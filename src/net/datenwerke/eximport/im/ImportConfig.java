package net.datenwerke.eximport.im;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.datenwerke.eximport.ExportDataProvider;

/**
 * 
 *
 */
public class ImportConfig {
	
	private List<ImporterSpecificConfig> specificImporterConfigs = new ArrayList<ImporterSpecificConfig>();
	
	private final ExportDataProvider exportDataProvider;
	private Map<String, ImportItemConfig> itemConfigMap = new HashMap<String, ImportItemConfig>();
	
	public ImportConfig(ExportDataProvider exportDataProvider){
		this.exportDataProvider = exportDataProvider;
	}
	
	public void setItemConfigs(Set<ImportItemConfig> itemConfigs) {
		itemConfigMap.clear();
		for(ImportItemConfig conf : itemConfigs)
			itemConfigMap.put(conf.getId(), conf);
	}
	
	public void addItemConfig(ImportItemConfig... configs){
		for(ImportItemConfig conf : configs)
			itemConfigMap.put(conf.getId(), conf);
	}
	
	public void addItemConfig(Collection<ImportItemConfig> configs){
		for(ImportItemConfig conf : configs)
			itemConfigMap.put(conf.getId(), conf);
	}

	public boolean containsItemConfig(ImportItemConfig config){
		return itemConfigMap.containsKey(config.getId());
	}
	
	public Collection<ImportItemConfig> getItemConfigs() {
		return itemConfigMap.values();
	}
	
	public ExportDataProvider getExportDataProvider() {
		return exportDataProvider;
	}
	
	public void setSpecificImporterConfigs(List<ImporterSpecificConfig> specificImporterConfigs) {
		this.specificImporterConfigs = specificImporterConfigs;
	}

	public List<ImporterSpecificConfig> getSpecificImporterConfigs() {
		return specificImporterConfigs;
	}
	
	public void addSpecificImporterConfigs(ImporterSpecificConfig... config){
		specificImporterConfigs.addAll(Arrays.asList(config));
	}

	public ImportItemConfig getItemConfigFor(String id) {
		return itemConfigMap.get(id);
	}

}
