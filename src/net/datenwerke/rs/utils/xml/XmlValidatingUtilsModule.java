package net.datenwerke.rs.utils.xml;

import net.datenwerke.rs.utils.xml.annotations.DisableXMLValidation;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class XmlValidatingUtilsModule extends AbstractModule {

	
	@Override
	protected void configure() {
		bind(XMLUtilsService.class).to(XMLUtilsServiceImpl.class).in(Scopes.SINGLETON);
	}
	
	@Provides @DisableXMLValidation
	public boolean providerDisableXMLValidation(){
		return true;
	}

}
