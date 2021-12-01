package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class CommaSeparatorExpressionToken implements ExpressionToken {

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof CommaSeparatorExpressionToken;
	}
	
	@Override
	public boolean isGreedy() {
		return true;
	}
}
