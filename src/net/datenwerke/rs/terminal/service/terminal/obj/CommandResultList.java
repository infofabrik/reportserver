package net.datenwerke.rs.terminal.service.terminal.obj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto",
	createDecorator=true
)
public class CommandResultList extends CommandResultEntry {
	
	@ExposeToClient
	private List<String> list = new ArrayList<String>();
	
	@ExposeToClient
	private boolean denyBreakUp = false;
	
	public CommandResultList() {
	}

	public CommandResultList(List<String> list) {
		this.setList(list);
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<String> getList() {
		return list;
	}
	
	public void addEntry(String entry){
		this.list.add(entry);
	}

	public int size() {
		return list.size();
	}

	public void addEntries(List<String> entries) {
		this.list.addAll(entries);
	}

	public void setDenyBreakUp(boolean denyBreakUp) {
		this.denyBreakUp = denyBreakUp;
	}

	public boolean isDenyBreakUp() {
		return denyBreakUp;
	}

	@Override
	public String toString() {
		if(list.isEmpty())
			return "";
		StringBuffer buf = new StringBuffer();
		
		for(String entry : list)
			buf.append(entry).append("\n");
		
		return buf.toString();
	}

	public void sort() {
		Collections.sort(list);
	}
	
}
