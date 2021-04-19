package net.datenwerke.rs.base.service.reportengines.jasper.hookers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.download.hooks.FileDownloadHandlerHook;
import net.datenwerke.rs.base.client.reportengines.jasper.JasperUiModule;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.treedb.actions.ReadAction;

public class JasperReportJRXMLDownloadHooker implements FileDownloadHandlerHook {

	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<JasperUtilsService> jasperUtilsProvider;
	private final Provider<ReportService> reportServiceProvider;
	private final Provider<HttpUtils> httpUtilsProvider;
	
	@Inject
	public JasperReportJRXMLDownloadHooker(
			Provider<JasperUtilsService> jasperUtilsProvider,
			Provider<ReportService> reportServiceProvider,
			Provider<SecurityService> securityServiceProvider, 
			Provider<HttpUtils> httpUtilsProvider
			) {
		this.jasperUtilsProvider = jasperUtilsProvider;
		this.reportServiceProvider = reportServiceProvider;
		this.securityServiceProvider = securityServiceProvider;
		this.httpUtilsProvider = httpUtilsProvider;
	}

	@Override
	public boolean consumes(String handler) {
		return JasperUiModule.JRXML_DOWNLOAD_HANDLER.equals(handler);
	}

	@Override
	public void processDownload(String id, String handler,
			Map<String, String> metadata, HttpServletResponse response) throws IOException {
		Long lid = Long.valueOf(id);
		
		JasperUtilsService jasperUtilsService = jasperUtilsProvider.get();
		JasperReportJRXMLFile file = jasperUtilsService.getJRXMLFileById(lid);
		JasperReport report = jasperUtilsService.getReportWithJRXMLFile(file);
		
		SecurityService securityService = securityServiceProvider.get();

		securityService.assertActions(report, ReadAction.class);
		
		response.setHeader(HttpUtils.CONTENT_DISPOSITION, httpUtilsProvider.get().makeContentDispositionHeader(true, file.getName()));
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("application/xml");
		response.getOutputStream().write(file.getContent().getBytes());
	}

}
