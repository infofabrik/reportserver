package net.datenwerke.rs.fileserver.service.fileserver.rsfs;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import javax.inject.Provider;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

public class RsfsUrlStreamHandler extends URLStreamHandler {
	

	private Provider<FileServerService> fileServerService;
	private Provider<SecurityService> securityService;

	@Inject
	public RsfsUrlStreamHandler(
			Provider<FileServerService> fileServerService, 
			Provider<SecurityService> securityService) {
		
		this.fileServerService = fileServerService;
		this.securityService = securityService;
	}

	@Override
	protected URLConnection openConnection(final URL u) throws IOException {
		
		return new URLConnection(u) {
			
			private FileServerFile file;

			@Override
			public InputStream getInputStream() throws IOException {
				if(!connected)
					connect();
				return new ByteArrayInputStream(file.getData());
			}
			
			@Override
			public int getContentLength() {
				return file.getData().length;
			}
			
			@Override
			public String getContentType() {
				return file.getContentType();
			}
			
			@Override
			public long getLastModified() {
				return file.getLastUpdated().getTime();
			}
			
			@Override
			public void connect() throws IOException {
				String path = u.getPath();
				AbstractFileServerNode node = fileServerService.get().getNodeByPath(path, false);
				if(null == node)
					throw new FileNotFoundException("The file " + u.toString() + " was not found");
				
				if(! (node instanceof FileServerFile))
					throw new IOException("The resource indicated by " + u.toString() + " is not of type FileServerFile");
				
				validateAccess(node, u);
				
				file = (FileServerFile) node;
				
				this.connected = true;
			}
			
			protected void validateAccess(AbstractFileServerNode file, URL u) throws IOException {
				boolean access = false;
				FileServerFolder folder = (FileServerFolder) file.getParent();
				while(null != folder){
					if(folder.isPubliclyAccessible()){
						access = true;
						break;
					}
					folder = (FileServerFolder) folder.getParent();
				}
				
				if(! access)
					access = securityService.get().checkRights(file, Read.class);
				
				if(! access)
					throw new IOException("Permission to access the resource " + u.toString() + " was denied.");
			}
		};
	}



}
