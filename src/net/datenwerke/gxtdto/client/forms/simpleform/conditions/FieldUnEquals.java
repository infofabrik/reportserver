package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

/**
 * 
 *
 */
public class FieldUnEquals implements SimpleFormCondition {

   private final Object value;

   /**
    * The value to test against
    * 
    * @param value
    */
   public FieldUnEquals(Object value) {
      this.value = value;
   }

   public boolean isMet(Widget formField, FormFieldProviderHook responsibleHook, SimpleForm form) {
      if (null == value)
         return null != responsibleHook.getValue(formField);
      return !value.equals(responsibleHook.getValue(formField));
   }

}
