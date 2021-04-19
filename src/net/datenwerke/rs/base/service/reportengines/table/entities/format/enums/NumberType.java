package net.datenwerke.rs.base.service.reportengines.table.entities.format.enums;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto"
)
public enum NumberType {
	
	@EnumLabel(msg=EnumMessages.class,key="numberDefault")
	DEFAULT,
	
	@EnumLabel(msg=EnumMessages.class,key="numberPercent")
	PERCENT,
	
	@EnumLabel(msg=EnumMessages.class,key="numberScientific")
	SCIENTIFIC
}
