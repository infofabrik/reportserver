package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 */
public class FieldEquals implements SimpleFormCondition {

	private final Object value;
	
	/**
	 * The value to test against
	 * 
	 * @param value
	 */
	public FieldEquals(Object value){
		this.value = value;
	}
	
	public boolean isMet(Widget formField,
			FormFieldProviderHook responsibleHook,
			SimpleForm form) {
		if(null == value)
			return null == responsibleHook.getValue(formField);
		return value.equals(responsibleHook.getValue(formField));
	}

}
