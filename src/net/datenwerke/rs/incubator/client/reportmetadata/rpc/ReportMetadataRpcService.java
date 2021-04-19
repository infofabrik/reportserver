package net.datenwerke.rs.incubator.client.reportmetadata.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;

@RemoteServiceRelativePath("reportmetadata")
public interface ReportMetadataRpcService extends RemoteService {

	public ReportDto updateMetadata(ReportDto reportDto, List<ReportMetadataDto> addedProperties,
			List<ReportMetadataDto> modifiedProperties, List<ReportMetadataDto> removedProperties) throws ServerCallFailedException;
	
	public List<String> getMetadataKeys() throws ServerCallFailedException;
}
