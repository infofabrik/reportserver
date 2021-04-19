package net.datenwerke.rs.core.service.mail;

import static java.util.stream.Collectors.toList;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.mail.annotations.MailModuleProperties;
import net.datenwerke.rs.core.service.mail.events.SendMailEvent;
import net.datenwerke.rs.core.service.mail.exceptions.MailerRuntimeException;
import net.datenwerke.rs.core.service.mail.interfaces.NeedsPostprocessing;
import net.datenwerke.rs.core.service.mail.interfaces.SessionProvider;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.security.service.crypto.CryptoService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public class MailServiceImpl implements MailService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final static String CFG_ENCRYPTION_POLICY = "mail.encryptionPolicy";
	private final static String CFG_FORCE_SENDER = "mail.forceSender";
	
	public interface MailSupervisor{
		void handleException(Exception e);
		void handleTrace(String trace);
	}
	
	public class MailSupervisorImpl implements MailSupervisor{
		@Override
		public void handleTrace(String trace) {
		}
		
		@Override
		public void handleException(Exception e) {
		    // TODO: throw exception
		    throw new MailerRuntimeException("Mail could not be send", e);
		}
	}
	
	private final Provider<SimpleMail> simpleMailProvider;
	private final Provider<SimpleCryptoMail> simpleCryptoMailProvider;
	private final CryptoService cryptoService;
	private final Provider<SimpleJuel> simpleJuelProvider;
	private final Provider<EventBus> eventBus;
	private final Provider<Configuration> config;
	
	/* thread pool used to send mails asynchronously */
	private final ExecutorService sendMailPool = Executors.newFixedThreadPool(1);
	
	@Inject
	public MailServiceImpl(
		Provider<SimpleMail> simpleMailProvider,
		Provider<SimpleCryptoMail> simpleCryptoMailProvider,
		CryptoService cryptoService,
		Provider<SimpleJuel> simpleJuelProvider,
		Provider<EventBus> eventBus, 
		@MailModuleProperties Provider<Configuration> config
		){
		
		/* store objects */
		this.simpleMailProvider = simpleMailProvider;
		this.simpleCryptoMailProvider = simpleCryptoMailProvider;
		this.cryptoService = cryptoService;
		this.simpleJuelProvider = simpleJuelProvider;
		this.eventBus = eventBus;
		this.config = config;
	}
	
	@Override
	public SimpleMail newSimpleMail() {
		return simpleCryptoMailProvider.get();
	}
	
	@Override
	public SimpleMail newTemplateMail(MailTemplate template, SimpleAttachement ... attachments) {
		SimpleMail mail = newSimpleMail();
		SimpleJuel juel = simpleJuelProvider.get();

		template.configureMail(mail, juel, attachments);
		
		return mail;
	}
	
	@Override
	public synchronized void sendMailSync(MimeMessage message) {
		sendMailSync(message, new MailSupervisorImpl());
	}
	
	
	@Override
	public synchronized void sendMailSync(MimeMessage message, MailSupervisor supervisor) {
		eventBus.get().fireEvent(new SendMailEvent(message));
		
		try {
			/* check crypto policy */
			List<Address> rcptTOencSupported = new ArrayList<Address>();
			List<Address> rcptTOencNotSupported = new ArrayList<Address>();
			List<Address> rcptCCencSupported = new ArrayList<Address>();
			List<Address> rcptCCencNotSupported = new ArrayList<Address>();
			List<Address> rcptBCCencSupported = new ArrayList<Address>();
			List<Address> rcptBCCencNotSupported = new ArrayList<Address>();
			
			if(null != message.getRecipients(RecipientType.TO)){
				for(Address a : message.getRecipients(RecipientType.TO)){
					if(null == cryptoService.getUserCryptoCredentials(a.toString())){
						rcptTOencNotSupported.add(a);
					}else{
						rcptTOencSupported.add(a);
					}
				}
			}
			if(null != message.getRecipients(RecipientType.CC)){
				for(Address a : message.getRecipients(RecipientType.CC)){
					if(null == cryptoService.getUserCryptoCredentials(a.toString())){
						rcptCCencNotSupported.add(a);
					}else{
						rcptCCencSupported.add(a);
					}
				}
			}
			if(null != message.getRecipients(RecipientType.BCC)){
				for(Address a : message.getRecipients(RecipientType.BCC)){
					if(null == cryptoService.getUserCryptoCredentials(a.toString())){
						rcptBCCencNotSupported.add(a);
					}else{
						rcptBCCencSupported.add(a);
					}
				}
			}
			
			/* if there are recipients that do not support encrypted communication */
			if(!(rcptTOencNotSupported.isEmpty() && rcptCCencNotSupported.isEmpty() && rcptBCCencNotSupported.isEmpty())){
				if(forceEncryption()){
					/* remove and warn */
					logger.warn( "Some reciepients were removed from a message, because no public key was found. " + rcptTOencNotSupported + ", " + rcptCCencNotSupported + ", " + rcptBCCencNotSupported);
					if(rcptTOencSupported.isEmpty()){
						throw new RuntimeException("no recipients found that support encryption, but strict encryption policy was configured");
					}
					message.setRecipients(RecipientType.TO, rcptTOencSupported.toArray(new Address[0]));
					message.setRecipients(RecipientType.CC, rcptCCencSupported.toArray(new Address[0]));
					message.setRecipients(RecipientType.BCC, rcptBCCencSupported.toArray(new Address[0]));
				}else{
					if(rcptTOencSupported.isEmpty()){
						/* only send plaintext */
						SimpleMail plaintextMail = simpleMailProvider.get();
						plaintextMail.addRecipients(RecipientType.TO, rcptTOencSupported.toArray(new Address[0]));
						plaintextMail.addRecipients(RecipientType.TO, rcptTOencNotSupported.toArray(new Address[0]));
						plaintextMail.addRecipients(RecipientType.CC, rcptCCencSupported.toArray(new Address[0]));
						plaintextMail.addRecipients(RecipientType.CC, rcptCCencNotSupported.toArray(new Address[0]));
						plaintextMail.addRecipients(RecipientType.BCC, rcptBCCencSupported.toArray(new Address[0]));
						plaintextMail.addRecipients(RecipientType.BCC, rcptBCCencNotSupported.toArray(new Address[0]));
						
						plaintextMail.setFrom(message.getFrom()[0]);
						plaintextMail.setSubject(message.getSubject());
						
						if(message instanceof SimpleCryptoMail){
							MimeMultipart mmp = new MimeMultipart();
							mmp.addBodyPart(((SimpleCryptoMail)message).rootBodyPart);
							plaintextMail.setContent(mmp);
						}else{
							plaintextMail.setContent((Multipart) message.getContent());
						}
						
						message = plaintextMail;
					}else{
						/* split message */
						SimpleMail plaintextMail = simpleMailProvider.get();
						plaintextMail.addRecipients(RecipientType.TO, rcptTOencNotSupported.toArray(new Address[0]));
						plaintextMail.addRecipients(RecipientType.CC, rcptCCencNotSupported.toArray(new Address[0]));
						plaintextMail.addRecipients(RecipientType.BCC, rcptBCCencNotSupported.toArray(new Address[0]));
						
						plaintextMail.setFrom(message.getFrom()[0]);
						plaintextMail.setSubject(message.getSubject());

						if(message instanceof SimpleCryptoMail){
							MimeMultipart mmp = new MimeMultipart();
							mmp.addBodyPart(((SimpleCryptoMail)message).rootBodyPart);
							plaintextMail.setContent(mmp);
						}else{
							plaintextMail.setContent((Multipart) message.getContent());
						}
						
						sendMailSync(plaintextMail);
						
						message.setRecipients(RecipientType.TO, rcptTOencSupported.toArray(new Address[0]));
						message.setRecipients(RecipientType.CC, rcptCCencSupported.toArray(new Address[0]));
						message.setRecipients(RecipientType.BCC, rcptBCCencSupported.toArray(new Address[0]));
					}
				}
			}
			
			if(message instanceof NeedsPostprocessing){
				((NeedsPostprocessing) message).postprocess();
			}
			
			boolean oldDebug = false;
			PrintStream oldOut = null;
			ByteArrayOutputStream newOut = new ByteArrayOutputStream();
			
			if(message instanceof SessionProvider){
				oldDebug = ((SessionProvider) message).getSession().getDebug();
				oldOut = ((SessionProvider) message).getSession().getDebugOut();
				((SessionProvider) message).getSession().setDebugOut(new PrintStream(newOut));
				((SessionProvider) message).getSession().setDebug(true);
			}
			
			if(forceDefaultSender()) {
				String senderName = config.get().getString(MailModule.PROPERTY_MAIL_SENDER_NAME, null);
				if (null == senderName)
					message.setFrom(new InternetAddress(config.get().getString(MailModule.PROPERTY_MAIL_SENDER, null)));
				else
					message.setFrom(new InternetAddress(config.get().getString(MailModule.PROPERTY_MAIL_SENDER, null), senderName));
			}
			
			message.setSentDate(new Date());
			
			Transport.send(message);
			
			if(message instanceof SessionProvider){
				((SessionProvider) message).getSession().setDebug(oldDebug);
				((SessionProvider) message).getSession().setDebugOut(oldOut);
				
				supervisor.handleTrace(newOut.toString());
			} else
				supervisor.handleTrace("successs");
		} catch (Exception e) {
			supervisor.handleException(e);
		}
	}
	
	@Override
	public void sendMail(final MimeMessage message){
		sendMail(message, new MailSupervisorImpl());
	}
	
	private boolean forceDefaultSender() {
		return ! config.get().getString(CFG_FORCE_SENDER, "false").equals("false");
	}
	
	private boolean forceEncryption() {
		return ! config.get().getString(CFG_ENCRYPTION_POLICY, "allow_mixed").equals("allow_mixed");
	}
	
	@Override
	public void sendMail(final MimeMessage message, final MailSupervisor supervisor){
		sendMailPool.execute(new Runnable(){
			@Override
			public void run() {
				try{
					sendMailSync(message, supervisor);
				} catch(Exception e){
					/* print stack trace to server log */
					logger.warn( e.getMessage(), e);
				}
			}
		});
	}

	@Override
	public List<Address> getEmailList(List<User> users) throws AddressException {
		return users
			.stream()
			.filter(user -> null != user.getEmail() && !"".equals(user.getEmail()))
			.map(LambdaExceptionUtil.rethrowFunction(user -> new InternetAddress(user.getEmail())))
			.distinct()
			.collect(toList());
	}

}
