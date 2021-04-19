package net.datenwerke.rs.adminutils.server.logs;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.adminutils.client.logs.rpc.LogFilesRpcService;
import net.datenwerke.rs.adminutils.service.logs.LogFilesService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class LogFilesRpcServiceImpl extends SecuredRemoteServiceServlet implements LogFilesRpcService {

	private static final long serialVersionUID = 1L;
	private LogFilesService logFilesService;

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	public LogFilesRpcServiceImpl(LogFilesService logFilesService) {
		this.logFilesService = logFilesService;
	}

	@Override
	public List<String> readLastLines(String filename) throws ServerCallFailedException {
		try {
			return logFilesService.readLastLines(filename);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			throw new ServerCallFailedException("Cannot read " + filename, e);
		}
	}

	@Override
	public void emailFile(String filename) throws ServerCallFailedException {
		try {
			logFilesService.emailLogFiles(
					Arrays.asList(Paths.get(logFilesService.getLogDirectory() + "/" + filename)), 
					filename);
		} catch (IOException | MessagingException e) {
			logger.warn(e.getMessage(), e);
			throw new ServerCallFailedException("Cannot read " + filename, e);
		}		
	}
}
