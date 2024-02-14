package net.datenwerke.security.service.crypto;

public interface CryptoService {

   public final static String CONFIG_FILE = "security/crypto.cf";

   public final static String KEY_SIGN = "signature";
   public final static String KEY_SFTP = "sftp";
   public final static String KEY_USER = "user";

   public boolean hasCryptoCredentials(String keyIdentifier);

   public CryptoCredentials getCryptoCredentials(String keyIdentifier);

   public CryptoCredentials getUserCryptoCredentials(String email);
   
   public String generateDefaultKey();

}
