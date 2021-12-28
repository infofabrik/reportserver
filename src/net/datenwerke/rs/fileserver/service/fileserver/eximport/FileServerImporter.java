package net.datenwerke.rs.fileserver.service.fileserver.eximport;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 */
public class FileServerImporter extends TreeNodeImporter {

	public static final String IMPORTER_ID = "FileServerImporter";
	
	private final FileServerService fileService;
	
	@Inject
	public FileServerImporter(
		FileServerService fileService
		){
		
		/* store objects */
		this.fileService = fileService;
	}
	
	@Override
	public Class<?>[] getRecognizedExporters() {
		return new Class<?>[]{FileServerExporter.class};
	}

	@Override
	protected TreeDBManager getTreeDBManager() {
		return fileService;
	}

	@Override
	public String getId() {
		return IMPORTER_ID;
	}
	
}
