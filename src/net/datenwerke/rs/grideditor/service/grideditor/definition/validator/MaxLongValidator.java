package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator = true
)
public class MaxLongValidator extends MaxNumberValidator {

	@ExposeToClient
	private Long number = 0l; 
	
	public MaxLongValidator(){
	}

	public MaxLongValidator(long number, String errorMsg){
		setErrorMsg(errorMsg);
		setNumber(number);
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	
	
	
}
