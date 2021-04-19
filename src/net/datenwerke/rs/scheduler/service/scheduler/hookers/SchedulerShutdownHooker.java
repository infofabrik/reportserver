package net.datenwerke.rs.scheduler.service.scheduler.hookers;

import javax.servlet.ServletContextEvent;

import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;

import com.google.inject.Inject;

public class SchedulerShutdownHooker implements ContextHook {

	private final SchedulerService schedulerService;
	
	@Inject
	public SchedulerShutdownHooker(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		schedulerService.shutdown();
		schedulerService.shutdownWatchdog();
	}

}
