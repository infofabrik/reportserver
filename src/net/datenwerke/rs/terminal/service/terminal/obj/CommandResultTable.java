package net.datenwerke.rs.terminal.service.terminal.obj;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto",
	createDecorator=true
)
public class CommandResultTable extends CommandResultEntry {

	@ExposeToClient
	@EnclosedEntity
	private RSTableModel table;

	public CommandResultTable(){
	}
	
	public CommandResultTable(RSTableModel table) {
		this.table = table;
	}

	public RSTableModel getTable() {
		return table;
	}

	public void setTable(RSTableModel table) {
		this.table = table;
	}
	
	@Override
	public String toString() {
		if(table.getData().isEmpty())
			return "";
		StringBuffer buf = new StringBuffer();
		
		for(RSTableRow row : table.getData()){
			boolean first = true;
			for(Object entry : row.getRow()){
				if(first)
					first = false;
				else
					buf.append('\t');
				if(null != entry)
					buf.append(entry.toString());
			}
			buf.append('\n');
		}
		
		return buf.toString();
	}
}
