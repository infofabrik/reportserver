package net.datenwerke.rs.passwordpolicy.service.activateuser;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.security.service.usermanager.entities.User;

public interface ActivateUserService {

   public void activateAccount(User user) throws ExpectedException;

   public void activateAccount(User user, boolean force) throws ExpectedException;
}
