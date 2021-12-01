package net.datenwerke.rs.base.service.dbhelper;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.hookers.CteQueryHandler;
import net.datenwerke.rs.base.service.dbhelper.hookers.FilterExecutorHooker;
import net.datenwerke.rs.base.service.dbhelper.hookers.InnerQueryColumnReplacementHooker;
import net.datenwerke.rs.base.service.dbhelper.hookers.ProvideBaseDatabaseHelpersHookers;
import net.datenwerke.rs.base.service.dbhelper.hooks.DatabaseHelperProviderHook;
import net.datenwerke.rs.base.service.dbhelper.hooks.DbFilterExecutorHook;
import net.datenwerke.rs.base.service.dbhelper.hooks.InnerQueryModificationHook;
import net.datenwerke.rs.base.service.dbhelper.hooks.StatementModificationHook;

public class DbHelperStartup {

	@Inject
	public DbHelperStartup(
		HookHandlerService hookHandler,
			
		Provider<FilterExecutorHooker> filterExecutor,
		Provider<InnerQueryColumnReplacementHooker> innerQueryColumnReplacementHooker,
		
		CteQueryHandler cteHandler,
		
		ProvideBaseDatabaseHelpersHookers provideBaseDbHelpers
		){
		
		hookHandler.attachHooker(DbFilterExecutorHook.class, filterExecutor);
		
		hookHandler.attachHooker(DatabaseHelperProviderHook.class, provideBaseDbHelpers);
		hookHandler.attachHooker(InnerQueryModificationHook.class, innerQueryColumnReplacementHooker);
		
		hookHandler.attachHooker(StatementModificationHook.class, cteHandler);
	}
}
