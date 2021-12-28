package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

/**
 * 
 *
 */
public interface SimpleFormCondition {

   /**
    * Determines whether the condition is met.
    */
   public boolean isMet(Widget formField, FormFieldProviderHook responsibleHook, SimpleForm form);

}
