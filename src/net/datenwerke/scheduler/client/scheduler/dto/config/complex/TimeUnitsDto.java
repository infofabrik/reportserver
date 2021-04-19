package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum TimeUnitsDto {

	HOURS {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelHours();
		}
	},
	MINUTES {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelMinutes();
		}
	}

}
