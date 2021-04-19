package net.datenwerke.scheduler.service.scheduler.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;

@GenerateDto(
	dtoPackage="net.datenwerke.scheduler.client.scheduler.dto"
)
public enum Outcome {
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelOutcomeSuccess")
	SUCCESS,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelOutcomeFailure")
	FAILURE,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelOutcomeVeto")
	VETO,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelOutcomeExecuting")
	EXECUTING,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelOutcomeActionVeto")
	ACTION_VETO,
}
