package net.datenwerke.rs.legacysaiku.service.saiku;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import net.datenwerke.rs.legacysaiku.server.rest.SaikuRestModule;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.SaikuReportService;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.SaikuReportServiceImpl;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.metadata.SaikuMetadataExporter;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.metadata.SaikuPlainMetadataExporter;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;

public class SaikuModule extends AbstractModule {

	public final static String OUTPUT_FORMAT_CHART_HTML = "SAIKU_CHART_HTML";
	
	@Override
	protected void configure() {
		requestStaticInjection(MondrianDatasource.class);
		
		/* bind metadata exporter */
		Multibinder<SaikuMetadataExporter> metadataExporterBinder = Multibinder.newSetBinder(binder(), SaikuMetadataExporter.class);
		metadataExporterBinder.addBinding().to(SaikuPlainMetadataExporter.class);
		
		bind(OlapUtilService.class).to(OlapUtilServiceImpl.class);
		bind(OlapQueryService.class).to(OlapQueryServiceImpl.class);
		bind(SaikuReportService.class).to(SaikuReportServiceImpl.class);
		bind(SaikuStartup.class).asEagerSingleton();
		
		install(new SaikuRestModule());
	}


}
