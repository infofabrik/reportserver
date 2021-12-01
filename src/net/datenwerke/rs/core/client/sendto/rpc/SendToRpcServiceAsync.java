package net.datenwerke.rs.core.client.sendto.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;

public interface SendToRpcServiceAsync {

	void loadClientConfigsFor(ReportDto report,
			AsyncCallback<ArrayList<SendToClientConfig>> callback);

	void sendTo(ReportDto report, String executorToken, String id, String format, List<ReportExecutionConfigDto> formatConfig, Map<String, String> values, AsyncCallback<String> callback);

}
