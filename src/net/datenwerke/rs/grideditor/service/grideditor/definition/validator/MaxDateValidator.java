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
public class MaxDateValidator extends Validator<Date> {

	@ExposeToClient
	private Date maxDate;
	
	public MaxDateValidator(){
	}

	public MaxDateValidator(Date maxDate, String errorMsg){
		setMaxDate(maxDate);
		setErrorMsg(errorMsg);
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	@Override
	public String validate(Date d) {
		if(null == d)
			return null;
		
		return d.before(maxDate) ? null :
			(null == getErrorMsg() ? GridEditorMessages.INSTANCE.validationFailedDefaultMessage() : getErrorMsg());
	}
	
	
}
