package net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers;

import com.google.inject.Inject;

import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ReportManagerExporter;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.ImportAllNodesHooker;

public class ImportAllReportsHooker extends ImportAllNodesHooker<AbstractReportManagerNode> {

   @Inject
   public ImportAllReportsHooker(ReportService reportService) {
      super(reportService, ReportManagerExporter.class);
   }

}
