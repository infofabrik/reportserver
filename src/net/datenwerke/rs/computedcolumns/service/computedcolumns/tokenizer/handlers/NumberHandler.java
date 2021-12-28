package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.NumberExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class NumberHandler implements ExpressionTokenHandlerHook {

   @Override
   public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer,
         String lookaheadChar) {
      boolean greedy = null == lookaheadChar || "".equals(lookaheadChar.trim());

      return strToken.matches("[-+]?[0-9]*\\.?[0-9]+") ? new NumberExpressionToken(strToken, greedy)
            : strToken.matches("[-+]?[0-9]+\\.")
                  ? new NumberExpressionToken(strToken.substring(0, strToken.length() - 1), greedy)
                  : null;
   }

}
