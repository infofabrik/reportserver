package net.datenwerke.dbpool.hooks.adapter;

import java.sql.Connection;

import net.datenwerke.dbpool.hooks.DbPoolConnectionHook;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class DbPoolConnectionHookAdapter implements DbPoolConnectionHook {

	@Override
	public void onAcquire(Connection connection)  {
	}


	@Override
	public void onCheckIn(Connection connection)  {
	}


	@Override
	public void onCheckOut(Connection connection)  {
	}


	@Override
	public void onDestroy(Connection connection)  {
	}

}
