package net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.sencha.gxt.core.client.util.DateWrapper;

import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;

/**
 * Dto Decorator for {@link TimeDto}
 *
 */
public class TimeDtoDec extends TimeDto {

   private static final long serialVersionUID = 1L;

   public TimeDtoDec() {
      super();
   }

   public TimeDtoDec(Date date) {
      setHour(new DateWrapper(date).getHours());
      setMinutes(new DateWrapper(date).getMinutes());
   }

   public Date toTime() {
      return new DateWrapper(1970, 0, 1).clearTime().addHours(getHour()).addMinutes(getMinutes()).asDate();
   }

   @Override
   public String toDisplayTitle() {
      return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.HOUR_MINUTE).format(toTime());
   }
}
