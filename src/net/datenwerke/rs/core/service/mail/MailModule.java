package net.datenwerke.rs.core.service.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleDefaultFrom;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleDefaultFromName;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleProperties;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleSMTPHost;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleSMTPPassword;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleSMTPPort;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleSMTPUsername;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleUseAuthentication;
import net.datenwerke.rs.core.service.mail.security.DummySSLSocketFactory;
import net.datenwerke.rs.utils.misc.Nullable;
import net.datenwerke.security.service.crypto.CryptoService;

/**
 * The mail module provides the ability to send EMails to users.
 * 
 * <h1>Description</h1>
 * The mail module provides simple classes and methods for sending EMails to users.
 * 
 * <h1>Content</h1>
 * <h2>Services</h2>
 * <ul>
 * <li>{@link MailService}</li>
 * </ul>
 * 
 * <h2>Annotations</h2>
 * <ul>
 * <li>{@link MailModuleDefaultFrom}</li>
 * <li>{@link MailModuleDefaultFromName}</li>
 * <li>{@link MailModuleProperties}</li>
 * <li>{@link MailModuleSMTPHost}</li>
 * <li>{@link MailModuleSMTPPassword}</li>
 * <li>{@link MailModuleSMTPPort}</li>
 * <li>{@link MailModuleSMTPUsername}</li>
 * </ul>
 * 
 * 
 * <h1>Extensibility</h1>
 * <h2>Hookability</h2>
 * 
 * <h1>Dependencies</h1>
 * 
 * <h2>Services</h2>
 * <ul>
 * <li>{@link CryptoService}</li>
 * <li>{@link HookHandlerService}</li>
 * </ul>
 * 
 * <h2>3rd-Party</h2>
 * <ul>
 * <li><a href="http://code.google.com/p/google-guice/">Google Guice</a></li>
 * <li><a href="http://www.bouncycastle.org/java.html">Bouncy Castle</a></li>
 * </ul>
 * 
 * <h2>Others</h2>
 * <ul>
 * <li>{@link net.datenwerke.rs.utils.juel.SimpleJuel}</li>
 * <li>{@link net.datenwerke.scheduler.service.scheduler.entities.Outcome}</li>
 * <li>{@link net.datenwerke.rs.core.service.mail.exceptions.MailerRuntimeException}</li>
 * </ul>
 */
public class MailModule extends AbstractReportServerModule {

	public static final String CONFIG_FILE = "mail/mail.cf"; //$NON-NLS-1$
	
	public static final String PROPERTY_SMTP_HOST = "smtp.host";
	public static final String PROPERTY_SMTP_PORT = "smtp.port";
	public static final String PROPERTY_SMTP_USERNAME = "smtp.username";
	public static final String PROPERTY_SMTP_PASSWORD = "smtp.password";
	public static final String PROPERTY_SMTP_SSL = "smtp.ssl";
	public static final String PROPERTY_SMTP_TLS_ENABLE = "smtp.tls.enable";
	public static final String PROPERTY_SMTP_TLS_REQUIRE = "smtp.tls.require";
	public static final String PROPERTY_SMTP_AUTH = "smtp.auth";
	
	public static final String PROPERTY_MAIL_SENDER = "mail.sender";
	public static final String PROPERTY_MAIL_SENDER_NAME = "mail.senderName";

	
	@Override
	protected void configure() {
		/* bind service */
		bind(MailService.class).to(MailServiceImpl.class).in(Scopes.SINGLETON);
		
		install(new FactoryModuleBuilder().build(MailBuilderFactory.class));
		install(new FactoryModuleBuilder().build(SimpleCryptoMailFactory.class));
		install(new FactoryModuleBuilder().build(SimpleMailFactory.class));
	}

	@Provides @Inject
	public Session provideSession(
		@MailModuleProperties Configuration config,	
		@MailModuleSMTPHost String host,
		@MailModuleSMTPPort Integer port,
		@MailModuleUseAuthentication boolean useAuth,
		final @Nullable @MailModuleSMTPUsername String username,
		final @Nullable @MailModuleSMTPPassword String password
		){
		
		/* get ssl */
		boolean ssl = config.getBoolean(PROPERTY_SMTP_SSL, false); 
		
		/* get tls */
		boolean enableTLS = config.getBoolean(PROPERTY_SMTP_TLS_ENABLE, false); 
		boolean requireTLS = config.getBoolean(PROPERTY_SMTP_TLS_REQUIRE, false); 
		
		/* prepare properties */
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", String.valueOf(host)); //$NON-NLS-1$
		props.setProperty("mail.smtp.port", String.valueOf(port)); //$NON-NLS-1$
		props.setProperty("mail.smtp.user", null == username ? "" : username); //$NON-NLS-1$
		props.setProperty("mail.smtp.auth", useAuth ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
		if(ssl){
			props.setProperty("mail.smtp.ssl.enable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		    props.put("mail.smtp.ssl.socketFactory", new DummySSLSocketFactory()); //$NON-NLS-1$
		}
		if(enableTLS)
			props.setProperty("mail.smtp.starttls.enable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		if(requireTLS)
			props.setProperty("mail.smtp.starttls.required", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		
		/* create authenticator */
		Authenticator auth = new Authenticator() {
		    
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication authenticator = new PasswordAuthentication(null == username ? "" : username, null == password ? "" : password);
				return authenticator;
	        }
		};
		
		/* create session */
		Session session = Session.getInstance(props, auth);
		return session;
	}
	
	@Provides @Inject @MailModuleProperties
	public Configuration providePropertyContainer(ConfigService configService){
		try{
			return configService.getConfig(CONFIG_FILE);
		} catch(Exception e){
			return configService.newConfig();
		}
		
	}

	@Provides @Inject @MailModuleSMTPHost
	public String provideSmtpHost(@MailModuleProperties Configuration config){
		return config.getString(PROPERTY_SMTP_HOST, null);
	}
	
	@Provides @Inject @MailModuleUseAuthentication
	public boolean provideUseAuthentication(@MailModuleProperties Configuration config){
		String password = config.getString(PROPERTY_SMTP_PASSWORD, null);
		return config.getBoolean(PROPERTY_SMTP_AUTH, null != password);
	}
	
	@Provides @Inject @MailModuleSMTPUsername
	public String provideSmtpHostUsername(@MailModuleProperties Configuration config){
		return config.getString(PROPERTY_SMTP_USERNAME, null);
	}
	
	@Provides @Inject @MailModuleSMTPPassword
	public String provideSmtpHostPassword(@MailModuleProperties Configuration config){
		return config.getString(PROPERTY_SMTP_PASSWORD, null);
	}
	
	@Provides @Inject @MailModuleDefaultFrom
	public String provideDefaultFrom(@MailModuleProperties Configuration config){
		return config.getString(PROPERTY_MAIL_SENDER, null);
	}
	
	@Provides @Inject @MailModuleDefaultFromName
	public String provideDefaultFromName(@MailModuleProperties Configuration config){
		return config.getString(PROPERTY_MAIL_SENDER_NAME, null);
	}

	@Provides @Inject @MailModuleSMTPPort
	public Integer provideSMTPPort(@MailModuleProperties Configuration config){
		return config.getInteger(PROPERTY_SMTP_PORT, 25);
	}

}
