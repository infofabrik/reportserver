package net.datenwerke.gf.server.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gf.service.upload.FileUploadService;
import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.rs.utils.misc.MimeUtils;
import net.datenwerke.security.server.SecuredHttpServlet;

@Singleton
public class FileUploadServlet extends SecuredHttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 5182188108157409531L;

   private final Logger logger = Logger.getLogger(getClass().getName());

   private final Provider<FileUploadService> fileUploadServiceProvider;
   private final MimeUtils mimeUtils;

   @Inject
   public FileUploadServlet(Provider<FileUploadService> fileUploadServiceProvider, MimeUtils mimeUtils) {

      /* store objects */
      this.fileUploadServiceProvider = fileUploadServiceProvider;
      this.mimeUtils = mimeUtils;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void doPost(HttpServletRequest request, HttpServletResponse response) {

      PrintWriter out;
      try {
         out = response.getWriter();
      } catch (IOException e1) {
         throw new RuntimeException(e1);
      }

      // check whether anything was uploaded
      if (!ServletFileUpload.isMultipartContent(request)) {
         Exception e = new IllegalArgumentException("no multipart request"); //$NON-NLS-1$
         e.printStackTrace(out);
      }

      // Create a new file upload handler
      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);

      Map<String, UploadedFile> uploadedFiles = new HashMap<String, UploadedFile>();

      try {
         // Parse the request
         List<FileItem> items = upload.parseRequest(request);
         if (null == items || items.isEmpty())
            out.println("no items"); //$NON-NLS-1$

         /* find upload ids */
         for (FileItem item : items) {
            String fieldName = item.getFieldName();

            if (item.isFormField()) {
               if (fieldName.startsWith(FileUploadUIModule.UPLOAD_FILE_ID_PREFIX)) { // get report id
                  if (fieldName.length() > FileUploadUIModule.UPLOAD_FILE_ID_PREFIX.length()) {
                     String id = fieldName.substring(FileUploadUIModule.UPLOAD_FILE_ID_PREFIX.length());
                     String value = item.getString();
                     if ("1".equals(value)) //$NON-NLS-1$
                        uploadedFiles.put(id, new UploadedFile(id));
                  }
               }
            }
         }

         /* ajax upload */
         for (FileItem item : items) {
            String fieldName = item.getFieldName();
            if (item.isFormField()) {
               if (fieldName.startsWith(FileUploadUIModule.UPLOAD_FILE_XHR_NAME_PREFIX)) {
                  String id = fieldName.substring(FileUploadUIModule.UPLOAD_FILE_XHR_NAME_PREFIX.length());
                  UploadedFile uploadedFile = uploadedFiles.get(id);
                  if ("".equals(item.getString()))
                     continue;

                  uploadedFile.setFileName(item.getString());

                  if (null == uploadedFile.getContentType()
                        || "application/octet-stream".equals(uploadedFile.getContentType())) {
                     String contentType = mimeUtils.getMimeTypeByExtension(uploadedFile.getFileName());
                     if (null != contentType && !"".equals(contentType.trim()))
                        uploadedFile.setContentType(contentType);
                  }
               } else if (fieldName.startsWith(FileUploadUIModule.UPLOAD_FILE_XHR_LENGTH_PREFIX)) {
                  String id = fieldName.substring(FileUploadUIModule.UPLOAD_FILE_XHR_LENGTH_PREFIX.length());
                  UploadedFile uploadedFile = uploadedFiles.get(id);
                  if ("".equals(item.getString()))
                     continue;

                  uploadedFile.setLength(Long.valueOf(item.getString()));
               } else if (fieldName.startsWith(FileUploadUIModule.UPLOAD_FILE_XHR_CONTENT_PREFIX)) {
                  String id = fieldName.substring(FileUploadUIModule.UPLOAD_FILE_XHR_CONTENT_PREFIX.length());
                  UploadedFile uploadedFile = uploadedFiles.get(id);
                  if ("".equals(item.getString()))
                     continue;

                  String data = item.getString();
                  String mimeType = fileUploadServiceProvider.get().extractContentTypeFromHtml5Upload(data);
                  byte[] fileData = fileUploadServiceProvider.get().extractContentFromHtml5Upload(data);

                  /* content type */
                  if (!"".equals(mimeType))
                     uploadedFile.setContentType(mimeType);

                  uploadedFile.setFileBytes(fileData);
                  uploadedFile.setLength(uploadedFile.getFileBytes().length);
               }
            }
         }

         /* find files and metadata */
         for (FileItem item : items) {
            String fieldName = item.getFieldName();
            if (item.isFormField()) {
               if (fieldName.startsWith(FileUploadUIModule.UPLOAD_FILE_HANDLER_PREFIX)) {
                  String id = fieldName.substring(FileUploadUIModule.UPLOAD_FILE_HANDLER_PREFIX.length());
                  UploadedFile uploadedFile = uploadedFiles.get(id);

                  uploadedFile.setHandler(item.getString());
               } else if (fieldName.startsWith(FileUploadUIModule.UPLOAD_FILE_META_PREFIX)) {
                  for (String id : uploadedFiles.keySet()) {
                     if (fieldName.startsWith(FileUploadUIModule.UPLOAD_FILE_META_PREFIX + id + "_")) {
                        String key = fieldName
                              .substring((FileUploadUIModule.UPLOAD_FILE_META_PREFIX + id + "_").length());
                        UploadedFile uploadedFile = uploadedFiles.get(id);

                        uploadedFile.addMetadata(key, item.getString());
                        break;
                     }
                  }
               }
            } else if (item.getFieldName().startsWith(FileUploadUIModule.UPLOAD_FILE_FILE_PREFIX)) { // $NON-NLS-1$
               String id = fieldName.substring(FileUploadUIModule.UPLOAD_FILE_FILE_PREFIX.length());
               UploadedFile uploadedFile = uploadedFiles.get(id);

               if (null == uploadedFile.getFileBytes()) {
                  String fileName = item.getName();

                  String contentType = item.getContentType();
                  if (null == contentType || contentType.isEmpty() || "application/octet-stream".equals(contentType)) {
                     contentType = mimeUtils.getMimeTypeByExtension(fileName);
                  }

                  fileName = fileName.replace("\\", "/");
                  if (fileName.contains("/")) {
                     fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                  }

                  byte[] fileBytes = IOUtils.toByteArray(item.getInputStream());

                  uploadedFile.setFileName(fileName);
                  uploadedFile.setContentType(contentType);
                  uploadedFile.setLength(fileBytes.length);
                  uploadedFile.setFileBytes(fileBytes);
               }
            }
         }
      } catch (IOException e) {
         logger.log(Level.INFO, "uplaod error", e);
         e.printStackTrace(out);
      } catch (FileUploadException e) {
         logger.log(Level.INFO, "upload error", e);
         e.printStackTrace(out);
      }

      String responseText = null;
      FileUploadService fileUploadService = fileUploadServiceProvider.get();
      for (String id : uploadedFiles.keySet()) {
         UploadedFile uploadedFile = uploadedFiles.get(id);
         if (uploadedFile.getLength() > 0) {
            responseText = fileUploadService.uploadOccured(uploadedFile);
         }
      }

      /* todo: could be extended to provide information on upload */
      response.setContentType("text/html");
      out.println(FileUploadUIModule.UPLOAD_SUCCESSFUL_PREFIX + (null != responseText ? " " + responseText : ""));
   }

}
