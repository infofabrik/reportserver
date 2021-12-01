package net.datenwerke.rs.base.service.datasources.transformers.database;

import java.sql.Connection;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.predefined.ReadOnlyConnectionConfig;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.transformers.DataSourceDefinitionTransformer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedDriverException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public class Database2JdbcConnectionTransformer implements DataSourceDefinitionTransformer<Connection> {

	private Provider<DbPoolService> dbPoolService;

	@Inject	
	public Database2JdbcConnectionTransformer(Provider<DbPoolService> dbPoolService) {
		super();
		this.dbPoolService = dbPoolService;
	}

	
	@Override
	public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		return (null != container && container.getDatasource() instanceof DatabaseDatasource && dst.isAssignableFrom(Connection.class));
	}

	@Override
	public Connection transform(
			DatasourceContainerProvider containerProvider,
			Class<?> dst, ParameterSet parameters) throws UnsupportedDriverException,
			DatabaseConnectionException {
		
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		DatabaseDatasource ds = (DatabaseDatasource) container.getDatasource();
		
		try {
			Connection conn = (Connection) dbPoolService.get().getConnection(ds.getConnectionConfig(), new ReadOnlyConnectionConfig()).get();
			return conn;
		} catch (Exception e) {
			DatabaseConnectionException dce = new DatabaseConnectionException(ds.getUrl(), ds.getUsername(), e);
			throw dce;
		}
	}


}
