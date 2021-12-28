package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class YearlyNthDayOfWeekConfig extends DateTriggerConfig {

   /**
    * Jeden yearlyNth(zweiten) yearlyDay(Dienstag) im yearlyMonth2(Dezember)
    */
   @ExposeToClient
   private Nth yearlyNth;

   @ExposeToClient
   private Days yearlyDay;

   @ExposeToClient
   private Months yearlyMonth;

   /**
    * Defines the nth ({@link Nth}) day in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern }
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern #YEARLY_Nth_DAY_OF_WEEK_IN_MONTH}
    * </p>
    * 
    */
   public Nth getYearlyNth() {
      return yearlyNth;
   }

   /**
    * Defines the nth ({@link Nth}) day in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern }
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern #YEARLY_Nth_DAY_OF_WEEK_IN_MONTH}
    * </p>
    * 
    * @param yearlyNth
    */
   public void setYearlyNth(Nth yearlyNth) {
      this.yearlyNth = yearlyNth;
   }

   /**
    * Defines the day ({@link Days}) in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern }
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern #YEARLY_Nth_DAY_OF_WEEK_IN_MONTH}
    * </p>
    * 
    */
   public Days getYearlyDay() {
      return yearlyDay;
   }

   /**
    * Defines the day ({@link Days}) in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern }
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern #YEARLY_Nth_DAY_OF_WEEK_IN_MONTH}
    * </p>
    * 
    * @param yearlyDay
    */
   public void setYearlyDay(Days yearlyDay) {
      this.yearlyDay = yearlyDay;
   }

   /**
    * Defines the month ({@link Months}) in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern }
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern #YEARLY_Nth_DAY_OF_WEEK_IN_MONTH}
    * </p>
    * 
    */
   public Months getYearlyMonth() {
      return yearlyMonth;
   }

   /**
    * Defines the month ({@link Months}) in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern }
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern #YEARLY_Nth_DAY_OF_WEEK_IN_MONTH}
    * </p>
    * 
    */
   public void setYearlyMonth(Months yearlyMonth) {
      this.yearlyMonth = yearlyMonth;
   }

}
