package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;


public class InExpressionToken implements ExpressionToken {

	public InExpressionToken() {
		super();
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof InExpressionToken;
	}
	
	@Override
	public boolean isGreedy() {
		return false;
	}
}
