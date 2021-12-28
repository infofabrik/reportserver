package net.datenwerke.scheduler.service.scheduler.helper;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
public enum RetryTimeUnit {
   MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS
}
