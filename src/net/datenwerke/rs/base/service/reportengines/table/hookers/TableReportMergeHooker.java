package net.datenwerke.rs.base.service.reportengines.table.hookers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.hookers.ReportDefaultMergeHooker;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;


public class TableReportMergeHooker extends ReportDefaultMergeHooker<TableReport> implements EntityMergeHook {

   @Inject
   public TableReportMergeHooker(Provider<ReportService> service, 
         Provider<EntityClonerService> cloneService, 
         Provider<ReportParameterService> pService
         ) {
      super(service, cloneService, pService, TableReport.class);
   }

   @Override
   protected void mergeSpecialFields(Object oldInstance, Object newInstance) {
      super.mergeSpecialFields(oldInstance, newInstance);
      TableReport oldReport = (TableReport) oldInstance; //source object 
      TableReport newReport = (TableReport) newInstance; //target values
         
      /* AdditionalColumnSpec /  Columns */
      Map<Integer, Column> columnRefMap = new HashMap<Integer, Column>();
      List<AdditionalColumnSpec> clonedColumnSpecs = newReport.createClonedColumnSpecs(newReport, columnRefMap);
      oldReport.getAdditionalColumns().clear();
      oldReport.getAdditionalColumns().addAll(clonedColumnSpecs);
      
      List<Column> clonedColumns = newReport.createClonedColumns(newReport, columnRefMap);
      oldReport.getColumns().clear();
      oldReport.getColumns().addAll(clonedColumns);
   }
   
   @Override
   protected void commitChanges(Object oldInstance) {     
      service.get().merge((TableReport) oldInstance);     
   }

}
