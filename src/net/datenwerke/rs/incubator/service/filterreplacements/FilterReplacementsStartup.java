package net.datenwerke.rs.incubator.service.filterreplacements;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.hooks.FilterReplacementProviderHook;
import net.datenwerke.rs.incubator.service.filterreplacements.agg.AggregateFilterReplacementProviderHooker;
import net.datenwerke.rs.incubator.service.filterreplacements.analytics.AnalyticalFilterReplacementProviderHooker;
import net.datenwerke.rs.incubator.service.filterreplacements.today.TodayFilterReplacementProviderHooker;

import com.google.inject.Inject;

public class FilterReplacementsStartup {

	@Inject
	public FilterReplacementsStartup(
		HookHandlerService hookHandler,
		
		TodayFilterReplacementProviderHooker todayFilter,
		AggregateFilterReplacementProviderHooker aggFilter,
		AnalyticalFilterReplacementProviderHooker analyticalFunctionFilter
		){
		
		
		hookHandler.attachHooker(FilterReplacementProviderHook.class, todayFilter);
		hookHandler.attachHooker(FilterReplacementProviderHook.class, aggFilter);
		hookHandler.attachHooker(FilterReplacementProviderHook.class, analyticalFunctionFilter);
	}
}
