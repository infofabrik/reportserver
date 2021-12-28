package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

/**
 * Defines the dayly trigger type.
 * 
 * 
 *
 */
@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
public enum DailyRepeatType {
   /**
    * The trigger fires exactly once a day.
    */
   ONCE,

   /**
    * The trigger fires multiple times within a specified bounded interval.
    */
   BOUNDED_INTERVAL
}
