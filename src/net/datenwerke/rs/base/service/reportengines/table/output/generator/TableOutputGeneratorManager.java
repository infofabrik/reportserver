package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.table.hooks.TableOutputGeneratorProviderHook;
import net.datenwerke.rs.core.service.reportmanager.output.AbstractReportOutputGeneratorManager;

import com.google.inject.Inject;


/**
 * Manages the output generators for table reports.
 * 
 * 
 * <p>
 * Currently only one instance of each output generator is created. This might
 * lead to problems and should be investigated.
 * </p>
 *
 *
 */
public class TableOutputGeneratorManager extends AbstractReportOutputGeneratorManager<TableOutputGenerator> {
	
	@Inject
	public TableOutputGeneratorManager(
			HookHandlerService hookHandler) {
		super(hookHandler, TableOutputGeneratorProviderHook.class);
	}

}
