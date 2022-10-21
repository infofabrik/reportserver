package net.datenwerke.rs.condition.service.condition.hookers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.condition.client.condition.dto.ConditionFailureStrategy;
import net.datenwerke.rs.condition.service.condition.ConditionModule;
import net.datenwerke.rs.condition.service.condition.ConditionService;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.condition.service.condition.hooks.ConditionProviderHook;
import net.datenwerke.rs.condition.service.condition.locale.ConditionMessages;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.BaseProperty;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.helper.RetryActionExecution;
import net.datenwerke.scheduler.service.scheduler.helper.RetryJobExecution;
import net.datenwerke.scheduler.service.scheduler.helper.RetryTimeUnit;
import net.datenwerke.scheduler.service.scheduler.helper.SkipActionExecution;
import net.datenwerke.scheduler.service.scheduler.helper.SkipJobExecution;
import net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecution;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecution;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class SchedulerVetoProvider implements SchedulerExecutionHook {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final ConditionService conditionService;
   private final HookHandlerService hookHandler;

   @Inject
   public SchedulerVetoProvider(ConditionService conditionService, HookHandlerService hookHandler) {
      this.conditionService = conditionService;
      this.hookHandler = hookHandler;
   }

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
      if (!(job instanceof ReportExecuteJob))
         return null;

      try {
         ReportExecuteJob rJob = (ReportExecuteJob) job;

         int i = 0;
         while (rJob
               .hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY)) {
            boolean result = true;
            String name = "";
            if (rJob.hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)) {
               String idStr = rJob
                     .getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)
                     .getValue();
               String expressionStr = rJob
                     .getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_EXPRESSION)
                     .getValue();

               Long id = Long.valueOf(idStr);

               ReportCondition cond = conditionService.getReportConditionById(id);
               if (null != cond) {
                  try {
                     result = conditionService.executeCondition(cond, expressionStr, rJob.getExecutor());
                     name = cond.getName();
                  } catch (Exception e) {
                     logger.warn("Evaluating scheduler veto expression failed", e);
                  }
               }
            } else {
               String key = rJob
                     .getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY)
                     .getValue();
               BaseProperty exprProperty = rJob
                     .getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_EXPRESSION);
               String expressionStr = null == exprProperty ? null : exprProperty.getValue();

               for (ConditionProviderHook provider : hookHandler.getHookers(ConditionProviderHook.class)) {
                  if (provider.consumes(key) && provider.isBeforeJob()) {
                     result = provider.execute(key, expressionStr, rJob.getExecutor(), rJob);
                     name = key;
                     break;
                  }
               }
            }

            if (!result) {
               ConditionFailureStrategy strategy = getFailureStrategy(rJob);
               RetryTimeUnit unit = getRetryUnit(rJob);
               int amount = getRetryAmount(rJob);

               switch (strategy) {
               case SKIP:
                  return new SkipJobExecution(ConditionMessages.INSTANCE.skipMsg(name));
               case RETRY:
                  return new RetryJobExecution(ConditionMessages.INSTANCE.retryMsg(name), unit, amount);
               default:
                  throw new IllegalArgumentException("Do not know strategy: " + strategy);
               }
            }

            i++;
         }
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }

      return null;
   }

   @Override
   public VetoActionExecution doesVetoActionExecution(AbstractJob job, ExecutionLogEntry logEntry) {
      if (!(job instanceof ReportExecuteJob))
         return null;

      try {
         ReportExecuteJob rJob = (ReportExecuteJob) job;

         int i = 0;
         while (rJob
               .hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY)) {
            boolean result = true;
            String name = "";
            if (!rJob
                  .hasProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_ID)) {
               String key = rJob
                     .getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_CONDITION_KEY)
                     .getValue();
               BaseProperty exprProperty = rJob
                     .getProperty(ConditionModule.PROPERTY_PREFIX + i + ConditionModule.PROPERTY_POSTFIX_EXPRESSION);
               String expressionStr = null == exprProperty ? null : exprProperty.getValue();

               for (ConditionProviderHook provider : hookHandler.getHookers(ConditionProviderHook.class)) {
                  if (provider.consumes(key) && provider.isBeforeActions()) {
                     result = provider.execute(key, expressionStr, rJob.getExecutor(), rJob);
                     name = key;
                     break;
                  }
               }
            }

            if (!result) {
               ConditionFailureStrategy strategy = getFailureStrategy(rJob);
               RetryTimeUnit unit = getRetryUnit(rJob);
               int amount = getRetryAmount(rJob);

               switch (strategy) {
               case SKIP:
                  return new SkipActionExecution(ConditionMessages.INSTANCE.skipMsg(name));
               case RETRY:
                  return new RetryActionExecution(ConditionMessages.INSTANCE.retryMsg(name), unit, amount);
               default:
                  throw new IllegalArgumentException("Do not know strategy: " + strategy);
               }
            }

            i++;
         }
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }

      return null;
   }

   private int getRetryAmount(ReportExecuteJob rJob) {
      if (rJob.hasProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_AMOUNT)) {
         int amount = Integer.parseInt(rJob.getProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_AMOUNT).getValue());
         return amount;
      }
      return 1;
   }

   private RetryTimeUnit getRetryUnit(ReportExecuteJob rJob) {
      if (rJob.hasProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_UNIT)) {
         int ordinal = Integer.valueOf(rJob.getProperty(ConditionModule.PROPERTY_RETRY_STRATEGY_UNIT).getValue());
         for (RetryTimeUnit unit : RetryTimeUnit.values()) {
            if (unit.ordinal() == ordinal)
               return unit;
         }

      }
      return RetryTimeUnit.HOURS;
   }

   private ConditionFailureStrategy getFailureStrategy(ReportExecuteJob rJob) {
      if (rJob.hasProperty(ConditionModule.PROPERTY_FAILURE_STRATEGY)) {
         int ordinal = Integer.valueOf(rJob.getProperty(ConditionModule.PROPERTY_FAILURE_STRATEGY).getValue());
         for (ConditionFailureStrategy strat : ConditionFailureStrategy.values()) {
            if (strat.ordinal() == ordinal)
               return strat;
         }
      }
      return ConditionFailureStrategy.SKIP;
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
      // TODO Auto-generated method stub

   }

   @Override
   public void informAboutVeto(AbstractJob job, ExecutionLogEntry logEntry, VetoActionExecution veto) {
      // TODO Auto-generated method stub

   }

}
