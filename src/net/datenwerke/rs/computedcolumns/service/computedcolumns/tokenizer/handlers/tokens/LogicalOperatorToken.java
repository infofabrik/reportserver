package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class LogicalOperatorToken implements ExpressionToken {

	public enum LogicalOperator {
		AND,
		OR,
		NOT
	}
	
	private final LogicalOperator type;
	private final boolean greedy;

	public LogicalOperatorToken(LogicalOperator type, boolean greedy) {
		super();
		this.type = type;
		this.greedy = greedy;
	}

	
	public LogicalOperator getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof LogicalOperatorToken))
			return false;
		return type.equals(((LogicalOperatorToken)obj).type);
	}
	
	@Override
	public boolean isGreedy() {
		return greedy;
	}
}
