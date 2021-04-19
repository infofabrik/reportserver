package net.datenwerke.rs.saiku.service.saiku.reportengine.config;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.saiku.client.saiku.dto",
		createDecorator=true
	)
public class RECSaikuChart implements ReportExecutionConfig {

	@ExposeToClient
	private String type = "bar";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
