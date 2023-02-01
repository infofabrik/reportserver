package net.datenwerke.scheduler.service.scheduler;

import net.datenwerke.security.service.usermanager.entities.User;

public interface SchedulerHelperService {

   public void replaceSchedulerUser(User oldUser, User newUser);

}
