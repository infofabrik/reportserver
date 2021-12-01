package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class TextBooleanEditor extends Editor {

	@ExposeToClient
	private String trueText = "true";
	
	@ExposeToClient
	private String falseText = "false";
	
	public String getTrueText() {
		return trueText;
	}
	
	public void setTrueText(String trueText) {
		this.trueText = trueText;
	}

	public String getFalseText() {
		return falseText;
	}

	public void setFalseText(String falseText) {
		this.falseText = falseText;
	}
	
	
}
