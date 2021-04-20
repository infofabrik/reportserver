package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("localfilesystem")
public interface LocalFileSystemRpcService extends RemoteService {

   void exportIntoLocalFileSystem(ReportDto reportDto, String executorToken,
         LocalFileSystemDatasinkDto localFileSystemDatasinkDto, String format, List<ReportExecutionConfigDto> configs,
         String name, String folder) throws ServerCallFailedException;

   Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;

   boolean testLocalFileSystemDataSink(LocalFileSystemDatasinkDto localFileSystemDatasinkDto)
         throws ServerCallFailedException;
}