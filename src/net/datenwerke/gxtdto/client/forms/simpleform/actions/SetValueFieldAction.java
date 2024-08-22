package net.datenwerke.gxtdto.client.forms.simpleform.actions;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

public class SetValueFieldAction implements SimpleFormAction {

   private String fieldKey;
   private Object value;
   private boolean first = true;

   public SetValueFieldAction(String fieldKey, Object value) {
      super();
      this.fieldKey = fieldKey;
      this.value = value;
   }

   @Override
   public void onSuccess(SimpleForm form) {
      if (skipFirst() && first)
         first = false;
      else
         form.setValue(fieldKey, value);
   }

   @Override
   public void onFailure(SimpleForm form) {
   }

   protected boolean skipFirst() {
      return true;
   }

}