package net.datenwerke.rs.base.client.reportengines.jasper.rpc;

import java.util.List;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface JasperReportRpcServiceAsync {

	void updateJRXMLFile(JasperReportJRXMLFileDto file,
			AsyncCallback<AbstractNodeDto> callback);

	void removeJRXMLFile(JasperReportJRXMLFileDto file,
			AsyncCallback<AbstractNodeDto> callback);

	void removeAllSubReports(JasperReportDto reoportDto,
			AsyncCallback<AbstractNodeDto> callback);

	void uploadJRXMLFiles(JasperReportDto report, List<FileToUpload> files, AsyncCallback<JasperReportDto> callback);

}
