package net.datenwerke.rs.terminal.service.terminal.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.ext.service.reportmanager.vfs.ReportManagerVFS;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;

public class ReportOpenTerminalHooker implements OpenTerminalHandlerHook {
   
   private final ReportService reportService;
   private final Provider<ReportManagerVFS> reportManagerVFSProvider;

   @Inject
   public ReportOpenTerminalHooker(ReportService reportService, Provider<ReportManagerVFS> reportManagerVFSProvider) {
      this.reportService = reportService;
      this.reportManagerVFSProvider = reportManagerVFSProvider;
   }

   @Override
   public boolean consumes(String type) {
      return reportService.getBaseType().getName().equals(type);
   }
   
   @Override
   public AbstractReportManagerNode getNode(Long nodeId) {
      return reportService.getNodeById(nodeId);
   }
   
   @Override
   public ReportManagerVFS getVfs() {
      return reportManagerVFSProvider.get();      
   }

}
