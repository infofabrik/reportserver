package net.datenwerke.rs.base.service.datasources;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.statementmanager.StatementManagerService;
import net.datenwerke.rs.base.service.datasources.statementmanager.StatementManagerServiceImpl;
import net.datenwerke.rs.base.service.datasources.table.TableDatasourceModule;
import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationServiceImpl;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DatasourceExtensionModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		/* startup */
		bind(DatasourceExtensionStartup.class).asEagerSingleton();
		bind(StatementManagerService.class).to(StatementManagerServiceImpl.class);
		bind(DatasourceTransformationService.class).to(DatasourceTransformationServiceImpl.class);
		
		/* install private modules */
		install(new TableDatasourceModule());
		
		/* static injection */
		requestStaticInjection(
			DatabaseDatasource.class
		);
	}

}
