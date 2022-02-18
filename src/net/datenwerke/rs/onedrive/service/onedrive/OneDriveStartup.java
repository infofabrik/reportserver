package net.datenwerke.rs.onedrive.service.onedrive;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.onedrive.service.onedrive.hooker.OneDriveDatasinkProviderHooker;
import net.datenwerke.rs.onedrive.service.onedrive.hooker.ScheduleAsOneDriveFileEmailNotificationHooker;
import net.datenwerke.rs.onedrive.service.onedrive.hooker.ScheduleConfigAsOneDriveDatasinkHooker;
import net.datenwerke.rs.onedrive.service.onedrive.terminal.OnedriveCommand;
import net.datenwerke.rs.onedrive.service.onedrive.terminal.OnedriveSubCommandHook;
import net.datenwerke.rs.onedrive.service.onedrive.terminal.subcommands.group.OnedriveGroupSubCommand;
import net.datenwerke.rs.onedrive.service.onedrive.terminal.subcommands.group.OnedriveGroupSubSubCommandHook;
import net.datenwerke.rs.onedrive.service.onedrive.terminal.subcommands.group.subsubcommands.OnedriveGroupGetmygroupsSubSubCommand;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class OneDriveStartup {

   @Inject
   public OneDriveStartup(HookHandlerService hookHandler,
         Provider<OneDriveDatasinkProviderHooker> oneDriveDatasinkProviderHooker,
         Provider<ScheduleAsOneDriveFileEmailNotificationHooker> emailOneDriveNotificationHooker,
         Provider<ScheduleConfigAsOneDriveDatasinkHooker> scheduleAsOneDriveConfigHooker,
         Provider<OnedriveCommand> onedriveCommand,
         Provider<OnedriveGroupSubCommand> onedriveGroupSubCommand,
         Provider<OnedriveGroupGetmygroupsSubSubCommand> onedriveGroupGetmygroupsSubSubCommand) {
      hookHandler.attachHooker(DatasinkProviderHook.class, oneDriveDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailOneDriveNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsOneDriveConfigHooker);
      hookHandler.attachHooker(TerminalCommandHook.class, onedriveCommand);
      hookHandler.attachHooker(OnedriveSubCommandHook.class, onedriveGroupSubCommand);
      hookHandler.attachHooker(OnedriveGroupSubSubCommandHook.class, onedriveGroupGetmygroupsSubSubCommand);
   }

}