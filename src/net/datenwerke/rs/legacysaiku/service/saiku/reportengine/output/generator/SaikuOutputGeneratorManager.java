package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.output.AbstractReportOutputGeneratorManager;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hooks.SaikuOutputGeneratorProviderHook;

@Singleton
public class SaikuOutputGeneratorManager extends AbstractReportOutputGeneratorManager<SaikuOutputGenerator> {

	@Inject
	public SaikuOutputGeneratorManager(HookHandlerService hookHandler) {
		super(hookHandler, SaikuOutputGeneratorProviderHook.class);
	}

}
