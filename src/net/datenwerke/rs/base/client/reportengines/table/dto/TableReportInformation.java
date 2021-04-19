package net.datenwerke.rs.base.client.reportengines.table.dto;

import java.io.Serializable;

public class TableReportInformation implements Serializable {

	private static final long serialVersionUID = -4535691619387899419L;
	private int columnCount;
	private int dataCount;
	private int visibleCount;
	private long executeDuration;
	
	
	public int getColumnCount() {
		return columnCount;
	}
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	public int getVisibleCount() {
		return visibleCount;
	}
	public void setVisibleCount(int visibleCount) {
		this.visibleCount = visibleCount;
	}
	public void setExecuteDuration(long executeDuration) {
		this.executeDuration = executeDuration;
	}
	public long getExecuteDuration() {
		return executeDuration;
	}
	
}
