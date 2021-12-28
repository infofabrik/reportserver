package net.datenwerke.rs.incubator.service.exportmetadata;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.incubator.service.exportmetadata.annotations.ExportMetadataModuleAuthor;
import net.datenwerke.rs.incubator.service.exportmetadata.annotations.ExportMetadataModuleCreator;
import net.datenwerke.rs.incubator.service.exportmetadata.annotations.ExportMetadataModuleProperties;
import net.datenwerke.rs.incubator.service.exportmetadata.annotations.ExportMetadataModuleTitle;

/**
 * 
 *
 */
public class ExportMetadataModule extends AbstractReportServerModule {

	public static final String CONFIG_FILE = "exportfilemd/metadata.cf"; //$NON-NLS-1$
	public static final String PROPERTY_TITLE = "exportmetadata.title"; //$NON-NLS-1$
	public static final String PROPERTY_AUTHOR = "exportmetadata.author"; //$NON-NLS-1$
	public static final String PROPERTY_CREATOR = "exportmetadata.creator"; //$NON-NLS-1$

	@Override
	protected void configure() {
		/* bind service */
		bind(ExportMetadataService.class).to(ExportMetadataServiceImpl.class).in(Singleton.class);
		
		/* bind startup */
		bind(ExportMetadataStartup.class).asEagerSingleton();
	}

	@Provides @Inject @ExportMetadataModuleProperties
	public Configuration providePropertyContainer(ConfigService configService){
		try{
			return configService.getConfig(CONFIG_FILE);
		} catch(Exception e){
			return configService.newConfig();
		}
	}
	
	@Provides @Inject @ExportMetadataModuleTitle
	public String provideTitle(@ExportMetadataModuleProperties Configuration config){
		return config.getString(PROPERTY_TITLE, "");
	}

	@Provides @Inject @ExportMetadataModuleAuthor
	public String provideAuthor(@ExportMetadataModuleProperties Configuration config){
		return config.getString(PROPERTY_AUTHOR, "");
	}
	
	@Provides @Inject @ExportMetadataModuleCreator
	public String provideCreator(@ExportMetadataModuleProperties Configuration config){
		return config.getString(PROPERTY_CREATOR, "");
	}
}
