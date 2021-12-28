package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

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
