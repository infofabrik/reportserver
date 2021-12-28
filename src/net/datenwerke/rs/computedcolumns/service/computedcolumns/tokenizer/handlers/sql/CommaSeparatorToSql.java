package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql;

import java.util.Iterator;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ExpressionTokenToSqlHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.CommaSeparatorExpressionToken;

public class CommaSeparatorToSql implements ExpressionTokenToSqlHook {

   @Override
   public boolean consumes(ExpressionToken token) {
      return token instanceof CommaSeparatorExpressionToken;
   }

   @Override
   public String handleToken(ExpressionToken token, Iterator<ExpressionToken> tokenIt) {
      return ",";
   }

}
