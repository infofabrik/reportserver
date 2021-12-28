package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class WhiteSpaceToken implements ExpressionToken {

   public WhiteSpaceToken() {
      super();
   }

   @Override
   public int hashCode() {
      return WhiteSpaceToken.class.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      return obj instanceof WhiteSpaceToken;
   }

   @Override
   public boolean isGreedy() {
      return true;
   }
}
