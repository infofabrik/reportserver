package net.datenwerke.rs.core.service.i18ntools;

import java.text.NumberFormat;

import com.google.inject.ImplementedBy;

import net.datenwerke.security.service.usermanager.entities.User;

@ImplementedBy(I18nToolsServiceImpl.class)
public interface I18nToolsService {

   String getUserDecimalSeparator(User u);

   void setUserDecimalSeparator(User u, String separator);

   NumberFormat getUserNumberFormatter(User user);

   NumberFormat getUserNumberFormatter();

   NumberFormat getSystemNumberFormatter();

   String getUserDecimalSeparator();

   String getSystemDecimalSeparator();

   String translateNumberFromUserToSystem(String fe);

   String translateNumberFromSystemToUser(String fe);

   String translateNumberFromSystemToUser(String valueOf, User user);

   void validateSystemNumber(String number);

   String getDefaultDateFormat();

   FormatPatterns getFormatPatterns();
   
   String getRegion();

}
