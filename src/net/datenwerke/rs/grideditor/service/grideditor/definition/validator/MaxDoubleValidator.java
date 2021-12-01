package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator = true
)
public class MaxDoubleValidator extends MaxNumberValidator {

	@ExposeToClient
	private Double number; 
	
	public MaxDoubleValidator(){
	}

	public MaxDoubleValidator(Double number, String errorMsg){
		setErrorMsg(errorMsg);
		setNumber(number);
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	
	
	
}
