package net.datenwerke.rs.saiku.service.saiku;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import net.datenwerke.rs.saiku.server.rest.SaikuRestModule;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.metadata.SaikuMetadataExporter;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.metadata.SaikuPlainMetadataExporter;

public class SaikuModule extends AbstractModule {

	public static final String CONFIG_FILE = "reportengines/reportengines.cf";
	
	public final static String OUTPUT_FORMAT_CHART_HTML = "SAIKU_CHART_HTML";
	
	@Override
	protected void configure() {
		requestStaticInjection(MondrianDatasource.class);
		
		/* bind metadata exporter */
		Multibinder<SaikuMetadataExporter> metadataExporterBinder = Multibinder.newSetBinder(binder(), SaikuMetadataExporter.class);
		metadataExporterBinder.addBinding().to(SaikuPlainMetadataExporter.class);
		
		bind(ThinQueryService.class).to(ThinQueryServiceImpl.class); // singleton necessary due to context map
		bind(OlapUtilService.class).to(OlapUtilServiceImpl.class);
		bind(SaikuService.class).to(SaikuServiceImpl.class);
		
		bind(SaikuStartup.class).asEagerSingleton();
		
		install(new SaikuRestModule());
	}

	

}
