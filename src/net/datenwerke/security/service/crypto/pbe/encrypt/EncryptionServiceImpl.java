package net.datenwerke.security.service.crypto.pbe.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

import net.datenwerke.security.service.crypto.pbe.PbeConfig;
import net.datenwerke.security.service.crypto.pbe.exception.PbeException;

import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EncryptionServiceImpl implements EncryptionService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	protected final String salt;
	protected final String passphrase;
	protected final int iterations;
	protected final int keylength;
	protected final String cipherAlgorithm;
	protected final String keySpecAlgorithm;
	
	protected final SecureRandom random = new SecureRandom();
	
	public EncryptionServiceImpl(
		String passphrase,
		String salt,
		int keylength,
		int iterations,
		String cipherAlgorithm,
		String keySpecAlgorithm
		){
		
		/* store parameters */
		this.salt = salt;
		this.passphrase = passphrase;
		this.keylength = keylength;
		this.iterations = iterations;
		this.cipherAlgorithm = cipherAlgorithm;
		this.keySpecAlgorithm = keySpecAlgorithm;
	}
	
	public EncryptionServiceImpl(PbeConfig config){
			/* store parameters */
			this.salt = config.getSalt();
			this.passphrase = config.getPassphrase();
			this.keylength = config.getKeylength();
			this.iterations = config.getIterations();
			this.cipherAlgorithm = config.getCipher();
			this.keySpecAlgorithm = config.getKeySpec();
		}

	protected SecretKey generateKey(String passphrase) {
		PBEKeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(), salt.getBytes(), iterations, keylength);
        SecretKeyFactory keyFactory;
		try {
			keyFactory = SecretKeyFactory.getInstance(keySpecAlgorithm);
			SecretKey key = keyFactory.generateSecret(keySpec);
			return key;
		} catch (NoSuchAlgorithmException e) {
			logger.warn( "error generating key", e);
		} catch (InvalidKeySpecException e) {
			logger.warn( "error generating key", e);
		}
		
		return null;
	}

	@Override
	public byte[] decryptFromHex(String ciphertextAsHex) {
		return decrypt(Hex.decode(ciphertextAsHex));
	}
	
	@Override
	public byte[] decrypt(byte[] ciphertextPlusIv) {
		if(null == ciphertextPlusIv || ciphertextPlusIv.length <= 16)
			throw new PbeException("Could not decrypt ciphertext (could not extract IV)");
		
		SecretKey key = generateKey(passphrase);

		/* get iv */
		byte[] ivByte = new byte[16];
		byte[] ciphertext = new byte[ciphertextPlusIv.length - 16];
		
		/* split array */
		System.arraycopy(ciphertextPlusIv, 0, ivByte, 0, 16);
		System.arraycopy(ciphertextPlusIv, 16, ciphertext, 0, ciphertext.length);
		
 		AlgorithmParameterSpec IV = new IvParameterSpec(ivByte);
		
        Cipher cipher;
		try {
			cipher = Cipher.getInstance(cipherAlgorithm);
	        cipher.init(Cipher.DECRYPT_MODE, key, IV, random);
	        return cipher.doFinal(ciphertext);
		} catch (Exception e) {
			PbeException pbeE = new PbeException("Could not decrypt ciphertext");
			pbeE.initCause(e);
			throw pbeE;
		}
	}

	@Override
	public byte[] encrypt(String plaintext) {
		return encrypt(plaintext.getBytes());
	}

	@Override
	public byte[] encrypt(byte[] plaintext) {
		SecretKey key = generateKey(passphrase);

		/* create iv */
		byte[] ivByte = new byte[16];
		new SecureRandom().nextBytes(ivByte);
		AlgorithmParameterSpec IV = new IvParameterSpec(ivByte);
		
		try{
		    Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		    cipher.init(Cipher.ENCRYPT_MODE, key, IV, random);
	        byte[] ciphertext = cipher.doFinal(plaintext);
	        
	        /* concatenate */
	        byte[] cipherPlusIv = new byte[16 + ciphertext.length];
	        System.arraycopy(ivByte, 0, cipherPlusIv, 0, ivByte.length);
	        System.arraycopy(ciphertext, 0, cipherPlusIv, 16, ciphertext.length);
	        
	        return cipherPlusIv;
		} catch(Exception e){
			PbeException pbeE = new PbeException("Could not encrypt plaintext");
			pbeE.initCause(e);
			throw pbeE;
		}
	}
	
}
