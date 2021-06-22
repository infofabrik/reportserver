package net.datenwerke.rs.passwordpolicy.service.lostpassword;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;

public interface LostPasswordService {

   public String requestNewPassword(String username) throws ExpectedException;
}
