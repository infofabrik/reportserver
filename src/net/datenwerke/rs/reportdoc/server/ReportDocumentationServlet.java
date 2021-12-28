package net.datenwerke.rs.reportdoc.server;

import static java.util.stream.Collectors.toList;
import static net.datenwerke.rs.utils.stream.shared.StreamUtil.streamOfNullable;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Singleton;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUIModule;
import net.datenwerke.rs.reportdoc.service.ReportDocumentationService;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class ReportDocumentationServlet extends SecuredHttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public static final String PROPERTY_ID = "id";
   public static final String PROPERTY_TEAMSPACE_ID = "tsid";
   public static final String PROPERTY_FORMAT = "format";
   public static final String PROPERTY_DOWNLOAD = "download";

   public static final String PROPERTY_REPORT = "report";
   public static final String PROPERTY_REVID = "revid";

   private final Provider<ReportDocumentationService> documentationServiceProvider;
   private final Provider<SecurityService> securityServiceProvider;
   private final Provider<ReportService> reportServiceProvider;
   private final Provider<ReportServerService> reportServerServiceProvider;
   private final Provider<HttpUtils> httpUtilsProvider;
   private final Provider<DatasourceService> datasourceServiceProvider;

   @Inject
   public ReportDocumentationServlet(Provider<ReportDocumentationService> documentationServiceProvider,
         Provider<SecurityService> securityServiceProvider, Provider<ReportService> reportServiceProvider,
         Provider<ReportServerService> reportServerServiceProvider, Provider<HttpUtils> httpUtilsProvider,
         Provider<DatasourceService> datasourceServiceProvider) {
      super();
      this.documentationServiceProvider = documentationServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
      this.reportServerServiceProvider = reportServerServiceProvider;
      this.httpUtilsProvider = httpUtilsProvider;
      this.datasourceServiceProvider = datasourceServiceProvider;
   }

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      if ("revisions".equals(request.getParameter(PROPERTY_REPORT))) {
         getRevisions(request, response);
      } else if ("revdocumentation".equals(request.getParameter(PROPERTY_REPORT))) {
         getRevisionDocumentation(request, response);
      } else if ("deployanalyze".equals(request.getParameter(PROPERTY_REPORT))) {
         getDeployAnalyzeDocumentation(request, response);
      } else if ("testvariant".equals(request.getParameter(PROPERTY_REPORT))) {
         getVariantTestDocumentation(request, response);
      } else {
         getDocumentation(request, response);
      }

   }

   private void getVariantTestDocumentation(HttpServletRequest request, HttpServletResponse response)
         throws IOException {
      String strReportId = request.getParameter(ReportDocumentationUIModule.REPORT_PROPERTY_ID);
      String[] ds = request.getParameterValues(ReportDocumentationUIModule.DATASOURCE_PROPERTY_ID);
      if (null == strReportId || "".equals(strReportId))
         throw new IllegalArgumentException("expected report id as parameter");

      /* try to get report id */
      Long reportId = Long.parseLong(strReportId);

      /* load report */
      Report report = reportServiceProvider.get().getReportById(reportId);

      if (null == report)
         throw new IllegalArgumentException("Could not load report with id " + report);

      /* check security */
      securityServiceProvider.get().assertRights(report, Read.class, Execute.class);

      /* load datasource ids */
      List<DatasourceDefinition> datasources = streamOfNullable(ds)
            .map(dsName -> datasourceServiceProvider.get().getDatasourceByName(dsName)).collect(toList());

      /* get format */
      String format = null != request.getParameter(PROPERTY_FORMAT) ? request.getParameter(PROPERTY_FORMAT) : "HTML";
      try {
         CompiledReport compiledReport = documentationServiceProvider.get().executeDocumentationVariantTest(report,
               datasources, format);
         output(request, response, compiledReport, "deploy-" + report.getId() + "-" + report.getName());
      } catch (ScriptException e) {
         throw new IllegalStateException(e);
      }

   }

   protected void getRevisions(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String strId = request.getParameter(PROPERTY_ID);

      if (null == strId || "".equals(strId))
         throw new IllegalArgumentException("expected report id as parameter");

      /* try get report id */
      Long reportId = Long.parseLong(strId);

      /* load report */
      Report report = reportServiceProvider.get().getReportById(reportId);
      if (null == report)
         throw new IllegalArgumentException("Could not load report with id " + reportId);

      /* check security */
      securityServiceProvider.get().assertRights(report, Execute.class);

      /* get format */
      String format = null != request.getParameter(PROPERTY_FORMAT) ? request.getParameter(PROPERTY_FORMAT) : "HTML";

      try {
         CompiledReport compiledReport = documentationServiceProvider.get().executeRevisions(report, format);

         output(request, response, compiledReport, "rev-" + report.getId() + "-" + report.getName());
      } catch (ScriptException e) {
         throw new IllegalStateException(e);
      }

   }

   protected void getRevisionDocumentation(HttpServletRequest request, HttpServletResponse response)
         throws IOException {
      String strId = request.getParameter(PROPERTY_ID);
      String strRevision = request.getParameter(PROPERTY_REVID);

      if (null == strId || "".equals(strId))
         throw new IllegalArgumentException("expected report id as parameter");
      if (null == strRevision || "".equals(strRevision))
         throw new IllegalArgumentException("expected rev id as parameter");

      /* try get report id */
      Long reportId = Long.parseLong(strId);
      Long revId = Long.parseLong(strRevision);

      /* load report */
      Report report = reportServiceProvider.get().getReportById(reportId);
      if (null == report)
         throw new IllegalArgumentException("Could not load report with id " + reportId);

      /* check security */
      securityServiceProvider.get().assertRights(report, Execute.class);

      /* get format */
      String format = null != request.getParameter(PROPERTY_FORMAT) ? request.getParameter(PROPERTY_FORMAT) : "HTML";

      try {
         CompiledReport compiledReport = documentationServiceProvider.get().executeDocumentationRev(report, revId,
               format);

         output(request, response, compiledReport, "doc-" + report.getId() + "-" + report.getName());
      } catch (ScriptException e) {
         throw new IllegalStateException(e);
      }

   }

   protected void getDeployAnalyzeDocumentation(HttpServletRequest request, HttpServletResponse response)
         throws IOException {
      String strLeftReportId = request.getParameter(ReportDocumentationUIModule.LEFT_REPORT_PROPERTY_ID);
      if (null == strLeftReportId || "".equals(strLeftReportId))
         throw new IllegalArgumentException("expected left report id as parameter");

      String strRightReportId = request.getParameter(ReportDocumentationUIModule.RIGHT_REPORT_PROPERTY_ID);
      if (null == strRightReportId || "".equals(strRightReportId))
         throw new IllegalArgumentException("expected right report id as parameter");

      String strIgnoreCase = request.getParameter(ReportDocumentationUIModule.IGNORE_CASE_PROPERTY_ID);
      if (null == strIgnoreCase || "".equals(strIgnoreCase))
         throw new IllegalArgumentException("expected ignore case parameter");

      /* try to get report id */
      Long leftReportId = Long.parseLong(strLeftReportId);
      Long rightReportId = Long.parseLong(strRightReportId);

      boolean ignoreCase = Boolean.parseBoolean(strIgnoreCase);

      /* load report */
      Report leftReport = reportServiceProvider.get().getReportById(leftReportId);
      Report rightReport = reportServiceProvider.get().getReportById(rightReportId);

      if (null == leftReport)
         throw new IllegalArgumentException("Could not load report with id " + leftReportId);

      if (null == rightReport)
         throw new IllegalArgumentException("Could not load report with id " + rightReportId);

      /* check security */
      securityServiceProvider.get().assertRights(leftReport, Read.class, Execute.class);

      /* check security */
      securityServiceProvider.get().assertRights(rightReport, Read.class, Execute.class);

      /* get format */
      String format = null != request.getParameter(PROPERTY_FORMAT) ? request.getParameter(PROPERTY_FORMAT) : "HTML";

      try {
         CompiledReport compiledReport = documentationServiceProvider.get()
               .executeDocumentationDeployAnalyze(leftReport, rightReport, format, ignoreCase);

         output(request, response, compiledReport, "deploy-" + leftReport.getId() + "-" + leftReport.getName() + "-"
               + rightReport.getId() + "-" + rightReport.getName());
      } catch (ScriptException e) {
         throw new IllegalStateException(e);
      }

   }

   protected void getDocumentation(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String strId = request.getParameter(PROPERTY_ID);
      String strTsId = request.getParameter(PROPERTY_TEAMSPACE_ID);

      if (null == strId || "".equals(strId))
         throw new IllegalArgumentException("expected report id as parameter");

      /* try get report id */
      Long reportId = Long.parseLong(strId);
      Long tsId = null;
      if (null != strTsId && !"".equals(strTsId.trim()))
         tsId = Long.parseLong(strTsId);

      /* load report */
      Report report = reportServiceProvider.get().getReportById(reportId);
      if (null == report)
         throw new IllegalArgumentException("Could not load report with id " + reportId);

      /* check security */
      securityServiceProvider.get().assertRights(report, Execute.class);

      /* get format */
      String format = null != request.getParameter(PROPERTY_FORMAT) ? request.getParameter(PROPERTY_FORMAT) : "HTML";

      try {
         CompiledReport compiledReport = documentationServiceProvider.get().executeDocumentation(report, tsId, format);

         output(request, response, compiledReport, "doc-" + report.getId() + "-" + report.getName());
      } catch (ScriptException e) {
         throw new IllegalStateException(e);
      }

   }

   protected void output(HttpServletRequest request, HttpServletResponse response, CompiledReport compiledReport,
         String name) throws IOException {
      /* output file name */
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
      String fileName = dateFormat.format(Calendar.getInstance().getTime()) + "_" + name;

      /* set mime type */
      response.setContentType(compiledReport.getMimeType());

      /* cache */
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
      response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
      response.setDateHeader("Expires", 0); // Proxies.

      /* set header and encoding */
      fileName += "." + compiledReport.getFileExtension();

      boolean download = "true".equals(request.getParameter(PROPERTY_DOWNLOAD));

      response.setHeader(HttpUtils.CONTENT_DISPOSITION,
            httpUtilsProvider.get().makeContentDispositionHeader(download, fileName));

      if (compiledReport.isStringReport()) {
         /* get charset */
         String charset = reportServerServiceProvider.get().getCharset();
         response.setCharacterEncoding(charset);

         try (PrintWriter writer = response.getWriter();) {
            writer.write((String) compiledReport.getReport());
         }
      } else {
         try (OutputStream os = response.getOutputStream()) {
            os.write((byte[]) compiledReport.getReport());
         }
      }
   }
}
