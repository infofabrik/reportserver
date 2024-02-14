package net.datenwerke.rs.terminal.service.terminal.vfs.hooks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;

import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public abstract class VirtualFileSystemManagerHookImpl implements VirtualFileSystemManagerHook {

   /**
    * 
    */
   private static final long serialVersionUID = -2358496393323557347L;

   protected TerminalSession terminalSession;

   protected boolean readOnly;

   @Inject
   private KeyNameGeneratorService keyGeneratorService;
  
   @Override
   public void init(TerminalSession terminalSession) {
      this.terminalSession = terminalSession;
   }

   @Override
   public TerminalSession getSession() {
      return terminalSession;
   }

   @Override
   public void setReadOnly(boolean readOnly) {
      this.readOnly = readOnly;
   }

   @Override
   public boolean isReadOnly() {
      return readOnly;
   }

   @Override
   public final VFSLocation createFolder(VFSLocation location, String folder) {
      if (isReadOnly())
         throw new IllegalStateException("filesystem is read only");
      return doCreateFolder(location, folder);
   }

   protected abstract VFSLocation doCreateFolder(VFSLocation location, String folder);

   @Override
   public final List<VFSLocation> moveFilesTo(VFSLocation sources, VFSLocation target) {
      if (isReadOnly())
         throw new IllegalStateException("filesystem is read only");
      return doMoveFilesTo(sources, target);
   }

   protected abstract List<VFSLocation> doMoveFilesTo(VFSLocation sources, VFSLocation target);

   @Override
   public List<VFSLocation> copyFilesTo(VFSLocation sources, VFSLocation target, boolean deep) throws VFSException {
      return copyFilesTo(sources, target, deep, false);
   }
   
   @Override
   public List<VFSLocation> copyFilesTo(VFSLocation sources, VFSLocation target, boolean deep, boolean assignDefaultKeys) throws VFSException {
      if (isReadOnly())
         throw new IllegalStateException("filesystem is read only");
       List<VFSLocation> doCopyFilesTo = doCopyFilesTo(sources, target, deep);
       if(assignDefaultKeys)
          assignDefaultKeys(sources, doCopyFilesTo);
       return doCopyFilesTo;
   }
   
   private void assignDefaultKeys(VFSLocation srcLocation, List<VFSLocation> copiedNodes) {
      /* Gather all nodes which could have been copied */
      AbstractNode<?> sourceNode = srcLocation.getFilesystemManager().getNodeByLocation(srcLocation);
      List<AbstractNode<?>> sourceNodes = (List<AbstractNode<?>>) sourceNode.getDescendants();
      sourceNodes.add(sourceNode);
      /* Gather keys of sourceNodes */
      List<String> sourceKeys = new ArrayList<String>();
      for(AbstractNode<?> node : sourceNodes) {
         try {
            String key = (String) node.getClass().getMethod("getKey").invoke(node);
            sourceKeys.add(key);
         } catch (Exception e) {
            // TODO: handle exception
         }
      }
      
      for (VFSLocation loc : copiedNodes) {
         AbstractNode<?> nodeByLocation = loc.getFilesystemManager().getNodeByLocation(loc);
         List<AbstractNode<?>> allNodes = (List<AbstractNode<?>>) nodeByLocation.getDescendants();
         allNodes.add(nodeByLocation);
         for (AbstractNode<?> node : allNodes) {    
            try {
               /* check if a certain node was contained in sourceNodes based on key */
               Method getKey = node.getClass().getMethod("getKey");
               String copyKey = (String) getKey.invoke(node);
               Optional<String> sourceKey = sourceKeys.stream().filter(key -> copyKey.startsWith(key)).findAny();
               if(!sourceKey.isPresent()) {
                  continue;
               }
               /* assign new key */
               TreeDBManager treeDBManager = loc.getFilesystemManager().getTreeDBManagerProvider().get();
               Method setKey = node.getClass().getMethod("setKey", String.class);
               setKey.invoke(node, keyGeneratorService.generateDefaultKey(treeDBManager));
               treeDBManager.merge(node);
            } catch (NoSuchMethodException | SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
               continue;
            }
         }
      }
   }

   protected abstract List<VFSLocation> doCopyFilesTo(VFSLocation sources, VFSLocation target, boolean deep)
         throws VFSException;

   public boolean hasContent(VFSLocation vfsLocation) {
      return false;
   }

   public byte[] getContent(VFSLocation vfsLocation) {
      return null;
   }

   public void setContent(VFSLocation vfsLocation, byte[] content) {
   }

   public String getContentType(VFSLocation vfsLocation) {
      return null;
   }

   @Override
   public long getSize(VFSLocation vfsLocation) {
      return 0;
   }

   @Override
   public boolean canWriteIntoLocation(VFSLocation vfsLocation) {
      return false;
   }

   @Override
   public boolean isLocationDeletable(VFSLocation vfsLocation) {
      return false;
   }

   @Override
   public void delete(VFSLocation vfsLocation) {

   }

   @Override
   public VFSLocation create(VFSLocation vfsLocation) throws VFSException {
      return null;
   }

   @Override
   public void writeIntoLocation(VFSLocation vfsLocation, byte[] uploadData) throws VFSException {
      throw new VFSException("Cannot write into: " + getFileSystemName());
   }

}
