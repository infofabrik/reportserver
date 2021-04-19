package net.datenwerke.rs.base.client.reportengines.table.dto;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum AggregateFunctionDto {

	AVG {
		public String toString(){
			return EnumMessages.INSTANCE.avg();
		}
	},
	COUNT {
		public String toString(){
			return EnumMessages.INSTANCE.count();
		}
	},
	MAX {
		public String toString(){
			return EnumMessages.INSTANCE.max();
		}
	},
	MIN {
		public String toString(){
			return EnumMessages.INSTANCE.min();
		}
	},
	SUM {
		public String toString(){
			return EnumMessages.INSTANCE.sum();
		}
	},
	VARIANCE {
		public String toString(){
			return EnumMessages.INSTANCE.variance();
		}
	},
	COUNT_DISTINCT {
		public String toString(){
			return EnumMessages.INSTANCE.countDistinct();
		}
	}

}
