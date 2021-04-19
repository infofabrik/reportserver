package net.datenwerke.scheduler.client.scheduler.dto.config.complex;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum DaysDto {

	MONDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelMonday();
		}
	},
	TUESDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelTuesday();
		}
	},
	WEDNESDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelWednesday();
		}
	},
	THURSDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelThursday();
		}
	},
	FRIDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelFriday();
		}
	},
	SATURDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelSaturday();
		}
	},
	SUNDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelSunday();
		}
	},
	DAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelDay();
		}
	},
	WORKINGDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelWorkingDay();
		}
	},
	WEEKENDDAY {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelWeekendDay();
		}
	}

}
