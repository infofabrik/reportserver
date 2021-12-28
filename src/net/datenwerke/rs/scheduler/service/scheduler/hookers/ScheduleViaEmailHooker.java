package net.datenwerke.rs.scheduler.service.scheduler.hookers;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.scheduler.client.scheduler.dto.EmailInformation;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.service.scheduler.mail.MailReportAction;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleViaEmailHooker implements ScheduleConfigProviderHook {

	private final Provider<MailReportAction> mailReportActionProvider;
	
	@Inject
	public ScheduleViaEmailHooker(
		Provider<MailReportAction> mailReportActionProvider
		) {
		
		/* store objects */
		this.mailReportActionProvider = mailReportActionProvider;
	}

	@Override
	public void adaptJob(ReportExecuteJob job,
			ReportScheduleDefinition scheduleDTO)
			throws InvalidConfigurationException {
		EmailInformation emailInfo = scheduleDTO.getAdditionalInfo(EmailInformation.class);
		if(null == emailInfo)
			return;
		
		if(scheduleDTO.getRecipients().isEmpty())
			throw new InvalidConfigurationException(SchedulerMessages.INSTANCE.errorNoRecipients());
		if(null == emailInfo.getSubject() || "".equals(emailInfo.getSubject()))
			throw new InvalidConfigurationException(SchedulerMessages.INSTANCE.errorNoSubject());
		
		/* create mail action */
		MailReportAction mailAction = mailReportActionProvider.get();
		mailAction.setSubject(emailInfo.getSubject());
		mailAction.setMessage(emailInfo.getMessage());
		mailAction.setCompressed(emailInfo.isCompressed());
		try {
			job.addAction(mailAction);
		} catch (ActionNotSupportedException e) {
			throw new InvalidConfigurationException(e);
		}
	}



	@Override
	public void adaptScheduleDefinition(ReportScheduleDefinition rsd,
			ReportExecuteJob job) {
		MailReportAction action = job.getAction(MailReportAction.class);
		if(null == action)
			return;
		
		EmailInformation info = new EmailInformation();
		
		info.setSubject(action.getSubject());
		info.setMessage(action.getMessage());
		info.setCompressed(action.isCompressed());
		
		rsd.addAdditionalInfo(info);
	}

}
