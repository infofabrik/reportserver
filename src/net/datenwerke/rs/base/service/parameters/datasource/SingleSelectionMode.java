package net.datenwerke.rs.base.service.parameters.datasource;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.parameters.datasource.dto"
)
public enum SingleSelectionMode {
	Dropdown,
	Popup,
	Radio,
	Listbox
}