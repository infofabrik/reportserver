package net.datenwerke.treedb.ext.service.eximport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.eximport.im.ImporterSpecificConfig;

/**
 * 
 *
 */
public class TreeNodeImporterConfig implements ImporterSpecificConfig {

	private Set<TreeNodeExImportOptions> exImporterOptions = new HashSet<TreeNodeExImportOptions>();

	public Set<TreeNodeExImportOptions> getExImporterOptions() {
		return exImporterOptions;
	}

	public void setExImporterOptions(Set<TreeNodeExImportOptions> exImporterOptions) {
		this.exImporterOptions = exImporterOptions;
	}
	
	public void addExImporterOptions(TreeNodeExImportOptions... option){
		this.exImporterOptions.addAll(Arrays.asList(option));
	}

	public boolean containsExImporterOption(TreeNodeExImportOptions options) {
		return exImporterOptions.contains(options);
	}
}

