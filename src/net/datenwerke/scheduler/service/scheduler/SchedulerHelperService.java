package net.datenwerke.scheduler.service.scheduler;

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

}
