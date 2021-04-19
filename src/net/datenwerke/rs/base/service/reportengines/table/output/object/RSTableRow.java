package net.datenwerke.rs.base.service.reportengines.table.output.object;

import java.io.Serializable;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;


/**
 * Represents a simple table row
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	generateDto2Poso=false
)
public class RSTableRow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9154292377110916477L;

	@ExposeToClient
	protected TableDefinition tableDefinition;
	
	protected boolean subtotalRow = false;
	
	protected Object[] row;
	
	public RSTableRow() {
	}
	
	public RSTableRow(TableDefinition tableDefinition, Object[] row) {
		this.tableDefinition = tableDefinition;
		this.row = row;
	}
	
	public RSTableRow(TableDefinition tableDefinition, List<Object> row) {
		this(tableDefinition, row.toArray());
	}
	
	public TableDefinition getTableDefinition() {
		return tableDefinition;
	}

	public void setTableDefinition(TableDefinition tableDefinition) {
		this.tableDefinition = tableDefinition;
	}
	
	public Object[] getRow() {
		return row;
	}

	public void setRow(Object[] row) {
		this.row = row;
	}


	public Object getAt(int columnIndex) {
		return row[columnIndex];
	}

	public void setAt(int columnIndex, Object value) {
		row[columnIndex] = value;
	}
	
	public Object getValueFor(String name){
		int i = tableDefinition.getColumnIndexOf(name);
		return row[i];
	}
	
	public boolean isSubtotalRow() {
		return subtotalRow;
	}
	
	public void setSubtotalRow(boolean subtotalRow) {
		this.subtotalRow = subtotalRow;
	}

}
