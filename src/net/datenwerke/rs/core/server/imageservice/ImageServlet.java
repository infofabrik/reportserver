package net.datenwerke.rs.core.server.imageservice;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.PreviewImage;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.treedb.actions.UpdateAction;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class ImageServlet extends SecuredHttpServlet{

	private static final long serialVersionUID = -1966524992884670842L;

	private final Provider<ReportService> reportManagerService;
	private final Provider<SecurityService> securityServiceProvider;
	

	@Inject
	public ImageServlet(
		Provider<ReportService> reportManagerService,
		Provider<SecurityService> securityServiceProvider
		) {

		this.reportManagerService = reportManagerService;
		this.securityServiceProvider = securityServiceProvider;
	}


	@Override
	@Transactional(rollbackOn={Exception.class})
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long reportId = Long.parseLong(req.getParameter("reportId")); //$NON-NLS-1$
		
		/* load Report */
		Report report = reportManagerService.get().getReportById(reportId);

		/* validate request */
		validateRequest(report);
		
		PreviewImage previewImage = report.getPreviewImage();
		
		byte[] image = previewImage.getContent();
		
		resp.setContentType("image/png"); //$NON-NLS-1$
		OutputStream os = resp.getOutputStream();
		os.write(image);

		os.close();
	}


	private void validateRequest(Report report) {
		securityServiceProvider.get().assertActions(report, UpdateAction.class);
	}

}
