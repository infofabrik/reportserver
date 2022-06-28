package net.datenwerke.rs.scheduleasfile.server.scheduleasfile;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFileReference;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.server.SecuredHttpServlet;

@Singleton
public class ExportScheduledAsDiskFileServlet extends SecuredHttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 6187861539076768770L;
   
   private static final String PROPERTY_FILE_ID = "fileId";
   
   protected final Provider<TeamSpaceService> teamSpaceServiceProvider;
   protected final Provider<TsDiskService> tsDiskServiceProvider;
   protected final Provider<HttpUtils> httpUtilsProvider;
   
   @Inject
   public ExportScheduledAsDiskFileServlet(
         Provider<TeamSpaceService> teamSpaceServiceProvider, 
         Provider<TsDiskService> tsDiskServiceProvider,
         Provider<HttpUtils> httpUtilsProvider
         ) {
      this.teamSpaceServiceProvider = teamSpaceServiceProvider;
      this.tsDiskServiceProvider = tsDiskServiceProvider;
      this.httpUtilsProvider = httpUtilsProvider;
   }

   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (null == req.getParameter(PROPERTY_FILE_ID))
         throw new IllegalArgumentException("no file id specified");

      Long id = Long.valueOf(req.getParameter(PROPERTY_FILE_ID));

      TsDiskService tsDiskService = tsDiskServiceProvider.get();
      TeamSpaceService teamSpaceService = teamSpaceServiceProvider.get();

      AbstractTsDiskNode node = tsDiskService.getNodeById(id);
      if (!(node instanceof TsDiskFileReference))
         throw new IllegalArgumentException("object is of wrong type");

      TsDiskFileReference reference = (TsDiskFileReference) node;
      TeamSpace ts = tsDiskService.getTeamSpaceFor(reference);
      if (!teamSpaceService.mayAccess(ts))
         throw new IllegalArgumentException("insufficient rights");

      OutputStream os = null;

      try {
         /* prepare the output */
         os = resp.getOutputStream();

         /* set mime type */
         resp.setContentType(reference.getContentType());

         /* set header and encoding */
         boolean download = false;
         if ("true".equals(req.getParameter("download")))
            download = true;

         SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
         String fileName = format.format(Calendar.getInstance().getTime()) + "_" + reference.getFileName();
         resp.setHeader(HttpUtils.CONTENT_DISPOSITION,
               httpUtilsProvider.get().makeContentDispositionHeader(download, fileName));

         /* set for disabled caching */
         /* needed by ticket #775 - problems with multiple files with the same name */
         /* IE cant download files in a popup when cache-control header is present... */
         if (!req.getHeader("User-Agent").contains("MSIE")) {
            resp.setHeader("Cache-Control", "no-cache");
         }

         /* set data */
         os.write(reference.getData());

      } finally {
         if (null != os)
            os.close();
      }
   }
}
