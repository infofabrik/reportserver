package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.StringExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class StringHandler implements ExpressionTokenHandlerHook {

   @Override
   public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer,
         String lookaheadChar) {
      return strToken.matches("\'.*\'") ? !strToken.endsWith("\\'")
            ? !strToken.matches("\'.*[^\\\\]\'.*\'") ? new StringExpressionToken(clean(strToken)) : null
            : null : null;
   }

   private String clean(String strToken) {
      return strToken.substring(1, strToken.length() - 1).replace("'", "\\'").replace("\\\\'", "\\'");
   }

}
