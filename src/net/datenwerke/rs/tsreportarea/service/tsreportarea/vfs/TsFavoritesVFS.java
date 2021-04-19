package net.datenwerke.rs.tsreportarea.service.tsreportarea.vfs;

import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFolder;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.security.service.security.rights.Right;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class TsFavoritesVFS extends TreeBasedVirtualFileSystem<AbstractTsDiskNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7186418223163754943L;
	
	private static final String FILESYSTEM_NAME = "tsreport";

	private final Provider<TeamSpaceService> teamSpaceService;
	
	@Inject
	public TsFavoritesVFS(
		Provider<TsDiskService> favoritesServiceProvider,
		Provider<TeamSpaceService> teamSpaceService	
		){
		super(favoritesServiceProvider);
		this.teamSpaceService = teamSpaceService;
	}

	@Override
	public String getFileSystemName() {
		return FILESYSTEM_NAME;
	}

	@Override
	protected String doGetNodeName(AbstractTsDiskNode node) {
		if(node instanceof TsDiskRoot)
			return ((TsDiskRoot)node).getTeamSpace().getName();
		else
			return node.getName();
	}
	
	@Override
	protected void doRename(AbstractTsDiskNode node, String name) {
		if(node instanceof TsDiskRoot)
			((TsDiskRoot)node).getTeamSpace().setName(name);
		else
			node.setName(name);
	}

	@Override
	protected AbstractTsDiskNode instantiateFolder(String folder) {
		return new TsDiskFolder(folder);
	}

	@Override
	protected boolean isFolder(AbstractTsDiskNode node) {
		return node instanceof TsDiskFolder || node instanceof TsDiskRoot;
	}

	@Override
	public boolean hasContent(VFSLocation vfsLocation) {
		AbstractTsDiskNode node = getNodeByLocation(vfsLocation);
		
		checkRead(node);
		
		if(node instanceof TsDiskGeneralReference)
			return ((TsDiskGeneralReference)node).hasData();
		
		return false;
	}
	
	@Override
	public byte[] getContent(VFSLocation vfsLocation) {
		AbstractTsDiskNode node = getNodeByLocation(vfsLocation);
		if(null == node)
			return null;
		
		checkRead(node);
		
		if(node instanceof TsDiskGeneralReference)
			return ((TsDiskGeneralReference)node).getData();
		
		return null;
	}
	
	
	@Override
	public long getSize(VFSLocation location) {
		AbstractTsDiskNode node = getNodeByLocation(location);
		if(null == node)
			return 0;
		
		checkRead(node);
		
		if(node instanceof TsDiskGeneralReference)
			return ((TsDiskGeneralReference)node).getSize();
		
		return 0;
	}
	
	@Override
	public String getContentType(VFSLocation vfsLocation) {
		AbstractTsDiskNode node = getNodeByLocation(vfsLocation);
		if(null == node)
			return null;
		
		checkRead(node);
		
		if(node instanceof TsDiskGeneralReference)
			return ((TsDiskGeneralReference)node).getDataContentType();
		
		return null;
	}

	@Override
	protected boolean canDo(AbstractTsDiskNode node,
			Class<? extends Right>... rights) {
		if(null == node){
			return true;
		}
		
		TeamSpace ts = ((TsDiskService)treeDBManagerProvider.get()).getTeamSpaceFor(node);
		if(null == ts)
			return false;
		
		return teamSpaceService.get().isUser(ts);
	}
	
	@Override
	public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
		if(! vfsLocation.exists()){
			VFSLocation parentLoc = vfsLocation.getParentLocation();
			if(! parentLoc.exists())
				return false;
			
			AbstractTsDiskNode parent = getNodeByLocation(parentLoc);
			if(! isFolder(parent))
				return false;
			
			return canWrite(parent);
		} else {
			return false;
		}
	}
}
