package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import java.math.BigDecimal;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator = true
)
public class MaxBigDecimalValidator extends MaxNumberValidator {

	@ExposeToClient
	private BigDecimal number; 
	
	public MaxBigDecimalValidator(){
	}

	public MaxBigDecimalValidator(BigDecimal number, String errorMsg){
		setErrorMsg(errorMsg);
		setNumber(number);
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	
	
	
}
