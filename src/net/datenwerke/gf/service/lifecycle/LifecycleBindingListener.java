package net.datenwerke.gf.service.lifecycle;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import net.datenwerke.gf.service.lifecycle.events.InvalidateSessionEvent;
import net.datenwerke.gf.service.lifecycle.hooks.SessionHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.eventbus.EventBus;

public class LifecycleBindingListener implements HttpSessionBindingListener {

   private final HookHandlerService hookHandler;
   private final EventBus eventBus;

   public LifecycleBindingListener(HookHandlerService hookHandler, EventBus eventBus) {
      this.hookHandler = hookHandler;
      this.eventBus = eventBus;
   }

   @Override
   public void valueBound(HttpSessionBindingEvent arg0) {
   }

   @Override
   public void valueUnbound(HttpSessionBindingEvent event) {
      for (SessionHook hook : hookHandler.getHookers(SessionHook.class))
         hook.beforeSessionInvalidate(event);

      eventBus.fireEvent(new InvalidateSessionEvent());
   }

}
