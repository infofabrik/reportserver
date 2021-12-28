package net.datenwerke.rs.incubator.service.crypto;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;

import javax.inject.Inject;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.security.service.crypto.credentialproviders.KeyStoreCredentialProvider;

public class FileServerKeyStoreKryptoCredentialProvider extends	KeyStoreCredentialProvider {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private static TerminalService terminalService;
	
	public FileServerKeyStoreKryptoCredentialProvider(HierarchicalConfiguration conf) {
		super(conf);
	}


	@Override
	public KeyStore getKeyStore(String location, String type, String secret) {
		try {
			Object obj = terminalService.getObjectByLocation(location, false);
			KeyStore keyStore = KeyStore.getInstance(type);
			keyStore.load(new ByteArrayInputStream(((FileServerFile) obj).getData()), secret.toCharArray());
			return keyStore;
		} catch (Exception e) {
			logger.info( "Error loading keystore form location \"" + location + "\"", e);
		} 
		
		return null;
	}



}

