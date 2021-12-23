package net.datenwerke.rs.emaildatasink.client.emaildatasink.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

@RemoteServiceRelativePath("email")
public interface EmailDatasinkRpcService extends RemoteService {

   void exportToEmail(ReportDto reportDto, String executorToken, EmailDatasinkDto emailDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String subject, String message, boolean compressed,
         List<StrippedDownUser> recipients) throws ServerCallFailedException;

   Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;

   boolean testEmailDatasink(EmailDatasinkDto emailDatasinkDto) throws ServerCallFailedException;

   DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException;
   
   void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder, boolean compressed, String subject, String message, List<StrippedDownUser> recipients) throws ServerCallFailedException;

}