package net.datenwerke.rs.terminal.service.terminal.obj;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto",
		createDecorator=true
	)
public class CommandResultHtml extends CommandResultEntry{
	
	@ExposeToClient(disableHtmlEncode=true)
	private String html = "";
	
	
	public CommandResultHtml() {

	}

	public CommandResultHtml(String html) {
		super();
		this.html = html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getHtml() {
		return html;
	}
	
	
}
