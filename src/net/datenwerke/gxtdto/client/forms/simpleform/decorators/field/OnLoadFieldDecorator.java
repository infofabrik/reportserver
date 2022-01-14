package net.datenwerke.gxtdto.client.forms.simpleform.decorators.field;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

public class OnLoadFieldDecorator implements SimpleFormFieldDecorator {

   public static final String DECORATOR_ID = "SimpleFormFieldOnLoadDecorator";

   @Override
   public String getDecoratorId() {
      return DECORATOR_ID;
   }

   @Override
   public void configureFieldOnLoad(SimpleForm form, Widget field, String key) {

   }

   @Override
   public void configureFieldAfterLayout(SimpleForm simpleForm, Widget widget, String key) {

   }

   @Override
   public Widget adjustFieldForDisplay(SimpleForm simpleForm, Widget formField, String fieldKey) {
      return formField;
   }

}
