package net.datenwerke.scheduler.service.scheduler.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecution;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecution;

@HookConfig
public interface SchedulerExecutionHook extends Hook {

   /**
    * happens outside a transaction ..
    * 
    * @param job
    * @param logEntry
    */
   void jobExecutionAboutToStart(AbstractJob job, ExecutionLogEntry logEntry);

   /**
    * happens outside a transaction ..
    * 
    * @param job
    * @param logEntry
    */
   void actionExecutionAboutToStart(AbstractJob job, ExecutionLogEntry logEntry);

   /**
    * wrapped in transaction
    * 
    * @param job
    * @param logEntry
    */
   void executionEndedSuccessfully(AbstractJob job, ExecutionLogEntry logEntry);

   /**
    * wrapped in transaction
    * 
    * @param job
    * @param logEntry
    * @param e
    */
   void executionEndedAbnormally(AbstractJob job, ExecutionLogEntry logEntry, Exception e);

   /**
    * 
    * @param job
    * @param logEntry
    */
   VetoJobExecution doesVetoExecution(AbstractJob job, ExecutionLogEntry logEntry);

   /**
    * 
    * @param job
    * @param logEntry
    */
   VetoActionExecution doesVetoActionExecution(AbstractJob job, ExecutionLogEntry logEntry);

   /**
    * 
    * @param job
    * @param logEntry
    */
   void informAboutVeto(AbstractJob job, ExecutionLogEntry logEntry, VetoJobExecution veto);

   /**
    *   
    */
   void informAboutVeto(AbstractJob job, ExecutionLogEntry logEntry, VetoActionExecution veto);

   /**
    * within transaction
    */
   void actionExecutionEndedAbnormally(AbstractJob job, AbstractAction action, ActionEntry actionEntry, Exception e);

   /**
    * within transaction
    */
   void jobExecutionEndedAbnormally(AbstractJob job, JobEntry jobEntry, Exception e);

   /**
    * wrapped in transaction
    * 
    * @param job
    * @param action
    * @param logEntry
    */
   void actionExecutionEndedSuccessfully(AbstractJob job, AbstractAction action, ExecutionLogEntry logEntry);

}
