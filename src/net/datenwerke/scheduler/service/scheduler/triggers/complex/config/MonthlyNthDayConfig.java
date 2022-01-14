package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MonthlyNthDayConfig extends DateTriggerConfig {

   /**
    * Am monthlyNthDay(1). Tag jedes monthlyM(2). Monats
    */
   @ExposeToClient
   private Integer dayInMonth;

   @ExposeToClient
   private Integer month = 1;

   /**
    * Defines the day in a month on which the trigger should fire.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#MONTHLY}
    * </p>
    *
    */
   public Integer getDayInMonth() {
      return dayInMonth;
   }

   /**
    * Defines the day in a month on which the trigger should fire.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#MONTHLY}
    * </p>
    * 
    * @param monthlyNDay
    */
   public void setDayInMonth(Integer monthlyNDay) {
      this.dayInMonth = monthlyNDay;
   }

   /**
    * Defines the months on which the trigger should fire.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#MONTHLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern#MONTHLY_Nth_DAY_Mth_MONTH}
    * </p>
    * 
    * <p>
    * Example: On the 5th ({@link #setDayInMonth(Integer)}) of every 2nd month
    * (getMontlyM);
    *
    */
   public Integer getMonth() {
      return month;
   }

   /**
    * Defines the months on which the trigger should fire.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#MONTHLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern#MONTHLY_Nth_DAY_Mth_MONTH}
    * </p>
    * 
    * <p>
    * Example: On the 5th ({@link #setDayInMonth(Integer)}) of every 2nd month
    * (getMontlyM);
    *
    * @param monthlyM
    */
   public void setMonth(Integer monthlyM) {
      if (null != monthlyM && monthlyM > 1000)
         throw new IllegalArgumentException(
               "Are you sure that you want to schedule something every " + monthlyM + " months?");

      this.month = monthlyM;
   }
}
