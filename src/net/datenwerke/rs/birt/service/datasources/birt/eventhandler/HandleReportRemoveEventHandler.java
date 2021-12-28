package net.datenwerke.rs.birt.service.datasources.birt.eventhandler;

import java.util.Collection;

import com.google.inject.Inject;

import net.datenwerke.rs.birt.service.datasources.BirtDatasourceService;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleReportRemoveEventHandler implements EventHandler<RemoveEntityEvent> {

   private BirtDatasourceService datasourceService;

   @Inject
   public HandleReportRemoveEventHandler(BirtDatasourceService datasourceService) {
      this.datasourceService = datasourceService;

   }

   @Override
   public void handle(RemoveEntityEvent event) {
      Report node = (Report) event.getObject();
      if (!(node instanceof BirtReport))
         return;

      Collection<BirtReportDatasourceConfig> datasourceConfigs = datasourceService.getDatasourceConfigsWith(node);

      for (BirtReportDatasourceConfig config : datasourceConfigs) {
         config.setReport(null);
         datasourceService.merge(config);
      }
   }

}
