package net.datenwerke.rs.saikupivot.client;

import javax.inject.Inject;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

public class SaikuPivotDao extends Dao {

   private final SaikuPivotRpcServiceAsync rpcService;

   @Inject
   public SaikuPivotDao(SaikuPivotRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public Request stashReport(String token, TableReportDto report, AsyncCallback<Void> callback) {
      return rpcService.stashReport(token, report, transformAndKeepCallback(callback));
   }

   public void cubeExport(String token, TableReportDto report, AsyncCallback<String> callback) {
      rpcService.cubeExport(token, report, transformAndKeepCallback(callback));
   }

   public void cubeExportMondrian3(String token, TableReportDto report, AsyncCallback<String> callback) {
      rpcService.cubeExportMondrian3(token, report, transformAndKeepCallback(callback));
   }

}
