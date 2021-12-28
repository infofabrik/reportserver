package net.datenwerke.security.service.crypto.pbe.encrypt;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import net.datenwerke.security.service.crypto.pbe.exception.PbeException;

public class ClientEncryptionService extends EncryptionServiceImpl {

   public static final String KEY_SPEC_ALGORITHM = "PBKDF2WithHmacSHA1";
   public static final String CIPHER_ALGORITHM = "AES/OFB/NOPADDING";

   public ClientEncryptionService(String passphrase, String salt, int keylength, int iterations) {
      super(passphrase, salt, keylength, iterations, CIPHER_ALGORITHM, KEY_SPEC_ALGORITHM);
   }

   @Override
   public byte[] decrypt(byte[] ciphertextIV) {
      SecretKey key = generateKey(passphrase);

      byte[] iv = Arrays.copyOfRange(ciphertextIV, 0, 16);
      byte[] ciphertext = Arrays.copyOfRange(ciphertextIV, 16, ciphertextIV.length);

      Cipher cipher;
      try {
         cipher = Cipher.getInstance(cipherAlgorithm);
         cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
         return cipher.doFinal(ciphertext);
      } catch (Exception e) {
         PbeException pbeE = new PbeException("Could not decrypt ciphertext");
         pbeE.initCause(e);
         throw pbeE;
      }
   }
}
