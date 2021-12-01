package net.datenwerke.rs.base.service.datasources.transformers.birtreport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDataSource;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableModelDbHelper;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.birt.service.datasources.helper.BirtToRSTableHelper;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.internaldb.TempTableResult;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.resultcache.ResultCacheService;

public class Birt2ConnectionTransformer extends Birt2XTransformer<Connection> {

	@Inject
	public Birt2ConnectionTransformer(
		Provider<BirtToRSTableHelper> birtToTableProvider,
		DbPoolService dbPoolService,
		DBHelperService dbHelperService, 
		TempTableService tempTableService, 
		TableModelDbHelper tableModelDbHelper, 
		ResultCacheService resultCacheService) {
		
		super(birtToTableProvider, dbPoolService, dbHelperService, tempTableService, tableModelDbHelper, resultCacheService);
	}

	@Override
	public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		return (null != container && container.getDatasource() instanceof BirtReportDatasourceDefinition && dst.isAssignableFrom(Connection.class));
	}
	

	@Override
	protected Connection transformResult(Object r, DatasourceContainerProvider datasourceContainerProvider, ParameterSet parameterSet, Class<TableDataSource> targetType) {
		TempTableResult tempTableResult = (TempTableResult) r;

		try {
			String query = tempTableResult.getFinalQuery();
			
			String queryWrapper = ((BirtReportDatasourceConfig)datasourceContainerProvider.getDatasourceContainer().getDatasourceConfig()).getQueryWrapper();
			if(null == queryWrapper || "".equals(queryWrapper.trim()))
				queryWrapper = query;
			
			parameterSet.addVariable("query", query);
			for(String alias : tempTableResult.getTableHelper().getTableAliases())
				parameterSet.addVariable(alias, tempTableResult.getTableHelper().getTableName(alias));
			
			Connection connection = (Connection) dbPoolService.getConnection(tempTableResult.getPoolConfig()).get();
			return connection;
		} catch (InterruptedException | ExecutionException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
