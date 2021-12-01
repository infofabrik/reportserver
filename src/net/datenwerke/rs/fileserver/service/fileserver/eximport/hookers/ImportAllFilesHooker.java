package net.datenwerke.rs.fileserver.service.fileserver.eximport.hookers;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.eximport.FileServerExporter;
import net.datenwerke.treedb.ext.service.eximport.helper.ImportAllNodesHooker;

import com.google.inject.Inject;

public class ImportAllFilesHooker extends ImportAllNodesHooker<AbstractFileServerNode>{

	@Inject
	public ImportAllFilesHooker(
		FileServerService treeDbManager
		) {
		super(treeDbManager, FileServerExporter.class);
	}

}
