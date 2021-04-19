package net.datenwerke.rs.core.service.mail;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.rs.utils.misc.MimeUtils;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.service.usermanager.entities.User;

public class MailBuilder {

	private final String subject;
	private final String body;
	private final List<User> recipients;
	
	private final Provider<MailService> mailServiceProvider;
	private final Provider<TempFileService> tempFileServiceProvider;
	private final Provider<ZipUtilsService> zipServiceProvider;
	private final Provider<MimeUtils> mimeUtilsProvider;
	
	private boolean template = false;
	private Optional<Map<String,Object>> replacements = Optional.empty();
	private Optional<String> zipFilename = Optional.empty();
	private Optional<List<Path>> attachments = Optional.empty();


	@Inject
	public MailBuilder(
			Provider<MailService> mailServiceProvider,
			Provider<TempFileService> tempFileServiceProvider,
			Provider<ZipUtilsService> zipServiceProvider,
			Provider<MimeUtils> mimeUtilsProvider,
			@Assisted("subject") String subject, 
			@Assisted("body") String body, 
			@Assisted List<User> recipients) {
		this.mailServiceProvider = mailServiceProvider;
		this.tempFileServiceProvider = tempFileServiceProvider;
		this.zipServiceProvider = zipServiceProvider;
		this.mimeUtilsProvider = mimeUtilsProvider;
		
		this.subject = subject;
		this.body = body;
		this.recipients = recipients;
	}
	
	public MailBuilder withTemplateReplacements(Map<String,Object> replacements) {
		template = true;
		this.replacements = Optional.of(replacements);
		return this;
	}
	
	public MailBuilder withZippedAttachments(String zipFilename, List<Path> attachments) {
		this.zipFilename = Optional.of(zipFilename);
		this.attachments = Optional.of(attachments);
		return this;
	}
	
	public SimpleMail build() throws IOException {
		
		if (recipients.isEmpty())
			throw new IllegalArgumentException("Recipient list is empty");
		
		if (recipients.stream()
				.anyMatch(u -> null == u.getEmail() || "".equals(u.getEmail())))
			throw new IllegalArgumentException("Recipients must have email defined");
		
		if (attachments.isPresent() && zipFilename.isPresent()) 
			return buildMailWithAttachment();
		else
			return buildMailWithoutAttachment();
		
	}
	
	private MailTemplate createMailTemplate() {
		MailTemplate mailTemplate = new MailTemplate();
		mailTemplate.setSubjectTemplate(subject);
		mailTemplate.setMessageTemplate(body);
		mailTemplate.setDataMap(replacements.get());
		return mailTemplate;
	}
	
	private SimpleMail buildMailWithoutAttachment() throws IOException {
		
		SimpleMail mail = null;
		
		if (template) {
			MailTemplate mailTemplate = createMailTemplate();
			mail = mailServiceProvider.get().newTemplateMail(mailTemplate);
		} else {
			mail = mailServiceProvider.get().newSimpleMail();
			mail.setSubject(subject);
			mail.setText(body);
		}
		
		configureRecipients(mail);
		return mail;
	}
	
	private SimpleMail buildMailWithAttachment() throws IOException {
		final Path tmpFile = tempFileServiceProvider.get().createTempFile().toPath();

		try (final OutputStream out = Files.newOutputStream(tmpFile)) {
			List<Path> files = attachments.get();
			zipServiceProvider.get().createZip(files, out);
			SimpleAttachement attachment = new SimpleAttachement(tmpFile,
					mimeUtilsProvider.get().getMimeTypeByExtension(zipFilename.get()), zipFilename.get());

			SimpleMail mail = null;
			
			if (template) {
				MailTemplate mailTemplate = createMailTemplate();
				mail = mailServiceProvider.get().newTemplateMail(mailTemplate, attachment);
			} else {
				mail = mailServiceProvider.get().newSimpleMail();
				mail.setSubject(subject);
				mail.setContent(body, attachment);
			}
			
			configureRecipients(mail);
			
			return mail;
		}
	}
	
	private void configureRecipients(SimpleMail mail) {
		mail.setToRecipients(
				recipients.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList()));
	}
	
}
