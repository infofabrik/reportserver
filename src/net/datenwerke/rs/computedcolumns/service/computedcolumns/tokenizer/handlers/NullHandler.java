package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.NullExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class NullHandler implements ExpressionTokenHandlerHook {

   @Override
   public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer,
         String lookaheadChar) {
      boolean greedy = null == lookaheadChar || "".equals(lookaheadChar.trim());

      strToken = strToken.toLowerCase().trim();

      return "null".equals(strToken) ? new NullExpressionToken(greedy) : null;
   }

}
