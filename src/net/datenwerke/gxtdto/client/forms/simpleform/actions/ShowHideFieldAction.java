package net.datenwerke.gxtdto.client.forms.simpleform.actions;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

public class ShowHideFieldAction implements SimpleFormAction {

   private final String fieldKey;

   public ShowHideFieldAction(String fieldKey) {
      this.fieldKey = fieldKey;
   }

   public void onSuccess(SimpleForm form) {
      Widget field = form.getDisplayedField(fieldKey);
      if (null == field)
         return;
      if (field instanceof Component)
         ((Component) field).show();
      else
         field.setVisible(true);

      form.updateFormLayout();
   }

   public void onFailure(SimpleForm form) {
      Widget field = form.getDisplayedField(fieldKey);
      if (null == field)
         return;
      if (field instanceof Component)
         ((Component) field).hide();
      else
         field.setVisible(false);

      form.updateFormLayout();
   }

}
