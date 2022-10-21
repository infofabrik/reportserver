package net.datenwerke.rs.base.client.reportengines.jasper.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@RemoteServiceRelativePath("jasperreport")
public interface JasperReportRpcService extends RemoteService {

   public JasperReportDto uploadJRXMLFiles(JasperReportDto report, List<FileToUpload> files)
         throws ServerCallFailedException;

   public AbstractNodeDto updateJRXMLFile(JasperReportJRXMLFileDto file) throws ServerCallFailedException;

   public AbstractNodeDto removeJRXMLFile(JasperReportJRXMLFileDto file) throws ServerCallFailedException;

   public AbstractNodeDto removeAllSubReports(JasperReportDto reportDto) throws ServerCallFailedException;

}
