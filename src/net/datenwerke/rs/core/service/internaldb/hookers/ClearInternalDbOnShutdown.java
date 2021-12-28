package net.datenwerke.rs.core.service.internaldb.hookers;

import javax.servlet.ServletContextEvent;

import com.google.inject.Inject;

import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;
import net.datenwerke.rs.core.service.internaldb.TempTableService;

public class ClearInternalDbOnShutdown implements ContextHook {

   private TempTableService tempTableService;

   @Inject
   public ClearInternalDbOnShutdown(TempTableService tempTableService) {
      this.tempTableService = tempTableService;
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {

   }

   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent) {
      tempTableService.shutdown();
   }

}
