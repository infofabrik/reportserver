package net.datenwerke.rs.utils.crypto;



/**
 * 
 *
 */
public interface PasswordHasher {

	/**
	 * Returns a hashed password
	 * 
	 * @param password
	 */
	public abstract String hashPassword(String password);
	
	public abstract String hashPassword(String password, String salt);
	
	public boolean validatePassword(String hashedPassword, String cleartextPassword);

	public String getHmacPassphrase();

}