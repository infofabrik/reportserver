package net.datenwerke.rs.terminal.service.terminal.vfs.hooks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.LocationDoesNotExistException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.NodeDoesNotExistException;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public abstract class TreeBasedVirtualFileSystem<N extends AbstractNode<N>> extends VirtualFileSystemManagerHookImpl {

   /**
    * 
    */
   private static final long serialVersionUID = 6582949760239657132L;

   final protected Provider<? extends TreeDBManager<N>> treeDBManagerProvider;

   @Inject
   static protected Provider<SecurityService> securityServiceProvider;

   public TreeBasedVirtualFileSystem(Provider<? extends TreeDBManager<N>> treeDBManagerProvider) {

      /* store objects */
      this.treeDBManagerProvider = treeDBManagerProvider;
   }

   @Override
   public boolean handlesFilesystem(String filesystemName) {
      return getFileSystemName().equals(filesystemName);
   }

   @Override
   public VFSLocation getLocation(VFSLocation momentaryLocation, String pathway) throws VFSException {
      N currentNode = getNodeByLocation(momentaryLocation);

      List<N> possibleChildren;
      if (null == currentNode && momentaryLocation.isFileSystemRoot())
         possibleChildren = treeDBManagerProvider.get().getRoots();
      else if (null == currentNode)
         throw new LocationDoesNotExistException("could not find: " + pathway);
      else
         possibleChildren = (List<N>) currentNode.getChildren();

      String realPathway = pathway;
      int takeItemNr = 1;
      int seenItems = 0;
      if (pathway.matches("\\[\\d+\\]$")) {
         realPathway = pathway.substring(0, pathway.lastIndexOf('[', pathway.length() - 1));
         takeItemNr = Integer
               .parseInt(pathway.substring(pathway.lastIndexOf('[', pathway.length() - 1), pathway.length() - 1));
      }

      for (N child : possibleChildren) {
         if (pathwayMatchesObject(realPathway, child)) {
            seenItems++;
            if (seenItems == takeItemNr) {
               /* check rights */
               checkRead(child);

               return momentaryLocation.newSubLocation(child.getId(), true);
            }
         }
      }

      return momentaryLocation.newNonExistingSubLocation(pathway);
   }

   @Override
   public String getNameFor(VFSLocation location) {
      N node = getNodeByLocation(location);

      return getNodeName(node);
   }

   @Override
   public String translatePathWay(VFSLocation location) {
      return getNodeName((N) getObjectFor(location));
   }

   protected N getNodeByLocation(VFSLocation location) {
      if (!location.isFileSystemRoot()) {
         String idStr = location.getPathHelper().getLastPathway();
         if (!idStr.startsWith(VFSLocation.LOCATION_ID_PREFIX))
            return null;
         long id = Long.valueOf(idStr.substring(3));
         N node = treeDBManagerProvider.get().getNodeById(id);
         return node;
      } else if (!treeDBManagerProvider.get().allowsMultipleRoots()) {
         List<N> roots = treeDBManagerProvider.get().getRoots();
         if (null != roots && roots.size() > 0)
            return roots.get(0);
      }

      return null;
   }

   private N getCurrentNode() throws NodeDoesNotExistException {
      String currentId = terminalSession.getFileSystem().getCurrentObjectId();
      if (null == currentId)
         return null;

      Long id = Long.valueOf(currentId);
      return treeDBManagerProvider.get().getNodeById(id);
   }

   @Override
   public Long getObjectId(Object obj) throws VFSException {
      try {
         N node = (N) obj;
         return node.getId();
      } catch (ClassCastException e) {
         throw new NodeDoesNotExistException();
      }
   }

   @Override
   public Object getParent(VFSLocation location) throws VFSException {
      N currentNode = getNodeByLocation(location);

      /* check rights */
      canRead(currentNode);

      if (null == currentNode || currentNode.isRoot())
         return null;

      /* check rights */
      canRead(currentNode.getParent());

      return currentNode.getParent();
   }

   protected boolean pathwayMatchesObject(String pathway, N child) {
      if (pathway.startsWith(VFSLocation.LOCATION_ID_PREFIX)) {
         try {
            Long id = Long.valueOf(pathway.substring(3));
            return child.getId().equals(id);
         } catch (Exception e) {
         }
      } else {
         String name = getNodeName(child);
         name = null == name ? "" : name;
         if (name.equals(pathway))
            return true;
      }
      return false;
   }

   @Override
   public String prettyPrintPathway(String pathway) {
      StringBuilder prettyPrinter = new StringBuilder();

      for (String id : pathway.split("/")) {
         if (!id.startsWith(VFSLocation.LOCATION_ID_PREFIX))
            throw new IllegalArgumentException("Do not recognize pathway: " + pathway);
         N node = treeDBManagerProvider.get().getNodeById(Long.valueOf(id.substring(3)));
         prettyPrinter.append("/").append(getNodeName(node));
      }

      return prettyPrinter.toString();
   }

   @Override
   public Collection<VFSObjectInfo> getFileSystemObjectInfos() {
      VFSObjectInfo info = new VFSObjectInfo(VirtualFileSystemManagerHook.class, getFileSystemName(),
            getFileSystemName(), true);
      return Collections.singleton(info);
   }

   @Override
   public VFSLocationInfo getLocationInfo(VFSLocation location) {
      N currentNode = getNodeByLocation(location);

      /* check rights */
      checkRead(currentNode);

      VFSObjectInfo objectInfo = null;
      if (location.isFileSystemRoot() && treeDBManagerProvider.get().allowsMultipleRoots()) {
         objectInfo = getFilesystemRootInfo();
      } else {
         if (null == currentNode)
            return null;

         objectInfo = getObjectInfosFor(currentNode);
      }

      VFSLocationInfo info = new VFSLocationInfo(location, objectInfo);

      if (null == currentNode) {
         for (N child : treeDBManagerProvider.get().getRoots())
            if (canRead(child))
               info.addChildInfo(getObjectInfosFor(child));
      } else {
         List<N> children = (List<N>) currentNode.getChildrenSorted();
         for (N child : children) {
            if (canRead(child))
               info.addChildInfo(getObjectInfosFor(child));
         }
      }

      return info;
   }

   private VFSObjectInfo getFilesystemRootInfo() {
      if (treeDBManagerProvider.get().allowsMultipleRoots())
         return new VFSObjectInfo(getClass(), getFileSystemName(), "/" + getFileSystemName(), true);
      else {
         return getObjectInfosFor(treeDBManagerProvider.get().getRoots().get(0));
      }
   }

   protected VFSObjectInfo getObjectInfosFor(N node) {
      return new VFSObjectInfo(node.getClass(), getNodeName(node), String.valueOf(node.getId()), isFolder(node));
   }

   abstract protected boolean isFolder(N node);

   @Override
   public Object getObjectIn(VFSLocation vfsLocation, VFSObjectInfo objectInfo) {
      return treeDBManagerProvider.get().getNodeById(Long.valueOf(objectInfo.getId()));
   }

   @Override
   public Object getObjectFor(VFSLocation location) {
      return getNodeByLocation(location);
   }

   public boolean isFolder(VFSLocation location) {
      N node = getNodeByLocation(location);
      return doIsFolder(node);
   }

   protected boolean doIsFolder(N node) {
      return ((AbstractNode<?>) node).getSupportedChildren().length > 0;
   }

   @Override
   public boolean supportedByFileSystem(Object obj) {
      List<N> roots = treeDBManagerProvider.get().getRoots();
      if (null == roots || roots.isEmpty())
         return false;

      return roots.get(0).getBaseType().isAssignableFrom(obj.getClass());
   }

   @Override
   public VFSLocation getLocationFor(Object obj) {
      N node = (N) obj;

      /* check rights */
      checkRead(node);

      VFSLocation baseLocation = new VFSLocation("/" + getFileSystemName(), this);

      List<?> rootLine = node.getRootLine();
      Collections.reverse(rootLine);

      if (!treeDBManagerProvider.get().allowsMultipleRoots() && rootLine.size() > 0) {
         rootLine.remove(0);
      }

      for (Object parent : rootLine)
         baseLocation = baseLocation.newSubLocation(((N) parent).getId(), true);

      baseLocation = baseLocation.newSubLocation(node.getId(), true);

      return baseLocation;
   }

   @Override
   public VFSLocation doCreateFolder(VFSLocation location, String folder) {
      N parentFolder = getNodeByLocation(location);

      /* check rights */
      checkWrite(parentFolder);

      N folderNode = instantiateFolder(folder);
      if (null != parentFolder)
         parentFolder.addChild(folderNode);

      TreeDBManager<N> manager = treeDBManagerProvider.get();
      manager.persist(folderNode);

      if (null != parentFolder)
         manager.merge(parentFolder);

      return location.newSubLocation(folderNode.getId(), true);
   }

   @Override
   public List<VFSLocation> doMoveFilesTo(VFSLocation sources, VFSLocation target) {
      N newParent = getNodeByLocation(target);

      /* check rights */
      checkWrite(newParent);

      List<VFSLocation> movedFiles = new ArrayList<>();

      for (VFSLocation source : sources.resolveWildcards(terminalSession.getFileSystem())) {
         N sourceNode = getNodeByLocation(source);

         /* check rights */
         checkRead(sourceNode);

         treeDBManagerProvider.get().move(sourceNode, newParent);

         movedFiles.add(target.newSubLocation(sourceNode.getId(), true));
      }

      return movedFiles;
   }

   @Override
   public List<VFSLocation> doCopyFilesTo(VFSLocation sources, VFSLocation target, boolean deep) throws VFSException {
      N newParent = getNodeByLocation(target);

      /* check rights */
      checkWrite(newParent);

      List<VFSLocation> copiedFiles = new ArrayList<>();

      for (VFSLocation source : sources.resolveWildcards(terminalSession.getFileSystem())) {
         Object obj = source.getObject();
         if (!supportedByFileSystem(obj))
            throw new VFSException("do not recognize object");

         N sourceNode = (N) obj;

         /* check rights */
         checkRead(sourceNode);

         /* the copy method without checking rights is somewhat hidden... */
         SecuredTreeDBManagerImpl treeDBManager = (SecuredTreeDBManagerImpl) treeDBManagerProvider.get();
         SecuredAbstractNode copiedNode = treeDBManager.copy((SecuredAbstractNode) sourceNode,
               (SecuredAbstractNode) newParent, deep, false);

         copiedFiles.add(target.newSubLocation(copiedNode.getId(), true));
      }

      return copiedFiles;
   }

   @Override
   public void remove(VFSLocation location, boolean force) {
      N node = getNodeByLocation(location);

      /* check rights */
      checkDelete(node);

      if (force)
         treeDBManagerProvider.get().forceRemove(node);
      else
         treeDBManagerProvider.get().remove(node);
   }

   @Override
   public VFSLocation rename(VFSLocation location, String name) {
      N node = getNodeByLocation(location);

      /* check rights */
      checkWrite(node);

      doRename(node, name);

      return location.getParentLocation().newSubLocation(node.getId(), true);
   }

   abstract protected void doRename(N node, String name);

   @Override
   public Provider<? extends TreeDBManager> getTreeDBManagerProvider() {
      return treeDBManagerProvider;
   }

   @Override
   public boolean isObjectAncestorOf(Object object, VFSLocation baseLocation) {
      if (baseLocation.isWildcardLocation())
         throw new IllegalArgumentException("Cannot be determined for wildcard location");
      N base = getNodeByLocation(baseLocation);

      if (!supportedByFileSystem(object))
         throw new IllegalArgumentException("Object " + object.getClass() + " not supported");

      return base.isAncestorOf((N) object);
   }

   protected String getNodeName(N node) {
      if (node instanceof HibernateProxy)
         node = (N) ((HibernateProxy) node).getHibernateLazyInitializer().getImplementation();

      return doGetNodeName(node);
   }

   @Override
   public Date getLastModified(VFSLocation location) {
      N node = getNodeByLocation(location);
      if (null == node)
         return null;

      return node.getLastUpdated();
   }

   @Override
   public boolean exists(VFSLocation location) {
      return null != getNodeByLocation(location);
   }

   protected boolean canDo(N node, Class<? extends Right>... rights) {
      if (terminalSession.isCheckRights() && node instanceof SecurityTarget
            && !securityServiceProvider.get().checkRights((SecurityTarget) node, rights))
         return false;
      return true;
   }

   protected boolean canRead(N node) {
      return canDo(node, Read.class);
   }

   protected void checkRead(N node) {
      if (!canRead(node))
         throw new ViolatedSecurityException(node, Read.class);
   }

   protected boolean canDelete(N node) {
      return canDo(node, Delete.class);
   }

   protected void checkDelete(N node) {
      if (!canDelete(node))
         throw new ViolatedSecurityException(node, Delete.class);
   }

   protected boolean canWrite(N node) {
      return canDo(node, Write.class);
   }

   protected void checkWrite(N node) {
      if (!canWrite(node))
         throw new ViolatedSecurityException(node, Write.class);
   }

   protected abstract N instantiateFolder(String folder);

   protected abstract String doGetNodeName(N node);

}
