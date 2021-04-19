package net.datenwerke.rs.adminutils.service.logs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.mail.MessagingException;

public interface LogFilesService {

	String getLogDirectory();

	List<String> readLastLines(String filename) throws IOException;
	
	void emailLogFiles(List<Path> files, String filter) throws MessagingException, IOException;
	
	void checkLogFiles(List<Path> files) throws IOException;
}
