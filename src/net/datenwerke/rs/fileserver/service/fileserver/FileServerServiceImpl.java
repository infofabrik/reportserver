package net.datenwerke.rs.fileserver.service.fileserver;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;

import groovy.lang.Closure;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode__;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile__;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.vfs.FileServerVfs;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;
import net.datenwerke.rs.utils.misc.MimeUtils;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;
import net.datenwerke.security.service.treedb.actions.InsertAction;

public class FileServerServiceImpl extends SecuredTreeDBManagerImpl<AbstractFileServerNode>
      implements FileServerService {
   

   @Inject
   private TerminalService terminalService;

   @Inject
   private SecurityService securityService;
   
   @Inject
   private MimeUtils mimeUtils;

   @Override
   @QueryByAttribute(where = AbstractFileServerNode__.parent, type = PredicateType.IS_NULL)
   public List<AbstractFileServerNode> getRoots() {
      return null; // magic
   }

   @Override
   @SimpleQuery
   public List<AbstractFileServerNode> getAllNodes() {
      return null;
   }

   @Override
   @QueryById
   public AbstractFileServerNode getNodeById(long id) {
      return null; // magic
   }

   @Override
   public AbstractFileServerNode getNodeByPath(String path) {
      return getNodeByPath(path, true);
   }

   @Override
   public AbstractFileServerNode getNodeByPath(String path, boolean checkRights) {
      if (path.startsWith("/"))
         path = "/" + FileServerVfs.FILESYSTEM_NAME + path;
      else
         path = "/" + FileServerVfs.FILESYSTEM_NAME + "/" + path;

      try {
         Object object = terminalService.getObjectByQuery(path, checkRights);
         if (object instanceof AbstractFileServerNode)
            return (AbstractFileServerNode) object;
         return null;
      } catch (ObjectResolverException e) {
         return null;
      }
   }
   @Override
   protected AbstractFileServerNode copy(AbstractFileServerNode source, AbstractFileServerNode target) {
      /* reuse existing folders */
      if (source instanceof FileServerFolder) {
         for (FileServerFolder childfolder : target.getChildrenOfType(FileServerFolder.class)) {
            if (source.getName().equals(childfolder.getName()))
               return childfolder;
         }
      }

      AbstractFileServerNode cloned = cloneNode(source);
      afterNodeCopy(cloned, target);
      target.addChild(cloned);
      persist(cloned);
      merge(target);

      return cloned;
   }

   @Override
   public FileServerFile createFileAtLocation(VFSLocation location) {
      return createFileAtLocation(location, true);
   }
   
   @Override
   public FileServerFolder createFolderAtLocation(VFSLocation location) {
      return createFolderAtLocation(location, true);
   }

   @Override
   public FileServerFile createFileAtLocation(VFSLocation location, boolean checkRights) {
      return createFileAtLocation(location.getAbsolutePath(), checkRights);
   }
   
   @Override
   public FileServerFolder createFolderAtLocation(VFSLocation location, boolean checkRights) {
      return createFolderAtLocation(location.getAbsolutePath(), checkRights);
   }

   @Override
   public FileServerFile createFileAtLocation(String locationPath) {
      return createFileAtLocation(locationPath, true);
   }
   
   @Override
   public FileServerFolder createFolderAtLocation(String locationPath) {
      return createFolderAtLocation(locationPath, true);
   }

   @Override
   public FileServerFolder createFolderAtLocation(String locationPath, boolean checkRights) {
      return (FileServerFolder) doCreateNodeAtLocation(locationPath, checkRights, true);
   }
   
   private AbstractFileServerNode doCreateNodeAtLocation(String locationPath, boolean checkRights, boolean isFolder) {
      PathHelper helper = new PathHelper(locationPath);

      Iterator<String> paths = helper.getPathways().iterator();

      Object root = getRoots().get(0);
      if (root instanceof HibernateProxy)
         root = ((HibernateProxy) root).getHibernateLazyInitializer().getImplementation();

      FileServerFolder parent = (FileServerFolder) root;

      String currentPath = "";
      while (paths.hasNext()) {
         String path = paths.next();
         currentPath += "/" + path;

         if (paths.hasNext()) {
            FileServerFolder newParent = null;
            try {
               newParent = (FileServerFolder) terminalService.getObjectByQuery(currentPath);
            } catch (ObjectResolverException e) {
            }

            if (null == newParent) {
               if (checkRights && !securityService.checkActions(parent, InsertAction.class))
                  throw new ViolatedSecurityException("Cannot create node at: " + parent);

               newParent = new FileServerFolder();
               newParent.setName(path);
               if (null != parent)
                  parent.addChild(newParent);
               persist(newParent);
               merge(parent);
            }

            parent = newParent;

         } else {
            Object obj = null;
            try {
               obj = terminalService.getObjectByQuery(currentPath);
            } catch (ObjectResolverException e) {
            }

            if (isFolder) {
               if (null != obj && !(obj instanceof FileServerFolder))
                  throw new IllegalArgumentException("Expected null or folder");
               if (null != obj && (obj instanceof FileServerFolder))
                  return (FileServerFolder) obj;
            } else {
               if (null != obj && !(obj instanceof FileServerFile))
                  throw new IllegalArgumentException("Expected null or file");
               if (null != obj && (obj instanceof FileServerFile))
                  return (FileServerFile) obj;
            }

            if (checkRights && !securityService.checkActions(parent, InsertAction.class))
               throw new ViolatedSecurityException("Cannot create node at: " + parent);

            AbstractFileServerNode node = null;
            if (isFolder) {
               FileServerFolder folder = new FileServerFolder();
               folder.setName(path);
               node = folder;
            } else {
               FileServerFile file = new FileServerFile();
               file.setName(path);
               node = file;
            }
            parent.addChild(node);
            persist(node);
            merge(parent);
            return node;
         }
      }
      return null;
   }
   
   @Override
   public FileServerFile createFileAtLocation(String locationPath, boolean checkRights) {
      return (FileServerFile) doCreateNodeAtLocation(locationPath, checkRights, false);
   }

   @Override
   public FileServerFile getFileFromUploadFile(FileToUpload uFile) {
      int b64idx = uFile.getB64Data().indexOf("base64,");

      String strData = uFile.getB64Data().substring(b64idx + 7);
      String mimeType = (b64idx < 6) ? "" : uFile.getB64Data().substring(5, b64idx - 1);

      if (null == mimeType || mimeType.isEmpty() || "application/octet-stream".equals(mimeType)) {
         mimeType = mimeUtils.getMimeTypeByExtension(uFile.getName());
      }

      byte[] data = Base64.decodeBase64(strData.getBytes());

      FileServerFile file = new FileServerFile();
      file.setName(uFile.getName());
      file.setContentType(mimeType);
      file.setData(data);

      return file;
   }

   @Override
   @QueryByAttribute(
         select = FileServerFile__.id, 
         from = FileServerFile.class, 
         where = FileServerFile__.key, 
         throwNoResultException = true
   )
   public long getFileIdFromKey(String key) {
      return -1; // magic
   }
   
   @Override
   @QueryByAttribute(
         where = FileServerFile__.key
   )
   public FileServerFile getFileByKey(String key) {
      return null; // by magic
   }
   
   @Override
   protected void afterNodeCopy(AbstractFileServerNode copiedNode, AbstractFileServerNode parent) {
      if (copiedNode instanceof FileServerFile) {
         FileServerFile clone = (FileServerFile) copiedNode;

         Closure getAllNodes = new Closure(null) {
            public List<AbstractFileServerNode> doCall() {
               return parent.getChildren();
            }
         };
         clone.setName(clone.getName() == null
               ? keyNameGeneratorService.getNextCopyNameFileServerFile("", getAllNodes)
               : keyNameGeneratorService.getNextCopyNameFileServerFile(clone.getName(), getAllNodes));
         clone.setKey(keyNameGeneratorService.getNextCopyKey(clone.getKey(), this));
      }
   }

   @Override
   public AbstractFileServerNode getNodeByKey(String key) {
      return getFileByKey(key);
   }
}
