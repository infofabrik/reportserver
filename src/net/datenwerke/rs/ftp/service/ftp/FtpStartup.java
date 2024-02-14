package net.datenwerke.rs.ftp.service.ftp;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.ftp.service.ftp.hooker.FtpDatasinkProviderHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.FtpsDatasinkProviderHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleAsFtpFileEmailNotificationHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleAsFtpsFileEmailNotificationHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleAsSftpFileEmailNotificationHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleConfigAsFtpFileHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleConfigAsFtpsFileHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleConfigAsSftpFileHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.SftpDatasinkProviderHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.SftpPrivateKeyUploadHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.UsageFtpStatisticsProviderHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.UsageFtpsStatisticsProviderHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.UsageSftpStatisticsProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class FtpStartup {

   @Inject
   public FtpStartup(
         final HookHandlerService hookHandler,

         final Provider<FtpDatasinkProviderHooker> ftpDatasinkProvider,
         final Provider<SftpDatasinkProviderHooker> sftpDatasinkProvider,
         final Provider<FtpsDatasinkProviderHooker> ftpsDatasinkProvider,

         final Provider<ScheduleConfigAsFtpFileHooker> scheduleAsFtpFileConfigHooker,
         final Provider<ScheduleConfigAsSftpFileHooker> scheduleAsSftpFileConfigHooker,
         final Provider<ScheduleConfigAsFtpsFileHooker> scheduleAsFtpsFileConfigHooker,
         final Provider<ScheduleAsFtpFileEmailNotificationHooker> emailFtpNotificationHooker,
         final Provider<ScheduleAsSftpFileEmailNotificationHooker> emailSftpNotificationHooker,
         final Provider<ScheduleAsFtpsFileEmailNotificationHooker> emailFtpsNotificationHooker,

         final Provider<SftpPrivateKeyUploadHooker> sftpPrivateKeyUploadHooker,
         
         final Provider<UsageFtpStatisticsProviderHooker> usageFtpStats,
         final Provider<UsageSftpStatisticsProviderHooker> usageSftpStats,
         final Provider<UsageFtpsStatisticsProviderHooker> usageFtpsStats,
         
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory

   ) {
      hookHandler.attachHooker(FileUploadHandlerHook.class, sftpPrivateKeyUploadHooker);

      hookHandler.attachHooker(DatasinkProviderHook.class, ftpDatasinkProvider);
      hookHandler.attachHooker(DatasinkProviderHook.class, sftpDatasinkProvider);
      hookHandler.attachHooker(DatasinkProviderHook.class, ftpsDatasinkProvider);

      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsFtpFileConfigHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsSftpFileConfigHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsFtpsFileConfigHooker);

      hookHandler.attachHooker(SchedulerExecutionHook.class, emailFtpNotificationHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailSftpNotificationHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailFtpsNotificationHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(FtpDatasink.class));
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(SftpDatasink.class));
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(FtpsDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageFtpStats,
            HookHandlerService.PRIORITY_MEDIUM + 20);
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageFtpsStats,
            HookHandlerService.PRIORITY_MEDIUM + 25);
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageSftpStats,
            HookHandlerService.PRIORITY_MEDIUM + 65);

   }
}
