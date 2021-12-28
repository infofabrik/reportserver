package net.datenwerke.rs.core.client.reportexecutor;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;

/**
 * 
 *
 */
public class ReportExecutorUIModule extends AbstractGinModule{
	
	public static final String REPORT_EXECUTOR_HISTORY_TOKEN = "reportexec";
	
	@Override
	protected void configure() {
		/* bind service */
		bind(ReportExecutorUIService.class).to(ReportExecutorUIServiceImpl.class).in(Singleton.class);
		
		/* bind hook setup */
		bind(ReportExecutorUIStartup.class).asEagerSingleton();
		
		requestStaticInjection(
			ReportExecutorMainPanel.class
		);
	}
	
}
