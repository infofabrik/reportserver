package net.datenwerke.rs.scheduler.service.scheduler.mail;

import static java.util.stream.Collectors.toSet;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.mail.internet.InternetAddress;
import javax.persistence.Transient;

import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportJobForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserListForJuelPrinter;
import net.datenwerke.rs.core.service.mail.MailModule;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.core.service.mail.annotations.MailModuleProperties;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleProperties;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.security.service.usermanager.entities.User;

public class SchedulerMailHelper {
	
	private static final String PROPERTY_JOB_ID = "jobId";
	private static final String PROPERTY_HASDATA = "hasData";
	
	private static final String PROPERTY_NOTIFICATION_HTML = "scheduler.mailaction[@html]";
	
	@Transient
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	
	private MailService mailService;
	private Provider<SimpleJuel> simpleJuelProvider;

	private ReportExecutorService reportExecutorService;
	private Provider<Configuration> config;
	private Provider<Configuration> mailConfig;

	@Inject
	public SchedulerMailHelper(
			MailService mailService, 
			Provider<SimpleJuel> simpleJuelProvider,
			ReportExecutorService reportExecutorService,
			@SchedulerModuleProperties Provider<Configuration> config,
			@MailModuleProperties Provider<Configuration> mailConfig
		) {
		this.mailService = mailService;
		this.simpleJuelProvider = simpleJuelProvider;
		this.reportExecutorService = reportExecutorService;
		this.config = config;
		this.mailConfig = mailConfig;
	}

	public SimpleMail prepareSimpleMail(ReportExecuteJob job){
		/* create new object */
		SimpleMail mail = mailService.newSimpleMail();
					
		/* set from */
		InternetAddress mailFrom = getMailFrom(job);
		mail.setFrom(mailFrom, true);
		
		/* set recipients */
		mail.setToRecipients(new ArrayList<>(getRecipientEmailList(job)));
		
		return mail;
	}
	
	public boolean isHTML(){
		try{
			return config.get().getBoolean(PROPERTY_NOTIFICATION_HTML, false);
		}catch(Exception e){
			return false;
		}
	}
	
	public  InternetAddress getMailFrom(ReportExecuteJob job) {
		User sender = job.getExecutor();
		String mailFrom = ((User)sender).getEmail();
		
		if(null == mailFrom || "".equals(mailFrom)) {
			mailFrom = mailConfig.get().getString(MailModule.PROPERTY_MAIL_SENDER, null);
		}
		
		if(null == mailFrom || "".equals(mailFrom)){
			IllegalArgumentException ex = new IllegalArgumentException("Sender does not have email address: " + sender.getId());
			logger.warn( ex.getMessage(), ex);
			throw ex;
		}
		try {
			return new InternetAddress(mailFrom, sender.getFirstname() + " " + sender.getLastname());
		} catch (UnsupportedEncodingException e) {
			logger.warn( e.getMessage(), e);
			throw new RuntimeException("Failed to set mail sender address ",e);
		}
	}


	/**
	 * Gets all non-empty, unique email addresses of recipients in the given {@link ReportExecuteJob}.
	 * @param job the job containing the recipients
	 * @return all non-empty unique email addresses
	 */
	public Set<String> getRecipientEmailList(ReportExecuteJob job) {
		return job.getRecipients()
		   .stream()
		   .filter(user -> null != user.getEmail() && !"".equals(user.getEmail().trim()))
		   .map(User::getEmail)
		   .collect(toSet()); /* email address should not contain duplicates */
	}
	
	public SimpleJuel getConfiguredJuel(ReportExecuteJob job ) {
		SimpleJuel juel = simpleJuelProvider.get();
		
		juel.addReplacement(PROPERTY_JOB_ID, job.getId().toString());
		juel.addReplacement(PROPERTY_HASDATA, job.getExecutedReport().hasData());
		
		/* addReport */
		juel.addReplacement("report", new ReportForJuel(job.getReport()));
		juel.addReplacement("job", new ReportJobForJuel(job));
		juel.addReplacement("user", UserForJuel.createInstance(job.getExecutor()));
		juel.addReplacement("executor", UserForJuel.createInstance(job.getExecutor()));
		juel.addReplacement("scheduledBy", UserForJuel.createInstance(job.getScheduledBy()));
		juel.addReplacement("recipients", UserListForJuelPrinter.createInstance(job.getRecipients(), isHTML()));
		juel.addReplacement("owners", UserListForJuelPrinter.createInstance(new ArrayList<>(job.getOwners()), isHTML()));
		
		/* metadata description */
		try {
			String metadata = (String) reportExecutorService.exportMetadata(job.getReport(), job.getExecutor(), null, ReportExecutorService.METADATA_FORMAT_PLAIN).getMetadata();
			juel.addReplacement("metadata", metadata);
		} catch (ReportExecutorException e) {
			logger.warn( e.getMessage(), e);
		}
		
		return juel;
	}
	
}
