package net.datenwerke.rs.terminal.service.terminal.vfs;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualContentProviderHook;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class VFSLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5754473159148141082L;
	
	public static final String LOCATION_ID_PREFIX = "id:";
	
	private String absolutePath = "/";
	private VirtualFileSystemManagerHook filesystemManager;

	private VirtualContentProviderHook virtualContentProvider;

	private boolean cannotExist = false;
	
	public VFSLocation(){
	}
	
	public VFSLocation(String path,
			VirtualFileSystemManagerHook vfsManager) {
		setAbsolutePath(path);
		setFilesystemManager(vfsManager);
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	
	public VirtualFileSystemManagerHook getFilesystemManager() {
		return filesystemManager;
	}
	
	public void setFilesystemManager(VirtualFileSystemManagerHook filesystemManager) {
		this.filesystemManager = filesystemManager;
	}
	
	public VirtualFileSystemDeamon getFileSystem() {
		return filesystemManager.getSession().getFileSystem();
	}
	
	public boolean isRoot(){
		return null == filesystemManager;
	}
	
	public boolean isFileSystemRoot() {
		return ! isRoot() && absolutePath.lastIndexOf('/') == 0;
	}
	
	public boolean isWildcardLocation(){
		return absolutePath.contains("*");
	}
	
	public boolean isVirtualLocation() {
		return null != getVirtualContentProvider();
	}
	
	public Collection<VFSLocation> resolveWildcards(VirtualFileSystemDeamon vfs) {
		if(! isWildcardLocation())
			return Collections.singleton(this);
		
		Collection<VFSLocation> locations = new HashSet<VFSLocation>();
		
		PathHelper pathHelper = getPathHelper();
		String lastWay = pathHelper.getLastPathway();
		
		StringBuilder regexBuilder = new StringBuilder().append("^");
		
		for(char c : lastWay.toCharArray()){
			if('*' == c)
				regexBuilder.append(".*");
			else
				regexBuilder.append(c);
		}
		
		String regex = regexBuilder.append("$").toString();
		
		VFSLocation parent = getParentLocation();
		VFSLocationInfo parentInfo = vfs.getLocationInfo(parent);
		
		for(VFSObjectInfo childInfo : parentInfo.getChildInfos()){
			if((null == childInfo.getName() && "".matches(regex)) || (null != childInfo.getName() && childInfo.getName().matches(regex))){
				locations.add(parent.newSubLocation(childInfo.getId(), true));
			}
		}
		
		return locations;
	}
	
	public String prettyPrint() {
		if(isRoot())
			return absolutePath;
		
		if(isFileSystemRoot())
			return absolutePath;
		
		StringBuilder prettyPrinter = new StringBuilder();
		if(isVirtualLocation()){
			VFSLocation base = getVirtualBaseLocation().getParentLocation();
			prettyPrinter.append(base.prettyPrint());
			prettyPrinter.append(getVirtualContentProvider().prettyPrint(getPathInVirtualSystem()));
		} else {
			prettyPrinter.append(absolutePath.substring(0,absolutePath.indexOf('/', 1) ));
			prettyPrinter.append(filesystemManager.prettyPrintPathway(getPathInFileSystem()));
		}
		return prettyPrinter.toString();
	}
	
	public String getPathInVirtualSystem() {
		return getPathHelper().getPathInVirtualSystem();
	}

	public String getPathInFileSystem() {
		if(isFileSystemRoot())
			return "";
		return absolutePath.substring(absolutePath.indexOf('/', 1)+1);
	}

	public PathHelper getPathHelper(){
		return new PathHelper(absolutePath);
	}

	public VFSLocation newSubLocation(Object pathway, boolean id) {
		return newSubLocation(String.valueOf(pathway),id);
	}
	
	public VFSLocation newSubLocation(String pathway, boolean id) {
		if(null == pathway)
			throw new NullPointerException("pathway should not be null");
		
		VFSLocation location = clone();
		
		location.setAbsolutePath(getAbsolutePath() + "/" + (! isRoot() && id ? LOCATION_ID_PREFIX : "") + pathway);
		
		return location;
	}
	
	public VFSLocation newNonExistingSubLocation(String pathway) {
		VFSLocation location = newSubLocation(pathway, false);
		location.cannotExist = true;
		
		return location;
	}

	

	public VFSLocation newVirtualSubLocation(VirtualContentProviderHook virtualContentProvider) {
		if(isVirtualLocation())
			throw new IllegalArgumentException("already virtual");
		
		VFSLocation location = clone();
		location.setAbsolutePath(getAbsolutePath() + "/#v-" + virtualContentProvider.getName());
		location.setVirtualContentProvider(virtualContentProvider);
		
		return location;
	}
	
	protected void setVirtualContentProvider(
			VirtualContentProviderHook virtualContentProvider) {
		this.virtualContentProvider = virtualContentProvider;
	}
	
	public VirtualContentProviderHook getVirtualContentProvider() {
		return virtualContentProvider;
	}
	

	public VFSLocation getVirtualBaseLocation() {
		if(! isVirtualLocation())
			throw new IllegalArgumentException("expected virtual");
		
		if(! getPathHelper().getLastPathHelperWay().isVirtualLocation())
			return getParentLocation().getVirtualBaseLocation();
		
		return this;
	}

	public VFSLocation getVirtualParentLocation() {
		return getVirtualBaseLocation().getParentLocation();
	}
	
	public VFSLocation getParentLocation() {
		if(isRoot() || isFileSystemRoot()){
			return new VFSLocation();
		}
		
		VFSLocation location = clone();
		if(isVirtualLocation() && getPathHelper().getLastPathHelperWay().isVirtualLocation())
			location.setVirtualContentProvider(null);
		location.setAbsolutePath(getAbsolutePath().substring(0, getAbsolutePath().lastIndexOf('/')));
		
		return location;
	}
	
	
	@Override
	protected VFSLocation clone() {
		VFSLocation clone = new VFSLocation();
		
		clone.setAbsolutePath(getAbsolutePath());
		clone.setFilesystemManager(getFilesystemManager());
		clone.setVirtualContentProvider(getVirtualContentProvider());
		
		return clone;
	}

	public boolean isFolder() {
		if(isRoot() || isFileSystemRoot())
			return true;
		if(isVirtualLocation())
			return getVirtualContentProvider().isFolder(this);
		return getFilesystemManager().isFolder(this);
	}

	public Object getChildObject(VFSObjectInfo childInfo) {
		if(isRoot())
			throw new IllegalStateException();
		
		VFSLocationInfo locationInfo = getFileSystem().getLocationInfo(this);
		if(! locationInfo.getChildInfos().contains(childInfo))
			throw new IllegalArgumentException();
		
		return getFilesystemManager().getObjectIn(this, childInfo);
	}
	
	public Object getObject() throws VFSException{
		if(isRoot())
			throw new IllegalStateException();
		if(isVirtualLocation())
			return getVirtualContentProvider().getObjectFor(this);
		
		return getFilesystemManager().getObjectFor(this);
	}
	
	@Override
	public int hashCode() {
		return absolutePath.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof VFSLocation))
			return false;
		return absolutePath.equals(((VFSLocation)obj).getAbsolutePath());
	}

	@Override
	public String toString() {
		return absolutePath;
	}

	public boolean hasChildren() {
		if(isRoot())
			return true;
		
		VFSLocationInfo locationInfo;
		if(isVirtualLocation())
			locationInfo = getVirtualContentProvider().getLocationInfo(this);
		else
			locationInfo = getFileSystem().getLocationInfo(this);
		
		return ! locationInfo.getChildInfos().isEmpty();
	}

	public boolean hasContent()throws VFSException{
		if(isRoot() || isFileSystemRoot())
			return false;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().hasContent(this);
		
		return getFilesystemManager().hasContent(this);
	}

	public byte[] getContent() throws VFSException{
		if(! exists() || isRoot() || isFileSystemRoot())
			return null;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().getContent(this);
		
		return getFilesystemManager().getContent(this);
	}
	
	public void setContent(byte[] content) throws VFSException{
		if(isRoot() || isFileSystemRoot())
			return;
		
		if(isVirtualLocation())
			getVirtualContentProvider().setContent(this, content);
		else
			getFilesystemManager().setContent(this, content);
	}
	
	public String getContentType() throws VFSException{
		if(isRoot() || isFileSystemRoot())
			return null;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().getContentType(this);
		
		return getFilesystemManager().getContentType(this);
	}

	public String getName() {
		if(isRoot())
			return absolutePath;
		if(isFileSystemRoot())
			return absolutePath.substring(1);
		
		if(isVirtualLocation())
			return getVirtualContentProvider().translatePathWay(this);
		
		return getFilesystemManager().translatePathWay(this);
	}

	public Date getLastModified() {
		if(! exists())
			return null;
		
		if(isRoot() || isFileSystemRoot())
			return null;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().getLastModified(this);
		
		return getFilesystemManager().getLastModified(this);
	}

	public boolean exists() {
		if(cannotExist )
			return false;
		
		if(isRoot() || isFileSystemRoot())
			return true;
		
		if(isWildcardLocation())
			return false;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().exists(this);
		
		return getFilesystemManager().exists(this);
	}
	

	public long getSize() {
		if(cannotExist || isRoot() || isFileSystemRoot())
			return 0;
		
		if(isWildcardLocation())
			return 0;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().getSize(this);
		
		return getFilesystemManager().getSize(this);
	}

	public boolean canWriteIntoLocation() {
		if(isRoot() || isFileSystemRoot())
			return false;
		
		if(isWildcardLocation())
			return false;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().canWriteIntoLocation(this);
		
		return getFilesystemManager().canWriteIntoLocation(this);
	}

	public boolean isLocationDeletable() {
		if(isRoot() || isFileSystemRoot())
			return false;
		
		if(isWildcardLocation())
			return false;
		
		if(isVirtualLocation())
			return getVirtualContentProvider().isLocationDeletable(this);
		
		return getFilesystemManager().isLocationDeletable(this);
	}

	public void delete() throws VFSException {
		if(! isLocationDeletable())
			throw new IllegalStateException();
		

		if(isVirtualLocation())
			getVirtualContentProvider().delete(this);
		else 
			getFilesystemManager().delete(this);
	}

	public VFSLocation rename(String name) {
		if(isVirtualLocation())
			throw new IllegalStateException("Cannot rename a virtual location");
		
		return getFilesystemManager().rename(this, name);
	}
	
	public boolean create() throws VFSException {
		if(exists() || isRoot() || isFileSystemRoot() || isWildcardLocation())
			return false;
		
		VFSLocation created = null;
		if(isVirtualLocation())
			created = getVirtualContentProvider().create(this);
		else
			created = getFilesystemManager().create(this);
		
		if(null != created) {
			cannotExist = false;
			setAbsolutePath(created.getAbsolutePath());
		}
		
		return null != created;
	}
	
	public boolean mkdir() throws VFSException {
		if(exists() || isRoot() || isFileSystemRoot() || isWildcardLocation() || isVirtualLocation())
			return false;
		
		VFSLocation created = getFilesystemManager().createFolder(this.getParentLocation(), getPathHelper().getLastPathway());
		
		if(null != created) {
			cannotExist = false;
			setAbsolutePath(created.getAbsolutePath());
		}
		
		return null != created;
	}
	

	public void writeIntoLocation(byte[] uploadData) throws VFSException {
		if(isRoot() || isFileSystemRoot())
			throw new VFSException("Cannot write to root");
		
		if(isWildcardLocation())
			throw new VFSException("Cannot write to wildcard location");
		
		if(isVirtualLocation())
			getVirtualContentProvider().writeIntoLocation(this, uploadData);
		else
			getFilesystemManager().writeIntoLocation(this, uploadData);
	}



}
