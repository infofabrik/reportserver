package net.datenwerke.rs.terminal.service.terminal.vfs.hooks;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface VirtualFileSystemManagerHook extends Hook, Serializable {

   void init(TerminalSession terminalSession);

   TerminalSession getSession();

   public String getFileSystemName();

   boolean handlesFilesystem(String filesystemName) throws VFSException;

   Object getObjectId(Object obj) throws VFSException;

   String prettyPrintPathway(String pathway);

   VFSLocation getLocation(VFSLocation momentaryLocation, String pathway) throws VFSException;

   Object getParent(VFSLocation location) throws VFSException;

   Collection<VFSObjectInfo> getFileSystemObjectInfos();

   /**
    * Should not be called directly
    * 
    * @param location
    */
   VFSLocationInfo getLocationInfo(VFSLocation location);

   boolean isFolder(VFSLocation vfsLocation);

   Object getObjectIn(VFSLocation vfsLocation, VFSObjectInfo objectInfo);

   boolean supportedByFileSystem(Object obj);

   VFSLocation getLocationFor(Object obj);

   VFSLocation createFolder(VFSLocation location, String folder);

   List<VFSLocation> moveFilesTo(VFSLocation source, VFSLocation target);

   List<VFSLocation> copyFilesTo(VFSLocation source, VFSLocation target, boolean deep) throws VFSException;

   void remove(VFSLocation location, boolean force);

   Provider<? extends TreeDBManager> getTreeDBManagerProvider();

   void setReadOnly(boolean readOnly);

   boolean isReadOnly();

   boolean isObjectAncestorOf(Object object, VFSLocation baseLocation);

   Object getObjectFor(VFSLocation vfsLocation);

   boolean hasContent(VFSLocation vfsLocation);

   byte[] getContent(VFSLocation vfsLocation);

   void setContent(VFSLocation vfsLocation, byte[] content);

   String getContentType(VFSLocation vfsLocation);

   String translatePathWay(VFSLocation location);

   Date getLastModified(VFSLocation vfsLocation);

   boolean exists(VFSLocation vfsLocation);

   long getSize(VFSLocation vfsLocation);

   boolean canWriteIntoLocation(VFSLocation vfsLocation);

   boolean isLocationDeletable(VFSLocation vfsLocation);

   void delete(VFSLocation vfsLocation);

   VFSLocation create(VFSLocation vfsLocation) throws VFSException;

   void writeIntoLocation(VFSLocation vfsLocation, byte[] uploadData) throws VFSException;

   VFSLocation rename(VFSLocation vfsLocation, String name);

   String getNameFor(VFSLocation loc);
   
   AbstractNode<?> getNodeByLocation(VFSLocation location);
}
