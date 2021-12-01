package net.datenwerke.rs.fileserver.service.fileserver.vfs;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.LocationDoesNotExistException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FileServerVfs extends TreeBasedVirtualFileSystem<AbstractFileServerNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165976148782773400L;
	
	public static final String FILESYSTEM_NAME = "fileserver";
	
	@Inject
	public FileServerVfs(
		Provider<FileServerService> treeServiceProvider	
		){
		super(treeServiceProvider);
	}

	@Override
	public String getFileSystemName() {
		return FILESYSTEM_NAME;
	}

	@Override
	protected String doGetNodeName(AbstractFileServerNode node) {
		if(node instanceof FileServerFolder)
			return ((FileServerFolder)node).getName();
		else
			return ((FileServerFile)node).getName();
	}
	
	@Override
	protected void doRename(AbstractFileServerNode node, String name) {
		if(node instanceof FileServerFolder)
			((FileServerFolder)node).setName(name);
		else
			((FileServerFile)node).setName(name);
	}

	@Override
	protected AbstractFileServerNode instantiateFolder(String folder) {
		return new FileServerFolder(folder);
	}

	@Override
	protected boolean isFolder(AbstractFileServerNode node) {
		return node instanceof FileServerFolder;
	}
	
	@Override
	public byte[] getContent(VFSLocation vfsLocation) {
		AbstractFileServerNode node = getNodeByLocation(vfsLocation);
		
		checkRead(node);
		
		if(node instanceof FileServerFile)
			return ((FileServerFile)node).getData();
		
		return null;
	}
	
	@Override
	public boolean hasContent(VFSLocation vfsLocation) {
		AbstractFileServerNode node = getNodeByLocation(vfsLocation);
		
		checkRead(node);
		
		if(node instanceof FileServerFile)
			return null != ((FileServerFile)node).getData();
		
		return false;
	}
	
	@Override
	public long getSize(VFSLocation location) {
		AbstractFileServerNode node = getNodeByLocation(location);
		
		checkRead(node);
		
		if(node instanceof FileServerFile)
			return ((FileServerFile)node).getSize();
		
		return 0;
	}
	
	@Override
	public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
		if(! vfsLocation.exists()){
			VFSLocation parentLoc = vfsLocation.getParentLocation();
			if(! parentLoc.exists())
				return false;
			
			AbstractFileServerNode parent = getNodeByLocation(parentLoc);
			if(! (parent instanceof FileServerFolder))
				return false;
			
			return canWrite(parent);
		} else {
			AbstractFileServerNode file = getNodeByLocation(vfsLocation);
			if(! (file instanceof FileServerFile))
				return false;
			
			return canWrite(file);
		}
	}
	
	@Override
	public boolean isLocationDeletable(VFSLocation vfsLocation) {
		AbstractFileServerNode file = getNodeByLocation(vfsLocation);
		return null != file && canDelete(file);
	}
	
	@Override
	public void delete(VFSLocation vfsLocation) {
		final TreeDBManager<AbstractFileServerNode> treeDBManager = treeDBManagerProvider.get();
		
		AbstractFileServerNode node = getNodeByLocation(vfsLocation);
		
		canDelete(node);
		
		treeDBManager.remove(node);
	}
	
	@Override
	public VFSLocation create(VFSLocation vfsLocation) throws VFSException {
		VFSLocation parentLoc = vfsLocation.getParentLocation();
		if(! parentLoc.exists())
			throw new VFSException("Cannot create new item");
		
		AbstractFileServerNode parent = getNodeByLocation(parentLoc);
		if(! (parent instanceof FileServerFolder))
			throw new VFSException("Parent is no folder");
		
		checkWrite(parent);
		
		/* new node */
		FileServerFile target = new FileServerFile();
		target.setName(vfsLocation.getPathHelper().getLastPathway());
		parent.addChild(target);
		
		final TreeDBManager<AbstractFileServerNode> treeDBManager = treeDBManagerProvider.get();
		
		treeDBManager.persist(target);
		treeDBManager.merge(parent);
		
		return vfsLocation.getParentLocation().newSubLocation(target.getId(), true);
	}
	
	@Override
	public void writeIntoLocation(VFSLocation vfsLocation, byte[] uploadData) throws VFSException {
		final TreeDBManager<AbstractFileServerNode> treeDBManager = treeDBManagerProvider.get();
		
		FileServerFile target = null;
		if(! vfsLocation.exists())
			throw new LocationDoesNotExistException(vfsLocation.getAbsolutePath());
		else {
			AbstractFileServerNode node = getNodeByLocation(vfsLocation);
			if(! (node instanceof FileServerFile))
				throw new VFSException("Target is no file");
			
			target = (FileServerFile) node;
			
			checkWrite(target);
		}
		
		if(target instanceof HibernateProxy)
			target = (FileServerFile) ((HibernateProxy)target).getHibernateLazyInitializer().getImplementation();
		
		target.setData(uploadData);
		treeDBManager.merge(target);
	}



}
