package net.datenwerke.gf.server.download;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.download.FileDownloadUiModule;
import net.datenwerke.gf.service.download.FileDownloadService;
import net.datenwerke.security.server.SecuredHttpServlet;

@Singleton
public class FileDownloadServlet extends SecuredHttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = -7745426896682214945L;

   private final Provider<FileDownloadService> fileDownloadServiceProvider;

   @Inject
   public FileDownloadServlet(Provider<FileDownloadService> fileDownloadServiceProvider) {
      this.fileDownloadServiceProvider = fileDownloadServiceProvider;

   }

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /* grep handler and id */
      String id = request.getParameter("id");
      String handler = request.getParameter("handler");

      /* get metadata */
      Map<String, String> metadata = new HashMap<String, String>();

      Enumeration names = request.getParameterNames();
      while (names.hasMoreElements()) {
         Object el = names.nextElement();
         if (el instanceof String) {
            String name = (String) el;

            if (name.startsWith(FileDownloadUiModule.META_FIELD_PREFIX)
                  && name.length() > FileDownloadUiModule.META_FIELD_PREFIX.length()) {
               String metaname = name.substring(FileDownloadUiModule.META_FIELD_PREFIX.length());
               String value = request.getParameter(name);

               metadata.put(metaname, value);
            }

         }
      }

      FileDownloadService fileDownloadService = fileDownloadServiceProvider.get();

      fileDownloadService.processDownload(id, handler, metadata, response);
   }
}
