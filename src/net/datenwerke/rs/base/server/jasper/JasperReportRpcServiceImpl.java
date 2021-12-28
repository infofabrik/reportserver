package net.datenwerke.rs.base.server.jasper;

import java.util.Iterator;
import java.util.List;

import javax.inject.Singleton;

import org.apache.commons.codec.binary.Base64;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.rs.base.client.reportengines.jasper.rpc.JasperReportRpcService;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.actions.UpdateAction;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@Singleton
public class JasperReportRpcServiceImpl extends SecuredRemoteServiceServlet implements
		JasperReportRpcService {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1673736127397766517L;
	
	private final DtoService dtoService;
	private final JasperUtilsService jasperUtils;
	private final ReportService reportManager;
	private final SecurityService securityService;
	
	@Inject
	public JasperReportRpcServiceImpl(
		DtoService dtoService, 
		JasperUtilsService jasperUtils,
		ReportService reportManager,
		SecurityService securityService
		){
		
		/* store objects */
		this.dtoService = dtoService;
		this.jasperUtils = jasperUtils;
		this.reportManager = reportManager;
		this.securityService = securityService;
	}

	@Transactional(rollbackOn={Exception.class})
	public AbstractNodeDto updateJRXMLFile(@Named("file")JasperReportJRXMLFileDto file) throws ServerCallFailedException {
		/* get real file */
		JasperReportJRXMLFile realFile = jasperUtils.getJRXMLFileById(file.getId());
		
		/* validate rights */
		Report report = jasperUtils.getReportWithJRXMLFile(realFile);
		if(! securityService.checkActions(report, UpdateAction.class))
			throw new ViolatedSecurityExceptionDto();
		
		/* copy parameters */
		dtoService.mergePoso(file, realFile);
		
		/* merge file */
		jasperUtils.merge(realFile);
		
		/* get corresponding report */
		report = jasperUtils.getReportWithJRXMLFile(realFile);
		
		/* return report dto */
		return (AbstractNodeDto) dtoService.createDto(report);
	}

	@Transactional(rollbackOn={Exception.class})
	public AbstractNodeDto removeJRXMLFile(@Named("file")JasperReportJRXMLFileDto file) throws ServerCallFailedException {
		/* get real file */
		JasperReportJRXMLFile realFile = jasperUtils.getJRXMLFileById(file.getId());

		/* validate rights */
		Report report = jasperUtils.getReportWithJRXMLFile(realFile);
		if(! securityService.checkActions(report, UpdateAction.class))
			throw new ViolatedSecurityExceptionDto();
		
		/* remove file */
		report = jasperUtils.removeJRXMLFile(realFile);
		
		/* return report dto */
		return (AbstractNodeDto) dtoService.createDto(report);
	}
	
	@SecurityChecked(
			argumentVerification = {
				@ArgumentVerification(
					name = "report",
					isDto = true,
					verify = @RightsVerification(rights=Write.class)
				)
			}
		)
	@Transactional(rollbackOn={Exception.class})
	@Override
	public AbstractNodeDto removeAllSubReports(@Named("report")JasperReportDto reportDto) throws ServerCallFailedException{
		JasperReport report = (JasperReport) reportManager.getReportById(reportDto.getId());
		
		Iterator<JasperReportJRXMLFile> fileIterator = report.getSubFiles().iterator();
		while(fileIterator.hasNext()){
			JasperReportJRXMLFile current = fileIterator.next();
			fileIterator.remove();
			jasperUtils.removeJRXMLFile(current);
		}
		
		reportManager.merge(report);
		
		/* return report dto */
		return (AbstractNodeDto) dtoService.createDto(report);
	}

	@SecurityChecked(
			argumentVerification = {
				@ArgumentVerification(
					name = "report",
					isDto = true,
					verify = @RightsVerification(rights=Write.class)
				)
			}
		)
	@Transactional(rollbackOn={Exception.class})
	@Override
	public JasperReportDto uploadJRXMLFiles(@Named("report")JasperReportDto reportDto, List<FileToUpload> files) throws ServerCallFailedException {
		JasperReport report = (JasperReport) dtoService.loadPoso(reportDto);
		
		for(FileToUpload file : files){
			String strData = file.getB64Data().substring(file.getB64Data().indexOf(";base64,") + 8);
			String mimeType = file.getB64Data().substring(5, file.getB64Data().indexOf(";base64,"));
			
			JasperReportJRXMLFile jrxmlFile = new JasperReportJRXMLFile();
			jrxmlFile.setName(file.getName().contains(".") ? file.getName().substring(0, file.getName().indexOf(".")) : file.getName());
			jrxmlFile.setContent(new String(Base64.decodeBase64(strData.getBytes())));
			
			report.addSubfile(jrxmlFile);
			
			jasperUtils.persist(jrxmlFile);
		}
		
		reportManager.merge(report);
		
		return (JasperReportDto) dtoService.createDto(report);
	}

}
