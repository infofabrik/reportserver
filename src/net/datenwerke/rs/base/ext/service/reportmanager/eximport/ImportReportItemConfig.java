package net.datenwerke.rs.base.ext.service.reportmanager.eximport;

import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;

public class ImportReportItemConfig extends TreeNodeImportItemConfig {

	private boolean cleanKeys = false;
	
	public ImportReportItemConfig(String id) {
		super(id);
	}

	public void setCleanKeys(boolean cleanKeys) {
		this.cleanKeys = cleanKeys;
	}
	
	public boolean isCleanKeys() {
		return cleanKeys;
	}
}
