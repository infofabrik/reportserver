package net.datenwerke.rs.core.server.reportexport;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.http.client.Response;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.service.tempfile.TempFile;
import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.server.reportexport.helper.ReportSessionCache;
import net.datenwerke.rs.core.server.reportexport.helper.ReportSessionCacheEntry;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportAdjustHttpHeaderHook;
import net.datenwerke.rs.core.service.error.RsErrorHelper;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportExecutionConfigFromPropertyMapHook;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHttpRequestHook;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.utils.exception.ExceptionService;
import net.datenwerke.rs.utils.misc.HttpUtils;
import net.datenwerke.rs.utils.stream.shared.StreamUtil;
import net.datenwerke.security.server.SecuredHttpServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class ReportExportServlet extends SecuredHttpServlet {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   class ReportNotExistsException extends RuntimeException {
      private static final long serialVersionUID = 1L;
   }

   private static final long serialVersionUID = -1966524992884670842L;

   public static final String PARAMETER_BACKLINK_ID = "bid";

   public static final String SESSION_KEY_BACKLINKS = "rs.reportexport.backlinks"; //$NON-NLS-1$

   public static final String PARAMETER_SUGGESTED_FILENAME = "suggestedFilename";

   public static final String SERVLET_NAME = "reportexport";

   protected final Provider<AuthenticatorService> authenticatorServiceProvider;
   protected final Provider<HookHandlerService> hookHandlerProvider;
   protected final Provider<SecurityService> securityServiceProvider;
   protected final Provider<ReportExecutorService> reportExecutor;
   protected final Provider<ReportService> reportService;
   protected final Provider<ReportServerService> reportServerService;
   protected final Provider<RsErrorHelper> errorHelperProvider;
   protected final ParameterSetFactory parameterSetFactory;
   protected final Provider<TempFileService> tempFileService;
   protected final Provider<ExceptionService> exceptionServices;
   protected final Provider<ConfigService> configService;
   protected final Provider<ReportSessionCache> sessionCacheProvider;
   protected final Provider<HttpUtils> httpUtilsProvider;

   @Inject
   public ReportExportServlet(Provider<AuthenticatorService> authenticatorService,
         Provider<HookHandlerService> hookHandlerProvider, Provider<SecurityService> securityServiceProvider,
         Provider<ReportExecutorService> reportExecutor, Provider<ReportService> reportService,
         Provider<ReportServerService> reportServerService, Provider<RsErrorHelper> errorHelperProvider,
         ParameterSetFactory parameterSetFactory, Provider<TempFileService> tempFileService,
         Provider<ExceptionService> exceptionServices, Provider<ConfigService> configService,
         Provider<ReportSessionCache> sessionCacheProvider, Provider<HttpUtils> httpUtilsProvider) {

      super();
      this.authenticatorServiceProvider = authenticatorService;
      this.hookHandlerProvider = hookHandlerProvider;
      this.securityServiceProvider = securityServiceProvider;
      this.reportExecutor = reportExecutor;
      this.reportService = reportService;
      this.reportServerService = reportServerService;
      this.errorHelperProvider = errorHelperProvider;
      this.parameterSetFactory = parameterSetFactory;
      this.tempFileService = tempFileService;
      this.exceptionServices = exceptionServices;
      this.configService = configService;
      this.sessionCacheProvider = sessionCacheProvider;
      this.httpUtilsProvider = httpUtilsProvider;
   }

   protected void validateRequest(Report report, Report adjustedReport, HttpServletRequest req) {
      if (null == getUser(req))
         throw new RuntimeException("Security Violation"); //$NON-NLS-1$

      if (null == report)
         throw new ReportNotExistsException();

      SecurityService securityService = securityServiceProvider.get();

      if (!securityService.checkRights(report, Execute.class))
         throw new RuntimeException("Security Violation"); //$NON-NLS-1$
   }

   @Override
   @SecurityChecked(loginRequired = false)
   @Transactional(rollbackOn = { Exception.class })
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /* test if data was provided */
      try {
         if (!isAuthenticated()) {
            requireLogin(req, resp);
            return;
         }

         if (null != req.getParameter("back"))
            exportBackLinkReport(req, resp);
         else if (null != req.getParameter("id") || null != req.getParameter("key")) { //$NON-NLS-1$ //$NON-NLS-2$
            exportReportByIdViaRequest(req, resp);
         } else if (null != req.getParameter("tid"))
            exportReportViaSession(req, resp);
         else if (null != req.getParameter("path")) {
            exportByPath(req, resp);
         } else {
            throw new IllegalArgumentException("No id, key or path to identify the report");
         }
      } catch (Exception e) {
         final Optional<String> details = exceptionServices.get().getReportExecutionExceptionDetailsMessage(e);
         logger.warn(e.getMessage() + (details.isPresent() ? ", details: " + details.get() : ""), e);
         try {
            displayErrorMessage(e, req, resp);
         } catch (Exception e2) {
            // swallow
         }
      }
   }

   private void exportByPath(HttpServletRequest request, HttpServletResponse resp)
         throws IOException, ReportExecutorException {

      if (null == request.getParameter("path"))
         throw new ReportExecutorException("No path defined in url");

      String path = request.getParameter("path");

      ReportService service = reportService.get();
      AbstractReportManagerNode node = service.getNodeByPath(path, false);
      if (node instanceof Report) {
         exportReportById(node.getId(), request, resp);
      }
   }

   @Override
   @SecurityChecked(loginRequired = false)
   @Transactional(rollbackOn = { Exception.class })
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }

   @Override
   @SecurityChecked(loginRequired = false)
   @Transactional(rollbackOn = { Exception.class })
   protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.sendError(Response.SC_NOT_IMPLEMENTED);
   }

   protected void displayErrorMessage(Exception e, HttpServletRequest req, HttpServletResponse resp)
         throws IOException {
      resp.setStatus(500, e.getMessage());

      if (null != req.getParameter("isJsViewer")) {
         try {
            PrintWriter writer = resp.getWriter();
            writer.write(exceptionServices.get().exceptionToString(e));
            writer.flush();
            writer.close();
         } catch (Exception e2) {
            OutputStream os = resp.getOutputStream();
            os.write(exceptionServices.get().exceptionToString(e).getBytes());
            os.flush();
            os.close();
         }
         return;
      }

      resp.setContentType("text/html");
      resp.setCharacterEncoding("UTF-8");
      resp.setHeader("Content-Disposition", "inline");

      try {
         PrintWriter writer = resp.getWriter();
         try {
            String msg = "";
            if (e instanceof ReportNotExistsException)
               msg = errorHelperProvider.get()
                     .getHtmlErrorPage(ReportManagerMessages.INSTANCE.exceptionReportDoesNotExist(), e);
            else
               msg = errorHelperProvider.get()
                     .getHtmlErrorPage(ReportManagerMessages.INSTANCE.exceptionWhileReportExecution(), e);
            writer.append(msg);
         } finally {
            writer.flush();
            writer.close();
         }
      } catch (Exception e2) {
         OutputStream os = resp.getOutputStream();
         String msg = "";
         if (e instanceof ReportNotExistsException)
            msg = errorHelperProvider.get()
                  .getHtmlErrorPage(ReportManagerMessages.INSTANCE.exceptionReportDoesNotExist(), e);
         else
            msg = errorHelperProvider.get()
                  .getHtmlErrorPage(ReportManagerMessages.INSTANCE.exceptionWhileReportExecution(), e);
         os.write(msg.getBytes());
         os.flush();
         os.close();
      }

   }

   protected void exportBackLinkReport(HttpServletRequest req, HttpServletResponse resp)
         throws IOException, ReportExecutorException {
      HttpSession session = req.getSession();

      if (null == session.getAttribute(SESSION_KEY_BACKLINKS))
         return;

      Map<String, BackLinkReport> map = (Map<String, BackLinkReport>) session.getAttribute(SESSION_KEY_BACKLINKS);
      String backId = (String) req.getParameter("back");
      BackLinkReport backLinkReport = map.get(backId);

      if (null != backLinkReport)
         exportReport(backLinkReport.getReportId(), backLinkReport.getAdjustedReport(),
               backLinkReport.getOutputFormat(), null, backLinkReport, req, resp, null);
   }

   protected void exportReportByIdViaRequest(HttpServletRequest req, HttpServletResponse resp)
         throws IOException, ReportExecutorException {
      long reportId = getReportId(req);
      exportReportById(reportId, req, resp);
   }

   private void exportReportById(long reportId, HttpServletRequest req, HttpServletResponse resp)
         throws IOException, ReportExecutorException {
      String outputFormat = getOutputFormatFromRequest(req);
      Report report = getReportFromRequest(req);
      String suggestedFileName = req.getParameter(PARAMETER_SUGGESTED_FILENAME);
      ReportExecutionConfig[] configs = getConfigsFromRequest(report, req);

      exportReport(reportId, report, outputFormat, suggestedFileName, null, req, resp, null, configs);
   }

   private ReportExecutionConfig[] getConfigsFromRequest(final Report report, final HttpServletRequest req) {
      return hookHandlerProvider.get().getHookers(ReportExecutionConfigFromPropertyMapHook.class).stream()
            .flatMap(configProvider -> configProvider.parse(report, req, req.getParameterMap()).stream())
            .toArray(ReportExecutionConfig[]::new);
   }

   private boolean isDownload(HttpServletRequest req) {
      String cd = "attachment";
      if (configService.get().getConfigFailsafe("ui/ui.cf").getBoolean("exportReportsDefaultInline", false))
         cd = "inline";

      if (null != req.getParameter("download")) {
         if ("true".equals(req.getParameter("download")))
            cd = "attachment";
         if ("false".equals(req.getParameter("download")))
            cd = "inline";
      }
      if (configService.get().getConfigFailsafe("ui/ui.cf").getBoolean("exportReportsInline", false))
         cd = "inline";

      return "attachment".equals(cd);
   }

   private void exportReportViaSession(HttpServletRequest req, HttpServletResponse resp)
         throws IOException, ReportExecutorException {
      /* retrieve token */
      String tid = req.getParameter("tid");
      if (null == tid)
         throw new IllegalStateException("Expected token id");

      /* retrieve the report to export from the session */
      ReportSessionCacheEntry entry = sessionCacheProvider.get().get(tid);
      if (null == entry)
         throw new IllegalStateException("Could not retrieve entry from session");

      String outputFormat = entry.getOutputFormat();
      Report adjustedReport = entry.getAdjustedReport();
      ReportExecutionConfig[] configs = entry.getExecutorConfigs();
      Long reportId = entry.getId();

      if (entry.isCached()) {
         /* check cache */
         TempFile tempFile = tempFileService.get().getTempFileById(tid);
         if (null != tempFile && !tempFile.isPermittedUser(getUser(req)))
            tempFile = null;

         if (null == tempFile)
            throw new IllegalStateException(
                  "Report could not be loaded from cache, because the previous execution failed. See below for original cause. ", //$NON-NLS-1$
                  entry.getError());

         String baseFilename = tempFile.getDownloadName().substring(0, tempFile.getDownloadName().lastIndexOf(".") - 1);
         String mimetype = tempFile.getMimeType();
         String extension = tempFile.getDownloadName().substring(tempFile.getDownloadName().lastIndexOf("."));
         boolean isStringRprt = false;

         sendHeaders(resp, req, baseFilename, mimetype, extension, isStringRprt, isDownload(req));

         byte[] fileContents = Files.readAllBytes(tempFile.getPath());
         resp.getOutputStream().write(fileContents);
         resp.getOutputStream().flush();

      } else {
         /* export */
         exportReport(reportId, adjustedReport, outputFormat, null, null, req, resp, entry, configs);
      }

   }

   private void exportReport(long reportId, Report adjustedReport, String outputFormat, String suggestedFileName,
         BackLinkReport backLinkReport, HttpServletRequest req, HttpServletResponse resp, ReportSessionCacheEntry entry,
         ReportExecutionConfig... reportExecutorConfigs) throws IOException, ReportExecutorException {
      try {
         doExportReport(reportId, adjustedReport, outputFormat, suggestedFileName, backLinkReport, req, resp, entry,
               reportExecutorConfigs);
         if (null != entry)
            entry.setError(null);
      } catch (Exception e) {
         if (null != entry)
            entry.setError(e);
         throw e;
      }
   }

   private void doExportReport(long reportId, Report adjustedReport, String outputFormat, String suggestedFileName,
         BackLinkReport backLinkReport, HttpServletRequest req, HttpServletResponse resp, ReportSessionCacheEntry entry,
         ReportExecutionConfig... reportExecutorConfigs) throws IOException, ReportExecutorException {
      Report referenceReport = reportService.get().getUnmanagedReportById(reportId);
      String rexToken = getReportExecutorToken(reportExecutorConfigs);

      validateRequest(reportService.get().getReportById(reportId), adjustedReport, req);

      /* merge reports if necessary */
      if (null != adjustedReport)
         referenceReport = referenceReport.createTemporaryVariant(adjustedReport);

      /* backlink */
      ParameterSet backLinkSet = createParameterSet(req, referenceReport);
      if (null == backLinkReport) {
         String storedBackLink = storeBackLinkReport(req, reportId, referenceReport, outputFormat);
         backLinkSet.addVariable("_RS_BACKLINK_ID", storedBackLink.toString());
      } else {
         backLinkSet.addVariable("_RS_BACKLINK_ID", backLinkReport.getId());
      }

      /* load backlink */
      if (null != req.getParameter(PARAMETER_BACKLINK_ID) || null != backLinkReport) {
         String backlinkid = null != backLinkReport ? backLinkReport.getPredecessor()
               : req.getParameter(PARAMETER_BACKLINK_ID);
         if (isValidBackLink(req, backlinkid)) {
            String backlinkUrl = req.getRequestURL().toString() + "?back=" + backlinkid;
            backLinkSet.addVariable("_RS_BACKLINK_URL", backlinkUrl);
         }
      }

//		/* set for disabled caching */
//		/* needed by ticket #775 - problems with multiple files with the same name */
//		/* IE cant download files in a popup when cache-control header is present... */
//		if(!req.getHeader("User-Agent").contains("MSIE")){
//			resp.setHeader("Cache-Control", "no-cache");
//		}
//		resp.setHeader("Pragma", "No-cache");
//		resp.setDateHeader("Expires", 1);	

      if (supportsStreaming(referenceReport, backLinkSet, outputFormat, reportExecutorConfigs, req)) {
         /* execte report dry */
         CompiledReport dryReport = exeucteReportDry(referenceReport, backLinkSet, outputFormat, reportExecutorConfigs,
               req);

         String baseFilename = (null == suggestedFileName || "".equals(suggestedFileName.trim()))
               ? makeExportFilename(referenceReport)
               : suggestedFileName;
         String mimetype = dryReport.getMimeType();
         String extension = dryReport.getFileExtension();
         boolean isStringRprt = dryReport.isStringReport();

         sendHeaders(resp, req, baseFilename, mimetype, extension, isStringRprt, isDownload(req));

         /* allow for header adjustments */
         for (ReportExportAdjustHttpHeaderHook hooker : hookHandlerProvider.get()
               .getHookers(ReportExportAdjustHttpHeaderHook.class))
            hooker.adjustHeaders(referenceReport, dryReport, req, resp);

         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         TeeOutputStream tos = new TeeOutputStream(resp.getOutputStream(), bos);
         executeReport(tos, req, referenceReport, backLinkSet, outputFormat, reportExecutorConfigs);

         storeInCache(rexToken, baseFilename, mimetype, extension, bos, req, entry);
      } else {
         /* execute report */
         CompiledReport executedReport = executeReport(null, req, referenceReport, backLinkSet, outputFormat,
               reportExecutorConfigs);

         String baseFilename = (null == suggestedFileName || "".equals(suggestedFileName.trim()))
               ? makeExportFilename(referenceReport)
               : suggestedFileName;
         String mimetype = executedReport.getMimeType();
         String extension = executedReport.getFileExtension();
         boolean isStringRprt = executedReport.isStringReport();

         sendHeaders(resp, req, baseFilename, mimetype, extension, isStringRprt, isDownload(req));

         /* allow for header adjustments */
         for (ReportExportAdjustHttpHeaderHook hooker : hookHandlerProvider.get()
               .getHookers(ReportExportAdjustHttpHeaderHook.class))
            hooker.adjustHeaders(referenceReport, executedReport, req, resp);

         storeInCache(rexToken, baseFilename, mimetype, extension, executedReport.getReport(), req, entry);

         /* send data */
         if (executedReport.getReport() instanceof byte[]) {
            resp.getOutputStream().write((byte[]) executedReport.getReport());

         } else if (executedReport.getReport() instanceof String) {
            String charset = reportServerService.get().getCharset();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), charset));
            bw.write(new String(executedReport.getReport().toString().getBytes(charset), charset));
            bw.close();

         } else if (executedReport.getReport() instanceof BufferedImage[]) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(((BufferedImage[]) executedReport.getReport())[0], "png", baos);
            IOUtils.write(baos.toByteArray(), resp.getOutputStream());

         } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));
            bw.write(executedReport.getReport().toString());
            bw.close();
         }

      }

      resp.getOutputStream().flush();
   }

   private void storeInCache(String rexToken, String baseFilename, String mimetype, String extension,
         Object executedReport, HttpServletRequest req, ReportSessionCacheEntry entry)
         throws FileNotFoundException, IOException {
      if (null == entry)
         return;

      entry.setCached(true);

      TempFile tempFile = tempFileService.get().createWebAccessibleTempFile(rexToken, getUser(req));
      tempFile.setDownloadName(baseFilename + "." + extension);
      tempFile.setMimeType(mimetype);

      /* set data */
      if (executedReport instanceof ByteArrayOutputStream) {
         try (OutputStream outputStream = Files.newOutputStream(tempFile.getPath());
               BufferedOutputStream bos = new BufferedOutputStream(outputStream)) {
            ((ByteArrayOutputStream) executedReport).writeTo(bos);
         }
      } else if (executedReport instanceof byte[]) {
         // reports not supporting streaming, e.g. dynamic list pdf export
         Files.write(tempFile.getPath(), (byte[]) executedReport);
      } else if (executedReport instanceof String) {
         String charset = reportServerService.get().getCharset();
         Files.write(tempFile.getPath(), executedReport.toString().getBytes(charset));
      } else if (executedReport instanceof BufferedImage[]) {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ImageIO.write(((BufferedImage[]) executedReport)[0], "png", baos);
         Files.write(tempFile.getPath(), baos.toByteArray());
      } else {
         Files.write(tempFile.getPath(), executedReport.toString().getBytes(StandardCharsets.UTF_8));
      }
   }

   private void sendHeaders(HttpServletResponse resp, HttpServletRequest request, String baseFilename, String mimetype,
         String extension, boolean isStringRprt, boolean download) {
      /* output file name */
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
      String fileName = dateFormat.format(Calendar.getInstance().getTime()) + "_" + baseFilename + "." + extension;

      resp.setContentType(mimetype);
      resp.setHeader(HttpUtils.CONTENT_DISPOSITION,
            httpUtilsProvider.get().makeContentDispositionHeader(download, fileName));

      if (isStringRprt) {
         String charset = reportServerService.get().getCharset();
         resp.setCharacterEncoding(charset);

      }
   }

   private String getReportExecutorToken(final ReportExecutionConfig[] reportExecutorConfigs) {
      return StreamUtil.streamOfNullable(reportExecutorConfigs).filter(cfg -> cfg instanceof RECReportExecutorToken)
            .map(cfg -> ((RECReportExecutorToken) cfg).getToken()).findAny().orElse(null);
   }

   protected boolean supportsStreaming(Report report, ParameterSet backLinkSet, String outputFormat,
         ReportExecutionConfig[] reportExecutorConfigs, HttpServletRequest req) throws ReportExecutorException {

      User user = getUser(req);
      return reportExecutor.get().supportsStreaming(report, backLinkSet, user, outputFormat, reportExecutorConfigs);
   }

   protected CompiledReport exeucteReportDry(Report report, ParameterSet backLinkSet, String outputFormat,
         ReportExecutionConfig[] reportExecutorConfigs, HttpServletRequest req) throws ReportExecutorException {

      User user = getUser(req);
      return reportExecutor.get().executeDry(report, backLinkSet, user, outputFormat, reportExecutorConfigs);
   }

   protected CompiledReport executeReport(OutputStream os, HttpServletRequest req, Report report,
         ParameterSet backLinkSet, String outputFormat, ReportExecutionConfig[] reportExecutorConfigs)
         throws ReportExecutorException {

      User user = getUser(req);
      return reportExecutor.get().execute(os, report, backLinkSet, user, outputFormat, reportExecutorConfigs);
   }

   protected ParameterSet createParameterSet(HttpServletRequest req, Report adjustedReport) {
      return parameterSetFactory.create();
   }

   private String makeExportFilename(Report report) {
      String reportName = report.getName();
      if (report instanceof ReportVariant) {
         reportName = (((Report) report.getParent()).getName() + " - " + report.getName());
      }

      return reportName;
   }

   private boolean isValidBackLink(HttpServletRequest req, String backlinkId) {
      HttpSession session = req.getSession();

      if (null == session.getAttribute(SESSION_KEY_BACKLINKS))
         return false;

      Map<String, BackLinkReport> map = (Map<String, BackLinkReport>) session.getAttribute(SESSION_KEY_BACKLINKS);

      return map.containsKey(backlinkId);
   }

   private String storeBackLinkReport(HttpServletRequest req, long reportId, Report adjustedReport,
         String outputFormat) {
      HttpSession session = req.getSession();

      if (null == session.getAttribute(SESSION_KEY_BACKLINKS)) {
         Map<String, BackLinkReport> map = new HashMap<String, BackLinkReport>();
         session.setAttribute(SESSION_KEY_BACKLINKS, map);
      }

      Map<String, BackLinkReport> map = (Map<String, BackLinkReport>) session.getAttribute(SESSION_KEY_BACKLINKS);
      BackLinkReport linkReport = new BackLinkReport(reportId, adjustedReport, outputFormat);

      String backlinkId = req.getParameter(PARAMETER_BACKLINK_ID);
      linkReport.setPredecessor(backlinkId);

      map.put(linkReport.getId().toString(), linkReport);
      session.setAttribute(SESSION_KEY_BACKLINKS, map);

      return linkReport.getId();
   }

   private void requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      /* store data in session */
      HttpSession session = req.getSession();

      String reqUrl = req.getRequestURL().toString();
      String queryString = req.getQueryString();

      if (queryString != null && !queryString.isEmpty()) {
         reqUrl += "?" + queryString;
      }

//		long reportId = getReportId(req);
//		String format = getOutputFormatFromRequest(req);
//		Report report = getReportFromRequest(req);
//		
//		session.setAttribute(SESSION_KEY_OUTPUT_FORMAT, format);
//		session.setAttribute(SESSION_KEY_REPORT_ID, reportId);
//		session.setAttribute(SESSION_KEY_ADJUSTED_REPORT, report);
//		session.setAttribute(SESSION_KEY_EXECUTOR_CONFIGS, getConfigsFromRequest(report, req));
//		session.setAttribute(SESSION_KEY_DOWNLOAD, req.getParameter("download"));

      String url = req.getContextPath() + "/ReportServer.html?redir=" + URLEncoder.encode(reqUrl, "UTF-8");
      resp.sendRedirect(url);
   }

   private long getReportId(HttpServletRequest req) {
      if (null != req.getParameter("id")) { //$NON-NLS-1$
         return Long.valueOf(req.getParameter("id")); //$NON-NLS-1$
      }

      if (null != req.getParameter("key")) { //$NON-NLS-1$
         return reportService.get().getReportIdFromKey(req.getParameter("key")); //$NON-NLS-1$
      }

      return -1;
   }

   private Report getReportFromRequest(final HttpServletRequest req) {
      long reportId = getReportId(req);

      final Report report = reportService.get().getUnmanagedReportById(reportId);

      hookHandlerProvider.get().getHookers(ConfigureReportViaHttpRequestHook.class)
            .forEach(adjuster -> adjuster.adjustReport(report, req));

      return report;
   }

   private String getOutputFormatFromRequest(HttpServletRequest req) {
      String outputFormat = req.getParameter("format"); //$NON-NLS-1$
      if (null == outputFormat)
         outputFormat = "HTML";
      else
         outputFormat = outputFormat.toUpperCase();

      return outputFormat;
   }

   private boolean isAuthenticated() {
      return authenticatorServiceProvider.get().isAuthenticated();
   }

   protected User getUser(HttpServletRequest req) {
      AuthenticatorService authService = authenticatorServiceProvider.get();
      return authService.getCurrentUser();
   }

}
