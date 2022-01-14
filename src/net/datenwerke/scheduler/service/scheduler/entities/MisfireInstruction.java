package net.datenwerke.scheduler.service.scheduler.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto")
public enum MisfireInstruction {
   EXECUTE_ONCE, EXECUTE_ALL
}
