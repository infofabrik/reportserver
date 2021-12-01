package net.datenwerke.rs.terminal.service.terminal.obj;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto"
)
public class CreMessage extends CommandResultExtension {

	@ExposeToClient
	private String title;
	
	@ExposeToClient
	private String windowTitle;
	
	@ExposeToClient
	private String text;
	
	@ExposeToClient(disableHtmlEncode=true)
	private String html;
	
	@ExposeToClient
	private int width;

	@ExposeToClient
	private int height;
	
	public CreMessage(){
		super();
	}
	
	public CreMessage(String title) {
		super();
		this.title = title;
	}
	
	public CreMessage(String title, String text) {
		super();
		this.title = title;
		this.text = text;
	}

	public CreMessage(String title, String windowTitle, String text,
			int width, int height) {
		this.title = title;
		this.windowTitle = windowTitle;
		this.text = text;
		this.width = width;
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}
	
	
}
