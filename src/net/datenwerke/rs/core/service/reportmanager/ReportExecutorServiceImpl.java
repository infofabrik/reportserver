package net.datenwerke.rs.core.service.reportmanager;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.base.service.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngines;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECSetExecutionUUID;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECSinglePage;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.events.ReportExecutedEvent;
import net.datenwerke.rs.core.service.reportmanager.events.ReportExecutionFailedEvent;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutionNotificationHook;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.dsbundle.service.dsbundle.SimpleDatasourceBundleService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskServiceImpl;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * 
 *
 */
public class ReportExecutorServiceImpl implements ReportExecutorService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<Set<Class<? extends ReportEngine>>> reportEnginesProvider;
   private final Injector injector;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final HookHandlerService hookHandler;
   private final EventBus eventBus;
   private final Provider<ExceptionServices> exceptionServiceProvider;
   private final Provider<SimpleDatasourceBundleService> bundleServiceProvider;
   private final Provider<TsDiskServiceImpl> tsDiskServiceProvider;
   private final Provider<JasperUtilsService> jasperUtilsServiceProvider;
   private final Provider<UserManagerService> userManagerServiceProvider;
   private final Provider<FilterService> filterServiceProvider;

   private final static String NOW_FORMAT = "yyyy-MM-dd-HH-mm-ss";

   private Map<UUID, ReportExecutorJob> jobMap = new HashMap<UUID, ReportExecutorJob>();

   private final ParameterSetFactory parameterSetFactory;

   @Inject
   public ReportExecutorServiceImpl(@ReportEngines Provider<Set<Class<? extends ReportEngine>>> reportEnginesProvider,
         Injector injector, Provider<AuthenticatorService> authenticatorServiceProvider, HookHandlerService hookHandler,
         Provider<ExceptionServices> exceptionServiceProvider, EventBus eventBus,
         Provider<SimpleDatasourceBundleService> bundleServiceProvider,
         Provider<TsDiskServiceImpl> tsDiskServiceProvider, Provider<JasperUtilsService> jasperUtilsServiceProvider,
         Provider<UserManagerService> userManagerServiceProvider, ParameterSetFactory parameterSetFactory,
         Provider<FilterService> filterServiceProvider) {

      /* store objects */
      this.injector = injector;
      this.reportEnginesProvider = reportEnginesProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.hookHandler = hookHandler;
      this.exceptionServiceProvider = exceptionServiceProvider;
      this.eventBus = eventBus;
      this.bundleServiceProvider = bundleServiceProvider;
      this.tsDiskServiceProvider = tsDiskServiceProvider;
      this.jasperUtilsServiceProvider = jasperUtilsServiceProvider;
      this.userManagerServiceProvider = userManagerServiceProvider;
      this.parameterSetFactory = parameterSetFactory;
      this.filterServiceProvider = filterServiceProvider;
   }

   @Override
   public CompiledReport execute(Report report, String outputFormat) throws ReportExecutorException {
      return execute(report, outputFormat, ReportExecutionConfig.EMPTY_CONFIG);
   }

   @Override
   public CompiledReport execute(Report report, ParameterSet parameterSet, String outputFormat)
         throws ReportExecutorException {
      return execute(report, parameterSet, outputFormat, ReportExecutionConfig.EMPTY_CONFIG);
   }

   @Override
   public CompiledReport execute(Report report, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException {
      AuthenticatorService authenticatorService = authenticatorServiceProvider.get();
      User user = authenticatorService.getCurrentUser();
      return execute(report, null, user, outputFormat, configs);
   }

   @Override
   public CompiledReport execute(Report report, ParameterSet parameterSet, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException {
      AuthenticatorService authenticatorService = authenticatorServiceProvider.get();
      User user = authenticatorService.getCurrentUser();
      return execute(report, parameterSet, user, outputFormat, configs);
   }

   @Override
   public CompiledReport execute(Report report, User user, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException {
      return execute(report, null, user, outputFormat, configs);
   }

   @Override
   public CompiledReport execute(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException {
      return execute(null, report, parameterSet, user, outputFormat, configs);
   }

   /**
    * Loops over the list of registered report engines and executes the report with
    * the corresponding engine.
    */
   @Override
   public CompiledReport execute(OutputStream os, Report report, ParameterSet parameterSet, User user,
         String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException {
      return execute(false, os, report, parameterSet, user, outputFormat, configs);
   }

   @Override
   public CompiledReport executeDry(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException {
      return execute(true, null, report, parameterSet, user, outputFormat, configs);
   }

   @Override
   public boolean supportsStreaming(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException {
      for (Class<? extends ReportEngine> componentClass : reportEnginesProvider.get()) {
         ReportEngine e = injector.getInstance(componentClass);

         if (e.consumes(report))
            return e.supportsStreaming(report, parameterSet, user, outputFormat, configs);
      }
      return false;
   }

   protected CompiledReport execute(final boolean dry, final OutputStream os, final Report report,
         final ParameterSet parameterSet, final User user, final String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException {
      /* Create uuid to trace report execution in log */
      String uuid = UUID.randomUUID().toString();

      String token = null;
      for (ReportExecutionConfig recc : configs) {
         if (recc instanceof RECReportExecutorToken)
            token = ((RECReportExecutorToken) recc).getToken();
      }
//		System.out.println("token: " + token);

      ParameterSet ps = parameterSetFactory.create(user, report);
      ps.addAll(report.getParameterInstances());
      ps.addVariable("_RS_REX_TOKEN", token);

      if (null != parameterSet)
         ps.add(parameterSet);

      Map<String, Object> reportConfiguration = new HashMap<>();

      List<ReportExecutionNotificationHook> notificees = hookHandler.getHookers(ReportExecutionNotificationHook.class);

      try {
         Map<String, Object> configParameters = ps.getCompleteConfiguration(true, true);
         reportConfiguration.put("configuration_parameters", configParameters);

         if (report instanceof TableReport) {
            reportConfiguration.put("filters", filterServiceProvider.get().getFilterMap((TableReport) report));
            reportConfiguration.put("prefilters", filterServiceProvider.get().getPrefilterMap((TableReport) report));
         }

         if (!dry) {
            eventBus.fireEvent(new ReportExecutedEvent("report_id",
                  null == report.getId() ? report.getOldTransientId() : report.getId(), "executing_user", user.getId(),
                  "output_format", outputFormat, "uuid", uuid, "token", token, "report_configuration",
                  new ObjectMapper().writeValueAsString(reportConfiguration)));
         }

         /* sanitize configs */
         if (null == configs)
            configs = new ReportExecutionConfig[] {};

         if (!dry) {
            for (ReportExecutionNotificationHook notificee : notificees)
               notificee.notifyOfReportExecution(report, parameterSet, user, outputFormat, configs);
         }

         if (!dry) {
            for (ReportExecutionNotificationHook notificee : notificees)
               notificee.doVetoReportExecution(report, parameterSet, user, outputFormat, configs);
         }

         for (Class<? extends ReportEngine> componentClass : reportEnginesProvider.get()) {
            ReportEngine e = injector.getInstance(componentClass);

            if (e.consumes(report)) {
               CompiledReport compiledReport;

               if (!dry) {
                  ArrayList<ReportExecutionConfig> cfgList = new ArrayList<ReportExecutionConfig>();
                  cfgList.addAll(Arrays.asList(configs));
                  cfgList.add(new RECSetExecutionUUID(uuid));
                  compiledReport = e.execute(os, report, parameterSet, user, outputFormat,
                        cfgList.toArray(new ReportExecutionConfig[0]));
               } else
                  compiledReport = e.executeDry(os, report, parameterSet, user, outputFormat, configs);

               if (!dry) {
                  for (ReportExecutionNotificationHook notificee : notificees)
                     notificee.notifyOfReportsSuccessfulExecution(compiledReport, report, parameterSet, user,
                           outputFormat, configs);
               }

               return compiledReport;
            }
         }
      } catch (ReportExecutorException e) {
         if (!dry) {
            /* fire event */
            eventBus.fireEvent(new ReportExecutionFailedEvent("report_id",
                  null == report.getId() ? report.getOldTransientId() : report.getId(), "executing_user", user.getId(),
                  "output_format", outputFormat, "error_msg", e.getMessage(), "error_st",
                  exceptionServiceProvider.get().exceptionToString(e), "uuid", uuid));

            /* notify hookers */
            for (ReportExecutionNotificationHook notificee : notificees)
               notificee.notifyOfReportsUnsuccessfulExecution(e, report, parameterSet, user, outputFormat, configs);
         }

         throw e;
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
         ReportExecutorException rsre = new ReportExecutorException(
               ReportManagerMessages.INSTANCE.exceptionReportCouldNotBeExecuted(e.getLocalizedMessage()), e);

         if (!dry) {
            /* fire event */
            eventBus.fireEvent(new ReportExecutionFailedEvent("report_id",
                  null == report.getId() ? report.getOldTransientId() : report.getId(), "executing_user", user.getId(),
                  "output_format", outputFormat, "error_msg", e.getMessage(), "error_st",
                  exceptionServiceProvider.get().exceptionToString(rsre), "uuid", uuid));

            /* notify hookers */
            for (ReportExecutionNotificationHook notificee : notificees)
               notificee.notifyOfReportsUnsuccessfulExecution(rsre, report, parameterSet, user, outputFormat, configs);
         }

         throw rsre;
      }

      throw new IllegalArgumentException("Reporttype " + report.getClass().getName() + " seems not to be supported."); //$NON-NLS-1$ //$NON-NLS-2$
   }

   @Override
   public CompiledReportMetadata exportMetadata(Report report, String outputFormat) throws ReportExecutorException {
      AuthenticatorService authenticatorService = authenticatorServiceProvider.get();
      User user = authenticatorService.getCurrentUser();
      return exportMetadata(report, user, null, outputFormat);
   }

   @Override
   public CompiledReportMetadata exportMetadata(Report report, User user, ParameterSet parameterSet,
         String outputFormat) throws ReportExecutorException {
      try {
         for (Class<? extends ReportEngine> componentClass : reportEnginesProvider.get()) {
            ReportEngine e = injector.getInstance(componentClass);

            if (e.consumes(report))
               return e.exportMetadata(report, parameterSet, user, outputFormat);
         }
      } catch (Exception e) {
         throw new ReportExecutorException(
               ReportManagerMessages.INSTANCE.exceptionReportCouldNotBeExecuted(e.getLocalizedMessage()), e);
      }

      throw new IllegalArgumentException("Reporttype " + report.getClass().getName() + " seems not to be supported."); //$NON-NLS-1$ //$NON-NLS-2$
   }

   @Override
   public void isExecutable(Report report, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException {
      isExecutable(report, authenticatorServiceProvider.get().getCurrentUser(), outputFormat, configs);
   }

   @Override
   public void isExecutable(Report report, User user, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException {
      isExecutable(report, null, user, outputFormat, configs);
   }

   @Override
   public void isExecutable(Report report, ParameterSet additionalParameters, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException {

      for (Class<? extends ReportEngine> componentClass : reportEnginesProvider.get()) {
         ReportEngine e = injector.getInstance(componentClass);

         if (e.consumes(report))
            e.isExecutable(report, additionalParameters, user, outputFormat, configs);
      }
   }

   @Override
   public List<Map<String, Object>> variantTest(Report report, List<DatasourceDefinition> datasources) {
      List<Map<String, Object>> testResultList = new ArrayList<>();
      Set<DatasourceDefinition> allDatasources = bundleServiceProvider.get().getAllDatasources(report,
            bundleServiceProvider.get().getAvailableBundleKeys());

      if (!allDatasources.containsAll(datasources))
         throw new IllegalArgumentException("The given datasources are not valid for this report");

      /* if no given datasources, test all */
      for (DatasourceDefinition datasource : (!datasources.isEmpty() ? datasources : allDatasources))
         doVariantTestForDataSource(report, datasource, testResultList);

      return testResultList;
   }

   private void doVariantTestForDataSource(Report report, DatasourceDefinition datasource,
         List<Map<String, Object>> testResultList) {
      Map<String, Object> testResults = new LinkedHashMap<>();
      DatasourceDefinition oldDatasource = report.getDatasourceContainer().getDatasource();
      report.getDatasourceContainer().setDatasource(datasource);
      /* store variant data to show in result */
      Long reportId = report.getId();
      String reportName = report.getName();
      Object reportPath = mkPath(report);
      String reportClass = report.getClass().getName();
      String reportQuery = null;
      String executionResult = null;
      String executionError = null;
      String executionShortError = null;

      Long baseReportId = null;
      String baseReportName = null;
      Object baseReportPath = null;
      String baseReportClass = null;

      if (report instanceof ReportVariant) {
         Report baseReport = ((ReportVariant) report).getBaseReport();
         if (null != baseReport) {
            baseReportId = baseReport.getId();
            baseReportName = baseReport.getName();
            baseReportPath = mkPath(baseReport);
            baseReportClass = baseReport.getClass().getName();
         }
      }

      String teamspaces = "";
      for (TsDiskReportReference reportReference : tsDiskServiceProvider.get().getReferencesTo(report)) {
         teamspaces += tsDiskServiceProvider.get().getTeamSpaceFor(reportReference).getName() + ": "
               + mkPath(reportReference) + "\n";
      }
      /* time execution */
      String executionStart = new SimpleDateFormat(NOW_FORMAT).format(new Date());
      try {
         /* fetch and store report query (TableReport) */
         if (report instanceof TableReport && !(report instanceof ReportVariant)) {
            reportQuery = ((DatabaseDatasourceConfig) report.getDatasourceContainer().getDatasourceConfig()).getQuery();
            ((TableReport) report).setSelectAllColumns(true);
         }

         /* fetch and store report query (JasperReport) */
         if (report instanceof JasperReport)
            reportQuery = jasperUtilsServiceProvider.get()
                  .getQueryFromJRXML(((JasperReport) report).getMasterFile().getContent());

         /* execute the report */
         Long currentUserId = authenticatorServiceProvider.get().getCurrentUser().getId();
         User currentUser = (User) userManagerServiceProvider.get().getNodeById(currentUserId);
         execute(report, currentUser, ReportExecutorService.OUTPUT_FORMAT_HTML, new RECSinglePage(1, 1));

         /* record success */
         executionResult = "SUCCESS";
      } catch (Exception e) {
         /* Store data from exception */
         executionShortError = ExceptionUtils.getRootCauseMessage(e);
         executionError = exceptionServiceProvider.get().exceptionToString(e);
         executionResult = "FAILURE";
      } finally {
         /* log error results */
         Long datasourceId = report.getDatasourceContainer().getDatasource().getId();
         String datasourceName = report.getDatasourceContainer().getDatasource().getName();

         String now = new SimpleDateFormat(NOW_FORMAT).format(new Date());
         testResults.put("RUN", now);
         testResults.put("REPORT_ID", reportId);
         testResults.put("REPORT_NAME", reportName);
         testResults.put("REPORT_PATH", reportPath);
         testResults.put("REPORT_TYPE", reportClass);
         if (null != baseReportId)
            testResults.put("REPORT_BASE_ID", baseReportId);
         if (null != baseReportName)
            testResults.put("REPORT_BASE_NAME", baseReportName);
         if (null != baseReportName)
            testResults.put("REPORT_BASE_PATH", baseReportPath);
         if (null != baseReportClass)
            testResults.put("REPORT_BASE_TYPE", baseReportClass);
         testResults.put("STATUS", executionResult);
         if (null != executionShortError)
            testResults.put("SHORT_ERROR", executionShortError);
         if (null != executionError)
            testResults.put("ERROR", executionError);
         if (null != reportQuery)
            testResults.put("QUERY", reportQuery);
         testResults.put("DATASOURCE_ID", datasourceId);
         testResults.put("DATASOURCE", datasourceName);
         testResults.put("TEAMSPACES", teamspaces);
         testResults.put("START_TSP", executionStart);
         String executionEnd = new SimpleDateFormat(NOW_FORMAT).format(new Date());
         testResults.put("END_TSP", executionEnd);
      }
      report.getDatasourceContainer().setDatasource(oldDatasource);
      testResultList.add(testResults);
   }

   private String mkPath(AbstractNode<?> elem) {
      if (elem.getParent() != null) {
         return mkPath(elem.getParent()) + "/" + elem.getNodeName();
      } else
         return "";
   }

}
