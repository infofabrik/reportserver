package net.datenwerke.rs.ftp.client.ftp.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface SftpRpcServiceAsync {

   void exportIntoSftp(ReportDto reportDto, String executorToken, SftpDatasinkDto sftpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed, AsyncCallback<Void> callback);

   void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback);

   Request testSftpDatasink(SftpDatasinkDto sftpDatasinkDto, AsyncCallback<Boolean> callback);

   void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback);
}
