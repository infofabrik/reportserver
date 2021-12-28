package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;


import com.sencha.gxt.widget.core.client.form.Validator;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;


public interface SFFCStringValidator extends SimpleFormFieldConfiguration {
	
	public Validator<String> getValidator();
	public void setAllowBlank(boolean allowBlank);

}
