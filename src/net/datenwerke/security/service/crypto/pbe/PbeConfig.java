package net.datenwerke.security.service.crypto.pbe;

import javax.inject.Inject;

import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;

public class PbeConfig {
	
	public static final String PBE_PROPERTY_SALT_KEY = "rs.crypto.pbe.salt";
	public static final String PBE_PROPERTY_PASSPHRASE_KEY = "rs.crypto.pbe.passphrase";
	public static final String PBE_PROPERTY_KEY_LENGTH = "rs.crypto.pbe.keylength";
	
	public static final String PBE_PROPERTY_KEY_SPEC = "rs.crypto.pbe.keyspec";
	public static final String PBE_PROPERTY_CIPHER_ALGORITHM = "rs.crypto.pbe.cipher";
	public static final String PBE_PROPERTY_ITERATIONS = "rs.crypto.pbe.iterations";

	
	private ApplicationPropertiesService propertiesService;

	@Inject
	public PbeConfig(ApplicationPropertiesService propertiesService) {
		this.propertiesService = propertiesService;
	}
	
	public String getKeySpec(){
		return propertiesService.getString(PBE_PROPERTY_KEY_SPEC, "PBKDF2WithHmacSHA1");
	}

	public String getCipher(){
		return propertiesService.getString(PBE_PROPERTY_CIPHER_ALGORITHM, "AES/CBC/ISO7816-4Padding");
	}
	
	public int getIterations(){
		return propertiesService.getInteger(PBE_PROPERTY_ITERATIONS, 2000);
	}

	public String getSalt(){
		return propertiesService.getString(PBE_PROPERTY_SALT_KEY);
	}
	
	public String getPassphrase(){
		return propertiesService.getString(PBE_PROPERTY_PASSPHRASE_KEY);
	}
	
	public int getKeylength(){
		return propertiesService.getInteger(PBE_PROPERTY_KEY_LENGTH, 128);
	}
}
