package net.datenwerke.security.service.crypto.credentialproviders;

import net.datenwerke.security.service.crypto.CryptoCredentials;

public interface CryptoCredentialProvider {

   public CryptoCredentials getCredentials();

   public CryptoCredentials getCredentials(String id);

}
