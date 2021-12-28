package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCDate extends SimpleFormFieldConfiguration {

   public enum Mode {
      Date, Time, DateTime
   }

   public Mode getMode();

   public String getDatePattern();

   public String getTimePattern();
}
