package net.datenwerke.rs.base.client.reportengines.table.columnfilter;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.hookers.PreviewEnhancerHook;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.hookers.ToolbarEnhancerEditFilter;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.hooks.FilterViewEnhanceToolbarHook;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.propertywidgets.FilterViewFactory;
import net.datenwerke.rs.base.client.reportengines.table.cubeconfig.CubeConfigViewFactory;
import net.datenwerke.rs.base.client.reportengines.table.hooks.TableReportPreviewCellEnhancerHook;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;

/**
 * 
 *
 */
public class FilterUIStartup {
	
	@Inject
	public FilterUIStartup(
		HookHandlerService hookHandler,
		FilterViewFactory filterWidgetFactory,
		CubeConfigViewFactory cubeViewFactory,
		PreviewEnhancerHook previewEnhancer,
		
		Provider<ToolbarEnhancerEditFilter> toolbarEnhancerEditFilterProvider
		){
		
		/* attach hooks */
		hookHandler.attachHooker(
				ReportViewHook.class,
				new ReportViewHook(filterWidgetFactory),
				HookHandlerService.PRIORITY_MEDIUM);
		
		hookHandler.attachHooker(
				ReportViewHook.class,
				new ReportViewHook(cubeViewFactory),
				HookHandlerService.PRIORITY_MEDIUM);
		
		/* preview */
		hookHandler.attachHooker(TableReportPreviewCellEnhancerHook.class, previewEnhancer);
		
		/* toolbar */
		hookHandler.attachHooker(FilterViewEnhanceToolbarHook.class, toolbarEnhancerEditFilterProvider);
	}

}
