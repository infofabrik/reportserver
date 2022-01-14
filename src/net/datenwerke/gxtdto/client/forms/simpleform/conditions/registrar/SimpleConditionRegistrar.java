package net.datenwerke.gxtdto.client.forms.simpleform.conditions.registrar;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SimpleFormAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.SimpleFormCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;

public class SimpleConditionRegistrar implements ConditionRegistrar {

   private final String fieldKey;
   private final SimpleFormCondition condition;
   private final SimpleFormAction action;
   private final SimpleForm form;

   public SimpleConditionRegistrar(String fieldKey, SimpleFormCondition condition, SimpleFormAction action,
         SimpleForm form) {
      super();
      this.fieldKey = fieldKey;
      this.condition = condition;
      this.action = action;
      this.form = form;
   }

   @SuppressWarnings("unchecked")
   public void registerCondition() {
      /* get field */
      final Widget formField = form.getField(fieldKey);
      final FormFieldProviderHook responsibleHook = form.getResponsibleHook(fieldKey);

      /* add a selection listener */
      responsibleHook.addValueChangeHandler(new ValueChangeHandler() {
         @Override
         public void onValueChange(ValueChangeEvent event) {
            checkCondition(formField, responsibleHook);
         }
      });

      /* check condition */
      checkCondition(formField, responsibleHook);
   }

   protected void checkCondition(Widget field, FormFieldProviderHook responsibleHook) {
      /* ask condition if it is met */
      if (condition.isMet(field, responsibleHook, form))
         action.onSuccess(form);
      else
         action.onFailure(form);
   }

}
