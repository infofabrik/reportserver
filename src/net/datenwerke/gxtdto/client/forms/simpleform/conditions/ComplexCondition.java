package net.datenwerke.gxtdto.client.forms.simpleform.conditions;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

/**
 * 
 *
 */
public class ComplexCondition implements SimpleFormCondition {

   private final String fieldKey;
   private final SimpleFormCondition condition;

   public ComplexCondition(String fieldKey, SimpleFormCondition condition) {
      this.fieldKey = fieldKey;
      this.condition = condition;
   }

   public String getFieldKey() {
      return fieldKey;
   }

   public SimpleFormCondition getCondition() {
      return condition;
   }

   @Override
   public boolean isMet(Widget formField, FormFieldProviderHook responsibleHook, SimpleForm form) {
      return getCondition().isMet(form.getField(fieldKey), form.getResponsibleHook(fieldKey), form);
   }

}
