package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql;

import java.util.Iterator;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ExpressionTokenToSqlHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.CaseExpressionToken;

public class CaseExpressionToSql implements ExpressionTokenToSqlHook {

   @Override
   public boolean consumes(ExpressionToken token) {
      return token instanceof CaseExpressionToken;
   }

   @Override
   public String handleToken(ExpressionToken token, Iterator<ExpressionToken> tokenIt) {
      switch (((CaseExpressionToken) token).getType()) {
      case CASE:
         return "CASE";
      case WHEN:
         return "WHEN";
      case THEN:
         return "THEN";
      case ELSE:
         return "ELSE";
      case END:
         return "END";
      default:
         throw new IllegalArgumentException();
      }
   }

}
