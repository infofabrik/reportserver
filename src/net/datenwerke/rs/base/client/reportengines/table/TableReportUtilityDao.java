package net.datenwerke.rs.base.client.reportengines.table;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.FilterType;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.SelectorPanelLoadConfig;
import net.datenwerke.rs.base.client.reportengines.table.rpc.TableReportUtilityServiceAsync;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public class TableReportUtilityDao extends Dao {

   private final TableReportUtilityServiceAsync rpcService;

   @Inject
   public TableReportUtilityDao(TableReportUtilityServiceAsync rpcService) {
      super();
      this.rpcService = rpcService;
   }

   public Request getDistinctValuesPaged(SelectorPanelLoadConfig pagingLoadConfig, TableReportDto report,
         ColumnDto column, FilterType type, boolean useFilters, boolean countResults, String executeToken,
         AsyncCallback<PagingLoadResult<StringBaseModel>> callback) {
      report = unproxy(report);
      column = unproxy(column);
      return rpcService.getDistinctValuesPaged(pagingLoadConfig, report, column, type, useFilters, countResults,
            executeToken, transformAndKeepCallback(callback));
   }

   public Request getReturnedColumns(TableReportDto tableReport, String executeToken,
         AsyncCallback<ListLoadResult<ColumnDto>> callback) {
      tableReport = unproxy(tableReport);
      return rpcService.getReturnedColumns(tableReport, executeToken, transformAndKeepCallback(callback));
   }

   public Request getReportInformation(TableReportDto tableReport, String executeToken,
         AsyncCallback<TableReportInformation> callback) {
      tableReport = unproxy(tableReport);
      DaoAsyncCallback<TableReportInformation> daoCallback = transformAndKeepCallback(callback);
      daoCallback.ignoreExpectedExceptions(true);
      return rpcService.getReportInformation(tableReport, executeToken, daoCallback);
   }

   public void loadColumnDefinition(ReportDto report, DatasourceContainerDto containerDto, String query,
         String executeToken, AsyncCallback<List<ColumnDto>> callback) {
      report = unproxy(report);
      containerDto = unproxy(containerDto);
      rpcService.loadColumnDefinition(report, containerDto, query, executeToken, transformAndKeepCallback(callback));
   }

   public void loadData(ReportDto report, DatasourceContainerDto containerDto, PagingLoadConfig loadConfig,
         String query, AsyncCallback<PagingLoadResult<ListStringBaseModel>> callback) {
      report = unproxy(report);
      containerDto = unproxy(containerDto);
      rpcService.loadData(report, containerDto, loadConfig, query, transformAndKeepCallback(callback));
   }

   public void getSpecialParameter(TableReportDto tableReport, String executeToken,
         AsyncCallback<Map<String, List<String>>> callback) {
      rpcService.getSpecialParameter(tableReport, executeToken, transformAndKeepCallback(callback));
   }

   public void exportPrefilterToDot(String token, TableReportDto report, AsyncCallback<String> callback) {
      rpcService.exportPrefilterToDot(token, report, transformAndKeepCallback(callback));
   }
   
   public void exportPrefilterToSvg(String token, TableReportDto report, AsyncCallback<String> callback) {
      rpcService.exportPrefilterToSvg(token, report, transformAndKeepCallback(callback));
   }

}
