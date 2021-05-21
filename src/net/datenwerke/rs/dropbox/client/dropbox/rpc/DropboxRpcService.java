package net.datenwerke.rs.dropbox.client.dropbox.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("dropbox")
public interface DropboxRpcService extends RemoteService {

   void exportIntoDropbox(ReportDto reportDto, String executorToken, DropboxDatasinkDto dropboxDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder)
         throws ServerCallFailedException;

   Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;

   boolean testDropboxDatasink(DropboxDatasinkDto dropboxDatasinkDto) throws ServerCallFailedException;

}