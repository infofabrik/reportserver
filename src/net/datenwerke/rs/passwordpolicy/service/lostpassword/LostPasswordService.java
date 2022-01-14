package net.datenwerke.rs.passwordpolicy.service.lostpassword;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;

public interface LostPasswordService {

   String requestNewPassword(String username) throws ExpectedException;

   boolean isLostPasswordDisabled();
}
