package net.datenwerke.rs.base.service.datasources.table;

import com.google.inject.PrivateModule;

import net.datenwerke.rs.base.service.datasources.table.impl.TableDBDataSource;

public class TableDatasourceModule extends PrivateModule {

	@Override
	protected void configure() {
		requestStaticInjection(TableDBDataSource.class);
	}

}
