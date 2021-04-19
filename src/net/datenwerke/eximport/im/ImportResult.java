package net.datenwerke.eximport.im;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 
 *
 */
public class ImportResult {

	private final String name;
	private final String description;
	private final Date date;
	private final Map<String, Object> importedObjects;
	private final Set<String> ignoredReferencesWithNoConfig;

	public ImportResult(
			String name, 
			String description, 
			Date date,
			Map<String, Object> importedObjects,
			Set<String> ignoredReferencesWithNoConfig) {
		super();
		
		this.name = name;
		this.description = description;
		this.date = date;
		this.importedObjects = importedObjects;
		this.ignoredReferencesWithNoConfig = ignoredReferencesWithNoConfig;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public Map<String, Object> getImportedObjects() {
		return importedObjects;
	}
	
	public Set<String> getIgnoredReferencesWithNoConfig(){
		return ignoredReferencesWithNoConfig;
	}
	
}
