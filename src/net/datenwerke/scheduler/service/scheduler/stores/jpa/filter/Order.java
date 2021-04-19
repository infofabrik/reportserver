package net.datenwerke.scheduler.service.scheduler.stores.jpa.filter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.scheduler.client.scheduler.dto.filter"
)
public enum Order{
	ASC,
	DESC
}