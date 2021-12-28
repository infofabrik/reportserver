package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

/**
 * 
 *
 */
public class NotIsEmptyString implements SimpleFormCondition {

	public boolean isMet(Widget formField,
			FormFieldProviderHook responsibleHook,
			SimpleForm form) {
		String value = (String) responsibleHook.getValue(formField);
		if(null == value || "".equals(value.trim()))
			return false;
		return true;
	}

}
