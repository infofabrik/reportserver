package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 */
public class FieldChanged implements SimpleFormCondition {


	
	public boolean isMet(Widget formField,
			FormFieldProviderHook responsibleHook,
			SimpleForm form) {
		return true;
	}

}
