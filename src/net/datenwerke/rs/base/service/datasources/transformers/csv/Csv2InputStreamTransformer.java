package net.datenwerke.rs.base.service.datasources.transformers.csv;

import java.io.IOException;
import java.io.InputStream;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.transformers.DataSourceDefinitionTransformer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedDriverException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public class Csv2InputStreamTransformer implements DataSourceDefinitionTransformer<Object> {

	
	@Inject
	public Csv2InputStreamTransformer(
		) {
		super();
	}
	
	@Override
	public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		return (null != container && container.getDatasource() instanceof CsvDatasource && dst.isAssignableFrom(InputStream.class));
	}

	@Override
	public Object transform(DatasourceContainerProvider containerProvider, Class<?> dst, ParameterSet parameters) throws UnsupportedDriverException, DatabaseConnectionException {
		/* get correct datasource definition and config */
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		CsvDatasource ds = (CsvDatasource) container.getDatasource();
		FormatBasedDatasourceConfig dsConfig = (FormatBasedDatasourceConfig) container.getDatasourceConfig();
		
		try {
			return ds.getDataStream(dsConfig);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
