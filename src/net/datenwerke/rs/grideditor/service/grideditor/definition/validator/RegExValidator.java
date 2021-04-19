package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.grideditor.service.grideditor.locale.GridEditorMessages;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class RegExValidator extends Validator<Object> {

	@ExposeToClient
	private String regex;

	public RegExValidator(){
	}

	public RegExValidator(String regex, String errorMsg){
		setRegex(regex);
		setErrorMsg(errorMsg);
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	@Override
	public String validate(Object casted) {
		if(null == casted)
			return null;
		
		String str = String.valueOf(casted);

		return str.matches(regex) ? null : 
			(null == getErrorMsg() ? GridEditorMessages.INSTANCE.validationFailedDefaultMessage() : getErrorMsg());
	}
}
