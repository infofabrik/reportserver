package net.datenwerke.scheduler.client.scheduler.dto;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum JobExecutionStatusDto {

	INACTIVE {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelExecutionStatusInactive();
		}
	},
	WAITING {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelExecutionStatusWaiting();
		}
	},
	EXECUTING {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelExecutionStatusExecuting();
		}
	},
	EXECUTING_ACTIONS {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelExecutionStatusActions();
		}
	},
	BAD_FAILURE {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelExecutionStatusBadFailure();
		}
	}

}
