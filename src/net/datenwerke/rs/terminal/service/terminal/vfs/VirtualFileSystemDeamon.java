package net.datenwerke.rs.terminal.service.terminal.vfs;

import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser.CurrentArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionDeamonHook;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.LocationDoesNotExistException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.LocationIsNoFolderException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualContentProviderHook;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class VirtualFileSystemDeamon implements TerminalSessionDeamonHook {

	protected static final Logger logger = LoggerFactory.getLogger(VirtualFileSystemDeamon.class.getName());
	
	private final HookHandlerService hookHandler;
	
	private List<VirtualFileSystemManagerHook> fileSystemManagers;
	private TerminalSession terminalSession;
	
	private VFSLocation currentLocation = new VFSLocation();


	@Inject
	public VirtualFileSystemDeamon(
		HookHandlerService hookHandler
		){
		
		/* store objects */
		this.hookHandler = hookHandler;
	}
	
	@Override
	public void init(TerminalSession terminalSession) {
		this.terminalSession = terminalSession;
		
		/* load managers */
		fileSystemManagers = hookHandler.getHookers(VirtualFileSystemManagerHook.class);
		for(VirtualFileSystemManagerHook vfsManager : fileSystemManagers)
			vfsManager.init(terminalSession);
	}

	public String getCurrentPath() {
		if(inRoot())
			return "/";
		return currentLocation.getAbsolutePath();
	}

	public void cd(String path) throws VFSException {
		VFSLocation newLocation = getLocation(path);
		if(!newLocation.exists())
			throw new LocationDoesNotExistException("No such location: " + newLocation.getAbsolutePath());
		
		if(! newLocation.isFolder())
			if ( ! ( newLocation.getObject() instanceof Report && ! (newLocation.getObject() instanceof ReportVariant) ) )
				throw new LocationIsNoFolderException();
		
		currentLocation = newLocation;
	}
	
	public void addFilesystemManager(VirtualFileSystemManagerHook manager){
		this.fileSystemManagers.add(manager);
	}
	
	public VFSLocation getLocation(String pathRaw) throws VFSException{
		PathHelper path = new PathHelper(pathRaw);
		
		if(! path.isValid())
			throw new LocationDoesNotExistException("empty path");

		if(path.isAbsolute())
			return getAbsoluteLocation(path);
		return getRelativeLocation(path);
	}
	
	public void setLocation(VFSLocation location){
		currentLocation = location;
	}

	private VFSLocation getRelativeLocation(PathHelper path) throws VFSException {
		VFSLocation momentaryLocation = currentLocation;
		
		for(String pathway : path.getPathways())
			momentaryLocation = getLocation(momentaryLocation, pathway);
		
		return momentaryLocation;
	}

	private VFSLocation getAbsoluteLocation(PathHelper path) throws VFSException {
		VFSLocation momentaryLocation = new VFSLocation();
		
		for(String pathway : path.getPathways()){
			if(momentaryLocation.isWildcardLocation())
				throw new IllegalArgumentException("Can only use wildcards in file selector");
			if(! "".equals(pathway))
				momentaryLocation = getLocation(momentaryLocation, pathway);
		}
		
		return momentaryLocation;
	}

	public VFSLocation getLocation(VFSLocation momentaryLocation, String pathway) throws VFSException {
		PathHelper pathHelper = new PathHelper(pathway);
		
		if(pathHelper.isDot())
			return momentaryLocation;
		if(pathHelper.isDotDot())
			return momentaryLocation.getParentLocation();
		if(pathHelper.isWildcard())
			return momentaryLocation.newSubLocation(pathway, false);
		
		/* we need to check if we are in a virtual location */
		if(momentaryLocation.isVirtualLocation()){
			for(VirtualContentProviderHook contentProvider : hookHandler.getHookers(VirtualContentProviderHook.class))
				if(contentProvider.consumes(momentaryLocation))
					return contentProvider.getLocation(momentaryLocation,pathHelper);
			throw new LocationDoesNotExistException();
		}
			
		/* going into a virtual direction */
		if(pathHelper.isVirtualLocation()){
			for(VirtualContentProviderHook contentProvider : hookHandler.getHookers(VirtualContentProviderHook.class))
				if(contentProvider.consumes(momentaryLocation, pathHelper))
					return contentProvider.getLocation(momentaryLocation,pathHelper);
			throw new LocationDoesNotExistException();
		} 
		
		/* clear any virtuality (##v- -> #v) from pathway */
		pathway = pathHelper.clearEscapedVirtualLocation();
		
		if(momentaryLocation.isRoot())
			return getFilesystemRoot(pathway);
		else 
			return momentaryLocation.getFilesystemManager().getLocation(momentaryLocation, pathway);
	}

	public VFSLocation getFilesystemRoot(String filesystemName) throws VFSException {
		if(null == filesystemName || "".equals(filesystemName))
			throw new LocationDoesNotExistException("empty filesystem");
		
		for(VirtualFileSystemManagerHook vfsManager : fileSystemManagers){
			if(vfsManager.handlesFilesystem(filesystemName)){
				return new VFSLocation("/" + filesystemName, vfsManager);
			}
		}
		
		throw new LocationDoesNotExistException("filesystem not found: " + filesystemName);
	}

	public boolean inRoot(){
		return currentLocation.isRoot();
	}

	public boolean inFileSystemRoot(){
		return ! inRoot() && currentLocation.isFileSystemRoot();
	}
	
	public VFSLocation getCurrentLocation(){
		return currentLocation;
	}
	
	public VirtualFileSystemManagerHook getCurrentFileSystem(){
		return currentLocation.getFilesystemManager();
	}

	public String prettyPrintCurrentPath() {
		return currentLocation.prettyPrint();
	}

	public VFSLocationInfo getLocationInfo(VFSLocation location) {
		if(location.isWildcardLocation())
			throw new IllegalArgumentException("Cannot get info for wildcard location");
		else if(location.isRoot()){
			VFSObjectInfo rootInfo = getRootObjectInfo();
			VFSLocationInfo info = new VFSLocationInfo(location, rootInfo);
			
			for(VirtualFileSystemManagerHook vfsManager : fileSystemManagers)
				info.addChildInfos(vfsManager.getFileSystemObjectInfos());
			
			return info;
		} else if(location.isVirtualLocation()) 
			return location.getVirtualContentProvider().getLocationInfo(location);
		else {
			VFSLocationInfo info = location.getFilesystemManager().getLocationInfo(location);
			for(VirtualContentProviderHook contentProvider : hookHandler.getHookers(VirtualContentProviderHook.class))
				try{
					if(contentProvider.enhanceNonVirtual(location))
						info.addChildInfo(new VFSObjectInfo(contentProvider.getClass(), "#v-" + contentProvider.getName(), "#v-" + contentProvider.getName(), true));
				} catch (VFSException e) {
					logger.warn( e.getMessage(), e);
					throw new IllegalStateException(e);
				}
			return info;
		}
	}

	private VFSObjectInfo getRootObjectInfo() {
		VFSObjectInfo info = new VFSObjectInfo(VirtualFileSystemDeamon.class, "ROOT", "ROOT", true);
		return info;
	}

	public String getCurrentObjectId() {
		if(currentLocation.isRoot())
			return null;
		
		return currentLocation.getPathHelper().getLastPathway();
	}

	/**
	 * add autocomplete entries for any command that needs it
	 * 
	 * @param autocompleteHelper
	 */
	public void autocomplete(AutocompleteHelper autocompleteHelper) {
		CommandParser parser = autocompleteHelper.getParser();
		CurrentArgument cArg = parser.getCurrentArgument(autocompleteHelper.getCursorPosition());
		if(null != cArg && cArg.getArgumentNr() > 1){
			PathHelper pathHelper = new PathHelper(cArg.getArgument());
			String path = pathHelper.getPath();
			String lastPathway = pathHelper.getLastPathway();
			try {
				if((null == path || "".equals(path.trim())) && (null == lastPathway || "".equals(lastPathway.trim())) && "".equals(parser.getBaseCommand())){
					return;
				}
				
				VFSLocation location = getLocation(path);
				VFSLocationInfo info = getLocationInfo(location);
				if(null == info)
					return;
				
				for(VFSObjectInfo child : info.getChildInfos()){
					if(null == lastPathway || "".equals(lastPathway) || (null != child.getName() && child.getName().startsWith(lastPathway))){
						String pathSuffix = child.getName() + (child.isFolder() ? "/" : "");
						if(path.endsWith("/" ) || "".equals(path))
							autocompleteHelper.addObjectResultEntry(path + pathSuffix);
						else
							autocompleteHelper.addObjectResultEntry(path + "/" + pathSuffix);
					}
				}
			} catch (VFSException e) {
			}
		}
	}

	public VFSLocation getLocationFor(Object obj) {
		for(VirtualFileSystemManagerHook manager : fileSystemManagers){
			if(manager.supportedByFileSystem(obj)){
				return manager.getLocationFor(obj);
			}
		}
		
		return null;
	}

	public VFSLocation getFileSystemRoot(Class<? extends VirtualFileSystemManagerHook> type) {
		for(VirtualFileSystemManagerHook manager : fileSystemManagers){
			if(type.isAssignableFrom(manager.getClass())){
				VFSLocation baseLocation = new VFSLocation("/" + manager.getFileSystemName(), manager);
				return baseLocation;
			}
		}
		
		return null;
	}
	
	public VFSLocation createFolderIn(VFSLocation location, String folder) throws VFSException {
		if(location.isRoot())
			throw new VFSException("cannot create folder in root");
		
		return location.getFilesystemManager().createFolder(location, folder);
	}

	public void remove(VFSLocation location, boolean recursive, boolean force) throws VFSException{
		if(location.isRoot() || location.isFileSystemRoot())
			throw new VFSException("Cannot delete root or filesystem root");
		
		if(location.hasChildren() && ! recursive)
			throw new VFSException(location.prettyPrint() + " has children");
		
		location.getFilesystemManager().remove(location, force);
	}

	public boolean isValidMountPoint(String mountPoint) {
		if(null == mountPoint)
			return false;
		
		if(! mountPoint.matches("^[a-zA-Z0-9]*$"))
			return false;
		
		return ! isMountPointTaken(mountPoint);
	}
	
	public boolean isMountPointTaken(String mountPoint) {
		if(null == mountPoint)
			return true;
		
		for(VirtualFileSystemManagerHook manager : fileSystemManagers)
			if(mountPoint.equals(manager.getFileSystemName()))
				return true;
		
		return false;
	}

	public boolean isObjectAncestorOf(Object object, VFSLocation baseLocation) {
		return baseLocation.getFilesystemManager().isObjectAncestorOf(object, baseLocation);
	}

	public boolean isFileSystem(String locationStr) {
		if(null == locationStr)
			return false;
		for(VirtualFileSystemManagerHook vfs : fileSystemManagers)
			if(locationStr.equals(vfs.getFileSystemName()))
				return true;
		return false;
	}





}
