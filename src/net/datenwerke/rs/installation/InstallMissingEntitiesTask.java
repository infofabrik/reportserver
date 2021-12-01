package net.datenwerke.rs.installation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.rs.ftp.service.ftp.action.ScheduleAsFtpFileAction;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;

public class InstallMissingEntitiesTask implements DbInstallationTask {

   private final DatasinkTreeService datasinkService;
   private final Provider<EntityManager> emp;

   @Inject
   public InstallMissingEntitiesTask(DatasinkTreeService datasinkService, Provider<EntityManager> emp) {

      /* store objects */
      this.datasinkService = datasinkService;
      this.emp = emp;
   }

   @Override
   public void executeOnStartup() {
      createRootFolders();

      /* look for jobs using legacy ftp functionality */
      if (hasLegacyFtpActions())
         installDummyFtpDatasink();
   }

   private void createRootFolders() {
      if (datasinkService.getRoots().isEmpty()) {
         DatasinkFolder dsRoot = new DatasinkFolder();
         dsRoot.setName(PrepareDbForReportServer.REPORTSERVER_ROOT_DATASINK);
         datasinkService.persist(dsRoot);
      }
   }

   /**
    * Legacy ftp actions are scheduler actions that were created by the user before
    * the {@see DatasinkDefinition} functionality was added (to be specific before
    * version 3.4.0). Returns true if any such legacy ftp scheduler action is
    * found.
    * 
    * @return true if any legacy ftp jobs is found
    */
   private boolean hasLegacyFtpActions() {
      EntityManager em = emp.get();
      Long countLegacyActions = (Long) em
            .createQuery("select count(*) from ScheduleAsFtpFileAction a WHERE a.ftpDatasink IS NULL")
            .getSingleResult();

      return (countLegacyActions != 0);
   }

   /**
    * Installs a dummy ftp datasink into {@see ScheduleAsFtpFileAction}, used as a
    * placeholder for legacy ftp scheduler actions.
    */
   protected void installDummyFtpDatasink() {
      AbstractDatasinkManagerNode root = datasinkService.getRoots().get(0);

      FtpDatasink dummyFtpDatasink = new FtpDatasink();

      dummyFtpDatasink.setName("Dummy ftp datasink");
      root.addChild(dummyFtpDatasink);
      datasinkService.persist(dummyFtpDatasink);

      /* install the placeholder */
      EntityManager em = emp.get();
      Query q = em.createQuery("SELECT a from ScheduleAsFtpFileAction a WHERE a.ftpDatasink IS NULL");
      List<ScheduleAsFtpFileAction> actions = (List) q.getResultList();
      actions.forEach(a -> a.setFtpDatasink(dummyFtpDatasink));
   }

   @Override
   public void executeOnFirstRun() {
   }

}
