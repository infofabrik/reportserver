package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.InExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class InExpressionHandler implements ExpressionTokenHandlerHook {

   @Override
   public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer,
         String lookaheadChar) {
      strToken = strToken.toLowerCase();
      return "in".equals(strToken) ? new InExpressionToken() : null;
   }

}
