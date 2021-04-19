package net.datenwerke.rs.configservice.service.configservice;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ExpressionEngine;

public abstract class AbstractConfigStore {
	
	protected HierarchicalConfiguration createBaseConfig(){
		XMLConfiguration config = new XMLConfiguration();

		/* initialize and use localeawareexpressionengine */
		ExpressionEngine originalExpressionEngine = config.getExpressionEngine();
		ExpressionEngine localeAwareExpressionEngine = new LocaleAwareExpressionEngine(originalExpressionEngine);
		config.setExpressionEngine(localeAwareExpressionEngine);
		
		/* don't interpret values as lists */
		config.setDelimiterParsingDisabled(true);
		
		return config;
	}

	public abstract HierarchicalConfiguration loadConfig(String identifier) throws ConfigurationException;

}
