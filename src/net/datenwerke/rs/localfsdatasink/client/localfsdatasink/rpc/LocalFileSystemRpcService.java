package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("localfilesystem")
public interface LocalFileSystemRpcService extends RemoteService {

   void exportIntoLocalFileSystem(ReportDto reportDto, String executorToken,
         LocalFileSystemDatasinkDto localFileSystemDatasinkDto, String format, List<ReportExecutionConfigDto> configs,
         String name, String folder, boolean compressed) throws ServerCallFailedException;

   Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;

   boolean testLocalFileSystemDatasink(LocalFileSystemDatasinkDto localFileSystemDatasinkDto)
         throws ServerCallFailedException;
   
   DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException;
   
   void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder, boolean compressed) throws ServerCallFailedException;
}