package net.datenwerke.scheduler.service.scheduler.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;

@GenerateDto(
	dtoPackage="net.datenwerke.scheduler.client.scheduler.dto"
)
public enum JobExecutionStatus {
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelExecutionStatusInactive")
	INACTIVE,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelExecutionStatusWaiting")
	WAITING,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelExecutionStatusExecuting")
	EXECUTING,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelExecutionStatusActions")
	EXECUTING_ACTIONS, 
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelExecutionStatusBadFailure")
	BAD_FAILURE
}
