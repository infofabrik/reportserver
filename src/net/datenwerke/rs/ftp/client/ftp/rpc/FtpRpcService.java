package net.datenwerke.rs.ftp.client.ftp.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("ftp")
public interface FtpRpcService extends RemoteService {

   void exportIntoFtp(ReportDto reportDto, String executorToken, FtpDatasinkDto ftpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed)
         throws ServerCallFailedException;

   /**
    * Gets the FTP storage enabled configs. If all storage configs are needed (FTP,
    * SFTP, FTPS), refer to {@link #getAllStorageEnabledConfigs()}.
    * 
    * @return
    * @throws ServerCallFailedException
    */
   Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;

   /**
    * Gets all storage enabled configs, including FTP, FTPS, and SFTP. If only FTP
    * storage configs are needed, refer to {@link #getStorageEnabledConfigs()}.
    * 
    * @return all storage enabled configs
    * @throws ServerCallFailedException
    */
   Map<StorageType, Boolean> getAllStorageEnabledConfigs() throws ServerCallFailedException;

   boolean testFtpDataSink(FtpDatasinkDto ftpDatasinkDto) throws ServerCallFailedException;

   DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException;
   
   void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder,boolean compressed) throws ServerCallFailedException;

}
