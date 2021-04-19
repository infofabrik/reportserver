package net.datenwerke.gxtdto.client.forms.simpleform.decorators.field;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

import com.google.gwt.user.client.ui.Widget;

public interface SimpleFormFieldDecorator {

	String getDecoratorId(); 
	
	void configureFieldOnLoad(SimpleForm form, Widget field, String key);

	void configureFieldAfterLayout(SimpleForm simpleForm, Widget widget,
			String key);

	Widget adjustFieldForDisplay(SimpleForm simpleForm, Widget formField,
			String fieldKey);
}
