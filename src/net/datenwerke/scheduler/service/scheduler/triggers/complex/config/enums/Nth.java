package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.scheduler.service.scheduler.locale.LocaliseDateHelper;

@GenerateDto(
	dtoPackage="net.datenwerke.scheduler.client.scheduler.dto.config.complex"
)
public enum Nth {
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelFirst")
	FIRST,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelSecond")
	SECOND,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelThird")
	THIRD,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelFourth")
	FOURTH,
	
	@EnumLabel(msg=SchedulerMessages.class,key="enumLabelLast")
	LAST;
	
	public String toString() {
		return LocaliseDateHelper.localNth(this);
	};
}
