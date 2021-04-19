package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class ArithmeticOperatorExpressionToken implements ExpressionToken {

	public enum OperatorType {
		PLUS,
		MINUS,
		TIMES,
		DIVIDES,
		POWER
	}
	
	private final OperatorType type;

	public ArithmeticOperatorExpressionToken(OperatorType type) {
		super();
		this.type = type;
	}

	public OperatorType getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof ArithmeticOperatorExpressionToken))
			return false;
		return type.equals(((ArithmeticOperatorExpressionToken)obj).type);
	}

	@Override
	public boolean isGreedy() {
		return false;
	}
}
