package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.rs.grideditor.service.grideditor.locale.GridEditorMessages;

public abstract class MaxNumberValidator extends Validator<Number> {

	abstract public Number getNumber();
	
	@Override
	public String validate(Number value) {
		if(null == value)
			return null;
		
		Number number = getNumber();
		return number.doubleValue() >= ((Number)value).doubleValue() ? null :
			(null == getErrorMsg() ? GridEditorMessages.INSTANCE.validationFailedDefaultMessage() : getErrorMsg());
	}
}
