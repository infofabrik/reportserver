package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class NumberExpressionToken implements ExpressionToken {

   private final String number;
   private final boolean greedy;

   public NumberExpressionToken(String number) {
      this(number, false);
   }

   public NumberExpressionToken(String number, boolean greedy) {
      super();
      this.number = number;
      this.greedy = greedy;
   }

   public String getNumber() {
      return number;
   }

   @Override
   public int hashCode() {
      return number.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof NumberExpressionToken))
         return false;
      return number.equals(((NumberExpressionToken) obj).number);
   }

   @Override
   public boolean isGreedy() {
      return greedy;
   }
}
