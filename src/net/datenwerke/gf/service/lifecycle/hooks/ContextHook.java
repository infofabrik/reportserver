package net.datenwerke.gf.service.lifecycle.hooks;

import javax.servlet.ServletContextEvent;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ContextHook extends Hook {

	public void contextInitialized(ServletContextEvent servletContextEvent);
	
	public void contextDestroyed(ServletContextEvent servletContextEvent);
}
