package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
public enum DailyPattern {

   /**
    * Defines that the event should occur every nth day.
    */
   DAILY_EVERY_Nth_DAY,

   /**
    * Defines that an event should only occur on workdays.
    */
   DAILY_WORKDAY,

}
