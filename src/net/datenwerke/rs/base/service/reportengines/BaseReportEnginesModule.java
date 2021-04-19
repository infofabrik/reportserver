package net.datenwerke.rs.base.service.reportengines;

import net.datenwerke.rs.base.service.reportengines.jasper.JasperReportModule;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsModule;
import net.datenwerke.rs.base.service.reportengines.table.TableReportModule;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;

import com.google.inject.AbstractModule;

public class BaseReportEnginesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BaseReportEnginesStartup.class).asEagerSingleton();
		
		requestStaticInjection(
			TableReport.class
		);
		
		install(new TableReportModule());
		install(new JasperReportModule());
		install(new JasperUtilsModule());
	}

}
