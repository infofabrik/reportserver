package net.datenwerke.rs.xml;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.xml.XMLUtilsService;
import net.datenwerke.rs.utils.xml.XMLUtilsServiceImpl;
import net.datenwerke.rs.utils.xml.annotations.DisableXMLValidation;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class XmlRsUtilsModule extends AbstractModule {

	public static final String DISABLE_XML_VALIDATION_PROPERTY = "xmlutils.disablevalidation";
	
	@Override
	protected void configure() {
		System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
		
		bind(XMLUtilsService.class).to(XMLUtilsServiceImpl.class).in(Scopes.SINGLETON);
	}
	
	@Provides @Inject @DisableXMLValidation
	public boolean providerDisableXMLValidation(ConfigService configService){
		return configService.getConfigFailsafe("misc/misc.cf").getBoolean(DISABLE_XML_VALIDATION_PROPERTY, false);
		
	}

}
