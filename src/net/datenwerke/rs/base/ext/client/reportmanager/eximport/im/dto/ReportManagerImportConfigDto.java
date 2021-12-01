package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.dto;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;

public class ReportManagerImportConfigDto extends TreeImportConfigDto<AbstractReportManagerNodeDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6274681789536912522L;

	private boolean removeKey;
	
	public boolean isRemoveKey() {
		return removeKey;
	}
	
	public void setRemoveKey(boolean removekey) {
		this.removeKey = removekey;
	}
	
	
}
