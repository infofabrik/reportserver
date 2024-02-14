package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip;

import java.util.LinkedList;
import java.util.Queue;

import javax.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.utils.misc.MimeUtils;

public class UnzipCommandHelper {

   private FileServerService fileServerService;
   private MimeUtils mimeUtils;
   @Inject
   private KeyNameGeneratorService keyGeneratorService;

   @Inject
   public UnzipCommandHelper(FileServerService fileServerService, MimeUtils mimeUtils) {
      this.fileServerService = fileServerService;
      this.mimeUtils = mimeUtils;
   }

   public AbstractFileServerNode createFileAndFolders(FileServerFolder base, String filename, boolean isDirectory) {
      String[] pathComponents = filename.split("/");
      Queue<String> q = new LinkedList<String>();
      for (String s : pathComponents) {
         if (null != s && !s.isEmpty())
            q.add(s);
      }

      return createFileAndFolders(base, q, isDirectory);
   }

   private AbstractFileServerNode createFileAndFolders(FileServerFolder base, Queue<String> filename,
         boolean isDirectory) {

      if (filename.size() > 1) {
         /* Check if folder exists */
         for (FileServerFolder f : base.getChildrenOfType(FileServerFolder.class)) {
            if (null != f.getName() && f.getName().equals(filename.peek())) {
               filename.poll();
               return createFileAndFolders(f, filename, isDirectory);
            }
         }
         /* create folder */
         FileServerFolder fsf = new FileServerFolder(filename.poll());
         fsf.setParent(base);
         fileServerService.persist(fsf);
         base.addChild(fsf);
         fileServerService.merge(base);

         return createFileAndFolders(fsf, filename, isDirectory);
      } else {
         AbstractFileServerNode file;
         if (isDirectory) {
            /* check if folder already exists, if so return folder */
            FileServerFolder existingFolder = base.getSubfolderByName(filename.peek());
            if (null != existingFolder)
               return existingFolder;

            /* folder does not exist .. create */
            file = new FileServerFolder();
            ((FileServerFolder) file).setName(filename.poll());
         } else {
            FileServerFile fsFile = new FileServerFile();

            fsFile.setName(filename.poll());
            String contentType = mimeUtils.getMimeTypeByExtension(fsFile.getName());
            if (null == contentType || "".equals(contentType))
               fsFile.setContentType("application/octet-stream");

            fsFile.setContentType(contentType);
            fsFile.setKey(keyGeneratorService.generateDefaultKey(fileServerService));
            file = fsFile;
         }
         file.setParent(base);
         fileServerService.persist(file);
         base.addChild(file);
         fileServerService.merge(base);

         return file;
      }
   }

}
