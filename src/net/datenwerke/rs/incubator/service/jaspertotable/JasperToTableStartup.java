package net.datenwerke.rs.incubator.service.jaspertotable;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportEngineTakeOverExecutionHook;
import net.datenwerke.rs.incubator.service.jaspertotable.hookers.JasperTableExecutorHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class JasperToTableStartup {

	@Inject
	public JasperToTableStartup(
		HookHandlerService hookHandler,
		
		Provider<JasperTableExecutorHook> jasperTableExecutor
		){
		
		
		hookHandler.attachHooker(ReportEngineTakeOverExecutionHook.class, jasperTableExecutor);
	}
}
