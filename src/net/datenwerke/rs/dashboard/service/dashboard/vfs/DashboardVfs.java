package net.datenwerke.rs.dashboard.service.dashboard.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.dashboard.service.dashboard.DashboardManagerService;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardFolder;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public class DashboardVfs extends TreeBasedVirtualFileSystem<AbstractDashboardManagerNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165976148782773400L;
	
	public static final String FILESYSTEM_NAME = "dashboardlib";
	
	@Inject
	public DashboardVfs(
		Provider<DashboardManagerService> treeServiceProvider	
		){
		super(treeServiceProvider);
	}

	@Override
	public String getFileSystemName() {
		return FILESYSTEM_NAME;
	}

	@Override
	protected String doGetNodeName(AbstractDashboardManagerNode node) {
		return node.getName();
	}
	
	@Override
	protected void doRename(AbstractDashboardManagerNode node, String name) {
		if(node instanceof DashboardNode)
			((DashboardNode)node).setName(name);
		else if(node instanceof DadgetNode)
			((DadgetNode)node).setName(name);
		else if(node instanceof DashboardFolder)
			((DashboardFolder)node).setName(name);
			
	}

	@Override
	protected AbstractDashboardManagerNode instantiateFolder(String folder) {
		return new DashboardFolder(folder);
	}

	@Override
	protected boolean isFolder(AbstractDashboardManagerNode node) {
		return node instanceof DashboardFolder;
	}
	
	@Override
	public byte[] getContent(VFSLocation vfsLocation) {
		return null;
	}
	
	@Override
	public boolean hasContent(VFSLocation vfsLocation) {
		return false;
	}
	
	@Override
	public long getSize(VFSLocation location) {
		return 0;
	}
	
	@Override
	public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
		if(! vfsLocation.exists()){
			VFSLocation parentLoc = vfsLocation.getParentLocation();
			if(! parentLoc.exists())
				return false;
			
			AbstractDashboardManagerNode parent = getNodeByLocation(parentLoc);
			if(! (parent instanceof DashboardFolder))
				return false;
			
			return canWrite(parent);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isLocationDeletable(VFSLocation vfsLocation) {
		AbstractDashboardManagerNode file = getNodeByLocation(vfsLocation);
		return null != file && canDelete(file);
	}
	
	@Override
	public void delete(VFSLocation vfsLocation) {
		final TreeDBManager<AbstractDashboardManagerNode> treeDBManager = treeDBManagerProvider.get();
		
		AbstractDashboardManagerNode node = getNodeByLocation(vfsLocation);
		
		canDelete(node);
		
		treeDBManager.remove(node);
	}
	
	@Override
	public VFSLocation create(VFSLocation vfsLocation) throws VFSException {
		return null;
	}
	
	@Override
	public void writeIntoLocation(VFSLocation vfsLocation, byte[] uploadData) throws VFSException {
		return;
	}
	
	



}
