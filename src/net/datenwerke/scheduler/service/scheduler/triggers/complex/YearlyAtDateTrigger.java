package net.datenwerke.scheduler.service.scheduler.triggers.complex;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.time.DateUtils;

import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.YearlyAtDateConfig;

@Entity
@Table(name = "SCHED_TRIG_YEARLY_AT_DATE")
public class YearlyAtDateTrigger extends DateTrigger<YearlyAtDateConfig> {

   public YearlyAtDateTrigger() {
      this(null);
   }

   public YearlyAtDateTrigger(YearlyAtDateConfig config) {
      super(config);
   }

   public Date nextIncludedDay(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.DAY_OF_MONTH, getConfig().getYearlyNDay());
      cal.set(Calendar.MONTH, getConfig().getYearlyMonth().ordinal());

      if (cal.before(date))
         cal.add(Calendar.YEAR, 1);

      return cal.getTime();
   }

   @Override
   protected Date getFirstIncludedDayBefore(Date date) {
      Calendar cal = new GregorianCalendar();
      cal.setTime(date);
      cal.add(Calendar.YEAR, -2);
      cal.setTime(_computeNextFireTime(cal.getTime(), true));

      Date last = null;
      while (!date.before(cal.getTime())) {
         last = cal.getTime();
         cal.setTime(_computeNextFireTime(cal.getTime(), true));
      }

      if (null != last)
         return last;

      throw new IllegalArgumentException("The first fire time comes before the submitted date"); //$NON-NLS-1$
   }

   @Override
   public Date _computeNextFireTime(Date afterTime, boolean isFirstRun) {
      Date adjustedDate = isFirstRun ? DateUtils.addSeconds(afterTime, 1) : afterTime;
      Date next = getNthFireTimeOfDayAfter(nextIncludedDay(adjustedDate), 1);
      if (null != next) {
         /* reset seconds */
         next = DateUtils.truncate(next, Calendar.MINUTE);
         return next; /* we have won */
      }

      Calendar cal = Calendar.getInstance();
      cal.setTime(afterTime);
      cal.set(Calendar.DAY_OF_YEAR, 1);
      cal = DateUtils.truncate(cal, Calendar.DATE);
      cal.add(Calendar.YEAR, 1);

      return _computeNextFireTime(cal.getTime(), false);
   }

   @Override
   public Date computeFirstFireTime() {
      Calendar cal = Calendar.getInstance();
      cal.setTime(config.getFirstExecution());
      cal.setTime(nextIncludedDay(cal.getTime()));

      /* get first time */
      switch (config.getDailyRepeatType()) {
      case ONCE:
         cal.set(Calendar.HOUR_OF_DAY, config.getAtTime().getHour());
         cal.set(Calendar.MINUTE, config.getAtTime().getMinutes());
         cal.set(Calendar.SECOND, 0);
         break;
      case BOUNDED_INTERVAL:
         cal.set(Calendar.HOUR_OF_DAY, config.getTimeRangeStart().getHour());
         cal.set(Calendar.MINUTE, config.getTimeRangeStart().getMinutes());
         cal.set(Calendar.SECOND, 0);
         break;
      }

      return cal.getTime();
   }

   @Override
   public boolean consumes(DateTriggerConfig config) {
      return (super.consumes(config) &&

            config instanceof YearlyAtDateConfig &&

            null != ((YearlyAtDateConfig) config).getYearlyNDay()
            && null != ((YearlyAtDateConfig) config).getYearlyMonth());
   }
}
