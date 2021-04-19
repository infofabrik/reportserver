package net.datenwerke.rs.core.service.parameters.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;


/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.parameters.dto"
)
public enum Datatype {

	String,
	Integer,
	Long,
	BigDecimal,
	Float,
	Double,
	Date,
	Boolean
}
