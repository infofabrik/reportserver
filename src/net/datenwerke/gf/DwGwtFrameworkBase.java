package net.datenwerke.gf;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.servlet.GuiceServletContextListener;

import net.datenwerke.gf.service.lifecycle.LifecycleBindingListener;
import net.datenwerke.gf.service.lifecycle.events.InitSessionEvent;
import net.datenwerke.gf.service.lifecycle.events.ShutdownEvent;
import net.datenwerke.gf.service.lifecycle.events.StartupEvent;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;
import net.datenwerke.gf.service.lifecycle.hooks.SessionHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.eventbus.EventBus;


public abstract class DwGwtFrameworkBase extends GuiceServletContextListener implements HttpSessionListener {

	public static final String BASE_URL = "/reportserver/"; //$NON-NLS-1$
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Inject
	protected HookHandlerService hookHandler;
	
	@Inject
	protected EventBus eventBus;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		/* make sure listener is listening for proper closing events */
		event.getSession().setAttribute("__xx_dw_gwt_fr_sessionListener", new LifecycleBindingListener(hookHandler, eventBus));
		
		eventBus.fireEvent(new InitSessionEvent());
		
		for(SessionHook hook : hookHandler.getHookers(SessionHook.class))
			hook.sessionCreated(event);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// event in lifecycle listener
		
		for(SessionHook hook : hookHandler.getHookers(SessionHook.class))
			hook.sessionDestroyed(event);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		super.contextInitialized(servletContextEvent);
		
		try{
			String systemUser = "";
			try{
				systemUser = System.getProperty("user.name");
			}catch(Exception e){}
			if(null != eventBus)
				eventBus.fireEvent(new StartupEvent("system_user", systemUser));
			
			if(null != hookHandler)
				for(ContextHook hook : hookHandler.getHookers(ContextHook.class))
					hook.contextInitialized(servletContextEvent);
		} catch (Exception e) {
			logger.info( "context initialization error", e);
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		try{
			if(null == eventBus){
				logger.debug("context destroy before init");
				return;
			}
			
			String systemUser = "";
			try{
				systemUser = System.getProperty("user.name");
			}catch(Exception e){}
			eventBus.fireEvent(new ShutdownEvent("system_user", systemUser));
			
			if(null != hookHandler)
				for(ContextHook hook : hookHandler.getHookers(ContextHook.class))
					hook.contextDestroyed(servletContextEvent);
		} catch (Exception e) {
			logger.info( "error on context destroy", e);
		} finally {
			super.contextDestroyed(servletContextEvent);
		}
	}
	
	protected void injectorInitialized(){
		for(ConfigDoneHook hook : hookHandler.getHookers(ConfigDoneHook.class))
			hook.configDone();
	}
}
