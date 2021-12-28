package net.datenwerke.rs.condition.client.condition;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.condition.client.condition.hookers.SchedulerConditionPageProvider;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleConfigWizardPageProviderHook;

public class ConditionUiStartup {

	
	@Inject
	public ConditionUiStartup(
		HookHandlerService hookHandler,
		
		Provider<SchedulerConditionPageProvider> conditionPageProvider
		){
		
		hookHandler.attachHooker(ScheduleConfigWizardPageProviderHook.class, conditionPageProvider);
		
	}
}
