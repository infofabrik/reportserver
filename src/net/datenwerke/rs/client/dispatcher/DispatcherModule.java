package net.datenwerke.rs.client.dispatcher;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.gf.client.DwGwtFrameworkUIModule;
import net.datenwerke.gf.client.administration.AdministrationUIModule;
import net.datenwerke.gf.client.config.ClientConfigModule;
import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gf.client.dispatcher.DispatcherServiceImpl;
import net.datenwerke.gf.client.dtoinfo.ClientDtoInfoModule;
import net.datenwerke.gf.client.history.HistoryUIModule;
import net.datenwerke.gf.client.homepage.DwMainViewportUiModule;
import net.datenwerke.gf.client.localization.LocalizationUiModule;
import net.datenwerke.gf.client.managerhelper.ManagerHelperUIModule;
import net.datenwerke.gf.client.theme.ThemeUiModule;
import net.datenwerke.gf.client.treedb.DwGwtTreeDbUiModule;
import net.datenwerke.gxtdto.client.dtomanager.ClientDtoManagerModule;
import net.datenwerke.gxtdto.client.eventbus.EventBusUIModule;
import net.datenwerke.gxtdto.client.forms.selection.FormSelectionToolsModule;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormModule;
import net.datenwerke.gxtdto.client.i18n.remotemessages.RemoteMessageUIModule;
import net.datenwerke.gxtdto.client.servercommunication.callback.CallbackHandlerUIModule;
import net.datenwerke.gxtdto.client.statusbar.StatusBarUIModule;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIModule;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIModule;
import net.datenwerke.hookhandler.shared.hookhandler.ClientHookHandlerModule;
import net.datenwerke.rs.adminutils.client.datasourcetester.DatasourceTesterUIModule;
import net.datenwerke.rs.adminutils.client.logs.LogFilesUiModule;
import net.datenwerke.rs.adminutils.client.suuser.SuUserUIModule;
import net.datenwerke.rs.adminutils.client.systemconsole.SystemConsoleUiModule;
import net.datenwerke.rs.authenticator.client.login.LoginModule;
import net.datenwerke.rs.authenticator.cr.client.ChallengeResponseUIModule;
import net.datenwerke.rs.base.client.RsBaseUiModule;
import net.datenwerke.rs.base.client.parameters.RSBasicParametersUIModule;
import net.datenwerke.rs.base.ext.client.RsBaseExtUiModule;
import net.datenwerke.rs.birt.client.reportengines.BirtUiModule;
import net.datenwerke.rs.box.client.box.BoxUiModule;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.ComputedColumnsUiModule;
import net.datenwerke.rs.condition.client.condition.ConditionUiModule;
import net.datenwerke.rs.core.client.RsCoreUiModule;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIModule;
import net.datenwerke.rs.core.client.easteregg.EasterEggModule;
import net.datenwerke.rs.core.client.parameters.ParameterUIModule;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIModule;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIModule;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerUIModule;
import net.datenwerke.rs.crystal.client.crystal.CrystalUiModule;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiModule;
import net.datenwerke.rs.dropbox.client.dropbox.DropboxUiModule;
import net.datenwerke.rs.dsbundle.client.dsbundle.DatasourceBundleUiModule;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkUiModule;
import net.datenwerke.rs.enterprise.client.EnterpriseCheckUiModule;
import net.datenwerke.rs.eximport.client.eximport.RsExImportUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.ftp.client.ftp.FtpUiModule;
import net.datenwerke.rs.globalconstants.client.globalconstants.GlobalConstantsUIModule;
import net.datenwerke.rs.googledrive.client.googledrive.GoogleDriveUiModule;
import net.datenwerke.rs.grideditor.client.grideditor.GridEditorUiModule;
import net.datenwerke.rs.incubator.client.RsIncubatorUIModule;
import net.datenwerke.rs.incubator.client.jaspertotable.JasperToTableUIModule;
import net.datenwerke.rs.incubator.client.jasperutils.JasperUIModule;
import net.datenwerke.rs.incubator.client.outputformatauth.OutputFormatAuthUiModule;
import net.datenwerke.rs.incubator.client.reportmetadata.ReportMetadataUIModule;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.JxlsReportUiModule;
import net.datenwerke.rs.license.client.LicenseUiModule;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.LocalFileSystemUiModule;
import net.datenwerke.rs.onedrive.client.onedrive.OneDriveUiModule;
import net.datenwerke.rs.passwordpolicy.client.PasswordPolicyUIModule;
import net.datenwerke.rs.passwordpolicy.client.lostpassword.LostPasswordUIModule;
import net.datenwerke.rs.remoteaccess.client.RemoteAccessUiModule;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUIModule;
import net.datenwerke.rs.saiku.client.saiku.SaikuUiModule;
import net.datenwerke.rs.saikupivot.client.SaikuPivotUiModule;
import net.datenwerke.rs.samba.client.samba.SambaUIModule;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.ScheduleAsFileUiModule;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerUIModule;
import net.datenwerke.rs.scp.client.scp.ScpUIModule;
import net.datenwerke.rs.scripting.client.scripting.ScriptingUiModule;
import net.datenwerke.rs.scriptreport.client.scriptreport.ScriptReportUiModule;
import net.datenwerke.rs.search.client.search.SearchUiModule;
import net.datenwerke.rs.tabletemplate.client.engines.TableTemplateEngineUiModule;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.TableTemplateUIModule;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIModule;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIModule;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIModule;
import net.datenwerke.rs.userprofile.client.userprofile.UserProfileUIModule;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariablesUIModule;
import net.datenwerke.rs.uservariables.client.variabletypes.RSUserVariabelsUIModule;
import net.datenwerke.security.client.security.SecurityDtoUiModule;
import net.datenwerke.security.ext.client.crypto.CryptoUIModule;
import net.datenwerke.security.ext.client.security.SecurityUIModule;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIModule;
import net.datenwerke.treedb.client.treedb.TreeDBUIModule;
import net.datenwerke.usermanager.ext.client.UserManagerExtUiModule;

public class DispatcherModule extends AbstractGinModule {

   @Override
   protected void configure() {
      install(new EnterpriseCheckUiModule());

      install(new ThemeUiModule());
      install(new RsCoreUiModule());
      install(new RsBaseUiModule());
      install(new RsBaseExtUiModule());
      install(new DwGwtFrameworkUIModule());

      /* install modules (in alphabetical order) */
      install(new EventBusUIModule());
      install(new AdministrationUIModule());
      install(new ReportDocumentationUIModule());
      install(new CallbackHandlerUIModule());
      install(new ClientDtoInfoModule());
      install(new CryptoUIModule());
      install(new CrystalUiModule());
      install(new DashboardUiModule());
      install(new DatasourceUIModule());
      install(new SystemConsoleUiModule());
      install(new DatasinkUIModule());
      install(new FtpUiModule());
      install(new DropboxUiModule());
      install(new OneDriveUiModule());
      install(new GoogleDriveUiModule());
      install(new BoxUiModule());
      install(new SambaUIModule());
      install(new ScpUIModule());
      install(new LocalFileSystemUiModule());
      install(new EmailDatasinkUiModule());
      install(new FileServerUiModule());
      install(new LogFilesUiModule());
      install(new RsExImportUiModule());
      install(new ClientDtoManagerModule());
      install(new FormSelectionToolsModule());
      install(new GlobalConstantsUIModule());
      install(new HistoryUIModule());
      install(new ClientHookHandlerModule());
      install(new DwMainViewportUiModule());
      install(new JasperUIModule());
      install(new JasperToTableUIModule());
      install(new LoginModule());
      install(new LicenseUiModule());
      install(new ManagerHelperUIModule());
      install(new ParameterUIModule());
      install(new ReportExecutorUIModule());
      install(new ReportExporterUIModule());
      install(new ReportMetadataUIModule());
      install(new ReportManagerUIModule());
      install(new RSBasicParametersUIModule());
      install(new RSUserVariabelsUIModule());
      install(new BirtUiModule());
      install(new ComputedColumnsUiModule());

      install(new TableTemplateEngineUiModule());

      install(new RsIncubatorUIModule());

      install(new SearchUiModule());

      install(new SecurityUIModule());
      install(new PasswordPolicyUIModule());
      install(new LostPasswordUIModule());
      install(new ChallengeResponseUIModule());

      install(new JxlsReportUiModule());

      install(new SecurityDtoUiModule());
      install(new SimpleFormModule());
      install(new StatusBarUIModule());
      install(new SuUserUIModule());
      install(new TableTemplateUIModule());
      install(new TeamSpaceUIModule());
      install(new TsDiskUIModule());
      install(new TerminalUIModule());

      install(new TreeDBUIModule());
      install(new DwGwtTreeDbUiModule());
      install(new RemoteAccessUiModule());
      install(new UserManagerUIModule());
      install(new UserManagerExtUiModule());

      install(new UserProfileUIModule());
      install(new UserVariablesUIModule());
      install(new UtilsUIModule());
      install(new WaitOnEventUIModule());
      install(new RemoteMessageUIModule());

      install(new ScriptingUiModule());
      install(new SchedulerUIModule());

      install(new OutputFormatAuthUiModule());
      install(new ScheduleAsFileUiModule());

      install(new ScriptReportUiModule());

      install(new ConditionUiModule());

      install(new DatasourceBundleUiModule());
      install(new ClientConfigModule());
      install(new LocalizationUiModule());

      install(new DatasourceTesterUIModule());

      install(new SaikuUiModule());
      install(new net.datenwerke.rs.legacysaiku.client.saiku.SaikuUiModule());

      install(new SaikuPivotUiModule());
      install(new GridEditorUiModule());

      install(new EasterEggModule());

      /* bind dispatcher module */
      bind(DispatcherService.class).to(DispatcherServiceImpl.class);
   }

}
