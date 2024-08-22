package net.datenwerke.rs.base.server.table;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.rs.base.service.reportengines.table.dot.http.HttpExportService;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.security.SecurityService;

/**
 * 
 *
 */
@Singleton
public class DotDownloadServlet extends SecuredHttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 2531374971329277458L;

   private final Provider<HttpUtils> httpUtilsProvider;
   private final Provider<HttpExportService> httpExportServiceProvider;

   @Inject
   public DotDownloadServlet(
         Provider<SecurityService> securityServiceProvider,
         Provider<HttpExportService> httpExportServiceProvider,
         Provider<HttpUtils> httpUtilsProvider
         ) {

      /* store objects */
      this.httpUtilsProvider = httpUtilsProvider;
      this.httpExportServiceProvider = httpExportServiceProvider;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String content = httpExportServiceProvider.get().getAndRemoveStoredExport();
      String name = httpExportServiceProvider.get().getExportName();
      String fileName = new SimpleDateFormat("yyyyMMdd-hhmm").format(Calendar.getInstance().getTime()) + "-" + name + "-prefilter-export"
            + ".dot";
 
      response.setContentType("application/octet-stream"); //$NON-NLS-1$
      response.setHeader("Cache-Control", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
      response.setHeader(HttpUtils.CONTENT_DISPOSITION,
            httpUtilsProvider.get().makeContentDispositionHeader(true, fileName));
      response.setCharacterEncoding("UTF-8");
      response.getOutputStream().print(content);
   }

}
