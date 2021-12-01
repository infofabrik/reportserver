package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto"
)
public enum BlockType {
	OR,
	AND
}
