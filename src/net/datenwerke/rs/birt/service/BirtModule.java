package net.datenwerke.rs.birt.service;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;

import net.datenwerke.rs.birt.service.datasources.BirtDatasourceModule;
import net.datenwerke.rs.birt.service.reportengine.BirtReportService;
import net.datenwerke.rs.birt.service.reportengine.BirtReportServiceImpl;
import net.datenwerke.rs.birt.service.reportengine.output.metadata.BirtMetadataExporter;
import net.datenwerke.rs.birt.service.reportengine.output.metadata.BirtPlainMetadataExporter;
import net.datenwerke.rs.birt.service.reportengine.sandbox.BirtEngineEnvironmentFactory;
import net.datenwerke.rs.birt.service.utils.BirtUtilService;
import net.datenwerke.rs.birt.service.utils.BirtUtilServiceImpl;

public class BirtModule extends AbstractModule {

	public final static String BIRT_LIBRARY_BASE_FOLDER_ID_PROPERETY_NAME = "birt.library.folder.id";
	public final static String BIRT_LIBRARY_BASE_FOLDER_PATH_PROPERETY_NAME = "birt.library.folder.path";
	
	@Override
	protected void configure() {
		bind(BirtReportService.class).to(BirtReportServiceImpl.class).in(Singleton.class);
		
		install(new BirtDatasourceModule());
		
		/* bind metadata exporter */
		Multibinder<BirtMetadataExporter> metadataExporterBinder = Multibinder.newSetBinder(binder(), BirtMetadataExporter.class);
		metadataExporterBinder.addBinding().to(BirtPlainMetadataExporter.class);
	
		bind(BirtUtilService.class).to(BirtUtilServiceImpl.class);
	
		install(new FactoryModuleBuilder().build(BirtEngineEnvironmentFactory.class));
		
		bind(BirtStartup.class).asEagerSingleton();
	}
	
}
