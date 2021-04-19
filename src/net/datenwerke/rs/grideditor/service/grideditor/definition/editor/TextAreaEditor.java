package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class TextAreaEditor extends Editor {

	@ExposeToClient
	private int width = 220;
	
	@ExposeToClient
	private int height = 200;

	public TextAreaEditor(){
		super();
	}
	
	public TextAreaEditor(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public TextAreaEditor(int height) {
		super();
		this.height = height;
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
	
	
	
}
