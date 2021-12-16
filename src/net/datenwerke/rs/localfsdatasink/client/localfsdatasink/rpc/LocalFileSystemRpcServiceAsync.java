package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.rpc;

import java.util.List;
import java.util.Map;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.http.client.Request;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface LocalFileSystemRpcServiceAsync {

   void exportIntoLocalFileSystem(ReportDto reportDto, String executorToken,
         LocalFileSystemDatasinkDto localFileSystemDatasinkDto, String format, List<ReportExecutionConfigDto> configs,
         String name, String folder, boolean compressed, AsyncCallback<Void> callback);

   void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback);

   Request testLocalFileSystemDatasink(LocalFileSystemDatasinkDto ftpDatasinkDto, AsyncCallback<Boolean> callback);
   
   void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback);
   
   void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder, boolean compressed,AsyncCallback<Void> callback);
}