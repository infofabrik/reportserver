package net.datenwerke.rs.installation;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

/**
 * Handles the installation of report server.
 *
 */
public class ReportServerInstallationServiceImpl {
   @Inject
   private PrepareDbForReportServer prepareDbTask;

   @Inject
   private List<DbInstallationTask> dbInstallTasks;

   @Inject
   private Provider<EntityManager> emp;

   @Transactional
   public void install() {

      if (!dataIsInstalled(emp)) {
         prepareDatabase(prepareDbTask);
         runAdditionalDbInstallTasks(dbInstallTasks);
      }

      dbInstallTasks.forEach(DbInstallationTask::executeOnStartup);
   }

   private boolean dataIsInstalled(Provider<EntityManager> entityManagerProvider) {
      /* determine if the usertable is empty */
      EntityManager em = entityManagerProvider.get();
      Long countUser = (Long) em.createQuery("select count(*) from User").getSingleResult(); //$NON-NLS-1$

      return (countUser != 0);
   }

   private void runAdditionalDbInstallTasks(final List<DbInstallationTask> dbInstallTasks) {
      dbInstallTasks.forEach(DbInstallationTask::executeOnFirstRun);
   }

   private void prepareDatabase(PrepareDbForReportServer prepareDbTask) {
      prepareDbTask.executeOnFirstRun();
   }
}
