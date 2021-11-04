package net.datenwerke.rs.onedrive.client.onedrive.rpc;

import java.util.List;
import java.util.Map;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.http.client.Request;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface OneDriveRpcServiceAsync {
   void exportIntoOneDrive(ReportDto reportDto, String executorToken, OneDriveDatasinkDto oneDriveDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed,
         AsyncCallback<Void> callback);

   void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback);

   Request testOneDriveDatasink(OneDriveDatasinkDto oneDriveDatasinkDto, AsyncCallback<Boolean> callback);

   void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback);
}