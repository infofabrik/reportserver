/**
 * 
 */
package net.datenwerke.security.service.security.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.security.client.security.dto"
)
public enum InheritanceType {
	HERE,
	INHERITED,
	BOTH
}