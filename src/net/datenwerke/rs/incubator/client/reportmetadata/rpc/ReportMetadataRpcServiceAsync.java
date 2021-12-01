package net.datenwerke.rs.incubator.client.reportmetadata.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;

public interface ReportMetadataRpcServiceAsync {

	void updateMetadata(ReportDto reportDto, List<ReportMetadataDto> addedProperties,
			List<ReportMetadataDto> modifiedProperties, List<ReportMetadataDto> removedProperties, AsyncCallback<ReportDto> callback);

	void getMetadataKeys(AsyncCallback<List<String>> callback);

}
