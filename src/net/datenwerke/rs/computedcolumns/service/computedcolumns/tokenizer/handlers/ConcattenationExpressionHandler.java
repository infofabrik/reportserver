package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.ConcattenationExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class ConcattenationExpressionHandler implements ExpressionTokenHandlerHook {

   @Override
   public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer,
         String lookaheadChar) {
      return "||".equals(strToken) ? new ConcattenationExpressionToken() : null;
   }

}
