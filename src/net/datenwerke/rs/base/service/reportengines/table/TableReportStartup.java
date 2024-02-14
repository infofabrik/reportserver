package net.datenwerke.rs.base.service.reportengines.table;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.hooks.DbFilterExecutorHook;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.hookers.TableFilterAdjustVariantHooker;
import net.datenwerke.rs.base.service.reportengines.table.eventhandler.HandleTableReportStoredEventHandler;
import net.datenwerke.rs.base.service.reportengines.table.eventhandler.RemoveAdditionalColumnSpecEventHandler;
import net.datenwerke.rs.base.service.reportengines.table.hookers.BaseTableOutputGeneratorProvider;
import net.datenwerke.rs.base.service.reportengines.table.hookers.BinaryColumnFilterExecutorHooker;
import net.datenwerke.rs.base.service.reportengines.table.hookers.CSVExecutionConfigFromPropertyMapHooker;
import net.datenwerke.rs.base.service.reportengines.table.hookers.ExecuteAsTableReportPreviewHooker;
import net.datenwerke.rs.base.service.reportengines.table.hookers.PagingExecutionConfigFromPropertyMapHooker;
import net.datenwerke.rs.base.service.reportengines.table.hookers.TableReportMergeHooker;
import net.datenwerke.rs.base.service.reportengines.table.hooks.TableOutputGeneratorProviderHook;
import net.datenwerke.rs.base.service.reportengines.table.maintenance.TableReportIntegrityValidator;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportExecutionConfigFromPropertyMapHook;
import net.datenwerke.rs.core.service.reportmanager.eventhandler.HandleDatasourceRemoveEventHandler;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutorExecuteAsHooker;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeEditedHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.JpaEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class TableReportStartup {

   @Inject
   public TableReportStartup(
         final HookHandlerService hookHandler, 
         final EventBus eventBus,
         final CSVExecutionConfigFromPropertyMapHooker csvFromProperty,
         final PagingExecutionConfigFromPropertyMapHooker pagingFromProperty,

         final Provider<ExecuteAsTableReportPreviewHooker> executeTablePreview,
         final Provider<BinaryColumnFilterExecutorHooker> binaryColumnFilterExecutor,

         final Provider<BaseTableOutputGeneratorProvider> baseExporters,

         final HandleDatasourceRemoveEventHandler handleDatasourceRemoveEventHandler,
         final HandleTableReportStoredEventHandler handleTableReportStoredEventHandler,

         final RemoveAdditionalColumnSpecEventHandler handleAdditionalColumnSepcRemoveHandler,

         final TableReportIntegrityValidator tableReportIntegrity, 
         final TableFilterAdjustVariantHooker adjustVariantHooker,
         
         final Provider<TableReportMergeHooker> tableReportMergeHooker
         ) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, DatasourceDefinition.class,
            handleDatasourceRemoveEventHandler);

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, AdditionalColumnSpec.class,
            handleAdditionalColumnSepcRemoveHandler);

      eventBus.attachObjectEventHandler(JpaEvent.class, TableReport.class, handleTableReportStoredEventHandler);

      /* maintenance */
      hookHandler.attachHooker(MaintenanceTask.class, tableReportIntegrity);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, tableReportMergeHooker);

      hookHandler.attachHooker(ReportExecutionConfigFromPropertyMapHook.class, csvFromProperty);
      hookHandler.attachHooker(ReportExecutionConfigFromPropertyMapHook.class, pagingFromProperty);

      hookHandler.attachHooker(ReportExecutorExecuteAsHooker.class, executeTablePreview);

      hookHandler.attachHooker(DbFilterExecutorHook.class, binaryColumnFilterExecutor);

      /* base exporters */
      hookHandler.attachHooker(TableOutputGeneratorProviderHook.class, baseExporters, HookHandlerService.PRIORITY_LOW);

      hookHandler.attachHooker(VariantToBeEditedHook.class, adjustVariantHooker);
   }
}
