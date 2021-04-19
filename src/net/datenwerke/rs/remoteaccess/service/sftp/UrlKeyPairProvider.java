package net.datenwerke.rs.remoteaccess.service.sftp;

import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

import org.apache.sshd.common.keyprovider.AbstractKeyPairProvider;
import org.apache.sshd.common.util.SecurityUtils;
import org.bouncycastle.openssl.PEMReader;

public class UrlKeyPairProvider extends AbstractKeyPairProvider {
	
	private URL[] ulrs;

	public UrlKeyPairProvider(URL... ulrs) {
		this.ulrs = ulrs;
		
	}

	@Override
	protected KeyPair[] loadKeys() {
		 if (!SecurityUtils.isBouncyCastleRegistered()) {
	            throw new IllegalStateException("BouncyCastle must be registered as a JCE provider");
	        }
	        List<KeyPair> keys = new ArrayList<KeyPair>();
	        for (int i = 0; i < ulrs.length; i++) {
	            try {
	            	PEMReader r = new PEMReader(new InputStreamReader(ulrs[i].openStream()), null);
	                try {
	                    Object o = r.readObject();
	                    if (o instanceof KeyPair) {
	                        keys.add((KeyPair) o);
	                    }
	                } finally {
	                    r.close();
	                }
	            } catch (Exception e) {
	                log.info("Unable to read key {}: {}", ulrs[i], e);
	            }
	        }
	        return keys.toArray(new KeyPair[keys.size()]);
	}

}
