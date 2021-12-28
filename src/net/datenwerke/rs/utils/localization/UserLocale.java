package net.datenwerke.rs.utils.localization;

import java.util.Locale;

import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class UserLocale {

   private Locale locale;
   private String userTimezone;

   public Locale getLocale() {
      return locale;
   }

   public void setLocale(Locale locale) {
      this.locale = locale;
   }

   public void setUserTimezone(String timezone) {
      this.userTimezone = timezone;
   }

   public String getUserTimezone() {
      return userTimezone;
   }

}
