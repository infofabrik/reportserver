package net.datenwerke.rs.base.client.reportengines.table.dto;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public enum NullHandlingDto {

	Include {
		public String toString(){
			return EnumMessages.INSTANCE.nullInclude();
		}
	},
	Exlude {
		public String toString(){
			return EnumMessages.INSTANCE.nullExclude();
		}
	}

}
