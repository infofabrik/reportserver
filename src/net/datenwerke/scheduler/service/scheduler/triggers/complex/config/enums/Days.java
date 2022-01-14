package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.scheduler.service.scheduler.locale.LocaliseDateHelper;

/**
 * Defines any day you can think of.
 * 
 *
 */
@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
public enum Days {
   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelMonday")
   MONDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelTuesday")
   TUESDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelWednesday")
   WEDNESDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelThursday")
   THURSDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelFriday")
   FRIDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelSaturday")
   SATURDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelSunday")
   SUNDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelDay")
   DAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelWorkingDay")
   WORKINGDAY,

   @EnumLabel(msg = SchedulerMessages.class, key = "enumLabelWeekendDay")
   WEEKENDDAY;

   public String toString() {
      return LocaliseDateHelper.localDay(this);
   };

   public static Days fromCalendarDay(int day) {
      /*
       * public static final int SUNDAY 1 public static final int MONDAY 2 public
       * static final int TUESDAY 3 public static final int WEDNESDAY 4 public static
       * final int THURSDAY 5 public static final int FRIDAY 6 public static final int
       * SATURDAY 7
       */
      switch (day) {
      case 1:
         return SUNDAY;
      case 2:
         return MONDAY;
      case 3:
         return TUESDAY;
      case 4:
         return WEDNESDAY;
      case 5:
         return THURSDAY;
      case 6:
         return FRIDAY;
      case 7:
         return SATURDAY;
      default:
         throw new IllegalArgumentException("Not a day: " + day); //$NON-NLS-1$
      }
   }

}
