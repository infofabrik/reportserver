package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class RelationExpressionToken implements ExpressionToken {

   public enum RelationType {
      LESS, LESS_OR_EQUAL, EQUAL, NOTEQUAL, GREATER_OR_EQUAL, GREATER, LIKE, BETWEEN, IS_NULL, IS_NOT_NULL
   }

   private final RelationType type;
   private final boolean greedy;

   public RelationExpressionToken(RelationType type, boolean greedy) {
      super();
      this.type = type;
      this.greedy = greedy;
   }

   public RelationType getType() {
      return type;
   }

   @Override
   public int hashCode() {
      return type.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof RelationExpressionToken))
         return false;
      return type.equals(((RelationExpressionToken) obj).type);
   }

   @Override
   public boolean isGreedy() {
      return greedy;
   }
}
