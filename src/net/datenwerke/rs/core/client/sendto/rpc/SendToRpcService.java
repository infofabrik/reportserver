package net.datenwerke.rs.core.client.sendto.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;

@RemoteServiceRelativePath("sendtorpc")
public interface SendToRpcService extends RemoteService {

   public ArrayList<SendToClientConfig> loadClientConfigsFor(ReportDto report) throws ServerCallFailedException;

   String sendTo(ReportDto report, String executorToken, String id, String format,
         List<ReportExecutionConfigDto> formatConfig, Map<String, String> values) throws ServerCallFailedException;

}
