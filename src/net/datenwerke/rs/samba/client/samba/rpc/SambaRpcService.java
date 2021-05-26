package net.datenwerke.rs.samba.client.samba.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("samba")
public interface SambaRpcService extends RemoteService {

   void exportIntoSamba(ReportDto reportDto, String executorToken, SambaDatasinkDto sambaDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder) throws ServerCallFailedException;

   Map<StorageType, Boolean> getSambaEnabledConfigs() throws ServerCallFailedException;

   boolean testSambaDataSink(SambaDatasinkDto sambaDatasinkDto) throws ServerCallFailedException;
   
   DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException;

}