package net.datenwerke.rs.scheduler.service.scheduler;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleStartupDelay;
import net.datenwerke.rs.scheduler.service.scheduler.eventhandler.HandleReportForceRemoveEventHandler;
import net.datenwerke.rs.scheduler.service.scheduler.eventhandler.HandleReportRemoveEventHandler;
import net.datenwerke.rs.scheduler.service.scheduler.eventhandler.HandleUserForceRemoveEventHandler;
import net.datenwerke.rs.scheduler.service.scheduler.eventhandler.HandleUserRemoveEventHandler;
import net.datenwerke.rs.scheduler.service.scheduler.hookers.ScheduleSendToActionHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hookers.ScheduleViaEmailHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hookers.SchedulerCategoryProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hookers.SchedulerShutdownHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hookers.UsageStatisticsSchedulerProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.SchedulerEntryProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.commands.SchedulerCommand;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.commands.SchedulerDaemonSubCommand;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.commands.SchedulerListFireTimesSubCommand;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.commands.SchedulerListSubCommand;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.commands.SchedulerRemoveSubCommand;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.commands.SchedulerReplaceUserSubCommand;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.commands.SchedulerUnscheduleSubCommand;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.hooks.SchedulerSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.exceptions.SchedulerNotEnabledException;
import net.datenwerke.scheduler.service.scheduler.exceptions.SchedulerStartupException;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.usermanager.entities.User;

public class RsSchedulerStartup {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Inject
   public RsSchedulerStartup(HookHandlerService hookHandler, EventBus eventBus,

         Provider<ScheduleViaEmailHooker> emailSchedulerProvider,
         Provider<ScheduleSendToActionHooker> sendToSchedulerProvider,

         Provider<SchedulerCommand> schedulerCommand,

         Provider<SchedulerListSubCommand> listSubCommand,
         Provider<SchedulerListFireTimesSubCommand> listFireTimesSubCommand,
         Provider<SchedulerRemoveSubCommand> removeSubCommand,
         Provider<SchedulerUnscheduleSubCommand> unscheduleSubCommand,
         Provider<SchedulerDaemonSubCommand> daemonSubCommand,
         Provider<SchedulerReplaceUserSubCommand> replaceUserSubCommand,

         HandleReportRemoveEventHandler handleReportRemoveEventHandler,
         HandleReportForceRemoveEventHandler handleReportForceRemoveEventHandler,

         HandleUserRemoveEventHandler handleUserRemoveEventHandler,
         HandleUserForceRemoveEventHandler handleUserForceRemoveEventHandler,

         SchedulerShutdownHooker shutdownHooker,

         @SchedulerModuleStartupDelay final Long startupDelay,

         final Injector injector, 
         final SchedulerService schedulerService,
         
         final Provider<SchedulerCategoryProviderHooker> usageStatistics,
         final Provider<UsageStatisticsSchedulerProviderHooker> usageStatsSchedulerProvider
         ) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, User.class, handleUserRemoveEventHandler);
      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, User.class, handleUserForceRemoveEventHandler);

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, Report.class, handleReportRemoveEventHandler);
      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, Report.class,
            handleReportForceRemoveEventHandler);

      hookHandler.attachHooker(TerminalCommandHook.class, schedulerCommand);

      hookHandler.attachHooker(SchedulerSubCommandHook.class, listSubCommand);
      hookHandler.attachHooker(SchedulerSubCommandHook.class, listFireTimesSubCommand);
      hookHandler.attachHooker(SchedulerSubCommandHook.class, removeSubCommand);
      hookHandler.attachHooker(SchedulerSubCommandHook.class, unscheduleSubCommand);
      hookHandler.attachHooker(SchedulerSubCommandHook.class, daemonSubCommand);
      hookHandler.attachHooker(SchedulerSubCommandHook.class, replaceUserSubCommand);

      hookHandler.attachHooker(ContextHook.class, shutdownHooker);

      hookHandler.attachHooker(ScheduleConfigProviderHook.class, emailSchedulerProvider);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, sendToSchedulerProvider);

      final Thread sStarter = new Thread(() -> {
         while (true) {
            try {
               injector.getInstance(EntityManager.class);
               break;
            } catch (Exception e) {

            }
         }
         try {
            /* wait for 5 minutes */
            try {
               Thread.sleep(startupDelay);
            } catch (InterruptedException e) {
               logger.warn("Scheduler was started prematurely", e);
            }

            schedulerService.start();
         } catch (SchedulerStartupException e) {
            if (!(e instanceof SchedulerNotEnabledException))
               logger.error("Could not start scheduler.", e);
         }
      });
      sStarter.setDaemon(true);

      hookHandler.attachHooker(LateInitHook.class, () -> sStarter.start(), HookHandlerService.PRIORITY_LOWER);
      
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 100);
      hookHandler.attachHooker(SchedulerEntryProviderHook.class, usageStatsSchedulerProvider,
            HookHandlerService.PRIORITY_LOW);
   }
}
