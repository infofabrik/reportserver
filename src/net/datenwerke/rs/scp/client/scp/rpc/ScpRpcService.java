package net.datenwerke.rs.scp.client.scp.rpc;

import java.util.List;
import java.util.Map;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;

@RemoteServiceRelativePath("scp")
public interface ScpRpcService extends RemoteService {

   void exportIntoScp(ReportDto reportDto, String executorToken, ScpDatasinkDto scpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder) throws ServerCallFailedException;

   Map<StorageType, Boolean> getScpEnabledConfigs() throws ServerCallFailedException;

   boolean testScpDatasink(ScpDatasinkDto scpDatasinkDto) throws ServerCallFailedException;

}
