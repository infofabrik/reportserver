package net.datenwerke.rs.base.client.reportengines.table.ui.model;

import net.datenwerke.gxtdto.client.model.DwModel;

public class TableReportPreviewConfig implements DwModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4950625065910601657L;
	
	private int pagesize;
	private int pagenumber;
	
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPagenumber() {
		return pagenumber;
	}
	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}
}