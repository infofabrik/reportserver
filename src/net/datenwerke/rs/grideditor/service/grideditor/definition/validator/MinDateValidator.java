package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import java.util.Date;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.grideditor.service.grideditor.locale.GridEditorMessages;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.grideditor.client.grideditor.dto",
	generateDto2Poso=false,
	createDecorator=true
)
public class MinDateValidator extends Validator<Date> {

	@ExposeToClient
	private Date minDate;
	
	public MinDateValidator(){
	}

	public MinDateValidator(Date maxDate, String errorMsg){
		setMinDate(maxDate);
		setErrorMsg(errorMsg);
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	@Override
	public String validate(Date d) {
		if(null == d)
			return null;
		
		return minDate.before(d) ? null :
				(null == getErrorMsg() ? GridEditorMessages.INSTANCE.validationFailedDefaultMessage() : getErrorMsg());
	}
	

	
	
}
