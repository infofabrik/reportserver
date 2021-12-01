package net.datenwerke.rs.base.service.reportengines.jasper.output.generator;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperOutputGeneratorProviderHook;
import net.datenwerke.rs.core.service.reportmanager.output.AbstractReportOutputGeneratorManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * 
 *
 */
@Singleton
public class JasperOutputGeneratorManager extends AbstractReportOutputGeneratorManager<JasperOutputGenerator> {
	
	@Inject
	public JasperOutputGeneratorManager(
			HookHandlerService hookHandler) {
		super(hookHandler, JasperOutputGeneratorProviderHook.class);
	}
	
}
