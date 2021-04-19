package net.datenwerke.rs.terminal.service.terminal.obj;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto",
		createDecorator=true
	)
public class CommandResultAnchor extends CommandResultEntry{
	

	@ExposeToClient
	private String url = "";
	
	@ExposeToClient
	private String text;
	
	@ExposeToClient
	private String target;
	
	public CommandResultAnchor() {

	}

	public CommandResultAnchor(String text, String url) {
		this(text, url, "_new");
	}
	
	public CommandResultAnchor(String text, String url, String target) {
		super();
		this.text = text;
		this.target = target;
		this.url = url;
	}


	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
