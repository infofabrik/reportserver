package net.datenwerke.rs.base.client.datasources.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.datasources.hooks.DatabaseConnectionTesterHook;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class DatabaseConnectionTesterHookAdapter implements DatabaseConnectionTesterHook {

	@Override
	public boolean consumes(DatabaseDatasource datasource, DatabaseHelper dbHelper)  {
		return false;
	}


	@Override
	public boolean testConnection(DatabaseDatasource datasource, DatabaseHelper dbHelper)  {
		return false;
	}



}
