package net.datenwerke.rs.birt.service.reportengine;

import java.lang.reflect.Field;
import java.util.logging.Level;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.layout.pdf.font.FontMappingManagerFactory;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;

@Singleton
public class BirtReportServiceImpl implements BirtReportService {

	private static final String BIRT_ENABLE_PROP = "reportengine.birt.enable";

	private static final String CONFIG_FILE = "reportengines/reportengines.cf";

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final Provider<EntityManager> entityManagerProvider;
	private final FileServerServiceResourceLocator fileServerServiceResourceLocator;
	
	private IReportEngine reportEngine;

	private ConfigService configService;
	
	@Inject
	public BirtReportServiceImpl(
		Provider<EntityManager> entityManagerProvider,
		ConfigService configService,
		FileServerServiceResourceLocator fileServerServiceResourceLocator
		) {
		this.entityManagerProvider = entityManagerProvider;
		this.configService = configService;
		this.fileServerServiceResourceLocator = fileServerServiceResourceLocator;
	}

	@Override
	@FireRemoveEntityEvents
	public void remove(BirtReportFile file) {
		EntityManager em = entityManagerProvider.get();
		file = em.find(file.getClass(), file.getId());
		if(null != file)
			em.remove(file);
	}

	@Override
	public boolean isBirtEnabled() {
		return configService.getConfigFailsafe(CONFIG_FILE).getBoolean(BIRT_ENABLE_PROP, true);
	}
	
	@Override
	public IReportEngine getReportEngine() throws BirtException{
		if(null != reportEngine)
			return reportEngine;

		EngineConfig config = createEngineConfig();
		Platform.startup(config); 
		IReportEngineFactory reportEngineFactory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
		IReportEngine reportEngine = reportEngineFactory.createReportEngine(config);
		reportEngine.changeLogLevel(Level.WARNING);

		this.reportEngine = reportEngine;

		return reportEngine;
	}
	
	@Override
	public void shutdown() {
		if(null == reportEngine)
			return;
		
		try{
			reportEngine.destroy();
			Platform.shutdown();
			RegistryProviderFactory.releaseDefault();
			clearFontCache();
		} finally {
			reportEngine = null;
		}
	}

	private EngineConfig createEngineConfig(){
		EngineConfig config = new EngineConfig();

		config.setResourceLocator(fileServerServiceResourceLocator);

		return config;
	}

	@Override
	public void clearFontCache() {
		try{
			Field instanceField = FontMappingManagerFactory.class.getDeclaredField("instance");
			instanceField.setAccessible(true);
			instanceField.set(null, null);
		} catch(Exception e){
			logger.warn("Could not clear BIRT font cache",e);
		}
		
	}
	
}
