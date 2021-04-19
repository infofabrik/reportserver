package net.datenwerke.rs.base.client.datasources.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface BaseDatasourceMessages extends Messages {

	public final static BaseDatasourceMessages INSTANCE = GWT.create(BaseDatasourceMessages.class);
	
	String databaseTypeName();

	String csvDatasourceTypeName();

	String csvQuoteLabel();
	String csvSeparatorLabel();

	String dataLabel();

	String datasourceConnectorLabel();

	String textConnectorName();

	String urlLabel();
	String urlConnectorName();
	
	String editDataSource();

	String argumentConnector();

	String csvDatabaseCacheLabel();

	String csvQueryWrapperLabel();

	String jdbcDriverIsNotAvailable();
}