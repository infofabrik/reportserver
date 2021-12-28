package net.datenwerke.rs.saikupivot.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

public interface SaikuPivotRpcServiceAsync {

   public Request stashReport(String token, TableReportDto report, AsyncCallback<Void> callback);

   void cubeExport(String token, TableReportDto report, AsyncCallback<String> callback);

   void cubeExportMondrian3(String token, TableReportDto report, AsyncCallback<String> callback);

}
