package net.datenwerke.scheduler.service.scheduler;

import java.util.Map;

import net.datenwerke.rs.core.service.mail.MailTemplate;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.security.service.usermanager.entities.User;

public interface SchedulerHelperService {

   /**
    * Replaces an old user with a new user in all owners, executors, scheduled-by
    * and recipients of all active scheduler jobs.
    * 
    * @param oldUser the user to be replaced
    * @param newUser the new user
    */
   void replaceSchedulerUser(User oldUser, User newUser);

   SimpleJuel getConfiguredJuel(ReportExecuteJob job);

   boolean isHTML();

   MailTemplate getMailTemplate(ReportExecuteJob job, AbstractAction action, Map<String, String> properties,
         Exception e);

}
