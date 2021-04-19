package net.datenwerke.rs.ftp.service.ftp;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.ftp.service.ftp.hooker.FtpDatasinkProviderHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleAsFtpFileEmailNotificationHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleAsSftpFileEmailNotificationHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleConfigAsFtpFileHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.ScheduleConfigAsSftpFileHooker;
import net.datenwerke.rs.ftp.service.ftp.hooker.SftpDatasinkProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class FtpStartup {

	@Inject
	public FtpStartup(
		HookHandlerService hookHandler,
		
		Provider<FtpDatasinkProviderHooker> ftpDatasinkProvider,
		Provider<SftpDatasinkProviderHooker> sftpDatasinkProvider,
		
		Provider<ScheduleConfigAsFtpFileHooker> scheduleAsFtpFileConfigHooker, 
		Provider<ScheduleConfigAsSftpFileHooker> scheduleAsSftpFileConfigHooker, 
		Provider<ScheduleAsFtpFileEmailNotificationHooker> emailFtpNotificationHooker,
		Provider<ScheduleAsSftpFileEmailNotificationHooker> emailSftpNotificationHooker
		
	){
		
		hookHandler.attachHooker(DatasinkProviderHook.class, ftpDatasinkProvider);
		hookHandler.attachHooker(DatasinkProviderHook.class, sftpDatasinkProvider);
		
		hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsFtpFileConfigHooker);
		hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsSftpFileConfigHooker);
		
		hookHandler.attachHooker(SchedulerExecutionHook.class, emailFtpNotificationHooker);
		hookHandler.attachHooker(SchedulerExecutionHook.class, emailSftpNotificationHooker);
		
	}
}
