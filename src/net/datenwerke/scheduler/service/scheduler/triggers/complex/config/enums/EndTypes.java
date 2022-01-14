package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * Defines when a trigger is done.
 * 
 *
 */
@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
public enum EndTypes {
   /**
    * The trigger should never stop
    */
   INFINITE,

   /**
    * The triggers should fire exactly n times.
    */
   COUNT,

   /**
    * The trigger should not fire after a specified date.
    */
   DATE
}
