package net.datenwerke.gf.service.lifecycle.hookers;

import javax.servlet.ServletContextEvent;

import com.google.inject.Inject;

import net.datenwerke.async.DwAsyncService;
import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;

public class CloseTaskAndConnectionsHooker implements ContextHook {

   private final DwAsyncService asyncService;
   private final DbPoolService dbPoolService;

   @Inject
   public CloseTaskAndConnectionsHooker(DwAsyncService asyncService, DbPoolService dbPoolService) {
      this.asyncService = asyncService;
      this.dbPoolService = dbPoolService;
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {
   }

   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent) {
      asyncService.shutdownAllNow();
      dbPoolService.shutdownAll();
   }

}
