package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.CommaSeparatorExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class CommaSeparatorHandler implements ExpressionTokenHandlerHook {

   @Override
   public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer,
         String lookaheadChar) {
      return ",".equals(strToken) ? new CommaSeparatorExpressionToken() : null;
   }

}
