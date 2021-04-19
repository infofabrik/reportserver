package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum NthDto {

	FIRST {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelFirst();
		}
	},
	SECOND {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelSecond();
		}
	},
	THIRD {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelThird();
		}
	},
	FOURTH {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelFourth();
		}
	},
	LAST {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelLast();
		}
	}

}
