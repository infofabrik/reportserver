package net.datenwerke.rs.base.client.reportengines.jasper;

import java.util.List;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.rs.base.client.reportengines.jasper.rpc.JasperReportRpcServiceAsync;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class JasperReportDao extends Dao {


	private final JasperReportRpcServiceAsync rpcService;
	
	@Inject
	public JasperReportDao(JasperReportRpcServiceAsync treeManager){
		this.rpcService = treeManager;
	}
	
	public void updateJRXMLFile(JasperReportJRXMLFileDto file,
			AsyncCallback<AbstractNodeDto> callback){
		rpcService.updateJRXMLFile(file, transformDtoCallback(callback));
	}

	public void removeJRXMLFile(JasperReportJRXMLFileDto file,
			AsyncCallback<AbstractNodeDto> callback){
		rpcService.removeJRXMLFile(file, transformDtoCallback(callback));
	}

	public void removeAllSubReports(JasperReportDto report,
			AsyncCallback<AbstractNodeDto> callback) {
		rpcService.removeAllSubReports(report, transformDtoCallback(callback));
	}
	
	public void uploadJRXMLFiles(JasperReportDto report, List<FileToUpload> files, AsyncCallback<JasperReportDto> callback){
		rpcService.uploadJRXMLFiles(report, files, transformDtoCallback(callback));
	}
}
