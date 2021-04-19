package net.datenwerke.rs.terminal.service.terminal.vfs.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.objresolver.hooks.ObjectResolverHook;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;

public class VfsObjectResolver implements ObjectResolverHook {

	@Override
	public boolean consumes(String locationStr, TerminalSession terminalSession) {
		try{
			PathHelper pathHelper = new PathHelper(locationStr);
			VFSLocation parentLoc = getParentLocation(pathHelper, terminalSession);
			if(parentLoc.isRoot()){
				if(! terminalSession.getFileSystem().isFileSystem(pathHelper.getLastPathway()))
					return false;
			}
			return true;
		} catch(VFSException e){
		}
		return false;
	}

	@Override
	public Collection<Object> getObjects(String locationStr, TerminalSession terminalSession) throws ObjectResolverException {
		try{
			PathHelper pathHelper = new PathHelper(locationStr);
			
			VFSLocation directLocation = getLocation(pathHelper, terminalSession);
			if(null != directLocation && ! directLocation.isWildcardLocation() && ! directLocation.isRoot())
				return Collections.singleton(directLocation.getObject());
			
			VFSLocation location = getParentLocation(pathHelper, terminalSession);
			VFSLocationInfo info = terminalSession.getFileSystem().getLocationInfo(location); 
				
			String lastWay = pathHelper.getLastPathway();
			
			return getObjects(location, info, lastWay);
		} catch(VFSException e){
		}
		
		return null;
	}

	private Collection<Object> getObjects(VFSLocation location, VFSLocationInfo info, String lastWay) {
		String regex = getRegex(lastWay);
		
		List<Object> objectList = new ArrayList<Object>();
		for(VFSObjectInfo objectInfo : info.getChildInfos())
			if(null != objectInfo.getName() && objectInfo.getName().matches(regex))
				objectList.add(location.getChildObject(objectInfo));
		
		return objectList;
	}

	private String getRegex(String lastWay) {
		StringBuilder regexBuilder = new StringBuilder().append("^");
		
		for(char c : lastWay.toCharArray()){
			if('*' == c)
				regexBuilder.append(".*");
			else
				regexBuilder.append(c);
		}
		
		return regexBuilder.append("$").toString();
	}

	private VFSLocation getParentLocation(PathHelper pathHelper,
			TerminalSession terminalSession) throws VFSException {
		return terminalSession.getFileSystem().getLocation(pathHelper.getPath());
	}

	private VFSLocation getLocation(PathHelper pathHelper,
			TerminalSession terminalSession) throws VFSException {
		return terminalSession.getFileSystem().getLocation(pathHelper.getCompletePath());
	}




}
