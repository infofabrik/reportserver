package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class CustomValidator<O> extends Validator<O> {

	@ExposeToClient(allowArbitraryLobSize=true,disableHtmlEncode=true)
	private String clientValidator;
	
	public CustomValidator(){
	}

	public CustomValidator(String clientValidator, String errorMsg){
		setClientValidator(clientValidator);
		setErrorMsg(errorMsg);
	}
	
	@Override
	public String validate(O casted){
		return null;
	}

	public String getClientValidator() {
		return clientValidator;
	}

	public void setClientValidator(String clientValidator) {
		this.clientValidator = clientValidator;
	}

	
}
