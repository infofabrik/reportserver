package net.datenwerke.rs.base.service.reportengines.table.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto"
)
public enum NullHandling {
	
	@EnumLabel(msg=EnumMessages.class,key="nullInclude")
	Include, 
	
	@EnumLabel(msg=EnumMessages.class,key="nullExclude")
	Exlude
}
