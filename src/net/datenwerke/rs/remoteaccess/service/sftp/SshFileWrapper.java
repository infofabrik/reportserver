package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.sshd.server.SshFile;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SshFileWrapper implements SshFile {

	protected static final Logger logger = LoggerFactory.getLogger(SshFileWrapper.class.getName());
	
	private final VFSLocation location;
	private final TerminalSession terminalSession;
	private final ServerSession sshSession;
	
	private byte[] uploadData;

	public SshFileWrapper(TerminalSession session, VFSLocation location, ServerSession sshSession) {
		this.location = location;
		this.terminalSession = session;
		this.sshSession = sshSession;
	}

	@Override
	public String getAbsolutePath() {
		return location.getAbsolutePath();
	}

	@Override
	public String getName() {
		try{
			return location.getName();
		} catch(Exception e){
			return "unknown";
		}
	}

	@Override
	public String getOwner() {
		return "owner";
	}

	@Override
	public boolean isDirectory() {
		return location.isFolder();
	}

	@Override
	public boolean isFile() {
		return ! location.isFolder();
	}

	@Override
	public boolean doesExist() {
		Callable<Boolean> callable = SftpRequestWrapper.wrapRequest(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return doDoesExist();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	protected Boolean doDoesExist() {
		return location.exists();
	}

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public boolean isWritable() {
		Callable<Boolean> callable = SftpRequestWrapper.wrapRequest(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return doIsWritable();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	protected Boolean doIsWritable() {
		return location.canWriteIntoLocation();
	}

	@Override
	public boolean isExecutable() {
		return false;
	}

	@Override
	public boolean isRemovable() {
		Callable<Boolean> callable = SftpRequestWrapper.wrapRequest(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return doIsRemovable();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	protected Boolean doIsRemovable() {
		return location.isLocationDeletable();
	}

	@Override
	public SshFile getParentFile() {
		Callable<SshFile> callable = SftpRequestWrapper.wrapRequest(new Callable<SshFile>() {

			@Override
			public SshFile call() throws Exception {
				return doGetParentFile();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return null;
		}
	}

	protected SshFile doGetParentFile() {
		return new SshFileWrapper(terminalSession, location.getParentLocation(), sshSession);
	}

	@Override
	public long getLastModified() {
		Callable<Long> callable = SftpRequestWrapper.wrapRequest(new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				return doGetLastModified();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return 0;
		}
	}

	protected Long doGetLastModified() {
		Date date = location.getLastModified();
		if(null == date)
			return 0l;
		
		return date.getTime();
	}

	@Override
	public boolean setLastModified(long time) {
		return false;
	}

	@Override
	public long getSize() {
		Callable<Long> callable = SftpRequestWrapper.wrapRequest(new Callable<Long>() {

			@Override
			public Long call() throws Exception {
				return doGetSize();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return 0;
		}
	}

	protected Long doGetSize() {
		return location.getSize();
	}

	@Override
	public boolean mkdir() {
		Callable<Boolean> callable = SftpRequestWrapper.wrapRequest(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return doMkdir();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}
	
	public boolean doMkdir() {
		try {
			return location.mkdir();
		} catch (VFSException e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean delete() {
		Callable<Boolean> callable = SftpRequestWrapper.wrapRequest(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return doDelete();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	protected boolean doDelete() {
		try {
			location.delete();
		} catch (VFSException e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public boolean create() throws IOException {
		Callable<Boolean> callable = SftpRequestWrapper.wrapRequest(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return doCreate();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	protected boolean doCreate() {
		try {
			return location.create();
		} catch (VFSException e) {
			logger.warn( e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void truncate() throws IOException {

	}

	@Override
	public boolean move(SshFile destination) {
		return false;
	}

	@Override
	public List<SshFile> listSshFiles() {
		Callable<List<SshFile>> callable = SftpRequestWrapper.wrapRequest(new Callable<List<SshFile>>() {

			@Override
			public List<SshFile> call() throws Exception {
				return doListSshFiles();
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return null;
		}
	}

	protected List<SshFile> doListSshFiles() {
		VFSLocationInfo	info = terminalSession.getFileSystem().getLocationInfo(location);
		
		if(info.getChildInfos().isEmpty())
			return Collections.emptyList();
		
		List<SshFile> list = new ArrayList<SshFile>();
		for(VFSObjectInfo child : info.getChildInfos()){
			try {
				list.add(new SshFileWrapper(terminalSession, terminalSession.getFileSystem().getLocation(location,child.getName()), sshSession));
			} catch (VFSException e) {
				logger.warn( e.getMessage(), e);
				throw new IllegalStateException(e);
			}
		}
		
		return list;
	}

	@Override
	public OutputStream createOutputStream(final long offset) throws IOException {
		if(null == uploadData && ! isWritable()){
			throw new IOException("Insufficient rights");
		}
		
		return new ByteArrayOutputStream(){
			@Override
			public void flush() throws IOException {
				handleData();
				super.flush();
			};
			
			@Override
			public void close() throws IOException {
				handleData();
				super.close();
			}

			private void handleData() {
				if(null == uploadData)
					uploadData = toByteArray();
				else if(offset > 0){
					if(uploadData.length > offset)
						uploadData = ArrayUtils.subarray(uploadData, 0, (int) offset);
					
					uploadData = ArrayUtils.addAll(uploadData, toByteArray());
				}
			}
		};
	}

	@Override
	public InputStream createInputStream(final long offset) throws IOException {
		Callable<InputStream> callable = SftpRequestWrapper.wrapRequest(new Callable<InputStream>() {

			@Override
			public InputStream call() throws Exception {
				return doCreateInputStream(offset);
			}
		}, sshSession);

		try {
			return callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
			return null;
		}
	}

	protected InputStream doCreateInputStream(long offset) throws IOException {
		try {
			if(location.hasContent()){
				byte[] content = location.getContent();
				ByteArrayInputStream bais = new ByteArrayInputStream(null == content ? new byte[0] : content);
				if(offset > 0)
					bais.skip(offset);
				return bais;
			}
		} catch (VFSException e) {
			logger.warn( e.getMessage(), e);
			throw new IOException(e);
		}
		return new ByteArrayInputStream(new byte[0]);
	}

	@Override
	public void handleClose() throws IOException {
		Callable<Void> callable = SftpRequestWrapper.wrapRequest(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				return doHandleClose();
			}
		}, sshSession);

		try {
			callable.call();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
		}
		
		uploadData = null;
	}

	protected Void doHandleClose() throws IOException {
		if(null == uploadData)
			return null;
		
		try {
			if(location.canWriteIntoLocation())
				location.writeIntoLocation(uploadData);
			return null;
		} catch (VFSException e) {
			logger.warn( e.getMessage(), e);
			throw new IOException(e);
		}
	}

}
