package net.datenwerke.rs.core.service.i18ntools.mockup;

import java.text.NumberFormat;

import net.datenwerke.rs.core.service.i18ntools.FormatPatterns;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.security.service.usermanager.entities.User;

public class I18nToolsServiceMockup implements I18nToolsService {

   @Override
   public String getUserDecimalSeparator(User u) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setUserDecimalSeparator(User u, String separator) {
      // TODO Auto-generated method stub

   }

   @Override
   public NumberFormat getUserNumberFormatter(User user) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public NumberFormat getUserNumberFormatter() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public NumberFormat getSystemNumberFormatter() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getUserDecimalSeparator() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getSystemDecimalSeparator() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String translateNumberFromUserToSystem(String fe) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String translateNumberFromSystemToUser(String fe) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String translateNumberFromSystemToUser(String valueOf, User user) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void validateSystemNumber(String number) {
      // TODO Auto-generated method stub

   }

   @Override
   public String getDefaultDateFormat() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public FormatPatterns getFormatPatterns() {
      // TODO Auto-generated method stub
      return null;
   }

}
