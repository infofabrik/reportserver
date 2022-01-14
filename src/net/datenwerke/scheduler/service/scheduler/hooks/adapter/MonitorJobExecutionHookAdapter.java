package net.datenwerke.scheduler.service.scheduler.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.hooks.MonitorJobExecutionHook;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class MonitorJobExecutionHookAdapter implements MonitorJobExecutionHook {

   @Override
   public void notifyOfExecution(AbstractJob reportExecuteJob) {
   }

   @Override
   public void jobExecutedSuccessfully(AbstractJob reportExecuteJob) {
   }

   @Override
   public void jobExecutionFailed(AbstractJob reportExecuteJob, Exception e) {
   }

}
