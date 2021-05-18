package net.datenwerke.rs.scheduler.service.scheduler.mail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.MailServiceImpl.MailSupervisor;
import net.datenwerke.rs.core.service.mail.SimpleAttachment;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.core.service.mail.helper.SmtpLogAnalizer;
import net.datenwerke.rs.core.service.mail.locale.MailMessages;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleEmailAttachementName;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleEmailSubject;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleEmailText;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

/**
 * 
 *
 */
@Entity
@Table(name="SCHED_ACTION_MAIL_REPORT")
@Inheritance(strategy=InheritanceType.JOINED)
public class MailReportAction extends AbstractAction {
	
	@Transient
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private static final String PROPERTY_SUBJECT = "subject";
	private static final String PROPERTY_MESSAGE = "message";
	private static final String PROPERTY_NAME = "name";
	private static final String PROPERTY_FILENAME = "filename";

	@Transient @Inject private static Provider<MailMessages> messages = LocalizationServiceImpl.getMessagesProvider(MailMessages.class);
	
	@Transient @Inject private MailService mailService;
	@Transient @Inject private ReportExecutorService reportExecutorService;
	@Transient @Inject private Provider<SimpleJuel> simpleJuelProvider;
	@Transient @Inject private SchedulerMailHelper mailHelper;
	@Transient @Inject private RemoteMessageService remoteMessageService;
	@Transient @Inject private ZipUtilsService zipUtilsService;
	
	
	@SchedulerModuleEmailSubject @Transient @Inject private String subjectTemplate;
	@SchedulerModuleEmailText @Transient @Inject private String messageTemplate;
	@SchedulerModuleEmailAttachementName @Transient @Inject private String attachementNameTemplate;
	
	private String subject = "";//messages.defaultSubject();
	
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String message = "";//messages.defaultMessageBody();
	
	@Transient
	private String smptTrace;

	private boolean compressed = false;
	
	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}
	
	public boolean isCompressed() {
		return compressed;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	@Override
	public void doExecute(AbstractJob abstractJob)  throws ActionExecutionException {
		if(!(abstractJob instanceof ReportExecuteJob))
			return;
		
		/* cast job */
		ReportExecuteJob job = (ReportExecuteJob) abstractJob;

		/* did everything go as planned ?*/
		if(null == job.getExecutedReport())
			return;
					
		/* start simplemail */
		SimpleMail mail = mailHelper.prepareSimpleMail(job);
		SimpleJuel juel = mailHelper.getConfiguredJuel(job);
		juel.addReplacement(PROPERTY_SUBJECT, getSubject());
		juel.addReplacement(PROPERTY_MESSAGE, getMessage());
		
		String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
		juel.addReplacement("msgs", remoteMessageService.getMessages(currentLanguage));
		
		/* set subject */
		mail.setSubject(juel.parse(subjectTemplate));
		
		/* prepare attachment */
		String filenamePrefix = juel.parse(attachementNameTemplate);
		SimpleAttachment attachement =	prepareAttachment(job, filenamePrefix);
		
		juel.addReplacement(PROPERTY_FILENAME, attachement.getFileName());
		juel.addReplacement(PROPERTY_NAME, FilenameUtils.getBaseName(attachement.getFileName()));
		
		/* set content */		
		if(mailHelper.isHTML()){
			mail.setHtml(juel.parse(messageTemplate), attachement);
		}else{
			mail.setContent(juel.parse(messageTemplate), attachement);
		}
		
		mailService.sendMailSync(mail, new MailSupervisor() {
			
			@Override
			public void handleTrace(String trace) {
				smptTrace = trace;
			}
			
			@Override
			public void handleException(Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	private SimpleAttachment prepareAttachment(ReportExecuteJob job, String filenamePrefix) throws ActionExecutionException {
		if (compressed) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			Object reportObj = job.getExecutedReport().getReport();
			String reportFileExtension = job.getExecutedReport().getFileExtension();
			try {
				zipUtilsService.createZip(Collections.singletonMap(filenamePrefix
							.replace(":", "_").replace("/", "_").replace("\\", "_").replace(" ", "_") 
							+ "." + reportFileExtension, reportObj), os);
			} catch (IOException e) {
				throw new ActionExecutionException(e.getMessage());
			}
			
			return new SimpleAttachment(
					os.toByteArray(), 
					"application/zip",
					filenamePrefix + ".zip" //$NON-NLS-1$
					);
		} else {
			return new SimpleAttachment(
					job.getExecutedReport().getReport(), 
					job.getExecutedReport().getMimeType(),
					filenamePrefix + "." + job.getExecutedReport().getFileExtension() //$NON-NLS-1$
					);
		}
	}


	public String getMessage() {
		return message;
	}

	public String getSubject() {
		return subject;
	}

	
	@Override
	public void adjustActionEntryForSuccess(ActionEntry actionEntry) {
		super.adjustActionEntryForSuccess(actionEntry);
		
		SmtpLogAnalizer analizer = new SmtpLogAnalizer(smptTrace);
		
		actionEntry.addHistoryProperty("smtp trace", analizer.getLogWithoutData());
		actionEntry.addHistoryProperty("mail header", analizer.getMessageHeader());
	}
	
}
