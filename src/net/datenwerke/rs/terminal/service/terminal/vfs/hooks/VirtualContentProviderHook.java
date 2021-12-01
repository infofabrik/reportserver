package net.datenwerke.rs.terminal.service.terminal.vfs.hooks;

import java.util.Date;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;

public interface VirtualContentProviderHook extends Hook {

	boolean consumes(VFSLocation momentaryLocation) throws VFSException;
	
	boolean consumes(VFSLocation momentaryLocation, PathHelper path) throws VFSException;

	VFSLocation getLocation(VFSLocation momentaryLocation, PathHelper path)throws VFSException;

	String getName();

	VFSLocationInfo getLocationInfo(VFSLocation vfsLocation);

	boolean isFolder(VFSLocation vfsLocation);

	String prettyPrint(String pathInVirtualSystem);

	boolean hasContent(VFSLocation vfsLocation) throws VFSException;

	byte[] getContent(VFSLocation vfsLocation) throws VFSException;

	void setContent(VFSLocation vfsLocation, byte[] content)  throws VFSException;

	String getContentType(VFSLocation vfsLocation)  throws VFSException;

	String translatePathWay(VFSLocation location);

	public boolean enhanceNonVirtual(VFSLocation location) throws VFSException;

	Date getLastModified(VFSLocation vfsLocation);

	boolean exists(VFSLocation vfsLocation);

	long getSize(VFSLocation vfsLocation);

	boolean canWriteIntoLocation(VFSLocation vfsLocation);

	boolean isLocationDeletable(VFSLocation vfsLocation);

	void delete(VFSLocation vfsLocation) throws VFSException;

	VFSLocation create(VFSLocation vfsLocation) throws VFSException;

	void writeIntoLocation(VFSLocation vfsLocation, byte[] uploadData) throws VFSException;

	Object getObjectFor(VFSLocation vfsLocation) throws VFSException;


}
