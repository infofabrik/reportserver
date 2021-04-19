package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.LocationDoesNotExistException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;

import org.apache.sshd.server.FileSystemView;
import org.apache.sshd.server.SshFile;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;

public class SftpFileSystemView implements FileSystemView {

	protected final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final Provider<TerminalService> terminalServiceProvider;

	private SftpAuthenticator authenticator;

	private ServerSession session;

	@Inject
	public SftpFileSystemView(
		Provider<TerminalService> terminalServiceProvider	
		){
			
		this.terminalServiceProvider = terminalServiceProvider;
	}
	
	public void setAuthenticator(SftpAuthenticator authenticator) {
		this.authenticator = authenticator;
	}
	
	public void setSession(ServerSession session) {
		this.session = session;
	}
	
	@Override
	public SshFile getFile(final String file) {
		Callable<SshFile> callable = SftpRequestWrapper.wrapRequest(new Callable<SshFile>() {

			@Override
			public SshFile call() throws Exception {
				return doGetFile(file);
			}
		}, session);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return null;
		}
	}
	
	public SshFile getFile(final String file, final boolean allowsNonExisting) throws IOException {
		Callable<SshFile> callable = SftpRequestWrapper.wrapRequest(new Callable<SshFile>() {

			@Override
			public SshFile call() throws Exception {
				return doGetFile(file, allowsNonExisting);
			}
		}, session);

		try {
			return callable.call();
		} catch (Exception e) {
			if(e instanceof IOException)
				throw (IOException)e;
			logger.warn( e.getMessage(), e);
			throw new IllegalStateException(e);
		}
	}

	

	@Override
	public SshFile getFile(final SshFile baseDir, final String file) {
		Callable<SshFile> callable = SftpRequestWrapper.wrapRequest(new Callable<SshFile>() {

			@Override
			public SshFile call() throws Exception {
				return doGetFile(baseDir, file);
			}
		}, session);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return baseDir;
		}
	}

	protected SshFile doGetFile(String file) throws IOException {
		return doGetFile(file, false);
	}
	
	protected SshFile doGetFile(final SshFile baseDir, final String file) {
		TerminalSession terminal = getSession();
		try {
			VFSLocation location = terminal.getFileSystem().getLocation(baseDir.getAbsolutePath() + "/" +  file);
			return new SshFileWrapper(terminal, location, session);
		} catch (VFSException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return baseDir;
	}

	protected TerminalSession getSession() {
		return terminalServiceProvider.get().getUnscopedTerminalSession();
	}

	protected SshFile doGetFile(String file, boolean allowNonExisting) throws IOException {
		TerminalSession terminal = getSession();
		try {
			VFSLocation location = terminal.getFileSystem().getLocation(file);
			if(!allowNonExisting && ! location.exists())
				throw new FileNotFoundException("Location does not exist: " + file);
			return new SshFileWrapper(terminal, location, session);
		} catch(LocationDoesNotExistException e){
			throw new FileNotFoundException(e.getMessage());
		} catch (VFSException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return null;
	}



	
}
