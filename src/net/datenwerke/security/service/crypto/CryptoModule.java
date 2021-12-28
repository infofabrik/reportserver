package net.datenwerke.security.service.crypto;

import java.security.KeyStore;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.ext.client.crypto.rpc.CryptoRpcService;
import net.datenwerke.security.ext.server.crypto.CryptoRpcServiceImpl;
import net.datenwerke.security.service.crypto.passwordhasher.HmacPassphrase;
import net.datenwerke.security.service.crypto.passwordhasher.PasswordHasherImpl;
import net.datenwerke.security.service.crypto.pbe.PbeModule;

/**
 * The crypto module provides functions for handling crypto stuff.
 * 
 * <h1>Description</h1>
 * <p>
 * The crypto module provides functions for handling crypto stuff.
 * </p>
 * 
 * 
 * <h1>Content</h1>
 * <h2>Services:</h2>
 * <ul>
 * <li>{@link CryptoService}</li>
 * </ul>
 * 
 * <h2>Entities:</h2>
 * <ul>
 * <li>{@link KeyStore}</li>
 * </ul>
 * 
 * <h2>Annotations:</h2>
 * <ul>
 * </ul>
 * 
 * <h2>ClientModule:</h2>
 * <ul>
 * <li>{@link net.datenwerke.security.ext.client.crypto.CryptoUIModule}</li>
 * </ul>
 * 
 * <h2>Hookability:</h2>
 * <ul>
 * </ul>
 * 
 * 
 * <h1>Dependencies:</h1>
 * 
 * <h2>Modules:</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.core.service.guice.AbstractReportServerModule}</li>
 * <li>{@link net.datenwerke.security.service.crypto.pbe.PbeModule}</li>
 * </ul>
 * <h2>Entities:</h2>
 * <ul>
 * <li>{@link net.datenwerke.security.service.usermanager.entities.User}</li>
 * </ul>
 * 
 * <h2>Services:</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.core.service.mail.MailService}</li>
 * </ul> 
 * <h2>Others:</h2>
 * <ul>
 * <li>{@link net.datenwerke.security.service.crypto.passwordhasher.HmacPassphrase}</li>
 * <li>{@link net.datenwerke.rs.utils.crypto.PasswordHasher}</li>
 * <li>{@link net.datenwerke.security.service.crypto.passwordhasher.PasswordHasherImpl}</li>
 * <li>{@link com.google.inject.Inject}</li>
 * <li>{@link com.google.inject.Provides}</li>
 * <li>{@link org.bouncycastle.cms.CMSSignedData}</li>
 * <li>{@link net.datenwerke.rs.core.service.mail.SimpleMail}</li>
 * <li>{@link net.datenwerke.rs.utils.crypto.HashUtil}</li>
 * <li>{@link net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery}</li>
 * <li>{@link org.apache.commons.lang.time.DateUtils}</li>
 * <li>{@link org.bouncycastle.asn1.x509.BasicConstraints}</li>
 * <li>{@link org.bouncycastle.asn1.x509.ExtendedKeyUsage}</li>
 * <li>{@link org.bouncycastle.asn1.x509.GeneralName}</li>
 * <li>{@link org.bouncycastle.asn1.x509.GeneralNames}</li>
 * <li>{@link org.bouncycastle.asn1.x509.KeyPurposeId}</li>
 * <li>{@link org.bouncycastle.asn1.x509.KeyUsage}</li>
 * <li>{@link org.bouncycastle.asn1.x509.X509Extensions}</li>
 * <li>{@link org.bouncycastle.cms.CMSSignedDataGenerator}</li>
 * <li>{@link org.bouncycastle.cms.CMSSignedGenerator}</li>
 * <li>{@link org.bouncycastle.util.encoders.Base64}</li>
 * <li>{@link org.bouncycastle.x509.X509V3CertificateGenerator}</li>
 * <li>{@link com.google.inject.Provider}</li>
 * <li>{@link com.google.inject.Singleton}</li>
 * <li>{@link com.google.inject.BindingAnnotation}</li>
 * <li>{@link org.hibernate.annotations.Type}</li>
 * <li>{@link org.hibernate.envers.Audited}</li>
 * <li>{@link org.hibernate.validator.constraints.Length}</li>
 * </ul>
 */
public class CryptoModule extends AbstractModule{
	
	public static final String PASSWORDHASHER_PROPERTY_HMAC_PASSPHRASE = "rs.crypto.passwordhasher.hmac.passphrase";

	@Inject
	@Provides @HmacPassphrase
	protected String provideHmacPassphrase(ApplicationPropertiesService rsService){
		return rsService.getString(PASSWORDHASHER_PROPERTY_HMAC_PASSPHRASE);
	}
	
	@Override
	protected void configure() {
		
		bind(CryptoModuleStartup.class).asEagerSingleton();
		bind(CryptoService.class).to(CryptoServiceImpl.class);
		bind(PasswordHasher.class).to(PasswordHasherImpl.class);
		bind(CryptoRpcService.class).to(CryptoRpcServiceImpl.class);
		requestStaticInjection(CryptoServiceImpl.class);
		
		install(new PbeModule());
	}
	
}
