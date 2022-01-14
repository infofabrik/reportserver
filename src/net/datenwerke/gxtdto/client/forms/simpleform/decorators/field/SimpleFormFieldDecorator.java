package net.datenwerke.gxtdto.client.forms.simpleform.decorators.field;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

public interface SimpleFormFieldDecorator {

   String getDecoratorId();

   void configureFieldOnLoad(SimpleForm form, Widget field, String key);

   void configureFieldAfterLayout(SimpleForm simpleForm, Widget widget, String key);

   Widget adjustFieldForDisplay(SimpleForm simpleForm, Widget formField, String fieldKey);
}
