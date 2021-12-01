package net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.JxlsReportUiModule;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.JxlsReportService;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.treedb.actions.UpdateAction;

public class JxlsReportUploadHooker implements FileUploadHandlerHook {

	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<ReportService> reportServiceProvider;
	private final Provider<JxlsReportService> jxlsReportServiceProvider;

	@Inject
	public JxlsReportUploadHooker(
			Provider<SecurityService> securityServiceProvider, 
			Provider<ReportService> reportServiceProvider,
			Provider<JxlsReportService> jxlsReportServiceProvider
			) {
		this.securityServiceProvider = securityServiceProvider;
		this.reportServiceProvider = reportServiceProvider;
		this.jxlsReportServiceProvider = jxlsReportServiceProvider;
	}

	@Override
	public boolean consumes(String handler) {
		return JxlsReportUiModule.UPLOAD_HANDLER_ID.equals(handler);
	}


	@Override
	public String uploadOccured(UploadedFile uploadedFile) {
		Map<String, String> metadataMap = uploadedFile.getMetadata();

		long reportId = Long.valueOf(metadataMap.get(JxlsReportUiModule.UPLOAD_REPORT_ID_FIELD));
		String reportName = uploadedFile.getFileName();
		byte[] content = uploadedFile.getFileBytes();


		if(null == reportName || null == content || "".equals(reportName.trim()) || content.length == 0)
			return null;

		if(! (reportName.endsWith(".xls") || reportName.endsWith(".xlsx") || reportName.endsWith(".xlsm")))
			throw new RuntimeException("Could not create template. No xls file specified");

		try {
			/* make sure template is indeed xls */
			WorkbookFactory.create(new ByteArrayInputStream(content));
		} catch (Exception e) {
			throw new RuntimeException("Could not load excel file", e);	
		}

		SecurityService securityService = securityServiceProvider.get();
		securityService.assertUserLoggedIn();

		ReportService reportService = reportServiceProvider.get();
		AbstractReportManagerNode rmn = reportService.getNodeById(reportId);

		securityService.assertActions(rmn, UpdateAction.class);

		if(rmn instanceof JxlsReport){
			JxlsReport jxlsReport = (JxlsReport) rmn;

			JxlsReportFile oldFile = jxlsReport.getReportFile();

			JxlsReportFile reportFile = new JxlsReportFile();
			reportFile.setName(reportName);
			reportFile.setContent(content);

			jxlsReport.setReportFile(reportFile);

			if(null != oldFile){
				JxlsReportService jxlsReportService = jxlsReportServiceProvider.get();
				jxlsReportService.remove(oldFile);
			}

			reportService.merge(jxlsReport);
		}
		
		return null;
	}


}
