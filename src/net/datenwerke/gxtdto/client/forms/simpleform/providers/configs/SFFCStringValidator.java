package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;


import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

import com.sencha.gxt.widget.core.client.form.Validator;


public interface SFFCStringValidator extends SimpleFormFieldConfiguration {
	
	public Validator<String> getValidator();
	public void setAllowBlank(boolean allowBlank);

}
