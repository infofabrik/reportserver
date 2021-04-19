package net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.security.service.usermanager.entities.User;

public abstract class SaikuOutputGeneratorImpl implements SaikuOutputGenerator {

	protected final HookHandlerService hookHandler;
	protected SaikuReport report;
	protected User user;
	
	@Override
	public void initialize(SaikuReport report, User user) {
		this.report = report;
		this.user = user;
	}
	
	public SaikuOutputGeneratorImpl(HookHandlerService hookHandler){
		this.hookHandler = hookHandler;
	}
	
	@Override
	public boolean isCatchAll() {
		return false;
	}
	
	protected <R extends ReportExecutionConfig> R getConfig(Class<? extends R> type, ReportExecutionConfig... configs){
		for(ReportExecutionConfig config : configs)
			if(type.isAssignableFrom(config.getClass()))
				return (R) config;
		return null;
	}
}
