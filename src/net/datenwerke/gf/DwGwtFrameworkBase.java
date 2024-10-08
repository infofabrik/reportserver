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
   public void sessionCreated(final HttpSessionEvent event) {
      /* make sure listener is listening for proper closing events */
      event.getSession().setAttribute("__xx_dw_gwt_fr_sessionListener",
            new LifecycleBindingListener(hookHandler, eventBus));

      eventBus.fireEvent(new InitSessionEvent());

      hookHandler.getHookers(SessionHook.class)
         .forEach(hook -> hook.sessionCreated(event));
   }

   @Override
   public void sessionDestroyed(final HttpSessionEvent event) {
      // event in lifecycle listener

      hookHandler.getHookers(SessionHook.class)
         .forEach(hook -> hook.sessionDestroyed(event));
   }

   @Override
   public void contextInitialized(final ServletContextEvent servletContextEvent) {
      super.contextInitialized(servletContextEvent);

      try {
         String systemUser = "";
         try {
            systemUser = System.getProperty("user.name");
         } catch (Exception e) {
         }
         if (null != eventBus)
            eventBus.fireEvent(new StartupEvent("system_user", systemUser));

         if (null != hookHandler)
            hookHandler.getHookers(ContextHook.class).forEach(hook -> hook.contextInitialized(servletContextEvent));
      } catch (Exception e) {
         logger.info("context initialization error", e);
      }
   }

   @Override
   public void contextDestroyed(final ServletContextEvent servletContextEvent) {
      try {
         if (null == eventBus) {
            logger.debug("context destroy before init");
            return;
         }

         String systemUser = "";
         try {
            systemUser = System.getProperty("user.name");
         } catch (Exception e) {
         }
         eventBus.fireEvent(new ShutdownEvent("system_user", systemUser));

         if (null != hookHandler)
            hookHandler.getHookers(ContextHook.class).forEach(hook -> hook.contextDestroyed(servletContextEvent));
      } catch (Exception e) {
         logger.info("error on context destroy", e);
      } finally {
         super.contextDestroyed(servletContextEvent);
      }
   }

   protected void injectorInitialized() {
      hookHandler.getHookers(ConfigDoneHook.class).forEach(ConfigDoneHook::configDone);
   }
}
