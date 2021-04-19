package net.datenwerke.rs.core.service.mail;

import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import net.datenwerke.rs.core.service.mail.MailServiceImpl.MailSupervisor;
import net.datenwerke.security.service.usermanager.entities.User;


/**
 * 
 *
 */
public interface MailService {

	/**
	 * Creates and returns a new instance of {@link SimpleMail}
	 * 
	 * @return If there is a CA certificate a new instance of
	 * 		   {@link SimpleCryptoMail}, else a new instance of {@link SimpleMail}
	 */
	public SimpleMail newSimpleMail();
	
	/**
	 * Creates and returns a new instance of {@link SimpleMail} configured with the given template
	 * 
	 * @param template The {@link MailTemplate} to use
	 * @return A new instance of {@link SimpleMail} using the given {@link MailTemplate}
	 */
	public SimpleMail newTemplateMail(MailTemplate template, SimpleAttachement ... attachments);
	
	/**
	 * Spawns a new worker which then sends the message
	 * 
	 * @param message The {@link MimeMessage} to send
	 */
	public void sendMail(MimeMessage message);

	/**
	 * Sends a mail synchronously
	 * 
	 * @param message The {@link MimeMessage} to send
	 */
	void sendMailSync(MimeMessage message);

	void sendMailSync(MimeMessage message, MailSupervisor supervisor);

	void sendMail(MimeMessage message, MailSupervisor supervisor);
	
	List<Address> getEmailList(List<User> users) throws AddressException;
	
}
