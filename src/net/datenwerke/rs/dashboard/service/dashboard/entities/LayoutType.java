package net.datenwerke.rs.dashboard.service.dashboard.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto"
)
public enum LayoutType {

	ONE_COLUMN,
	TWO_COLUMN_EQUI,
	TWO_COLUMN_LEFT_LARGE,
	TWO_COLUMN_RIGHT_LARGE,
	THREE_COLUMN
	
}
