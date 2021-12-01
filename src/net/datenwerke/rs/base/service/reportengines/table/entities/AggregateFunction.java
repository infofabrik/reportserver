package net.datenwerke.rs.base.service.reportengines.table.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.reportengines.table.locale.EnumMessages;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto"
)
public enum AggregateFunction {
	
	@EnumLabel(msg=EnumMessages.class,key="avg")
	AVG,
	
	@EnumLabel(msg=EnumMessages.class,key="count")
	COUNT,
	
	@EnumLabel(msg=EnumMessages.class,key="max")
	MAX,
	
	@EnumLabel(msg=EnumMessages.class,key="min")
	MIN,
	
	@EnumLabel(msg=EnumMessages.class,key="sum")
	SUM,
	
	@EnumLabel(msg=EnumMessages.class,key="variance")
	VARIANCE, 
	
	@EnumLabel(msg=EnumMessages.class,key="countDistinct")
	COUNT_DISTINCT;
}
