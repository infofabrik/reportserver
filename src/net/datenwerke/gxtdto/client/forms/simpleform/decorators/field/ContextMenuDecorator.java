package net.datenwerke.gxtdto.client.forms.simpleform.decorators.field;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.menu.Menu;

/**
 * 
 *
 */
public class ContextMenuDecorator implements SimpleFormFieldDecorator {

	public static final String DECORATOR_ID = "ContextMenuDecorator";
	
	protected final Map<String, Menu> fieldMenuMap = new HashMap<String, Menu>();
	
	@Override
	public String getDecoratorId() {
		return DECORATOR_ID;
	}
	
	@Override
	public void configureFieldAfterLayout(SimpleForm simpleForm, Widget widget,
			String key) {
	}
	
	@Override
	public void configureFieldOnLoad(SimpleForm form, Widget field, String key) {
		Menu menu = fieldMenuMap.get(key);
		if(null != menu && field instanceof Component)
			((Component)field).setContextMenu(menu);
	}

	public void addMenu(String key, Menu menu) {
		fieldMenuMap.put(key,menu);
	}
	
	public Menu getMenuFor(String key){
		return fieldMenuMap.get(key);
	}

	@Override
	public Widget adjustFieldForDisplay(SimpleForm simpleForm,
			Widget formField, String fieldKey) {
		return formField;
	}



}
