package net.datenwerke.rs.base.client.datasources.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.adminutils.client.datasourcetester.ConnectionTestFailedException;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

@HookConfig
public interface DatabaseConnectionTesterHook extends Hook {

   boolean consumes(DatabaseDatasource datasource, DatabaseHelper dbHelper);

   boolean testConnection(DatabaseDatasource datasource, DatabaseHelper dbHelper) throws ConnectionTestFailedException;

}
