package net.datenwerke.rs.base.service.datasources.transformers.csv;

import java.io.IOException;

import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.transformers.DataSourceDefinitionTransformer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedDriverException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRCsvDataSource;


/**
 * Transforms DataSourceDefitions into QRDataSources
 *
 */
public class Csv2JasperTransformer implements DataSourceDefinitionTransformer<JRDataSource> {
	
	@Override
	public JRDataSource transform(DatasourceContainerProvider containerProvider, Class<?> dst, ParameterSet parameters) throws UnsupportedDriverException, DatabaseConnectionException {
		
		/* get correct datasource definition and config */
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		CsvDatasource ds = (CsvDatasource) container.getDatasource();
		FormatBasedDatasourceConfig dsConfig = (FormatBasedDatasourceConfig) container.getDatasourceConfig();

		try {
			JRCsvDataSource jrds = new JRCsvDataSource(ds.getDataStream(dsConfig));
			if(null != ds.getSeparator() && ds.getSeparator().length() == 1)
				jrds.setFieldDelimiter( ds.getSeparator().subSequence(0, 1).charAt(0));
			jrds.setUseFirstRowAsHeader(true);
			return jrds;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		return (null != container && container.getDatasource() instanceof CsvDatasource && dst.isAssignableFrom(JRCsvDataSource.class));
	}

}
