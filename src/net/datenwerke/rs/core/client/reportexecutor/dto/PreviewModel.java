package net.datenwerke.rs.core.client.reportexecutor.dto;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

public class PreviewModel extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6799539859715703002L;

	private List<String> columnNames = new ArrayList<String>();
	private List<String[]> rows = new ArrayList<String[]>();
	
	private List<String[]> rawRows = new ArrayList<String[]>();
	
	private int size;
	
	public PreviewModel(){
		super();
	}

	public List<String[]> getRows() {
		return rows;
	}

	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}
	
	public void addRow(String[] sRow) {
		rows.add(sRow);
	}
	
	public List<String[]> getRawRows() {
		return rawRows;
	}

	public void setRawRows(List<String[]> rows) {
		this.rawRows = rows;
	}
	
	public void addRawRow(String[] row) {
		rawRows.add(row);
	}
	
	public String getRawData(int i, int j) {
		return rawRows.get(i)[j];
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public String getColumnName(int i) {
		return columnNames.get(i);
	}

	
}
