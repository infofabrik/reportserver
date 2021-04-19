package net.datenwerke.rs.base.service.reportengines.jasper;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.metadata.JasperMetadataExporter;
import net.datenwerke.rs.base.service.reportengines.jasper.output.metadata.JasperPlainExporter;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsModule;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

import com.google.inject.multibindings.Multibinder;

public class JasperReportModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		requestStaticInjection(
			JasperReport.class
		);
		
		/* bind metadata exporter */
		Multibinder<JasperMetadataExporter> metadataExporterBinder = Multibinder.newSetBinder(binder(), JasperMetadataExporter.class);
		metadataExporterBinder.addBinding().to(JasperPlainExporter.class);
		
		/* submodules */
		install(new JasperUtilsModule());
		
		bind(JasperReportStartup.class).asEagerSingleton();
	}
	
}
