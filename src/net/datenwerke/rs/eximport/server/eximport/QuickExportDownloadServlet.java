package net.datenwerke.rs.eximport.server.eximport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Execute;

/**
 * 
 *
 */
@Singleton
public class QuickExportDownloadServlet extends SecuredHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2531374971329277458L;
	
	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<HttpExportService> httpExportServiceProvider;
	private final Provider<ZipUtilsService> zipServiceProvider;
	private final Provider<HttpUtils> httpUtilsProvider;

	
	@Inject
	public QuickExportDownloadServlet(
		Provider<SecurityService> securityServiceProvider,
		Provider<HttpExportService> httpExportServiceProvider,
		Provider<ZipUtilsService> zipServiceProvider,
		Provider<HttpUtils> httpUtilsProvider
		){
	
		/* store objects */
		this.securityServiceProvider = securityServiceProvider;
		this.httpExportServiceProvider = httpExportServiceProvider;
		this.zipServiceProvider = zipServiceProvider;
		this.httpUtilsProvider = httpUtilsProvider;
	}
	
	@Override
	@Transactional(rollbackOn={Exception.class})
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validateRequest();
		
		String export = httpExportServiceProvider.get().getAndRemoveStoredExport();
		String name = httpExportServiceProvider.get().getExportName();
		name = null == name ? "" : name;
		if(null == export)
			throw new IllegalStateException("Could not find exported data.");

		response.setContentType("application/octet-stream"); //$NON-NLS-1$
		response.setHeader("Cache-Control", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
		String exName = new SimpleDateFormat("yyyyMMdd-hhmm").format(Calendar.getInstance().getTime()) + "-export-" + name + ".zip";
		
		response.setHeader(HttpUtils.CONTENT_DISPOSITION, httpUtilsProvider.get().makeContentDispositionHeader(true, exName));
		
		/* zip */
		ZipUtilsService zipUtils = zipServiceProvider.get();
		zipUtils.createZip(Collections.singletonMap("export.xml", export), response.getOutputStream());
	}
	
	private void validateRequest() {
		securityServiceProvider.get().assertRights(ExportSecurityTarget.class, Execute.class);
	}
}
