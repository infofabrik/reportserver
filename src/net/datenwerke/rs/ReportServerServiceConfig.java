package net.datenwerke.rs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import net.datenwerke.async.DwAsyncModule;
import net.datenwerke.dbpool.DbPoolModule;
import net.datenwerke.eximport.ExImportModule;
import net.datenwerke.gf.DwGwtFrameworkBase;
import net.datenwerke.gf.server.config.ClientConfigRpcServiceImpl;
import net.datenwerke.gf.server.download.FileDownloadServlet;
import net.datenwerke.gf.server.fileselection.FileSelectionRpcServiceImpl;
import net.datenwerke.gf.server.history.HistoryRpcServiceImpl;
import net.datenwerke.gf.server.homepage.HomepageRpcServiceImpl;
import net.datenwerke.gf.server.juel.JuelRpcServiceImpl;
import net.datenwerke.gf.server.localization.LocalizationRpcServiceImpl;
import net.datenwerke.gf.server.theme.RsThemeServlet;
import net.datenwerke.gf.server.theme.ThemeRpcServiceImpl;
import net.datenwerke.gf.server.upload.FileUploadRpcServiceImpl;
import net.datenwerke.gf.server.upload.FileUploadServlet;
import net.datenwerke.gf.service.DwGwtFrameworkModule;
import net.datenwerke.gf.service.authenticator.AuthenticatorModule;
import net.datenwerke.gf.service.dtoservice.DtoModule;
import net.datenwerke.gf.service.gwtstacktrace.GWTStacktraceModule;
import net.datenwerke.gf.service.history.HistoryModule;
import net.datenwerke.gf.service.lateinit.LateInitModule;
import net.datenwerke.gf.service.localization.LocalizationModule;
import net.datenwerke.gf.service.maintenance.MaintenanceModule;
import net.datenwerke.gf.service.properties.PropertiesModule;
import net.datenwerke.hookhandler.service.hookhandler.HookHandlerModule;
import net.datenwerke.rs.adminutils.server.datasourcetester.DatasourceTesterRPCServiceImpl;
import net.datenwerke.rs.adminutils.server.logs.LogFilesRpcServiceImpl;
import net.datenwerke.rs.adminutils.server.suuser.SuUserRpcServiceImpl;
import net.datenwerke.rs.adminutils.server.systemconsole.connpool.ConnectionPoolConsoleDummyRpcServiceImpl;
import net.datenwerke.rs.adminutils.server.systemconsole.generalinfo.GeneralInfoRpcDummyServiceImpl;
import net.datenwerke.rs.adminutils.server.systemconsole.memory.MemoryConsoleDummyRpcServiceImpl;
import net.datenwerke.rs.adminutils.service.SystemConsoleModule;
import net.datenwerke.rs.adminutils.service.datasourcetester.DatasourceTesterModule;
import net.datenwerke.rs.adminutils.service.logs.LogFilesModule;
import net.datenwerke.rs.adminutils.service.su.SuModule;
import net.datenwerke.rs.amazons3.server.amazons3.AmazonS3RpcServiceImpl;
import net.datenwerke.rs.amazons3.service.amazons3.AmazonS3Module;
import net.datenwerke.rs.authenticator.cr.server.ChallengeResponseRpcServiceImpl;
import net.datenwerke.rs.authenticator.cr.service.ChallengeResponseModule;
import net.datenwerke.rs.authenticator.server.LoginHandlerImpl;
import net.datenwerke.rs.base.ext.server.dashboardmanager.DashboardManagerExportRpcServiceImpl;
import net.datenwerke.rs.base.ext.server.dashboardmanager.DashboardManagerImportRpcServiceImpl;
import net.datenwerke.rs.base.ext.server.datasinkmanager.DatasinkManagerExportRpcServiceImpl;
import net.datenwerke.rs.base.ext.server.datasinkmanager.DatasinkManagerImportRpcServiceImpl;
import net.datenwerke.rs.base.ext.server.datasourcemanager.DatasourceManagerExportRpcServiceImpl;
import net.datenwerke.rs.base.ext.server.datasourcemanager.DatasourceManagerImportRpcServiceImpl;
import net.datenwerke.rs.base.ext.server.reportmanager.ReportManagerExportRpcServiceImpl;
import net.datenwerke.rs.base.ext.server.reportmanager.ReportManagerImportRpcServiceImpl;
import net.datenwerke.rs.base.ext.service.RsBaseExtModule;
import net.datenwerke.rs.base.server.datasources.BaseDatasourceRpcServiceImpl;
import net.datenwerke.rs.base.server.datasources.StatementManagerRpcServiceImpl;
import net.datenwerke.rs.base.server.helpers.tempfile.TempFileServlet;
import net.datenwerke.rs.base.server.jasper.JRXMLDownloadServlet;
import net.datenwerke.rs.base.server.jasper.JasperReportRpcServiceImpl;
import net.datenwerke.rs.base.server.parameters.DatasourceParameterRPCServiceImpl;
import net.datenwerke.rs.base.server.table.TableReportUtilityServiceImpl;
import net.datenwerke.rs.base.service.RsBaseModule;
import net.datenwerke.rs.base.service.dbhelper.DBHelperModule;
import net.datenwerke.rs.birt.server.BirtReportFileDownloadServlet;
import net.datenwerke.rs.birt.server.BirtUtilsRpcServiceImpl;
import net.datenwerke.rs.birt.service.BirtModule;
import net.datenwerke.rs.box.server.box.BoxRpcServiceImpl;
import net.datenwerke.rs.box.service.box.BoxModule;
import net.datenwerke.rs.compiledreportstore.CompiledReportStoreModule;
import net.datenwerke.rs.computedcolumns.server.computedcolumns.ComputedColumnsRpcServiceImpl;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.ComputedColumnsModule;
import net.datenwerke.rs.condition.server.condition.ConditionRpcServiceImpl;
import net.datenwerke.rs.condition.service.condition.ConditionModule;
import net.datenwerke.rs.configservice.service.configservice.ConfigModule;
import net.datenwerke.rs.configservice.service.manservice.ManPageModule;
import net.datenwerke.rs.core.server.contexthelp.ContextHelpRpcServiceImpl;
import net.datenwerke.rs.core.server.datasinks.DatasinkManagerTreeHandlerRpcServiceImpl;
import net.datenwerke.rs.core.server.datasinks.DatasinkRpcServiceImpl;
import net.datenwerke.rs.core.server.datasources.DatasourceManagerTreeHandlerRpcServiceImpl;
import net.datenwerke.rs.core.server.i18ntools.I18nToolsRpcServiceImpl;
import net.datenwerke.rs.core.server.imageservice.ImageServlet;
import net.datenwerke.rs.core.server.parameters.ParameterRpcServiceImpl;
import net.datenwerke.rs.core.server.reportexecutor.JasperPreviewProvider;
import net.datenwerke.rs.core.server.reportexecutor.ReportExecutorRpcServiceImpl;
import net.datenwerke.rs.core.server.reportexport.ReportExportRpcServiceImpl;
import net.datenwerke.rs.core.server.reportexport.ReportExportServlet;
import net.datenwerke.rs.core.server.reportmanager.ReportManagerTreeHandlerImpl;
import net.datenwerke.rs.core.server.reportproperties.ReportPropertiesRpcServiceImpl;
import net.datenwerke.rs.core.server.sendto.SendToRpcServiceImpl;
import net.datenwerke.rs.core.server.urlview.UrlViewRpcServiceImpl;
import net.datenwerke.rs.core.server.versioninfo.VersionInfoServlet;
import net.datenwerke.rs.core.service.RsCoreModule;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceModule;
import net.datenwerke.rs.core.service.genrights.GenRightsModule;
import net.datenwerke.rs.core.service.i18ntools.RemoteMessageServiceImpl;
import net.datenwerke.rs.core.service.mail.MailModule;
import net.datenwerke.rs.core.service.parameters.ParameterModule;
import net.datenwerke.rs.core.service.reportmanager.ReportManagerModule;
import net.datenwerke.rs.core.service.reportproperties.ReportPropertiesModule;
import net.datenwerke.rs.core.service.reportserver.ReportServerModule;
import net.datenwerke.rs.crystal.server.crystal.CrystalUtilsRpcDummyServiceImpl;
import net.datenwerke.rs.dashboard.server.dashboard.DashboardRpcServiceImpl;
import net.datenwerke.rs.dashboard.server.dashboard.DashboardTreeRpcServiceImpl;
import net.datenwerke.rs.dashboard.service.dashboard.DashboardModule;
import net.datenwerke.rs.demo.DemoDataModule;
import net.datenwerke.rs.dropbox.server.dropbox.DropboxRpcServiceImpl;
import net.datenwerke.rs.dropbox.service.dropbox.DropboxModule;
import net.datenwerke.rs.emaildatasink.server.emaildatasink.EmailDatasinkRpcServiceImpl;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.EmailDatasinkModule;
import net.datenwerke.rs.enterprise.server.CommunityEnterpriseCheckRpcServiceImpl;
import net.datenwerke.rs.eximport.server.eximport.ImportRpcServiceImpl;
import net.datenwerke.rs.eximport.server.eximport.QuickExportDownloadServlet;
import net.datenwerke.rs.eximport.service.RsExImportModule;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.server.fileserver.FileServerAccessServlet;
import net.datenwerke.rs.fileserver.server.fileserver.FileServerImportRpcServiceImpl;
import net.datenwerke.rs.fileserver.server.fileserver.FileServerRpcServiceImpl;
import net.datenwerke.rs.fileserver.server.fileserver.IndexRedirectServlet;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerModule;
import net.datenwerke.rs.ftp.server.ftp.FtpRpcServiceImpl;
import net.datenwerke.rs.ftp.server.ftp.FtpsRpcServiceImpl;
import net.datenwerke.rs.ftp.server.ftp.SftpRpcServiceImpl;
import net.datenwerke.rs.ftp.service.ftp.FtpModule;
import net.datenwerke.rs.globalconstants.server.globalconstants.GlobalConstantsRpcServiceImpl;
import net.datenwerke.rs.globalconstants.service.GlobalConstantsModule;
import net.datenwerke.rs.googledrive.server.googledrive.GoogleDriveRpcServiceImpl;
import net.datenwerke.rs.googledrive.service.googledrive.GoogleDriveModule;
import net.datenwerke.rs.incubator.server.httpauthexecute.HttpAuthExecuteServlet;
import net.datenwerke.rs.incubator.server.jaspertotable.JasperToTableRpcServiceImpl;
import net.datenwerke.rs.incubator.server.jasperutils.JasperUtilsRpcServiceImpl;
import net.datenwerke.rs.incubator.server.reportmetadata.ReportMetadataRpcServiceImpl;
import net.datenwerke.rs.incubator.service.RsIncubatorModule;
import net.datenwerke.rs.incubator.service.aliascmd.AliasCmdModule;
import net.datenwerke.rs.incubator.service.exportmetadata.ExportMetadataModule;
import net.datenwerke.rs.incubator.service.filterreplacements.FilterReplacementsModule;
import net.datenwerke.rs.incubator.service.jaspertotable.JasperToTableModule;
import net.datenwerke.rs.incubator.service.notify.NotifyModule;
import net.datenwerke.rs.incubator.service.outputformatauth.OutputFormatAuthModule;
import net.datenwerke.rs.incubator.service.versioning.VersioningModule;
import net.datenwerke.rs.installation.ReportServerInstallationModule;
import net.datenwerke.rs.installation.RsInstallModule;
import net.datenwerke.rs.jxlsreport.server.JxlsReportFileDownloadServlet;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.JxlsReportModule;
import net.datenwerke.rs.license.server.LicenseRpcServiceImpl;
import net.datenwerke.rs.license.service.LicenseModule;
import net.datenwerke.rs.localfsdatasink.server.localfsdatasink.LocalFileSystemRpcServiceImpl;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.LocalFileSystemModule;
import net.datenwerke.rs.oauth.server.oauth.OAuthRpcServiceImpl;
import net.datenwerke.rs.oauth.server.oauth.OAuthServlet;
import net.datenwerke.rs.onedrive.server.onedrive.OneDriveRpcServiceImpl;
import net.datenwerke.rs.onedrive.service.onedrive.OneDriveModule;
import net.datenwerke.rs.passwordpolicy.server.AccountInhibitionRpcServiceImpl;
import net.datenwerke.rs.passwordpolicy.server.ActivateUserRpcServiceImpl;
import net.datenwerke.rs.passwordpolicy.server.LostPasswordRpcServiceImpl;
import net.datenwerke.rs.passwordpolicy.service.PasswordPolicyModule;
import net.datenwerke.rs.passwordpolicy.service.activateuser.ActivateUserModule;
import net.datenwerke.rs.printer.server.printer.PrinterRpcServiceImpl;
import net.datenwerke.rs.printer.service.printer.PrinterModule;
import net.datenwerke.rs.remoteaccess.service.RemoteAccessModule;
import net.datenwerke.rs.reportdoc.server.ReportDocumentationServlet;
import net.datenwerke.rs.reportdoc.service.ReportDocumentationModule;
import net.datenwerke.rs.saiku.server.rest.SaikuRpcServiceImpl;
import net.datenwerke.rs.saiku.service.saiku.SaikuModule;
import net.datenwerke.rs.samba.server.samba.SambaRpcServiceImpl;
import net.datenwerke.rs.samba.service.samba.SambaModule;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.ScheduleAsFileUiModule;
import net.datenwerke.rs.scheduleasfile.server.scheduleasfile.ExportScheduledAsDiskFileServlet;
import net.datenwerke.rs.scheduleasfile.server.scheduleasfile.ExportScheduledAsFileReportServlet;
import net.datenwerke.rs.scheduleasfile.server.scheduleasfile.ScheduleAsFileRpcServiceImpl;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.ScheduleAsFileModule;
import net.datenwerke.rs.scheduler.server.scheduler.SchedulerRpcServiceImpl;
import net.datenwerke.rs.scheduler.service.scheduler.RsSchedulerModule;
import net.datenwerke.rs.scp.server.scp.ScpRpcServiceImpl;
import net.datenwerke.rs.scp.service.scp.ScpModule;
import net.datenwerke.rs.scriptdatasink.server.scriptdatasink.ScriptDatasinkRpcServiceImpl;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.ScriptDatasinkModule;
import net.datenwerke.rs.search.server.search.SearchRpcServiceImpl;
import net.datenwerke.rs.search.service.search.SearchModule;
import net.datenwerke.rs.tabledatasink.server.tabledatasink.TableDatasinkRpcServiceImpl;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.TableDatasinkModule;
import net.datenwerke.rs.teamspace.server.teamspace.TeamSpaceRpcServiceImpl;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceModule;
import net.datenwerke.rs.terminal.server.terminal.TerminalRpcServiceImpl;
import net.datenwerke.rs.terminal.service.terminal.TerminalModule;
import net.datenwerke.rs.tsreportarea.server.tsreportarea.TsDiskRpcServiceImpl;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskModule;
import net.datenwerke.rs.uservariables.server.uservariables.UserVariablesRpcServiceImpl;
import net.datenwerke.rs.uservariables.service.UserVariableModule;
import net.datenwerke.rs.utils.entitycloner.EntityClonerModule;
import net.datenwerke.rs.utils.eventlogger.jpa.JpaEventLoggerModule;
import net.datenwerke.rs.utils.juel.JuelModule;
import net.datenwerke.rs.utils.misc.TracerServlet;
import net.datenwerke.rs.utils.simplequery.SimpleQueryModule;
import net.datenwerke.rs.utils.zip.ZipUtilsModule;
import net.datenwerke.rs.xml.XmlRsUtilsModule;
import net.datenwerke.security.ext.server.SecurityRpcServiceImpl;
import net.datenwerke.security.ext.server.crypto.CryptoRpcServiceImpl;
import net.datenwerke.security.ext.server.usermanager.UserManagerRpcServiceImpl;
import net.datenwerke.security.ext.server.usermanager.UserManagerTreeHandlerImpl;
import net.datenwerke.security.ext.server.utils.PasswordRpcServiceImpl;
import net.datenwerke.security.service.crypto.CryptoModule;
import net.datenwerke.security.service.eventlogger.DwEventLoggerModule;
import net.datenwerke.security.service.security.SecurityModule;
import net.datenwerke.security.service.usermanager.UserManagerModule;
import net.datenwerke.treedb.ext.service.TreeDbExtModule;
import net.datenwerke.treedb.service.treedb.TreeDBModule;
import net.datenwerke.usermanager.ext.server.eximport.UserManagerExportRpcServiceImpl;
import net.datenwerke.usermanager.ext.server.eximport.UserManagerImportRpcServiceImpl;
import net.datenwerke.usermanager.ext.server.properties.UserPropertiesRpcServiceImpl;
import net.datenwerke.usermanager.ext.service.UserManagerExtModule;

/**
 * Registeres Guice and does the servlet configuration.
 * 
 * <p>
 * All configuration that normally goes into web.xml is now ported to java
 * </p>
 * 
 * <p>
 * Deployed once during startup.
 * </p>
 *
 */
public class ReportServerServiceConfig extends DwGwtFrameworkBase {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   public static final String CODE_VERSION = "2022-06-29-18-43-47";

   public static final String ENTERPRISE_MODULE_LOCATION = "net.datenwerke.rsenterprise.main.service.RsEnterpriseModule";
   private static final String ENTERPRISE_MODULE_LOAD_MODULE_METHOD = "getEnterpriseModules";
   private static final String ENTERPRISE_MODULE_CONFIG_SERVLETS_METHOD = "configureServlets";
   private static final String ENTERPRISE_CHECK_SERVLET = "net.datenwerke.rsenterprise.main.server.EnterpriseCheckRpcServiceImpl";

   static {
      LogSetupHelper.InitRsLogging();
   }

   @Override
   protected Injector getInjector() {
      boolean enterpriseAvailable = checkForEnterprise();

      boolean hasErrors = EnvironmentValidator.startup(enterpriseAvailable);
      if (hasErrors) {
         logger.error("ReportServer encountered an error while validating its environment. Startup interrupted. ");
         return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
               serve("/*").with(EnvironmentValidator.class);
            }
         });
      } else {
         return doGetInjector();
      }
   }

   private Injector doGetInjector() {
      /* check for enterprise */
      boolean enterpriseAvailable = checkForEnterprise();

      List<Module> modules = new ArrayList<Module>();
      modules.addAll(getModules());
      if (enterpriseAvailable) {
         try {
            modules.addAll(getEnterpriseModules());
         } catch (Exception e) {
            enterpriseAvailable = false;
         }
      }

      if (!enterpriseAvailable)
         modules.add(new LicenseModule());

      modules.add(initServletModule(enterpriseAvailable));

      Injector injector = Guice.createInjector(modules.toArray(new Module[] {}));

      injector.injectMembers(this);

      injectorInitialized();

      return injector;
   }

   protected Collection<? extends Module> getEnterpriseModules() {
      List<Module> modules = new ArrayList<Module>();
      try {
         Class<?> enterpriseModuleCls = Class.forName(ENTERPRISE_MODULE_LOCATION);

         Method getEnterpriseModulesMethod = enterpriseModuleCls.getMethod(ENTERPRISE_MODULE_LOAD_MODULE_METHOD, null);
         modules.addAll((Collection<? extends Module>) getEnterpriseModulesMethod.invoke(null));
      } catch (Exception e) {
         logger.error("Could not load enterprise modules. Fallback to Community edition.", e);
         throw new IllegalStateException();
      }
      return modules;
   }

   protected boolean checkForEnterprise() {
      try {
         Class.forName(ENTERPRISE_MODULE_LOCATION);
         return true;
      } catch (ClassNotFoundException e) {
         return false;
      }
   }

   protected ServletModule initServletModule(boolean isEnterprise) {
      Class<?> enterpriseCheckServlet = null;
      if (isEnterprise) {
         try {
            enterpriseCheckServlet = Class.forName(ENTERPRISE_CHECK_SERVLET);
         } catch (ClassNotFoundException e) {
            logger.error("Could not load enterprise servlet. Expect further failures.", e);
         }
      }

      final boolean fisEnterprise = null != enterpriseCheckServlet;
      final Class<?> fenterpriseCheckServlet = enterpriseCheckServlet;

      return new ServletModule() {
         @Override
         protected void configureServlets() {
            filter("/ReportServer.html").through(NoCacheFilter.class);
            filter("/reportserver.nocache.js").through(NoCacheFilter.class);

            filter("/*").through(PersistFilter.class);

//				filter("/ReportServer.html").through(SpnegoSsoFilter.class);

            serve("/").with(IndexRedirectServlet.class);
            serve("/index.html").with(IndexRedirectServlet.class);

            /* call enterprise if necessary */
            if (fisEnterprise) {
               try {
                  Class<?> enterpriseModuleCls = Class.forName(ENTERPRISE_MODULE_LOCATION);

                  Method configureServletsMethod = enterpriseModuleCls
                        .getMethod(ENTERPRISE_MODULE_CONFIG_SERVLETS_METHOD, ServletModule.class);
                  configureServletsMethod.invoke(null, this);
               } catch (Exception e) {
                  logger.error("Could not load enterprise modules. Fallback to Community edition.", e);
               }
            } else {
               /* servlets that are only here in community */
               serve(BASE_URL + "crystalutils").with(CrystalUtilsRpcDummyServiceImpl.class); //$NON-NLS-1$
               serve(BASE_URL + "generalinfoconsole").with(GeneralInfoRpcDummyServiceImpl.class); //$NON-NLS-1$
               serve(BASE_URL + "memoryconsole").with(MemoryConsoleDummyRpcServiceImpl.class); //$NON-NLS-1$
               serve(BASE_URL + "connectionpoolconsole").with(ConnectionPoolConsoleDummyRpcServiceImpl.class); //$NON-NLS-1$
            }

            serve(BASE_URL + "crypto").with(CryptoRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "base_datasource").with(BaseDatasourceRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "computed_columns").with(ComputedColumnsRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "conditions").with(ConditionRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "dashboard").with(DashboardRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "dashboardmanager_import").with(DashboardManagerImportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "dashboardmanager_export").with(DashboardManagerExportRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "datasources").with(DatasourceManagerTreeHandlerRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datasinks").with(DatasinkManagerTreeHandlerRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datasources_tree").with(DatasourceManagerTreeHandlerRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datasinks_tree").with(DatasinkManagerTreeHandlerRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datasources_import").with(DatasourceManagerImportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datasourcemanager_export").with(DatasourceManagerExportRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "datasinks_import").with(DatasinkManagerImportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datasinks_service").with(DatasinkRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datasinkmanager_export").with(DatasinkManagerExportRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "reportdocumentation").with(ReportDocumentationServlet.class); //$NON-NLS-1$
            serve(BASE_URL + "datenwerke/jaspertotable").with(JasperToTableRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "datenwerke/suuser").with(SuUserRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "executor").with(ReportExecutorRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "dashboardadmin").with(DashboardTreeRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "fileserver").with(FileServerRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "fileserver_export").with(FileServerRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "fileserver_import").with(FileServerImportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + FileServerUiModule.FILE_ACCESS_SERVLET + "/*").with(FileServerAccessServlet.class); //$NON-NLS-1$
            serve(BASE_URL + FileServerUiModule.FILE_ACCESS_SERVLET).with(FileServerAccessServlet.class); // $NON-NLS-1$

            serve(BASE_URL + "juel").with(JuelRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "license").with(LicenseRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "logfiles").with(LogFilesRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + ScheduleAsFileUiModule.EXPORT_SERVLET).with(ExportScheduledAsFileReportServlet.class);
            serve(BASE_URL + ScheduleAsFileUiModule.DISK_FILE_EXPORT_SERVLET).with(ExportScheduledAsDiskFileServlet.class);
            serve(BASE_URL + "ts/scheduelasfile").with(ScheduleAsFileRpcServiceImpl.class);

            serve(BASE_URL + "ftp").with(FtpRpcServiceImpl.class);
            serve(BASE_URL + "sftp").with(SftpRpcServiceImpl.class);
            serve(BASE_URL + "ftps").with(FtpsRpcServiceImpl.class);

            serve(BASE_URL + "samba").with(SambaRpcServiceImpl.class);
            serve(BASE_URL + "scp").with(ScpRpcServiceImpl.class);

            serve(BASE_URL + "oauth").with(OAuthServlet.class);
            serve(BASE_URL + "oauthrpc").with(OAuthRpcServiceImpl.class);

            serve(BASE_URL + "dropbox").with(DropboxRpcServiceImpl.class);
            serve(BASE_URL + "onedrive").with(OneDriveRpcServiceImpl.class);
            serve(BASE_URL + "googledrive").with(GoogleDriveRpcServiceImpl.class);
            serve(BASE_URL + "box").with(BoxRpcServiceImpl.class);
            serve(BASE_URL + "amazons3").with(AmazonS3RpcServiceImpl.class);
            serve(BASE_URL + "printer").with(PrinterRpcServiceImpl.class);
            serve(BASE_URL + "tabledatasink").with(TableDatasinkRpcServiceImpl.class);
            serve(BASE_URL + "scriptdatasink").with(ScriptDatasinkRpcServiceImpl.class);

            serve(BASE_URL + "localfilesystem").with(LocalFileSystemRpcServiceImpl.class);
            serve(BASE_URL + "email").with(EmailDatasinkRpcServiceImpl.class);

            serve(BASE_URL + "history").with(HistoryRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "homepage").with(HomepageRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "quickExportDownload").with(QuickExportDownloadServlet.class); //$NON-NLS-1$

            serve(BASE_URL + "globalconstants").with(GlobalConstantsRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "importrpc").with(ImportRpcServiceImpl.class);

            serve(BASE_URL + "enterprisecheck")
                  .with((Class<? extends HttpServlet>) (fisEnterprise ? fenterpriseCheckServlet
                        : CommunityEnterpriseCheckRpcServiceImpl.class));

            serve(BASE_URL + "login").with(LoginHandlerImpl.class); //$NON-NLS-1$

//				serve(BASE_URL + "messenger").with(MessengerRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "reportmanager_export").with(ReportManagerExportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "reportmanager_import").with(ReportManagerImportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "reportmanager_tree").with(ReportManagerTreeHandlerImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "reportmanager_parameter").with(ParameterRpcServiceImpl.class); //$NON-NLS-1$

            /*
             * load independent of whether libraries are available to allow to indicate this
             * to client
             */

            serve(BASE_URL + "remotemessage").with(RemoteMessageServiceImpl.class);

            serve(BASE_URL + "reportmetadata").with(ReportMetadataRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "reportproperties").with(ReportPropertiesRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "userproperties").with(UserPropertiesRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "legacysaiku").with(net.datenwerke.rs.legacysaiku.server.rest.SaikuRpcServiceImpl.class);
            serve(BASE_URL + "saiku").with(SaikuRpcServiceImpl.class);

            serve(BASE_URL + "rstheme").with(RsThemeServlet.class); //$NON-NLS-1$
            serve(BASE_URL + "themeservice").with(ThemeRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "JRXMLDownload").with(JRXMLDownloadServlet.class); //$NON-NLS-1$
            serve(BASE_URL + "birtDownload").with(BirtReportFileDownloadServlet.class);
            serve(BASE_URL + "birtutils").with(BirtUtilsRpcServiceImpl.class);

            serve(BASE_URL + "jasperreport").with(JasperReportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "jasperutils").with(JasperUtilsRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "jxlsReportDownload").with(JxlsReportFileDownloadServlet.class);

            serve(BASE_URL + "sendtorpc").with(SendToRpcServiceImpl.class);

            serve(BASE_URL + "usermanager_import").with(UserManagerImportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "usermanager_export").with(UserManagerExportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "usermanager_tree").with(UserManagerTreeHandlerImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "usermanager").with(UserManagerRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "reportexporterrpc").with(ReportExportRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + ReportExportServlet.SERVLET_NAME).with(ReportExportServlet.class); // $NON-NLS-1$
            serve(BASE_URL + ReportExportServlet.SERVLET_NAME + "/*").with(ReportExportServlet.class); //$NON-NLS-1$

            serve(BASE_URL + HttpAuthExecuteServlet.SERVLET_NAME).with(HttpAuthExecuteServlet.class); // $NON-NLS-1$
            serve(BASE_URL + HttpAuthExecuteServlet.SERVLET_NAME + "/*").with(HttpAuthExecuteServlet.class); //$NON-NLS-1$

            serve(BASE_URL + "filedownload").with(FileDownloadServlet.class);
            serve(BASE_URL + "fileupload").with(FileUploadServlet.class);
            serve(BASE_URL + "fileuploadservice").with(FileUploadRpcServiceImpl.class);
            serve(BASE_URL + "fileselectionservice").with(FileSelectionRpcServiceImpl.class);

            serve(BASE_URL + "scheduler").with(SchedulerRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "teamspace").with(TeamSpaceRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "terminal").with(TerminalRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "imageservice").with(ImageServlet.class); //$NON-NLS-1$
            serve(BASE_URL + "jasperpreviewprovider").with(JasperPreviewProvider.class); //$NON-NLS-1$
            serve(BASE_URL + "tempfileservice").with(TempFileServlet.class);

            serve(BASE_URL + "rs_basicparameters_datasource").with(DatasourceParameterRPCServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "search").with(SearchRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "security_security").with(SecurityRpcServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "security_activateuser").with(ActivateUserRpcServiceImpl.class);
            serve(BASE_URL + "security_password").with(PasswordRpcServiceImpl.class);
            serve(BASE_URL + "security_lostpassword").with(LostPasswordRpcServiceImpl.class);
            serve(BASE_URL + "security_challengeresponse").with(ChallengeResponseRpcServiceImpl.class);
            serve(BASE_URL + "security_accountinhibition").with(AccountInhibitionRpcServiceImpl.class);

            serve(BASE_URL + "statementmanager").with(StatementManagerRpcServiceImpl.class);

            serve(BASE_URL + "tablereportutility").with(TableReportUtilityServiceImpl.class); //$NON-NLS-1$
            serve(BASE_URL + "convenience_datasourcetester").with(DatasourceTesterRPCServiceImpl.class);

            serve(BASE_URL + "uservariables").with(UserVariablesRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "contexthelp").with(ContextHelpRpcServiceImpl.class); //$NON-NLS-1$

            // serve(BASE_URL + "cometrpc").with(CometRpcServiceImpl.class)

            serve(BASE_URL + "ts/favorites").with(TsDiskRpcServiceImpl.class); //$NON-NLS-1$

            serve(BASE_URL + "urlviewconfig").with(UrlViewRpcServiceImpl.class);

            serve(BASE_URL + "localization").with(LocalizationRpcServiceImpl.class);

            serve(BASE_URL + "clientconfig").with(ClientConfigRpcServiceImpl.class);

            serve(BASE_URL + "i18ntools").with(I18nToolsRpcServiceImpl.class);

            serve(BASE_URL + "trace").with(TracerServlet.class);
            serve(BASE_URL + "version").with(VersionInfoServlet.class);

            Map<String, String> jerseyParams = new HashMap<String, String>();
            jerseyParams.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
            serve(BASE_URL + "rest/*").with(GuiceContainer.class, jerseyParams);
         }
      };
   }

   protected List<Module> getModules() {
      return Arrays.asList(new Module[] { 
            new DwAsyncModule(),

            new GWTStacktraceModule(), 
            new DwGwtFrameworkModule(), 
            new ReportServerModule(), 
            new LocalizationModule(),
            /*
             * ConfigModule must be loaded before the crypto module is loaded, since the
             * crypto params are loaded there, eventually in an external config file.
             */
            new ConfigModule(),

            new SecurityModule(), 
            new PasswordPolicyModule(), 
            new ChallengeResponseModule(), 
            new RsSchedulerModule(),

            new RsCoreModule(), 
            new RsBaseModule(), 
            new RsBaseExtModule(),

            /* in alphabetical order */
            new JpaEventLoggerModule(), 
            new AuthenticatorModule(), 
            new ReportDocumentationModule(),
            new GenRightsModule(),

            new CryptoModule(), 
            new DashboardModule(), 
            new DatasourceModule(), 
            new DatasinkModule(),
            new DBHelperModule(), 
            new DtoModule(),

            new ConditionModule(),

            new DemoDataModule(),

            new EntityClonerModule(), 
            new ExportMetadataModule(),

            new FilterReplacementsModule(),

            new JxlsReportModule(),

            new GlobalConstantsModule(),

            new HookHandlerModule(), 
            new ExImportModule(), 
            new RsExImportModule(),

            new JasperToTableModule(), 
            new JuelModule(),

            new MailModule(), 
            new BirtModule(),
            new ComputedColumnsModule(),

            new RsIncubatorModule(),

            new NotifyModule(), 
            new ParameterModule(),

            new FileServerModule(),

            new ReportManagerModule(),

            new ActivateUserModule(), 
            new HistoryModule(),

            new MaintenanceModule(),

            new RemoteAccessModule(),

            new SaikuModule(), 
            new net.datenwerke.rs.legacysaiku.service.saiku.SaikuModule(), 
            new SearchModule(),
            new SimpleQueryModule(),

            new TeamSpaceModule(), 
            new TerminalModule(), 
            new TsDiskModule(),

            new LogFilesModule(), 
            new SystemConsoleModule(),

            new TreeDBModule(), 
            new TreeDbExtModule(),

            new UserManagerModule(), 
            new UserManagerExtModule(),

            new UserManagerExtModule(), 
            new UserVariableModule(), 
            new VersioningModule(), 
            new XmlRsUtilsModule(),
            new ZipUtilsModule(),

            new CompiledReportStoreModule(),

            new OutputFormatAuthModule(), 
            new ScheduleAsFileModule(), 
            new FtpModule(), 
            new LocalFileSystemModule(),
            new EmailDatasinkModule(), 
            new ReportPropertiesModule(),

            new DatasourceTesterModule(),

            new SambaModule(), 
            new DropboxModule(), 
            new OneDriveModule(), 
            new GoogleDriveModule(), 
            new BoxModule(),
            new AmazonS3Module(),
            new PrinterModule(),
            new TableDatasinkModule(),
            new ScriptDatasinkModule(),
            
            new ScpModule(),

            new AliasCmdModule(),

            new ReportServerPUModule(),

            new DwEventLoggerModule(),

            new ReportServerInstallationModule(),

            new ManPageModule(), 
            new SuModule(), 
            new PropertiesModule(),

            new RsInstallModule(),

            /* dbPool */
            new DbPoolModule(),

            /* should be last module */
            new LateInitModule(),

      });
   }

}
