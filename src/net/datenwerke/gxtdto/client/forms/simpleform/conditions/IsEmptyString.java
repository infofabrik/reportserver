package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 */
public class IsEmptyString implements SimpleFormCondition {

	public boolean isMet(Widget formField,
			FormFieldProviderHook responsibleHook,
			SimpleForm form) {
		String value = (String) responsibleHook.getValue(formField);
		if(null == value || "".equals(value.trim()))
			return true;
		return false;
	}

}
