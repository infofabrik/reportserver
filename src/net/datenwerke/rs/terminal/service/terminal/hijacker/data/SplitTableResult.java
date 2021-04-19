package net.datenwerke.rs.terminal.service.terminal.hijacker.data;

import java.util.List;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.PressKeyResultModifier;

public class SplitTableResult extends CommandResult {

	private List<List<String>> data;
	private int perView;
	private TableDefinition td;

	private int offset = 0;
	private boolean finished = false;
	
	public SplitTableResult(TableDefinition td, List<List<String>> data, int perView){
		this.td = td;
		this.data = data;
		this.perView = perView;
	}

	public CommandResult getNext() {
		RSTableModel table = new RSTableModel();
		table.setTableDefinition(td);
		
		int nrOfRows = Math.min(data.size() - offset, perView);
		for(int i = 0; i < nrOfRows; i++)
			table.addDataRow(new RSStringTableRow(data.get(i + offset)));
		offset += perView;
		
		CommandResult result = new CommandResult(table);
		result.addModifier(PressKeyResultModifier.class);
		
		if(offset >= data.size()){
			finished = true;
		} else {
			result.addResultLine("press enter for more results");
		}
		
		
		return result;
	}

	public boolean hasNext() {
		return ! finished;
	}
}
