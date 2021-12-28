package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyPattern;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DailyConfig extends DateTriggerConfig {

   /**
    * Daily, every N(3) Days
    */
   @ExposeToClient
   private Integer dailyN = 1;

   @ExposeToClient
   private DailyPattern pattern = DailyPattern.DAILY_EVERY_Nth_DAY;

   /**
    * Defines the period with which the trigger should fire (every nth day).
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#DAILY}
    * </p>
    */
   public Integer getDailyN() {
      return dailyN;
   }

   /**
    * Defines the period with which the trigger should fire (every nth day).
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#DAILY}
    * </p>
    * 
    * @param dailyN
    */
   public void setDailyN(Integer dailyN) {
      if (null != dailyN && dailyN > 1000)
         throw new IllegalArgumentException(
               "Are you sure that you want to schedule something every " + dailyN + " days?");
      this.dailyN = dailyN;
   }

   public void setPattern(DailyPattern pattern) {
      this.pattern = pattern;
   }

   public DailyPattern getPattern() {
      return pattern;
   }
}
