package net.datenwerke.rs.base.service.dbhelper.querybuilder;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.base.service.dbhelper.DBHelperModule;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;

public class QuoteHelper {

   @Inject
   private static Provider<ConfigService> configService;

   private DatabaseHelper databaseHelper;

   private QuoteMode quoteMode;

   private boolean smartModeQuotes = false;

   private enum QuoteMode {
      ALWAYS, NEVER, SMART
   }

   public QuoteHelper(DatabaseHelper databaseHelper, QueryBuilder builder) {
      this.databaseHelper = databaseHelper;

      String sQuoteMode = configService.get().getConfigFailsafe(DBHelperModule.CONFIG_FILE)
            .getString(DBHelperModule.QUOTE_MODE, "smart");
      quoteMode = QuoteMode.valueOf(sQuoteMode.toUpperCase());

      if (QuoteMode.SMART.equals(quoteMode)) {
         try {
            this.smartModeQuotes = builder.getInnerQuery().contains(databaseHelper.getIdentifierQuoteChar());

         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   public String quoteIdentifier(String identifier) {
      switch (quoteMode) {
      case ALWAYS:
         return databaseHelper.getIdentifierQuoteChar() + identifier + databaseHelper.getIdentifierQuoteChar();
      case NEVER:
         return identifier;
      case SMART:
         if (smartModeQuotes || null != identifier && identifier.contains(" ")) {
            return databaseHelper.getIdentifierQuoteChar() + identifier + databaseHelper.getIdentifierQuoteChar();
         } else {
            return identifier;
         }
      }
      return identifier;
   }

}
