package net.datenwerke.security.service.crypto.pbe.encrypt;


public interface EncryptionService {

	public byte[] decrypt(byte[] ciphertext);
	
	public byte[] decryptFromHex(String ciphertextAsHex);
	
	public byte[] encrypt(String plaintext);
	
	public byte[] encrypt(byte[] plaintext);
	
}