package net.datenwerke.gf.service.maintenance;

import com.google.inject.Inject;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class MaintenanceStartup {

   @Inject
   public MaintenanceStartup(final MaintenanceService maintenanceService, HookHandlerService hookHandlerService) {

      hookHandlerService.attachHooker(LateInitHook.class, new LateInitHook() {

         @Override
         public void initialize() {
            maintenanceService.start();
         }
      }, HookHandlerService.PRIORITY_LOW);

   }
}
