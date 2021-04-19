package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.grideditor.service.grideditor.locale.GridEditorMessages;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class FixedLengthValidator extends Validator<Object> {

	@ExposeToClient
	private int length;
	
	public FixedLengthValidator(){
	}

	public FixedLengthValidator(int length, String errorMsg){
		setErrorMsg(errorMsg);
		setLength(length);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public String validate(Object casted) {
		if(null == casted)
			return null;
		
		return String.valueOf(casted).length() == length ? null :
			(null == getErrorMsg() ? GridEditorMessages.INSTANCE.validationFailedDefaultMessage() : getErrorMsg());
	}
	
}
