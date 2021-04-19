package net.datenwerke.security.service.crypto;

import java.security.Security;

import com.google.inject.Inject;

public class CryptoModuleStartup {

	@Inject
	public CryptoModuleStartup() {
		/* Use BouncyCastle as security Provider */
		Security.insertProviderAt(new org.bouncycastle.jce.provider.BouncyCastleProvider(),1);
	}
}
