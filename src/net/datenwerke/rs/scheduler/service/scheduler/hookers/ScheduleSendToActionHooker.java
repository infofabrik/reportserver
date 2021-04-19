package net.datenwerke.rs.scheduler.service.scheduler.hookers;

import java.util.List;

import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinitionSendToConfig;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scheduler.service.scheduler.sendto.SendToReportAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ScheduleSendToActionHooker implements ScheduleConfigProviderHook {

	private final Provider<SendToReportAction> sendToActionProvider;
	
	@Inject
	public ScheduleSendToActionHooker(
		Provider<SendToReportAction> sendToActionProvider
		) {
		
		/* store objects */
		this.sendToActionProvider = sendToActionProvider;
	}

	
	@Override
	public void adaptJob(ReportExecuteJob job,
			ReportScheduleDefinition scheduleDTO)
			throws InvalidConfigurationException {
		for(ReportScheduleDefinitionSendToConfig config: scheduleDTO.getSendToConfigs()){
			/* create action */
			SendToReportAction sendToAction = sendToActionProvider.get();
			sendToAction.setSendToId(config.getId());
			sendToAction.setConfigValues(config.getValues());
			try {
				job.addAction(sendToAction);
			} catch (ActionNotSupportedException e) {
				throw new InvalidConfigurationException(e);
			}
		}
	}

	@Override
	public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) {
		List<AbstractAction> actions = job.getActions();
		if(null == actions)
			return;
		
		for(AbstractAction action : actions){
			if(! (action instanceof SendToReportAction))
				continue;
			
			SendToReportAction sendToAction = (SendToReportAction) action;
			rsd.addSendToConfigs(new ReportScheduleDefinitionSendToConfig(sendToAction.getSendToId(), sendToAction.getValueMap()));
		}
	}

}
