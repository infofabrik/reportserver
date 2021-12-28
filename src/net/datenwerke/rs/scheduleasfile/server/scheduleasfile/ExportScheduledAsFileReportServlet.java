package net.datenwerke.rs.scheduleasfile.server.scheduleasfile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.rs.compiledreportstore.entities.PersistentCompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.server.SecuredHttpServlet;

@Singleton
public class ExportScheduledAsFileReportServlet extends SecuredHttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 8567907149552461615L;

   private static final String PROPERTY_FILE_ID = "fileId";

   protected final Provider<ReportServerService> reportServerServiceProvider;
   protected final Provider<TeamSpaceService> teamSpaceServiceProvider;
   protected final Provider<TsDiskService> tsDiskServiceProvider;
   protected final Provider<HttpUtils> httpUtilsProvider;

   @Inject
   public ExportScheduledAsFileReportServlet(Provider<ReportServerService> reportServerServiceProvider,
         Provider<TeamSpaceService> teamSpaceServiceProvider, Provider<TsDiskService> tsDiskServiceProvider,
         Provider<HttpUtils> httpUtilsProvider) {

      /* store objects */
      this.reportServerServiceProvider = reportServerServiceProvider;
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
      if (!(node instanceof ExecutedReportFileReference))
         throw new IllegalArgumentException("object is of wrong type");

      ExecutedReportFileReference reference = (ExecutedReportFileReference) node;
      TeamSpace ts = tsDiskService.getTeamSpaceFor(reference);
      if (!teamSpaceService.mayAccess(ts))
         throw new IllegalArgumentException("insufficient rights");

      PersistentCompiledReport report = reference.getCompiledReport();
      CompiledReport cReport = report.getCompiledReport();

      OutputStream os = null;

      try {
         /* prepare the output */
         os = resp.getOutputStream();

         /* set mime type */
         resp.setContentType(cReport.getMimeType());

         /* set header and encoding */
         boolean download = false;
         if ("true".equals(req.getParameter("download")))
            download = true;

         SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
         String fileName = format.format(Calendar.getInstance().getTime()) + "_"
               + makeExportFilename(reference.getName()) + "." + cReport.getFileExtension();
         resp.setHeader(HttpUtils.CONTENT_DISPOSITION,
               httpUtilsProvider.get().makeContentDispositionHeader(download, fileName));

         /* set for disabled caching */
         /* needed by ticket #775 - problems with multiple files with the same name */
         /* IE cant download files in a popup when cache-control header is present... */
         if (!req.getHeader("User-Agent").contains("MSIE")) {
            resp.setHeader("Cache-Control", "no-cache");
         }

         /* set data */
         if (cReport.getReport() instanceof byte[]) {
            os.write((byte[]) cReport.getReport());
         } else if (cReport.getReport() instanceof String) {
            /* get charset */
            String charset = reportServerServiceProvider.get().getCharset();
            resp.setCharacterEncoding(charset);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, charset));
            bw.write(new String(cReport.getReport().toString().getBytes(charset), charset));
            bw.close();
         } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(cReport.getReport().toString());
            bw.close();
         }

      } finally {
         if (null != os)
            os.close();
      }
   }

   protected String makeExportFilename(String reportName) {
      if (null == reportName)
         return "unnamed";
      return reportName;
   }

}
