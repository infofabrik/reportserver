package net.datenwerke.rs.base.service.datasources.transformers.database;

import javax.inject.Inject;
import javax.sql.DataSource;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.predefined.ReadOnlyConnectionConfig;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.transformers.DataSourceDefinitionTransformer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedDriverException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public class Database2JdbcDatasourceTransformer implements DataSourceDefinitionTransformer<DataSource> {

	private DbPoolService<?> dbPoolService;

	@Inject
	public Database2JdbcDatasourceTransformer(DbPoolService dbPoolService) {
		this.dbPoolService = dbPoolService;
	}
	
	
	@Override
	public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		return (null != container && container.getDatasource() instanceof DatabaseDatasource && dst.isAssignableFrom(DataSource.class));
	}

	@Override
	public DataSource transform(
			DatasourceContainerProvider containerProvider,
			Class<?> dst, ParameterSet parameters) throws UnsupportedDriverException,
			DatabaseConnectionException {

		DatasourceContainer container = containerProvider.getDatasourceContainer();
		DatabaseDatasource ds = (DatabaseDatasource) container.getDatasource();
		
		try {
			DataSource conn = dbPoolService.getDataSource(ds.getConnectionConfig(), new ReadOnlyConnectionConfig());
			return conn;
		} catch (Exception e) {
			DatabaseConnectionException dce = new DatabaseConnectionException(ds.getUrl(), ds.getUsername(), e);
			throw dce;
		}

		
	}
}
