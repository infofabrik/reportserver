package net.datenwerke.dbpool.hooks;

import java.sql.Connection;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;
import com.mchange.v2.c3p0.ConnectionCustomizer;

public class C3p0ConnectionHook implements ConnectionCustomizer {

	@Inject static protected HookHandlerService hookHandler;

	public C3p0ConnectionHook(){
		// dummy
	}
	
	@Override
	public void onAcquire(Connection c, String parentDataSourceIdentityToken)
			throws Exception {
		for(DbPoolConnectionHook hooker : hookHandler.getHookers(DbPoolConnectionHook.class))
			hooker.onAcquire(c);
	}

	@Override
	public void onDestroy(Connection c, String parentDataSourceIdentityToken)
			throws Exception {
		for(DbPoolConnectionHook hooker : hookHandler.getHookers(DbPoolConnectionHook.class))
			hooker.onDestroy(c);
	}

	@Override
	public void onCheckOut(Connection c, String parentDataSourceIdentityToken)
			throws Exception {
		for(DbPoolConnectionHook hooker : hookHandler.getHookers(DbPoolConnectionHook.class))
			hooker.onCheckOut(c);
	}

	@Override
	public void onCheckIn(Connection c, String parentDataSourceIdentityToken)
			throws Exception {
		for(DbPoolConnectionHook hooker : hookHandler.getHookers(DbPoolConnectionHook.class))
			hooker.onCheckIn(c);
	} 
	
}
