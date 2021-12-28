package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class VariableExpressionToken implements ExpressionToken {

   private final String name;
   private final boolean greedy;

   public VariableExpressionToken(String name, boolean greedy) {
      super();
      this.name = name;
      this.greedy = greedy;
   }

   public String getName() {
      return name;
   }

   @Override
   public int hashCode() {
      return name.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof VariableExpressionToken))
         return false;
      return name.equals(((VariableExpressionToken) obj).name);
   }

   @Override
   public boolean isGreedy() {
      return greedy;
   }
}
