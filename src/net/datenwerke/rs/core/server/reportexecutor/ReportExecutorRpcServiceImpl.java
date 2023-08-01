package net.datenwerke.rs.core.server.reportexecutor;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.model.KeyValueBaseModel;
import net.datenwerke.gxtdto.client.model.SuccessIndicatorBaseModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.client.reportexecutor.rpc.ReportExecutorRpcService;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.server.reportexport.helper.ReportSessionCache;
import net.datenwerke.rs.core.server.reportexport.helper.ReportSessionCacheEntry;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportPreviewSelectorHook;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledPngReport;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.HasPages;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECExecStored;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECFirstPage;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.AdjustReportForExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHistoryLocationHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutorExecuteAsHooker;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class ReportExecutorRpcServiceImpl extends SecuredRemoteServiceServlet implements ReportExecutorRpcService {

   private static final String PREVIEW_MODE_USER_PROPERTY = "preview:mode";

   private static final String PREVIEW_MODE_CFG_KEY = "pdf.mode";

   private static final String PREVIEW_DEFAULT_COLUMN_WIDTH = "dynamicList.defaultColumnWidth";
   private static final String PREVIEW_MAX_COLUMN_WIDTH = "dynamicList.maxColumnWidth";
   
   private static final String WARN_EXPORT_THRESHOLD = "export.warnthreshold";

   /**
    * 
    */
   private static final long serialVersionUID = 4488686689998311711L;

   private final ReportExecutorService reportExecutor;
   private final ReportService reportService;
   private final DtoService dtoService;
   private final HookHandlerService hookHandler;
   private final SecurityService securityService;
   private final ReportDtoService reportDtoService;
   private final UserPropertiesService userPropertiesService;
   private final Provider<AuthenticatorService> authenticatorService;
   protected final Provider<ReportSessionCache> sessionCacheProvider;

   private UserManagerService userManagerService;

   private ConfigService configService;

   private Provider<HttpServletRequest> servletRequest;

   private EntityClonerService entityCloner;

   @Inject
   public ReportExecutorRpcServiceImpl(
         ReportExecutorService reportExecutor, 
         ReportService reportService,
         ReportDtoService reportDtoService, 
         DtoService dtoService, 
         HookHandlerService hookHandler,
         SecurityService securityService, 
         UserPropertiesService userPropertiesService, 
         Provider<AuthenticatorService> authenticatorService,
         UserManagerService userManagerService, 
         ConfigService configService,
         EntityClonerService entityCloner, 
         Provider<HttpServletRequest> servletRequest,
         Provider<ReportSessionCache> sessionCacheProvider
         ) {

      this.reportExecutor = reportExecutor;
      this.reportService = reportService;
      this.reportDtoService = reportDtoService;
      this.dtoService = dtoService;
      this.hookHandler = hookHandler;
      this.securityService = securityService;
      this.userPropertiesService = userPropertiesService;
      this.authenticatorService = authenticatorService;
      this.userManagerService = userManagerService;
      this.configService = configService;
      this.entityCloner = entityCloner;
      this.servletRequest = servletRequest;
      this.sessionCacheProvider = sessionCacheProvider;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public ReportDto createNewVariant(@Named("report") ReportDto reportDto, String executeToken, String title,
         String description) throws ServerCallFailedException {
      Report referenceReport = (Report) reportService.getReportById(reportDto.getId());

      /* check permissions */
      securityService.assertRights(referenceReport, Write.class, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      Report variant = referenceReport.createNewVariant(adjustedReport);

      variant.setName(title);
      variant.setDescription(description);

      reportService.prepareVariantForStorage((ReportVariant) variant, executeToken);

      /* persist variant */
      reportService.persist(variant);

      return loadFullDtoForExecution(variant);
   }

   /**
    * Security is checked inside
    */
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public ReportDto editVariant(ReportDto reportVariantDto, String executeToken, String title, String description)
         throws ServerCallFailedException {
      Report referenceVariant = (Report) reportService.getReportById(reportVariantDto.getId());

      if (!(referenceVariant instanceof ReportVariant))
         throw new IllegalArgumentException("Report is not a variant"); //$NON-NLS-1$

      /* test rights */
      securityService.assertRights(referenceVariant.getParent(), Write.class, Execute.class);

      /* validate the passed report */
      // TODO: validation

      reportService.prepareVariantForEdit((ReportVariant) referenceVariant, reportVariantDto, executeToken);

      /* merge dto back to report */
      dtoService.mergePoso(reportVariantDto, referenceVariant);
      reportService.prepareVariantForStorage((ReportVariant) referenceVariant, executeToken);
      referenceVariant.setName(title);
      referenceVariant.setDescription(description);

      /* merge variant */
      reportService.merge(referenceVariant);

      return loadFullDtoForExecution(referenceVariant);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void deleteVariant(ReportDto reportVariantDto) throws ServerCallFailedException {
      Report referenceVariant = (Report) reportService.getReportById(reportVariantDto.getId());

      /* test rights */
      securityService.assertRights(referenceVariant.getParent(), Write.class, Delete.class, Execute.class);

      reportService.remove(referenceVariant);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public DwModel executeAs(String format, String executeToken, @Named("report") ReportDto report, DwModel config)
         throws ServerCallFailedException {
      for (ReportExecutorExecuteAsHooker hooker : hookHandler.getHookers(ReportExecutorExecuteAsHooker.class)) {
         if (hooker.consumes(report, format)) {
            User currentUser = authenticatorService.get().getCurrentUser();

            ReportExecutorExecuteAsHooker.ExecuteConfig execConfig = hooker.getConfig(report, currentUser, format,
                  config);

            /* add executeToken into config */
            Collection<ReportExecutionConfig> configs = execConfig.getConfig();
            configs.add(new RECReportExecutorToken(executeToken));

            report = hooker.adjustReport(report, currentUser, format, configs);
            Object res = execute(report, execConfig.getFormat(), configs);

            return hooker.adjustResult(report, currentUser, configs, res);
         }
      }

      return null;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public SuccessIndicatorBaseModel storePNGInSession(String executorToken, @Named("report") ReportDto reportDto)
         throws ServerCallFailedException {
      if (null == reportDto.getId())
         throw new RuntimeException(
               new ReportExecutorException(ReportManagerMessages.INSTANCE.exceptionReportCouldNotBeLoaded()));

      Report referenceReport = reportDtoService.getReferenceReport(reportDto);

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

      /* copy data */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      referenceReport = referenceReport.createTemporaryVariant(adjustedReport);

      try {
         CompiledPngReport res = (CompiledPngReport) reportExecutor.execute(referenceReport,
               ReportExecutorService.OUTPUT_FORMAT_PNG, new RECFirstPage());

         /* store in session */
         HttpServletRequest request = this.getThreadLocalRequest();
         HttpSession session = request.getSession();

         SuccessIndicatorBaseModel bm = new SuccessIndicatorBaseModel();
         bm.setSuccess(false);

         ReportSessionCacheEntry entry = new ReportSessionCacheEntry();

         if (null != res.getReport() && res.getReport().length >= 1) {
            entry.setImage(res.getReport()[0]);
            bm.setSuccess(true);
         }

         sessionCacheProvider.get().put(executorToken, entry);

         if (res instanceof HasPages)
            bm.addData(new KeyValueBaseModel<String>("", ((HasPages) res).getPages() + ""));

         return bm;
      } catch (ReportExecutorException e) {
         throw new NonFatalException(e);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Execute.class)) })
   @Transactional(rollbackOn = { Exception.class })
   public ReportDto loadFullReportForExecution(@Named("node") ReportDto report) {
      /* get real report */
      AbstractReportManagerNode realReport = reportService.getNodeById(report.getId());

      if (!(realReport instanceof Report))
         throw new IllegalArgumentException();

      ReportDto fullDtoForExecution = loadFullDtoForExecution((Report) realReport);

      return fullDtoForExecution;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public ReportDto loadReportForExecutionFrom(HistoryLocation location) throws ServerCallFailedException {
      Report report = null;

      /* get report */
      String key = location.getParameterValue("key");
      if (null != key)
         report = reportService.getReportByKey(key);
      if (null == report) {
         String id = location.getParameterValue("id");
         if (null != id)
            report = reportService.getReportById(Long.valueOf(id));
      }
      if (null == report) {
         String uuid = location.getParameterValue("uuid");
         if (null != uuid)
            report = reportService.getReportByUUID(uuid);
      }
      if (null == report) {
         String path = location.getParameterValue("path");
         if (null != path) {
            AbstractReportManagerNode node = reportService
                  .getNodeByPath(path.replace("%2F", "/").replace("%2f", "/").replace("%20", " "));
            if (node instanceof Report)
               report = (Report) node;
         }
      }
      if (null == report)
         throw new ExpectedException("Could not find report. A valid key, id, path or uuid must be specified.");

      Report referenceReport = report instanceof ReportVariant ? ((ReportVariant) report).getBaseReport() : report;

      /* check security rights */
      if (!securityService.checkRights(referenceReport, Execute.class))
         throw new ViolatedSecurityExceptionDto();

      if (location.getParameterNames().size() == 1)
         return loadFullDtoForExecution(report);

      /* create variant */
      Report variant = report.createTemporaryVariant();

      /* adjust variant */
      for (ConfigureReportViaHistoryLocationHook adjuster : hookHandler
            .getHookers(ConfigureReportViaHistoryLocationHook.class))
         adjuster.adjustReport(variant, location);

      /* create dto and store it */
      ReportDto variantDto = loadFullDtoForExecution(variant);

      /* set id of original report */
      variantDto.setId(report.getId());

      return variantDto;
   }

   private ReportDto loadFullDtoForExecution(final Report realReport) {

      /* load metadata */
      hookHandler.getHookers(AdjustReportForExecutionHook.class)
         .forEach(adjuster -> adjuster.adjust(realReport));

      final ReportDto tmpReport = (ReportDto) dtoService.createDto(realReport);

      /* if report is a variant we need the full base report as well */
      if (realReport instanceof ReportVariant)
         ((ReportVariantDto) tmpReport).setBaseReport((ReportDto) dtoService.createDto(realReport.getParent()));

      hookHandler.getHookers(AdjustReportForExecutionHook.class)
         .forEach(adjuster -> adjuster.adjust(tmpReport));

      return tmpReport;
   }

   @Override
   public ReportDto loadFullReportUnmanaged(ReportDto report) {
      /* get real report */
      AbstractReportManagerNode realReport = reportService.getNodeById(report.getId());
      AbstractReportManagerNode unmanagedReport = entityCloner.cloneEntity(realReport);

      if (!(unmanagedReport instanceof Report))
         throw new IllegalArgumentException();

      ReportDto fullDtoForExecution = loadFullDtoForExecution((Report) unmanagedReport);

      return fullDtoForExecution;
   }

   private Object execute(@Named("report") ReportDto reportDto, String outputFormat,
         Collection<ReportExecutionConfig> configs) throws ServerCallFailedException {
      boolean executeAsStored = false;
      configs = (null == configs) ? new ArrayList<ReportExecutionConfig>() : configs;
      for (ReportExecutionConfig cfg : configs) {
         if (cfg instanceof RECExecStored)
            executeAsStored = true;
      }

      Report referenceReport;

      if (executeAsStored) {
         /* reload the report */
         referenceReport = reportService.getReportById(reportDto.getId());
      } else {
         /* Load base report and apply variant configuration */

         /* get reference report */
         referenceReport = reportDtoService.getReferenceReport(reportDto);

         if (null == referenceReport)
            throw new ExpectedException("Could not load report");

         /* check rights */
         securityService.assertRights(referenceReport, Execute.class);

         /* create variant */
         Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
         referenceReport = referenceReport.createTemporaryVariant(adjustedReport);
      }

      try {
         // TODO Proper error handling
         Object res = reportExecutor.execute(referenceReport, outputFormat,
               configs.toArray(new ReportExecutionConfig[] {}));
         return res;
      } catch (ReportExecutorException e) {
         throw new ExpectedException(e);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

   }

   public void dummy(SuccessIndicatorBaseModel success) {
      /* needed for whitelisting */
   }

   @Override
   @SecurityChecked(loginRequired = false)
   public String getPreviewMode() throws ServerCallFailedException {
      String usrVal = null;
      String cfgVal = null;
      String autoVal = "image";

      if (authenticatorService.get().isAuthenticated()) {
         User currentUser = authenticatorService.get().getCurrentUser();
         usrVal = userPropertiesService.getPropertyValue(currentUser, PREVIEW_MODE_USER_PROPERTY);

         /* ignore usrVal if "default" so cfgVal is used instead */
         if ("default".equals(usrVal))
            usrVal = null;
      }
      cfgVal = configService.getConfigFailsafe("ui/previews.cf").getString(PREVIEW_MODE_CFG_KEY);

      HttpServletRequest request = servletRequest.get();

      String res = (null != usrVal) ? usrVal : (null != cfgVal) ? cfgVal : autoVal;

      for (ReportPreviewSelectorHook hook : hookHandler.getHookers(ReportPreviewSelectorHook.class)) {
         res = hook.postprocessPreviewSelection(usrVal, cfgVal, autoVal, request, res);
      }

      return res;
   }

   @Override
   @Transactional(rollbackOn = Exception.class)
   public void setPreviewModeUserProperty(String value) {
      User currentUser = authenticatorService.get().getCurrentUser();
      userPropertiesService.setPropertyValue(currentUser, PREVIEW_MODE_USER_PROPERTY, value);

      userManagerService.merge(currentUser);
   }

   @Override
   public Integer getDefaultColumnWidth() throws ServerCallFailedException {
      return configService.getConfigFailsafe("ui/previews.cf").getInt(PREVIEW_DEFAULT_COLUMN_WIDTH, 200);
   }

   @Override
   public Integer getMaxColumnWidth() throws ServerCallFailedException {
      return configService.getConfigFailsafe("ui/previews.cf").getInt(PREVIEW_MAX_COLUMN_WIDTH, 1000);
   }

   @Override
   public Integer getWarnRecordExportThreshold() throws ServerCallFailedException {
      return configService.getConfigFailsafe("main/main.cf").getInt(WARN_EXPORT_THRESHOLD, 10000);
   }

}
