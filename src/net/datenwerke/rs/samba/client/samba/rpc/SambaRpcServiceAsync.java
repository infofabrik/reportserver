package net.datenwerke.rs.samba.client.samba.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface SambaRpcServiceAsync {

   void exportIntoSamba(ReportDto reportDto, String executorToken, SambaDatasinkDto sambaDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, AsyncCallback<Void> callback);
   
   void getSambaEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback);
   
   Request testSambaDataSink(SambaDatasinkDto sambaDatasinkDto, AsyncCallback<Boolean> callback);

}