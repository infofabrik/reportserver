package net.datenwerke.rs.computedcolumns.service.computedcolumns;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.annotations.AllowedFunctions;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;

public class ComputedColumnsModule extends AbstractModule {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public static final String CONFIG_FILE = "dynamiclists/computedcolumn.cf";
	public static final String ALLOWED_FUNCTIONS_PROPERTIES = "functions.function";
	
	@Override
	protected void configure() {
		bind(ComputedColumnsStartup.class).asEagerSingleton();
	}

	@Inject @Provides @AllowedFunctions
	public List<String> providerAllowedFunctions(ConfigService configService){
		HierarchicalConfiguration config = null; 
		try {
			config = (HierarchicalConfiguration) configService.getConfig(ComputedColumnsModule.CONFIG_FILE);
			if(null == config)
				return new ArrayList<String>();
		} catch(Exception e) {
			logger.warn( e.getMessage(), e);
			return new ArrayList<String>();
		}
		
		return config.getList(ComputedColumnsModule.ALLOWED_FUNCTIONS_PROPERTIES)
				.stream()
				.filter(o -> o instanceof String)
				.map(o -> (String)o)
				.collect(toList());
	}
}
