package net.datenwerke.scheduler.service.scheduler.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecution;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecution;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class SchedulerExecutionHookAdapter implements SchedulerExecutionHook {

   @Override
   public void jobExecutionAboutToStart(AbstractJob job, ExecutionLogEntry logEntry) {
   }

   @Override
   public void actionExecutionAboutToStart(AbstractJob job, ExecutionLogEntry logEntry) {
   }

   @Override
   public void executionEndedSuccessfully(AbstractJob job, ExecutionLogEntry logEntry) {
   }

   @Override
   public void executionEndedAbnormally(AbstractJob job, ExecutionLogEntry logEntry, Exception e) {
   }

   @Override
   public VetoJobExecution doesVetoExecution(AbstractJob job, ExecutionLogEntry logEntry) {
      return null;
   }

   @Override
   public void informAboutVeto(AbstractJob job, ExecutionLogEntry logEntry, VetoJobExecution veto) {
   }

   @Override
   public void actionExecutionEndedAbnormally(AbstractJob job, AbstractAction action, ActionEntry actionEntry,
         Exception e) {
   }

   @Override
   public void jobExecutionEndedAbnormally(AbstractJob job, JobEntry jobEntry, Exception e) {
   }

   @Override
   public void actionExecutionEndedSuccessfully(AbstractJob job, AbstractAction action, ExecutionLogEntry logEntry) {
   }

   @Override
   public VetoActionExecution doesVetoActionExecution(AbstractJob job, ExecutionLogEntry logEntry) {
      return null;
   }

   @Override
   public void informAboutVeto(AbstractJob job, ExecutionLogEntry logEntry, VetoActionExecution veto) {

   }

}
