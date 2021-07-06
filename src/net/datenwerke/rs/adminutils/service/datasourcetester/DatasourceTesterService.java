package net.datenwerke.rs.adminutils.service.datasourcetester;

import net.datenwerke.rs.adminutils.client.datasourcetester.ConnectionTestFailedException;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;

public interface DatasourceTesterService {

   boolean testConnection(DatabaseDatasource datasource) throws ConnectionTestFailedException, ReportExecutorException;
}
