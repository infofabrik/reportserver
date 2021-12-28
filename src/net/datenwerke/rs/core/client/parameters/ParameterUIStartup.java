package net.datenwerke.rs.core.client.parameters;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.parameters.propertywidgets.ParameterViewFactory;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;

/**
 * 
 *
 */
public class ParameterUIStartup {

	@Inject
	public ParameterUIStartup(
		HookHandlerService hookHandler,
		ParameterViewFactory parameterWidgetFactory
		){
		
		/* attach hooks */
		hookHandler.attachHooker(
				ReportViewHook.class,
				new ReportViewHook(parameterWidgetFactory),
				HookHandlerService.PRIORITY_HIGH);
	
	}
	
	
}
