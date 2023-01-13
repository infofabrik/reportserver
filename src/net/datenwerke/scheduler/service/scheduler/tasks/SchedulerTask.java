package net.datenwerke.scheduler.service.scheduler.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Callable;

import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.exception.ExceptionService;
import net.datenwerke.scheduler.service.scheduler.annotations.StandardVetoDelay;
import net.datenwerke.scheduler.service.scheduler.annotations.StandardVetoRandomDelay;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;
import net.datenwerke.scheduler.service.scheduler.exceptions.JobExecutionException;
import net.datenwerke.scheduler.service.scheduler.helper.RetryTimeUnit;
import net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecution;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecution;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;
import net.datenwerke.scheduler.service.scheduler.stores.JobExecutionCompanion;
import net.datenwerke.scheduler.service.scheduler.stores.JobStore;

public class SchedulerTask implements Callable<SchedulerTaskResult> {

   private static final int MAX_RETRY_EXECUTION_CNT = 10;
   private static final long SLEEP_ON_RETRY = 5000;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final ExceptionService exceptionService;
   private final HookHandlerService hookHandler;
   private final int standardDelayMinutes;
   private final int standardDelayRandomMinutes;

   private JobStore jobStore;

   private AbstractJob job;
   private ExecutionLogEntry logEntry;

   private JobExecutionCompanion executionCompanion;

   @Inject
   public SchedulerTask(ExceptionService exceptionService, HookHandlerService hookHandler,

         @StandardVetoDelay Integer standardDelayMinutes, @StandardVetoRandomDelay Integer standardDelayRandomMinutes) {

      /* store object */
      this.exceptionService = exceptionService;
      this.hookHandler = hookHandler;
      this.standardDelayMinutes = standardDelayMinutes;
      this.standardDelayRandomMinutes = standardDelayRandomMinutes;
   }

   public void setJob(AbstractJob job) {
      this.job = job;
   }

   public AbstractJob getJob() {
      return job;
   }

   public void setLogEntry(ExecutionLogEntry logEntry) {
      this.logEntry = logEntry;
   }

   public ExecutionLogEntry getLogEntry() {
      return logEntry;
   }

   public void setJobStore(JobStore jobStore) {
      this.jobStore = jobStore;
   }

   public void setCompanion(JobExecutionCompanion executionCompanion) {
      this.executionCompanion = executionCompanion;
   }

   @Override
   public SchedulerTaskResult call() throws Exception {
      try {
         return safeCall();
      } catch (Exception e) {
         // something has gone really bad
         logger.error(e.getMessage(), e);
         return null;
      }
   }

   public SchedulerTaskResult safeCall() throws Exception {
      if (null == job || null == jobStore)
         throw new IllegalArgumentException("Task not properly initialized");

      List<SchedulerExecutionHook> executionHookers = hookHandler.getHookers(SchedulerExecutionHook.class);

      boolean badFailure = false;
      try {
         job = executionCompanion.beginSchedulerTask(job);

         /* init history entry */
         logEntry = executionCompanion.initHistoryEntry(job);

         /* create string builder for failed hookers */
         StringBuilder hookerErrorBuilder = new StringBuilder();

         try {
            /* notify hookers aboutJobExecution */
            notifyHookersAboutJobExecution(executionHookers, hookerErrorBuilder);

            /* veto */
            VetoJobExecution veto = null;
            for (SchedulerExecutionHook hooker : executionHookers) {
               /* transaction for veto */
               executionCompanion.beginInnerTransaction(this);
               boolean txSuccess = false;
               try {
                  veto = hooker.doesVetoExecution(job, logEntry);
                  if (null != veto) {
                     switch (veto.getMode()) {
                     case SKIP:
                        Date nextFireTime = job.getTrigger().computeNextFireTime(logEntry.getStart());
                        job.getTrigger().updateStateAfterVetoedExecution(job, nextFireTime);
                        break;
                     case CUSTOM:
                        veto.updateTrigger(job, logEntry);
                        break;
                     case RETRY:
                        nextFireTime = Calendar.getInstance().getTime();

                        RetryTimeUnit unit = veto.getRetryUnit();
                        int amount = veto.getRetryAmount();

                        if (null == unit) {
                           int minutes = standardDelayMinutes + new Random().nextInt(standardDelayRandomMinutes);
                           nextFireTime = DateUtils.addMinutes(nextFireTime, minutes);
                        } else {
                           switch (unit) {
                           case MINUTES:
                              nextFireTime = DateUtils.addMinutes(nextFireTime, amount);
                              break;
                           case HOURS:
                              nextFireTime = DateUtils.addHours(nextFireTime, amount);
                              break;
                           case DAYS:
                              nextFireTime = DateUtils.addDays(nextFireTime, amount);
                              break;
                           case WEEKS:
                              nextFireTime = DateUtils.addWeeks(nextFireTime, amount);
                              break;
                           case MONTHS:
                              nextFireTime = DateUtils.addMonths(nextFireTime, amount);
                              break;
                           case YEARS:
                              nextFireTime = DateUtils.addYears(nextFireTime, amount);
                              break;
                           default:
                              throw new IllegalArgumentException("do not know time unit: " + unit);
                           }
                        }

                        job.getTrigger().updateStateAfterVetoedExecution(job, nextFireTime);
                        break;
                     }

                     logEntry.setVetoExplanation(veto.getExplanation());
                     logEntry.setVetoMode(veto.getMode());
                     logEntry.setOutcome(Outcome.VETO);
                     logEntry.setEnd(new Date());

                     job.setLastOutcome(logEntry.getOutcome());
                     job.setExecutionStatus(JobExecutionStatus.INACTIVE);

                     txSuccess = true;
                     break; /* no more vetoes */
                  }
                  txSuccess = true;
               } finally {
                  executionCompanion.finishInnerTransaction(this, txSuccess);
               }
            }

            if (null != veto) {
               /* notify hookers aboutJobExecution */
               notifyHookersAboutVeto(executionHookers, veto, hookerErrorBuilder);
            } else {
               /* execute job */
               executeJob(executionHookers, hookerErrorBuilder);

               /* execute actions */
               boolean actionsVetoed = executeActions(executionHookers, hookerErrorBuilder);

               if (!actionsVetoed) {
                  /*
                   * if we reached this point, then everything seems to have gone well prepare the
                   * history object and
                   */

                  /* prepare outcome */
                  executionCompanion.beginInnerTransaction(this);
                  boolean innerSuccess = false;
                  try {
                     /* update firetime */
                     List<ExecutionLogEntry> additionalEntries = job.getTrigger()
                           .updateStateAfterSuccessfulExecution(job);
                     if (null != additionalEntries)
                        for (ExecutionLogEntry entry : additionalEntries)
                           job.getHistory().addExecutionLogEntry(entry);

                     /* get errors from action execution hookers */
                     String hookerError = hookerErrorBuilder.toString();

                     /* everything worked fine */
                     if (!StringUtils.isEmpty(hookerError)) {
                        logEntry.setOutcome(Outcome.FAILURE);
                        logEntry.setBadErrorDescription(hookerError);
                     } else
                        logEntry.setOutcome(Outcome.SUCCESS);
                     logEntry.setEnd(new Date());
                     job.setExecutionStatus(JobExecutionStatus.INACTIVE);
                     job.setLastOutcome(logEntry.getOutcome());

                     innerSuccess = true;
                  } finally {
                     executionCompanion.finishInnerTransaction(this, innerSuccess);
                  }

                  /* notify hookers */
                  for (SchedulerExecutionHook hooker : executionHookers) {
                     executionCompanion.beginInnerTransaction(this);
                     innerSuccess = false;
                     try {
                        /* execute */
                        hooker.executionEndedSuccessfully(job, logEntry);

                        innerSuccess = true;
                     } finally {
                        executionCompanion.finishInnerTransaction(this, innerSuccess);
                     }
                  }
               }
            }
         } catch (Exception e) {
            executionCompanion.beginInnerTransaction(this);

            boolean innerSuccess = false;
            try {
               /* update firetime */
               List<ExecutionLogEntry> additionalEntries = job.getTrigger().updateStateAfterFailedExecution(job);
               if (null != additionalEntries)
                  for (ExecutionLogEntry entry : additionalEntries)
                     job.getHistory().addExecutionLogEntry(entry);

               /* get errors from action execution hookers */
               String hookerError = hookerErrorBuilder.toString();

               /* persist */
               logEntry.setOutcome(Outcome.FAILURE);
               logEntry.setEnd(new Date());
               logEntry.setBadErrorDescription(exceptionService.exceptionToString(e)
                     + (StringUtils.isEmpty(hookerError) ? "" : "\n" + String.valueOf(hookerError)));
               job.setExecutionStatus(JobExecutionStatus.INACTIVE);
               job.setLastOutcome(logEntry.getOutcome());

               innerSuccess = true;
            } finally {
               executionCompanion.finishInnerTransaction(this, innerSuccess);
            }

            /* notify hookers */
            for (SchedulerExecutionHook hooker : executionHookers) {
               executionCompanion.beginInnerTransaction(this);

               innerSuccess = false;
               try {
                  /* execute */
                  hooker.executionEndedAbnormally(job, logEntry, e);

                  innerSuccess = true;
               } catch (Exception e2) {
                  logger.warn(
                        "extension " + hooker.getClass().getName() + " failed to executed executionEndedAbnormally",
                        e2);
               } finally {
                  try {
                     executionCompanion.finishInnerTransaction(this, innerSuccess);
                  } catch (Exception e3) {
                     logger.warn(
                           "extension " + hooker.getClass().getName() + " failed to executed executionEndedAbnormally",
                           e3);
                  }
               }
            }
         }
      } catch (Exception e) {
         // TODO .. log bad!
         logger.error(e.getMessage(), e);
         badFailure = true;

         executionCompanion.beginInnerTransaction(this);

         boolean innerSuccess = false;
         try {
            logEntry.setOutcome(Outcome.FAILURE);
            logEntry.setEnd(new Date());
            logEntry.setBadErrorDescription(exceptionService.exceptionToString(e));

            innerSuccess = true;
         } finally {
            executionCompanion.finishInnerTransaction(this, innerSuccess);
         }
      } finally {
         executionCompanion.endSchedulerTask(job, logEntry, badFailure);
      }

      return new SchedulerTaskResult(job);
   }

   private void notifyHookersAboutVeto(List<SchedulerExecutionHook> executionHookers, VetoJobExecution veto,
         StringBuilder hookerErrorBuilder) {
      for (SchedulerExecutionHook hooker : executionHookers) {
         executionCompanion.beginInnerTransaction(this);
         boolean success = false;
         try {
            hooker.informAboutVeto(job, logEntry, veto);

            executionCompanion.flush(); /* fail early */

            success = true;
         } catch (Exception e) {
            logger.warn("extension " + hooker.getClass().getName() + " failed to executed informAboutVeto", e);

            hookerErrorBuilder.append("extension ").append(hooker.getClass().getName())
                  .append(" failed to executed informAboutVeto\n");
            hookerErrorBuilder.append(exceptionService.exceptionToString(e));
         } finally {
            finishInnerHookerTransaction(hooker, hookerErrorBuilder, "informAboutVeto", success);
         }
      }
   }

   private void notifyHookersAboutVeto(List<SchedulerExecutionHook> executionHookers, VetoActionExecution veto,
         StringBuilder hookerErrorBuilder) {
      for (SchedulerExecutionHook hooker : executionHookers) {
         executionCompanion.beginInnerTransaction(this);
         boolean success = false;
         try {
            hooker.informAboutVeto(job, logEntry, veto);

            executionCompanion.flush(); /* fail early */

            success = true;
         } catch (Exception e) {
            logger.warn("extension " + hooker.getClass().getName() + " failed to executed informAboutVeto", e);

            hookerErrorBuilder.append("extension ").append(hooker.getClass().getName())
                  .append(" failed to executed informAboutVeto\n");
            hookerErrorBuilder.append(exceptionService.exceptionToString(e));
         } finally {
            finishInnerHookerTransaction(hooker, hookerErrorBuilder, "informAboutVeto", success);
         }
      }
   }

   private void notifyHookersAboutJobExecution(List<SchedulerExecutionHook> executionHookers,
         StringBuilder hookerErrorBuilder) {
      for (SchedulerExecutionHook hooker : executionHookers) {
         executionCompanion.beginInnerTransaction(this);
         boolean success = false;
         try {
            hooker.jobExecutionAboutToStart(job, logEntry);

            executionCompanion.flush(); /* fail early */

            success = true;
         } catch (Exception e) {
            logger.warn("extension " + hooker.getClass().getName() + " failed to executed jobExecutionAboutToStart", e);

            hookerErrorBuilder.append("extension ").append(hooker.getClass().getName())
                  .append(" failed to executed jobExecutionAboutToStart\n");
            hookerErrorBuilder.append(exceptionService.exceptionToString(e));
         } finally {
            finishInnerHookerTransaction(hooker, hookerErrorBuilder, "jobExecutionAboutToStart", success);
         }
      }
   }

   /**
    * 
    * @param executionHookers
    * @param hookerErrorBuilder
    * @return true in case the actions were vetoed
    * @throws Exception
    */
   private boolean executeActions(List<SchedulerExecutionHook> executionHookers, StringBuilder hookerErrorBuilder)
         throws Exception {
      if (null == job.getActions())
         return false;

      /* veto */
      VetoActionExecution veto = null;
      for (SchedulerExecutionHook hooker : executionHookers) {
         /* transaction for veto */
         executionCompanion.beginInnerTransaction(this);
         boolean txSuccess = false;
         try {
            veto = hooker.doesVetoActionExecution(job, logEntry);
            if (null != veto) {
               switch (veto.getMode()) {
               case SKIP:
                  Date nextFireTime = job.getTrigger().computeNextFireTime(logEntry.getStart());
                  job.getTrigger().updateStateAfterVetoedExecution(job, nextFireTime);
                  break;
               case CUSTOM:
                  veto.updateTrigger(job, logEntry);
                  break;
               case RETRY:
                  nextFireTime = Calendar.getInstance().getTime();

                  RetryTimeUnit unit = veto.getRetryUnit();
                  int amount = veto.getRetryAmount();

                  if (null == unit) {
                     int minutes = standardDelayMinutes + new Random().nextInt(standardDelayRandomMinutes);
                     nextFireTime = DateUtils.addMinutes(nextFireTime, minutes);
                  } else {
                     switch (unit) {
                     case MINUTES:
                        nextFireTime = DateUtils.addMinutes(nextFireTime, amount);
                        break;
                     case HOURS:
                        nextFireTime = DateUtils.addHours(nextFireTime, amount);
                        break;
                     case DAYS:
                        nextFireTime = DateUtils.addDays(nextFireTime, amount);
                        break;
                     case WEEKS:
                        nextFireTime = DateUtils.addWeeks(nextFireTime, amount);
                        break;
                     case MONTHS:
                        nextFireTime = DateUtils.addMonths(nextFireTime, amount);
                        break;
                     case YEARS:
                        nextFireTime = DateUtils.addYears(nextFireTime, amount);
                        break;
                     default:
                        throw new IllegalArgumentException("do not know time unit: " + unit);
                     }
                  }

                  job.getTrigger().updateStateAfterVetoedExecution(job, nextFireTime);
                  break;
               }

               logEntry.setVetoExplanation(veto.getExplanation());
               switch (veto.getMode()) {
               case SKIP:
                  logEntry.setVetoMode(VetoJobExecutionMode.SKIP);
                  break;
               case RETRY:
                  logEntry.setVetoMode(VetoJobExecutionMode.RETRY);
                  break;
               case CUSTOM:
                  logEntry.setVetoMode(VetoJobExecutionMode.CUSTOM);
                  break;
               }

               logEntry.setOutcome(Outcome.ACTION_VETO);
               logEntry.setEnd(new Date());

               job.setLastOutcome(logEntry.getOutcome());
               job.setExecutionStatus(JobExecutionStatus.INACTIVE);

               txSuccess = true;
               break; /* no more vetoes */
            }
            txSuccess = true;
         } finally {
            executionCompanion.finishInnerTransaction(this, txSuccess);
         }
      }

      if (null != veto) {
         /* notify hookers aboutJobExecution */
         notifyHookersAboutVeto(executionHookers, veto, hookerErrorBuilder);
         return true;
      }

      Exception anActionException = null;
      for (AbstractAction action : new ArrayList<AbstractAction>(
            job.getActions())) { /*
                                  * copy actions into own array to not get lazy init exception in case of an
                                  * action failure
                                  */
         boolean actionExecutionSuccess = false;
         try {
            executeAction(action, executionHookers);

            actionExecutionSuccess = true;
         } catch (Exception e) {
            logger.warn("action execution failed", e);

            /* keep track of exception */
            anActionException = e;

            /*
             * action was NOT executed successfully add history denoting that
             */
            executionCompanion.beginInnerTransaction(this);
            ActionEntry actionEntry;
            boolean innerSuccess = false;
            try {
               actionEntry = executionCompanion.initActionEntry(logEntry);
               actionEntry.setOutcome(Outcome.FAILURE);
               if (e instanceof ActionExecutionException)
                  actionEntry.setErrorDescription(e.getMessage());
               else
                  actionEntry.setErrorDescription(exceptionService.exceptionToString(e));
               action.adjustActionEntryForFailure(actionEntry);

               logEntry.addActionEntry(actionEntry);

               executionCompanion.updateStateAfterActionExecution(job, logEntry, actionEntry, false);

               innerSuccess = true;
            } finally {
               executionCompanion.finishInnerTransaction(this, innerSuccess);
            }

            /* notify hookers that action failed */
            for (SchedulerExecutionHook hooker : executionHookers) {
               executionCompanion.beginInnerTransaction(this);
               boolean success = false;
               try {
                  hooker.actionExecutionEndedAbnormally(job, action, actionEntry, e);

                  executionCompanion.flush(); /* fail early */

                  success = true;
               } catch (Exception e2) {
                  /*
                   * as apparently the job failed, we won't further log the failure of this failed
                   * hooker
                   */
                  logger.warn("extension " + hooker.getClass().getName()
                        + " failed to executed actionExecutionEndedAbnormally", e2);
               } finally {
                  executionCompanion.finishInnerTransaction(this, success);
               }
            }

         }

         if (actionExecutionSuccess) {
            /*
             * action was executed successfully add history denoting that
             */
            executionCompanion.beginInnerTransaction(this);
            boolean innerSuccess = false;
            try {
               ActionEntry actionEntry = executionCompanion.initActionEntry(logEntry);
               actionEntry.setOutcome(Outcome.SUCCESS);
               action.adjustActionEntryForSuccess(actionEntry);

               executionCompanion.updateStateAfterActionExecution(job, logEntry, actionEntry, true);

               innerSuccess = true;
            } finally {
               executionCompanion.finishInnerTransaction(this, innerSuccess);
            }

            /* notify hookers */
            for (SchedulerExecutionHook hooker : executionHookers) {
               executionCompanion.beginInnerTransaction(this);
               boolean success = false;
               try {
                  hooker.actionExecutionEndedSuccessfully(job, action, logEntry);
                  executionCompanion.flush(); /* fail early */

                  success = true;
               } catch (Exception e) {
                  logger.warn("extension " + hooker.getClass().getName()
                        + " failed to executed actionExecutionEndedSuccessfully", e);

                  hookerErrorBuilder.append("extension ").append(hooker.getClass().getName())
                        .append(" failed to executed actionExecutionEndedSuccessfully\n");
                  hookerErrorBuilder.append(exceptionService.exceptionToString(e));
               } finally {
                  finishInnerHookerTransaction(hooker, hookerErrorBuilder, "actionExecutionEndedSuccessfully", success);
               }
            }
         }

      }

      if (null != anActionException)
         throw anActionException;

      return false;
   }

   private void executeAction(AbstractAction action, List<SchedulerExecutionHook> executionHookers)
         throws ActionExecutionException {
      /* init */
      action = executionCompanion.initActionForExecution(action);

      int retryCnt = MAX_RETRY_EXECUTION_CNT;

      while (retryCnt-- > 0) {
         try {
            executionCompanion.beginInnerTransaction(this);
            boolean success = false;
            try {
               action.execute(job);

               success = true;
            } finally {
               executionCompanion.finishInnerTransaction(this, success);
            }
            retryCnt = 0;
         } catch (RuntimeException e) {
            if (e instanceof PersistenceException || e instanceof HibernateException || (null != e.getCause()
                  && (e.getCause() instanceof PersistenceException || e.getCause() instanceof HibernateException))) {
               try {
                  Thread.sleep(SLEEP_ON_RETRY);
               } catch (InterruptedException e1) {
                  logger.info("awoke from sleep", e1);
               }
               continue;
            }
            throw e;
         }
      }

   }

   private void executeJob(List<SchedulerExecutionHook> executionHookers, StringBuilder hookerErrorBuilder)
         throws Exception {
      boolean jobExecutionSuccessful = false;
      try {
         int retryCnt = MAX_RETRY_EXECUTION_CNT;

         while (retryCnt-- > 0) {
            try {
               /* execute job */
               executionCompanion.beginInnerTransaction(this);
               try {
                  job.execute();

                  jobExecutionSuccessful = true;
               } finally {
                  executionCompanion.finishInnerTransaction(this, jobExecutionSuccessful);
               }
               retryCnt = 0;
            } catch (Exception e) {
               jobExecutionSuccessful = false;

               if (e instanceof PersistenceException || e instanceof HibernateException || (null != e.getCause()
                     && (e.getCause() instanceof PersistenceException || e.getCause() instanceof HibernateException))) {
                  try {
                     Thread.sleep(SLEEP_ON_RETRY);
                  } catch (InterruptedException e1) {
                     logger.info("awoke from sleep", e1);
                  }
                  continue;
               }
               throw (e);
            }
         }
      } catch (Exception e) {
         final Optional<String> details = exceptionService.getReportExecutionExceptionDetailsMessage(e);
         logger.warn("job execution failed " + (details.isPresent() ? ", details: " + details.get() : ""), e);

         /* denote that job ended not successfully */
         executionCompanion.beginInnerTransaction(this);
         JobEntry jobEntry;
         boolean innerSuccess = false;
         try {
            /* update fields */
            jobEntry = executionCompanion.initJobEntry(logEntry);
            jobEntry.setOutcome(Outcome.FAILURE);
            jobEntry.setErrorDescription(
                  e instanceof JobExecutionException ? e.getMessage() : exceptionService.exceptionToString(e));
            job.adjustJobEntryForFailure(jobEntry);

            logEntry.setJobEntry(jobEntry);

            executionCompanion.updateStateAfterJobExecution(job, logEntry, jobEntry, false);

            innerSuccess = false;
         } finally {
            executionCompanion.finishInnerTransaction(this, innerSuccess);
         }

         /* inform hookers that something went wrong */
         for (SchedulerExecutionHook hooker : executionHookers) {
            executionCompanion.beginInnerTransaction(this);
            boolean success = false;
            try {
               hooker.jobExecutionEndedAbnormally(job, jobEntry, e);

               success = true;
            } catch (Exception e2) {
               /*
                * as apparently the job failed, we won't further log the failure of this failed
                * hooker
                */
               logger.warn(
                     "extension " + hooker.getClass().getName() + " failed to executed jobExecutionEndedAbnormally",
                     e2);
            } finally {
               executionCompanion.finishInnerTransaction(this, success);
            }
         }

         throw e;
      }

      if (jobExecutionSuccessful) {
         /* denote that job ended successfully */
         executionCompanion.beginInnerTransaction(this);
         boolean innerSuccess = false;
         try {
            JobEntry jobEntry = executionCompanion.initJobEntry(logEntry);
            jobEntry.setOutcome(Outcome.SUCCESS);
            job.adjustJobEntryForSuccess(jobEntry);

            executionCompanion.updateStateAfterJobExecution(job, logEntry, jobEntry, true);

            innerSuccess = true;
         } finally {
            executionCompanion.finishInnerTransaction(this, innerSuccess);
         }

         /*
          * notify hookers that action execution is about to start (resp. that job
          * execution did go ok)
          */
         for (SchedulerExecutionHook hooker : executionHookers) {
            executionCompanion.beginInnerTransaction(this);
            boolean success = false;
            try {
               hooker.actionExecutionAboutToStart(job, logEntry);
               success = true;
            } catch (Exception e) {
               logger.warn(
                     "extension " + hooker.getClass().getName() + " failed to executed actionExecutionAboutToStart", e);

               hookerErrorBuilder.append("extension ").append(hooker.getClass().getName())
                     .append(" failed to executed actionExecutionAboutToStart\n");
               hookerErrorBuilder.append(exceptionService.exceptionToString(e));
            } finally {
               finishInnerHookerTransaction(hooker, hookerErrorBuilder, "actionExecutionAboutToStart", success);
            }

         }
      }
   }

   /**
    * Catch exception on transaction finish if necessary
    * 
    * @param hooker
    * @param hookerErrorBuilder
    * @param success
    */
   private void finishInnerHookerTransaction(Object hooker, StringBuilder hookerErrorBuilder, String methodName,
         boolean success) {
      try {
         executionCompanion.finishInnerTransaction(this, success);
      } catch (Exception e) {
         logger.warn("extension " + hooker.getClass().getName() + " failed to executed " + methodName, e);

         hookerErrorBuilder.append("extension ").append(hooker.getClass().getName())
               .append(" failed to executed " + methodName + "\n");
         hookerErrorBuilder.append(exceptionService.exceptionToString(e));
      }
   }
}
