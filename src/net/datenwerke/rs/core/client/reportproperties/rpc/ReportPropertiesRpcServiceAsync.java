package net.datenwerke.rs.core.client.reportproperties.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;

public interface ReportPropertiesRpcServiceAsync {

	void getPropertyKeys(ReportDto reportDto, AsyncCallback<List<String>> callback);

	void getSupportedPropertyKeys(ReportDto reportDto, AsyncCallback<List<String>> callback);

	void updateProperties(ReportDto report, List<ReportStringPropertyDto> addedProperties,
			List<ReportStringPropertyDto> modifiedProperties, List<ReportStringPropertyDto> removedProperties,
			AsyncCallback<ReportDto> callback);
	
	void getInheritedProperties(ReportDto report, AsyncCallback<List<ReportStringPropertyDto>> callback);
}
