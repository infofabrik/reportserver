package net.datenwerke.rs.search.service.search;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.search.service.search.hooks.SearchProvider;
import net.datenwerke.rs.search.service.search.provider.EntitySearchProvider;
import net.datenwerke.rs.search.service.search.terminal.commands.LocateCommand;
import net.datenwerke.rs.search.service.search.terminal.commands.UpdateDBCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class SearchStartup {

	
	
	@Inject
	public SearchStartup(
			HookHandlerService hookHandlerService,
			LocateCommand locateCommand,
			UpdateDBCommand locateUpdateDBCommand,
			final SearchService searchService,
			Provider<EntitySearchProvider> entitySearchProvider
	) {
		
		hookHandlerService.attachHooker(TerminalCommandHook.class, locateCommand);
		hookHandlerService.attachHooker(TerminalCommandHook.class, locateUpdateDBCommand);
		hookHandlerService.attachHooker(SearchProvider.class, entitySearchProvider);
		
		hookHandlerService.attachHooker(LateInitHook.class, new LateInitHook() {
			@Override
			public void initialize() {
				searchService.rebuildIndex();
			}
		});
	}
}
