package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator = true
)
public class MinFloatValidator extends MinNumberValidator {

	@ExposeToClient
	private Float number = 0f;
	
	public MinFloatValidator(){
	}

	public MinFloatValidator(float number, String errorMsg){
		setNumber(number);
		setErrorMsg(errorMsg);
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}
	
	
	
}
