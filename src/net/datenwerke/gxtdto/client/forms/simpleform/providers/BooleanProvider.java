package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.Field;

/**
 * 
 *
 */
public class BooleanProvider extends FormFieldProviderHookImpl {

	@Override
	public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
		return Boolean.class.equals(type);
	}

	@Override
	public Field<Boolean> createFormField() {
		final CheckBox cb = new CheckBox();
		cb.setName(name);
		SFFCBoolean config = getConfig();
		if(null != config)
			cb.setBoxLabel(config.getBoxLabel());
		else
		cb.setBoxLabel("");
		
		/* add change listener */
		cb.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				ValueChangeEvent.fire(BooleanProvider.this, cb.getValue());
			}
		});
		
		return cb;
	}
	
	protected SFFCBoolean getConfig(){
		for(SimpleFormFieldConfiguration config : configs)
			if(config instanceof SFFCBoolean)
				return (SFFCBoolean) config;
		return null;
	}
	
	@Override
	public Object getValue(Widget field){
		CheckBox cb = (CheckBox)(field);
		return cb.getValue();
	}

}
