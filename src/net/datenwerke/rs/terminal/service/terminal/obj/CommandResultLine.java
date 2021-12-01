package net.datenwerke.rs.terminal.service.terminal.obj;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto",
	createDecorator=true
)
public class CommandResultLine extends CommandResultEntry {
	
	@ExposeToClient
	private String line = "";
	
	public CommandResultLine() {
	}

	public CommandResultLine(String line) {
		this.setLine(line);
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLine() {
		return line;
	}

	@Override
	public String toString() {
		return line;
	}
}
