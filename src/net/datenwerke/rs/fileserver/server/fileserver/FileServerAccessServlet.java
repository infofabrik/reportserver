package net.datenwerke.rs.fileserver.server.fileserver;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.rs.utils.zip.ZipUtilsService.FileFilter;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class FileServerAccessServlet extends SecuredHttpServlet {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public static final String KEY_PATH = "path";
	public static final String KEY_TWIDTH = "twidth";
	public static final String KEY_THUMBNAIL = "thumbnail";

	public static final String KEY_FOLDER = "folder";
	public static final String KEY_DOWNLOAD = "download";
	public static final String KEY_ID = "id";
	public static final String KEY_NOREDIRECT = "noredirect";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4431257744620664216L;
	
	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<FileServerService> fileServerService;
	private final Provider<AuthenticatorService> authenticatorServiceProvider;
	private final Provider<ZipUtilsService> zipServiceProvider;
	private final Provider<HttpUtils> httpUtilsProvider;

	
	@Inject
	public FileServerAccessServlet(
		Provider<SecurityService> securityServiceProvider,
		Provider<FileServerService> fileServerService, 
		Provider<AuthenticatorService> authenticatorServiceProvider,
		Provider<ZipUtilsService> zipServiceProvider, 
		Provider<HttpUtils> httpUtilsProvider
		){
		
		/* store objects */
		this.securityServiceProvider = securityServiceProvider;
		this.fileServerService = fileServerService;
		this.authenticatorServiceProvider = authenticatorServiceProvider;
		this.zipServiceProvider = zipServiceProvider;
		this.httpUtilsProvider = httpUtilsProvider;
	}
	
	@Override
	@SecurityChecked(bypass=true)
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if("true".equals(request.getParameter(KEY_FOLDER)) && "true".equals(request.getParameter(KEY_DOWNLOAD))){
			if(null != request.getParameter(KEY_ID))
				downloadFolderById(request, response);
			else if(null != request.getParameter(KEY_PATH))
				downloadFolderByPath(request, response);
			else {
				String requestURI = request.getRequestURI();
				int indexOf = requestURI.indexOf(FileServerUiModule.FILE_ACCESS_SERVLET);
				if(-1 != indexOf){
					String path = requestURI.substring(indexOf + FileServerUiModule.FILE_ACCESS_SERVLET.length());
					FileServerService service = fileServerService.get();
					AbstractFileServerNode file = service.getNodeByPath(path, false);
					if(file instanceof FileServerFolder)
						downloadFolder((FileServerFolder) file, request, response);
				}
			}
		} else {
			if(null != request.getParameter(KEY_ID))
				getFileById(request, response);
			else if(null != request.getParameter(KEY_PATH))
				getFileByPath(request, response);
			else {
				String requestURI = request.getRequestURI();
				int indexOf = requestURI.indexOf(FileServerUiModule.FILE_ACCESS_SERVLET);
				if(-1 != indexOf){
					String path = requestURI.substring(indexOf + FileServerUiModule.FILE_ACCESS_SERVLET.length());
					FileServerService service = fileServerService.get();
					AbstractFileServerNode file = service.getNodeByPath(path, false);
					if(file instanceof FileServerFile)
						returnFile((FileServerFile) file, request, response);
				}
			}
		}
	}
		

	@SecurityChecked(bypass=true)
	protected void downloadFolderByPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileServerService service = fileServerService.get();
		AbstractFileServerNode folder = service.getNodeByPath(request.getParameter(KEY_PATH), false);
		if(folder instanceof FileServerFolder)
			downloadFolder((FileServerFolder) folder, request, response);
	}
	
	@SecurityChecked(bypass=true)
	protected void downloadFolderById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileServerService service = fileServerService.get();
		Long fileId = Long.parseLong(request.getParameter(KEY_ID)); //$NON-NLS-1$
		
		AbstractFileServerNode folder = service.getNodeById(fileId);
		if(folder instanceof FileServerFolder)
			downloadFolder((FileServerFolder) folder, request, response);
		
	}
	
	@SecurityChecked(bypass=true)
	protected void downloadFolder(FileServerFolder folder, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		if(null == folder)
			return;
		
		if(! validateAccess(folder, request, response))
			return;
		
		response.setContentType("application/zip");
		response.setHeader(HttpUtils.CONTENT_DISPOSITION, httpUtilsProvider.get().makeContentDispositionHeader(true, folder.getName() + ".zip"));
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		zipServiceProvider.get().createZip(folder, response.getOutputStream(), new FileFilter() {
			@Override
			public boolean addNode(AbstractFileServerNode node) {
				try{
					return validateAccess(node, request, response);
				} catch(Exception e){
				}
				return false;
			}
		});
	}
	
	@SecurityChecked(bypass=true)
	protected void getFileByPath(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		FileServerService service = fileServerService.get();
		AbstractFileServerNode file = service.getNodeByPath(request.getParameter(KEY_PATH), false);
		if(file instanceof FileServerFile)
			returnFile((FileServerFile) file, request, response);
	}

	@SecurityChecked(bypass=true)
	protected void getFileById(HttpServletRequest request, HttpServletResponse response) throws IOException{
		FileServerService service = fileServerService.get();
		
		Long fileId = Long.parseLong(request.getParameter(KEY_ID)); //$NON-NLS-1$
		
		FileServerFile file = (FileServerFile) service.getNodeById(fileId);
		
		returnFile(file, request, response);
	}
	
	@SecurityChecked(bypass=true)
	protected void returnFile(FileServerFile file, HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(null == file)
			return;
		
		if(! validateAccess(file, request, response))
			return;
		
		String content = file.getContentType()  == null ? "" : file.getContentType().toLowerCase();
		if(null != request.getParameter(KEY_DOWNLOAD)){
			boolean download = true;
			if(!"true".equals(request.getParameter(KEY_DOWNLOAD)))
				download = false;

			response.setHeader(HttpUtils.CONTENT_DISPOSITION, httpUtilsProvider.get().makeContentDispositionHeader(download, file.getName()));
		}
		
		if("image/png".equals(content) || "image/jpeg".equals(content))
			outputImage(file, request, response);
		else {
			response.setContentType(file.getContentType()); //$NON-NLS-1$
			try (OutputStream os = response.getOutputStream()) {
			   if (null != file.getData())
                  os.write(file.getData());
			} catch (IOException e) {
				logger.warn( "fileserver error", e);
			}
		}
	}

	@SecurityChecked(bypass=true)
	private void outputImage(FileServerFile file, HttpServletRequest request,
			HttpServletResponse response) {
		boolean thumbnail = null != request.getParameter(KEY_THUMBNAIL);
		int width = null != request.getParameter(KEY_TWIDTH) ? Integer.parseInt(request.getParameter(KEY_TWIDTH)) : 100;
		
		try {
			BufferedImage orgImage = ImageIO.read(new ByteArrayInputStream(file.getData()));

			OutputStream os = response.getOutputStream();
			if(thumbnail){
				double scale = width/(double)orgImage.getWidth() ;
				int height = (int) Math.ceil(orgImage.getHeight() * scale);
			
				BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				img.createGraphics().drawImage(orgImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH),0,0,null);
				
				response.setContentType("image/png"); //$NON-NLS-1$
				ImageIO.write(img, "png", os);
			} else {
				ImageIO.write(orgImage, "png", os);
			}
			
			os.close();
		} catch (IOException e) {
			logger.warn( "filserver error", e);
		}
	}
	
	private boolean isAuthenticated() {
		return authenticatorServiceProvider.get().isAuthenticated();
	}
	
	private void requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/* store data in session */
		HttpSession session = req.getSession();
		
		String reqUrl = req.getRequestURL().toString();
	    String queryString = req.getQueryString();   
	    
	    if (queryString != null && !queryString.isEmpty()) {
	        reqUrl += "?"+queryString;
	    }
		
//		long reportId = getReportId(req);
//		String format = getOutputFormatFromRequest(req);
//		Report report = getReportFromRequest(req);
//		
//		session.setAttribute(SESSION_KEY_OUTPUT_FORMAT, format);
//		session.setAttribute(SESSION_KEY_REPORT_ID, reportId);
//		session.setAttribute(SESSION_KEY_ADJUSTED_REPORT, report);
//		session.setAttribute(SESSION_KEY_EXECUTOR_CONFIGS, getConfigsFromRequest(report, req));
//		session.setAttribute(SESSION_KEY_DOWNLOAD, req.getParameter("download"));
		
	    String url = req.getContextPath() + "/ReportServer.html?redir=" + URLEncoder.encode(reqUrl, "UTF-8"); 
		resp.sendRedirect(url); 
	}

	@SecurityChecked(bypass=true)
	private boolean validateAccess(AbstractFileServerNode node, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		boolean access = false;
		FileServerFolder folder = (FileServerFolder) node.getParent();
		while(null != folder){
			if(folder.isPubliclyAccessible()){
				access = true;
				break;
			}
			folder = (FileServerFolder) folder.getParent();
		}
		
		if(! access)
			access = securityServiceProvider.get().checkRights(node, Read.class);
		
		if(! access && null != req.getParameter(KEY_NOREDIRECT)){
			throw new ViolatedSecurityException();
		}
		
		if(! access){
			if(!isAuthenticated()){
				requireLogin(req, resp);
				return false;
			}
			throw new ViolatedSecurityException();
		}
		return access;
	}
}
