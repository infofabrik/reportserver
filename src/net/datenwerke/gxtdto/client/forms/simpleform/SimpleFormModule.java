package net.datenwerke.gxtdto.client.forms.simpleform;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorBigDecimal;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorDouble;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringValidatorFloat;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * 
 *
 */
public class SimpleFormModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* startup */
		bind(SimpleFormStartup.class).asEagerSingleton();
		
		/* static injection */
		requestStaticInjection(
			SimpleForm.class, 
			ChainedCallbackWrapper.class,
			
			SFFCStringValidatorBigDecimal.class,
			SFFCStringValidatorDouble.class,
			SFFCStringValidatorFloat.class
		);
	}

}
