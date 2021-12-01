package net.datenwerke.rs.base.service.parameters.datetime;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.parameters.datetime.dto"
)
public enum Mode {
	Date,
	Time,
	DateTime
}