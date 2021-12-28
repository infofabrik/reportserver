package net.datenwerke.rs.ftp.client.ftp;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.datasinks.hooks.DatasinkAuthenticatorConfiguratorHook;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.ftp.client.ftp.hookers.ExportToFtpHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.ExportToFtpsHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.ExportToSftpHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FileExportToFtpHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FileExportToFtpsHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FileExportToSftpHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpExportSnippetProvider;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpSendToFormConfiguratorHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpsDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpsDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpsExportSnippetProvider;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpsSendToFormConfiguratorHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpsUsernamePasswordAuthenticatorHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpExportSnippetProvider;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpPublicKeyAuthenticatorHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpSendToFormConfiguratorHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpUsernamePasswordAuthenticatorHooker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class FtpUiStartup {

   private static final int SFTP_PRIO = HookHandlerService.PRIORITY_LOW + 10;
   private static final int FTPS_PRIO = HookHandlerService.PRIORITY_LOW + 15;
   private static final int FTP_PRIO = HookHandlerService.PRIORITY_LOW + 20;

   @Inject
   public FtpUiStartup(final HookHandlerService hookHandler, final Provider<ExportToFtpHooker> exportToFtpHooker,
         final Provider<FileExportToFtpHooker> fileExportToFtpDatasinkHooker,
         final Provider<FtpExportSnippetProvider> ftpExportSnippetProvider,
         final Provider<SftpExportSnippetProvider> sftpExportSnippetProvider,
         final Provider<FtpsExportSnippetProvider> ftpsExportSnippetProvider,
         final Provider<ExportToSftpHooker> exportToSftpHooker,
         final Provider<FileExportToSftpHooker> fileExportToSftpDatasinkHooker,
         final Provider<ExportToFtpsHooker> exportToFtpsHooker,
         final Provider<FileExportToFtpsHooker> fileExportToFtpsDatasinkHooker,
         final Provider<FtpDatasinkConfigProviderHooker> ftpTreeConfiguratorProvider,
         final Provider<SftpDatasinkConfigProviderHooker> sftpTreeConfiguratorProvider,
         final Provider<FtpsDatasinkConfigProviderHooker> ftpsTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, final FtpDao dao,
         final FtpDatasinkTesterToolbarConfigurator ftpTestToolbarConfigurator,
         final SftpDatasinkTesterToolbarConfigurator sftpTestToolbarConfigurator,
         final FtpsDatasinkTesterToolbarConfigurator ftpsTestToolbarConfigurator,
         final SftpUsernamePasswordAuthenticatorHooker sftpUsernamePasswordAuthenticator,
         final SftpPublicKeyAuthenticatorHooker sftpPublicKeyAuthenticator,
         final FtpsUsernamePasswordAuthenticatorHooker ftpsUsernamePasswordAuthenticator,
         final FtpUiService ftpUiService,

         final Provider<FtpSendToFormConfiguratorHooker> ftpSendToConfigHookProvider,
         final Provider<SftpSendToFormConfiguratorHooker> sftpSendToConfigHookProvider,
         final Provider<FtpsSendToFormConfiguratorHooker> ftpsSendToConfigHookProvider) {

      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, ftpSendToConfigHookProvider.get());
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sftpSendToConfigHookProvider.get());
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, ftpsSendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, sftpTreeConfiguratorProvider.get(),
            SFTP_PRIO);
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, ftpsTreeConfiguratorProvider.get(),
            FTPS_PRIO);
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, ftpTreeConfiguratorProvider.get(), FTP_PRIO);

      /* SFTP authenticators */
      hookHandler.attachHooker(DatasinkAuthenticatorConfiguratorHook.class, sftpUsernamePasswordAuthenticator,
            HookHandlerService.PRIORITY_MEDIUM);
      hookHandler.attachHooker(DatasinkAuthenticatorConfiguratorHook.class, sftpPublicKeyAuthenticator,
            HookHandlerService.PRIORITY_MEDIUM + 10);

      /* FTPS authenticators */
      hookHandler.attachHooker(DatasinkAuthenticatorConfiguratorHook.class, ftpsUsernamePasswordAuthenticator,
            HookHandlerService.PRIORITY_MEDIUM);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToSftpHooker, SFTP_PRIO);
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToFtpsHooker, FTPS_PRIO);
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToFtpHooker, FTP_PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToSftpDatasinkHooker, SFTP_PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToFtpsDatasinkHooker, FTPS_PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToFtpDatasinkHooker, FTP_PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, ftpTestToolbarConfigurator, SFTP_PRIO);
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, sftpTestToolbarConfigurator, FTPS_PRIO);
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, ftpsTestToolbarConfigurator, FTP_PRIO);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getAllStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((FtpUiServiceImpl) ftpUiService).setEnabledConfigs(result);
               if (result.get(StorageType.SFTP) && result.get(StorageType.SFTP_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, sftpExportSnippetProvider,
                        SFTP_PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, sftpExportSnippetProvider);

               if (result.get(StorageType.FTPS) && result.get(StorageType.FTPS_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, ftpsExportSnippetProvider,
                        FTPS_PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, ftpsExportSnippetProvider);

               if (result.get(StorageType.FTP) && result.get(StorageType.FTP_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, ftpExportSnippetProvider, FTP_PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, ftpExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });

   }
}
