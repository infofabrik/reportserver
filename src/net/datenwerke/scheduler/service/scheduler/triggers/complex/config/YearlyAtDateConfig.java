package net.datenwerke.scheduler.service.scheduler.triggers.complex.config;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Months;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.config.complex")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class YearlyAtDateConfig extends DateTriggerConfig {

   /**
    * Am yearlyNDay(8) yearlyMonth(Dezember)
    */
   @ExposeToClient
   private Integer yearlyNDay;

   @ExposeToClient
   private Months yearlyMonth;

   /**
    * Defines the day in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern#YEARLY_AT_DATE}
    * </p>
    *
    */
   public Integer getYearlyNDay() {
      return yearlyNDay;
   }

   /**
    * Defines the day in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern#YEARLY_AT_DATE}
    * </p>
    *
    * @param yearlyNDay
    */
   public void setYearlyNDay(Integer yearlyNDay) {
      this.yearlyNDay = yearlyNDay;
   }

   /**
    * Defines the month in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern#YEARLY_AT_DATE}
    * </p>
    */
   public Months getYearlyMonth() {
      return yearlyMonth;
   }

   /**
    * Defines the month in a yearly pattern.
    * 
    * <p>
    * Only applies if
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesPattern#YEARLY}
    * and
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern}
    * is set to
    * {@link net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.SeriesConfigurationForm.SeriesSubPattern#YEARLY_AT_DATE}
    * </p>
    * 
    * @param yearlyMonth
    */
   public void setYearlyMonth(Months yearlyMonth) {
      this.yearlyMonth = yearlyMonth;
   }

   public void setYearlyMonth(int month) {
      this.yearlyMonth = Months.fromCalendarMonth(month);
   }
}
