package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.dashboard.service.dashboard.DashboardManagerService;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.vfs.DashboardVfs;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;

public class DashboardOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final DashboardManagerService dashboardManagerService;
   private final Provider<DashboardVfs> dashboardVfsProvider;

   @Inject
   public DashboardOpenTerminalHooker(DashboardManagerService dashboardManagerService, Provider<DashboardVfs> dashboardVfsProvider) {
      this.dashboardManagerService = dashboardManagerService;
      this.dashboardVfsProvider = dashboardVfsProvider;
   }

   @Override
   public boolean consumes(String type) {
      return dashboardManagerService.getBaseType().getName().equals(type);
   }
   
   @Override
   public AbstractDashboardManagerNode getNode(Long nodeId) {
      return dashboardManagerService.getNodeById(nodeId);
   }
   
   @Override
   public DashboardVfs getVfs() {
      return dashboardVfsProvider.get();      
   }

}
