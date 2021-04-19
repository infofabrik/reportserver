package net.datenwerke.security.service.crypto.credentialproviders;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import net.datenwerke.security.service.crypto.CryptoCredentials;

import org.apache.commons.configuration.HierarchicalConfiguration;

public abstract class KeyStoreCredentialProvider implements	CryptoCredentialProvider {
	
	protected String location;
	protected String type;
	protected String alias;
	protected String secret;
	
	public KeyStoreCredentialProvider(HierarchicalConfiguration conf) {
		this.location = conf.getString("location");
		this.type = conf.getString("type");
		this.alias = conf.getString("alias");
		this.secret = conf.getString("secret");
	}

	@Override
	public CryptoCredentials getCredentials(String alias) {
		CryptoCredentials res = new CryptoCredentials();
		KeyStore keyStore = getKeyStore(this.location, this.type, this.secret);
		if(null == keyStore)
			return null;
		try {
			Certificate certificate = keyStore.getCertificate(alias);
			if(certificate instanceof X509Certificate){
				res.setX509Certificate((X509Certificate) certificate);
			}else{
				return null;
			}
		} catch (KeyStoreException e) {
			return null;
		}
		
		try {
			Certificate[] certificateChain = keyStore.getCertificateChain(alias);
			if(null != certificateChain && certificateChain.length > 1)
				for(int i = 1; i<certificateChain.length; i++){
					Certificate c = certificateChain[i];
					if(c instanceof X509Certificate)
						res.addCertificateToChain((X509Certificate) c);
				}
		} catch (KeyStoreException e1) {
		}
		
		try {
			Key key = keyStore.getKey(alias, secret.toCharArray());
			if(key instanceof PrivateKey)
				res.setPrivateKey((PrivateKey) key);
		} catch (UnrecoverableKeyException e) {
		} catch (KeyStoreException e) {
		} catch (NoSuchAlgorithmException e) {
		}
		
		return res;
	}
	
	@Override
	public CryptoCredentials getCredentials() {
		return getCredentials(this.alias);
	}
	
	public abstract KeyStore getKeyStore(String location, String type, String secret);

}
