package net.datenwerke.rs.core.service.reportmanager.engine.config;

import java.io.Serializable;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.reportexporter.dto"
)
public interface ReportExecutionConfig extends Serializable{

	public static final ReportExecutionConfig EMPTY_CONFIG = new ReportExecutionConfig() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6937101140659052318L;
	};
}
