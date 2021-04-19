package net.datenwerke.rs.base.service.reportengines.table.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto" 
	)
public enum Order {
	
	@EnumLabel(msg=EnumMessages.class,key="asc")
	ASC, 
	
	@EnumLabel(msg=EnumMessages.class,key="desc")
	DESC
}
