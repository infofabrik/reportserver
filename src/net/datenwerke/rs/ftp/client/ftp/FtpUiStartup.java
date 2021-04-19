package net.datenwerke.rs.ftp.client.ftp;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.ftp.client.ftp.hookers.ExportToFtpHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.ExportToSftpHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpDataSinkTesterToolbarConfigurator;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.FtpExportSnippetProvider;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpDataSinkTesterToolbarConfigurator;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpDatasinkConfigProviderHooker;
import net.datenwerke.rs.ftp.client.ftp.hookers.SftpExportSnippetProvider;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class FtpUiStartup {

   @Inject
   public FtpUiStartup(final HookHandlerService hookHandler, final Provider<ExportToFtpHooker> exportToFtpHooker,
         final Provider<FtpExportSnippetProvider> ftpExportSnippetProvider,
         final Provider<SftpExportSnippetProvider> sftpExportSnippetProvider,
         final Provider<ExportToSftpHooker> exportToSftpHooker,
         final Provider<FtpDatasinkConfigProviderHooker> ftpTreeConfiguratorProvider,
         final Provider<SftpDatasinkConfigProviderHooker> sftpTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, final FtpDao dao,
         final FtpDataSinkTesterToolbarConfigurator ftpTestToolbarConfigurator,
         final SftpDataSinkTesterToolbarConfigurator sftpTestToolbarConfigurator) {

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, sftpTreeConfiguratorProvider.get(), 10);
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, ftpTreeConfiguratorProvider.get(), 20);

      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToSftpHooker,
            HookHandlerService.PRIORITY_MEDIUM);
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToFtpHooker,
            HookHandlerService.PRIORITY_LOW);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, ftpTestToolbarConfigurator);
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, sftpTestToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {

               if (result.get(StorageType.SFTP) && result.get(StorageType.SFTP_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, sftpExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOWER - 10);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, sftpExportSnippetProvider);

               if (result.get(StorageType.FTP) && result.get(StorageType.FTP_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, ftpExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOWER);
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
