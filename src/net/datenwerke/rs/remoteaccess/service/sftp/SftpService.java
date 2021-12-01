package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.IOException;

public interface SftpService {

	void start() throws IOException;

	boolean isActive();

	boolean isShutdown();

	boolean isTerminated();

	void shutdown();

}
