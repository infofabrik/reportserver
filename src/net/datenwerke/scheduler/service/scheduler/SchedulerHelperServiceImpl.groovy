package net.datenwerke.scheduler.service.scheduler

import javax.inject.Inject

import com.google.inject.Provider

import net.datenwerke.rs.scheduler.service.scheduler.genrights.SchedulingBasicSecurityTarget
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob
import net.datenwerke.rs.scripting.service.jobs.ScriptExecuteJob
import net.datenwerke.security.service.security.SecurityService
import net.datenwerke.security.service.security.SecurityServiceSecuree
import net.datenwerke.security.service.security.rights.Execute
import net.datenwerke.security.service.security.rights.Read
import net.datenwerke.security.service.usermanager.UserManagerService
import net.datenwerke.security.service.usermanager.entities.User

public class SchedulerHelperServiceImpl implements SchedulerHelperService {
   
   private final Provider<SchedulerService> schedulerServiceProvider
   private final Provider<UserManagerService> userManagerServiceProvider
   private final Provider<SecurityService> securityServiceProvider
   
   @Inject
   public SchedulerHelperServiceImpl(
      Provider<SchedulerService> schedulerServiceProvider,
      Provider<UserManagerService> userManagerServiceProvider,
      Provider<SecurityService> securityServiceProvider
      ) {
      this.schedulerServiceProvider = schedulerServiceProvider
      this.userManagerServiceProvider = userManagerServiceProvider
      this.securityServiceProvider = securityServiceProvider
   }

   @Override
   public void replaceSchedulerUser(final User oldUser, final User newUser) {
      def schedulerService = schedulerServiceProvider.get()
      
      // check permissions
      schedulerService.jobStore.allJobs
            .findAll { it.active } // we don't want archived jobs
            .each { job ->
               if (job instanceof ReportExecuteJob)
                  assertReportJobPermissions job, oldUser, newUser
               else if (job instanceof ScriptExecuteJob)
                  assertScriptJobPermissions job, oldUser, newUser
            }
      
      // replace user
      schedulerService.jobStore.allJobs
         .findAll { it.active } // we don't want archived jobs
         .each{ job -> // Both ReportExecuteJobs and ScriptExecuteJobs
            if (job.scheduledBy == oldUser)
               job.scheduledBy = newUser
   
            /* Important: set executor before setting owner because of 
             * net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob.prePersist()
             */
            if (job.executor == oldUser)
               job.executor = newUser
            if (job instanceof ReportExecuteJob) {
               def owners = job.owners
               if (oldUser in owners) {
                  owners = owners - oldUser + newUser
                  job.owners = owners
               }
               def recipients = userManagerServiceProvider.get().getUsers(job.recipientsIds)
               if (oldUser in recipients) {
                  recipients = recipients - oldUser + newUser
                  job.recipients = (recipients as Set) as List // remove duplicates
               }
            }
            schedulerService.merge job
         }
      
   }
   
   private def assertReportJobPermissions(job, oldUser, newUser) {
      def securityService = securityServiceProvider.get()
      if (job.scheduledBy == oldUser
            || job.executor == oldUser
            || oldUser in job.owners) {
         if (!securityService.checkRights(newUser, SchedulingBasicSecurityTarget, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'SchedulingBasicSecurityTarget' permission: Execute")
         if (!securityService.checkRights(newUser, job.report, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'Execute' permission on report: '${job.report}'")
      }
      def recipients = userManagerServiceProvider.get().getUsers(job.recipientsIds)
      if (oldUser in recipients) {
         if(!newUser.email) 
            throw new IllegalStateException("'$newUser' email is empty")
         if(!securityService.checkRights(newUser, job.report, SecurityServiceSecuree, Read))
            throw new IllegalStateException("'$newUser' does not have 'Read' permission on report: '${job.report}'")
      }
   }
   
   private def assertScriptJobPermissions(job, oldUser, newUser) {
      def securityService = securityServiceProvider.get()
      if (job.scheduledBy == oldUser
            || job.executor == oldUser) {
         if (!securityService.checkRights(newUser, SchedulingBasicSecurityTarget, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'SchedulingBasicSecurityTarget' permission: Execute")
         if (!securityService.checkRights(newUser, job.script, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'Execute' permission on script: '${job.script}'")
      }
   }
   
}