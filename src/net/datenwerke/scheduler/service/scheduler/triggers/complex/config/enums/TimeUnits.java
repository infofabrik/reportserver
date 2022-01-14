package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.scheduler.service.scheduler.locale.LocaliseDateHelper;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
public enum TimeUnits {

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelHours")
   HOURS,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelMinutes")
   MINUTES;

   public String toString() {
      return LocaliseDateHelper.localTimeUnit(this);
   };

   public int getCalendarUnit() {
      switch (this) {
      case HOURS:
         return 11; // Calendar.HOUR_OF_DAY;
      case MINUTES:
         return 12; // Calendar.MINUTE;
      default:
         throw new IllegalArgumentException();
      }
   }
}
