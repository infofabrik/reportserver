package net.datenwerke.gf.service.lifecycle.hooks;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface SessionHook extends Hook {

   public void sessionCreated(HttpSessionEvent event);

   public void beforeSessionInvalidate(HttpSessionBindingEvent event);

   public void sessionDestroyed(HttpSessionEvent event);
}
