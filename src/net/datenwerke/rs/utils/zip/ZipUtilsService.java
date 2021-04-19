package net.datenwerke.rs.utils.zip;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;

public interface ZipUtilsService {

	public interface FileFilter{
		public boolean addNode(AbstractFileServerNode node);
	}
	
	public static Object DIRECTORY_MARKER = new Object();
	
	public void createZip(Map<String, ? extends Object> content, OutputStream os) throws IOException;
	
	public void createZip(List<Path> files, OutputStream os) throws IOException;
	
	public void extractZip(byte[] data, ZipExtractionConfig config) throws IOException;
	
	public void extractZip(InputStream is, ZipExtractionConfig config) throws IOException;

	void createZip(byte[] content, OutputStream os) throws IOException;

	void createZip(FileServerFolder folder, OutputStream os) throws IOException;

	void createZip(FileServerFolder folder, OutputStream os, FileFilter filter) throws IOException;
}
