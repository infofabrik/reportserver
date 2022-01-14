package net.datenwerke.security.service.crypto.pbe;

import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;
import net.datenwerke.security.service.usermanager.entities.User;

public interface PbeService {

   public EncryptionService getEncryptionService();

   EncryptionService getClientEncryptionService();

   EncryptionService getClientEncryptionService(User user);

   EncryptionService getEncryptionService(User user);

   EncryptionService getEncryptionService(String passphrase);

   EncryptionService getEncryptionService(String passphrase, String salt);

   public String getSalt();

   public int getIterations();

   public int getKeyLength();

   String getCipherAlgorithm();

   String getKeySpecAlgorithm();

}
