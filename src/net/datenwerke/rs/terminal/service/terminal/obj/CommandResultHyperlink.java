package net.datenwerke.rs.terminal.service.terminal.obj;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto",
		createDecorator=true
	)
public class CommandResultHyperlink extends CommandResultEntry{
	
	@ExposeToClient
	private String caption = "";
	
	@ExposeToClient
	private String historyToken = "";
	
	public CommandResultHyperlink() {

	}

	public CommandResultHyperlink(String caption, String historyToken) {
		super();
		this.caption = caption;
		this.historyToken = historyToken;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getHistoryToken() {
		return historyToken;
	}

	public void setHistoryToken(String historyToken) {
		this.historyToken = historyToken;
	}
	
	
}
