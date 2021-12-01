package net.datenwerke.rs.base.server.helpers.tempfile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.datenwerke.gf.service.tempfile.TempFile;
import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

@Singleton
public class TempFileServlet extends SecuredHttpServlet {

	private static final long serialVersionUID = -3699726518482517393L;
	
	private final TempFileService tempFileService;

	private Provider<AuthenticatorService> authenticatorService;
	
	@Inject
	public TempFileServlet(TempFileService tempFileService, Provider<AuthenticatorService> authenticatorService) {
		this.tempFileService = tempFileService;
		this.authenticatorService = authenticatorService;

	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		String tempfileId = req.getParameter("id");
		TempFile tempFile = tempFileService.getTempFileById(tempfileId);
		

		if(null == tempFile){
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);	
		}

		if(!tempFile.isPermittedUser(authenticatorService.get().getCurrentUser())){
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
		if(null != tempFile.getMimeType() && !tempFile.getMimeType().isEmpty()){
			resp.setContentType(tempFile.getMimeType());
		}
		
		OutputStream os = resp.getOutputStream();
		byte[] fileContents = Files.readAllBytes(tempFile.getPath());
		os.write(fileContents);
		os.close();
	}

}
