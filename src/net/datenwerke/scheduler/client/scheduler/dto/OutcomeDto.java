package net.datenwerke.scheduler.client.scheduler.dto;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum OutcomeDto {

	SUCCESS {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelOutcomeSuccess();
		}
	},
	FAILURE {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelOutcomeFailure();
		}
	},
	VETO {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelOutcomeVeto();
		}
	},
	EXECUTING {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelOutcomeExecuting();
		}
	},
	ACTION_VETO {
		public String toString(){
			return SchedulerMessages.INSTANCE.enumLabelOutcomeActionVeto();
		}
	}

}
