package net.datenwerke.rs.computedcolumns.client.computedcolumns;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.propertywidgets.ComputedColumnsViewFactory;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ComputedColumnsUiStartup {

	@Inject
	public ComputedColumnsUiStartup(
		HookHandlerService hookHandler,
		
		Provider<ComputedColumnsViewFactory> viewFactory
		){
		
		hookHandler.attachHooker(
				ReportViewHook.class,
				new ReportViewHook(viewFactory),
				HookHandlerService.PRIORITY_MEDIUM - 10);
	
	}
}
