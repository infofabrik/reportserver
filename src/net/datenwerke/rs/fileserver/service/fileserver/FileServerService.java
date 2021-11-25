package net.datenwerke.rs.fileserver.service.fileserver;

import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface FileServerService extends TreeDBManager<AbstractFileServerNode> {

	/**
	 * Checks rights.
	 * 
	 * @param location
	 */
	FileServerFile createFileAtLocation(String location);
	
	FileServerFile createFileAtLocation(String location, boolean checkRights);

	/**
	 * Checks rights.
	 * 
	 * @param location
	 */
	FileServerFile createFileAtLocation(VFSLocation location);
	
	FileServerFile createFileAtLocation(VFSLocation location, boolean checkRights);
	
	AbstractFileServerNode getNodeByPath(String path);

	AbstractFileServerNode getNodeByPath(String path, boolean checkRights);



}
