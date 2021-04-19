package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class TextDateEditor extends Editor {

	@ExposeToClient
	private String dateFormat = "yyyy-mm-dd";
	
	public TextDateEditor() {
		super();
	}

	public TextDateEditor(String dateFormat) {
		super();
		this.dateFormat = dateFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}
	
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	
}
