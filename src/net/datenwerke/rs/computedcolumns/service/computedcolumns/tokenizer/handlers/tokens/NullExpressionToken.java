package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class NullExpressionToken implements ExpressionToken {

	private final boolean greedy;

	public NullExpressionToken(boolean greedy) {
		this.greedy = greedy;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof NullExpressionToken;
	}
	
	@Override
	public boolean isGreedy() {
		return greedy;
	}
}
