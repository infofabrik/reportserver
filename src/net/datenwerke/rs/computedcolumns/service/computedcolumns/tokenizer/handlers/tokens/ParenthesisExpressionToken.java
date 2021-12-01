package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class ParenthesisExpressionToken implements ExpressionToken {

	public enum ParanthesisType {
		L_BRACKET,
		R_BRACKET
	}
	
	private final ParanthesisType type;

	public ParenthesisExpressionToken(ParanthesisType type) {
		super();
		this.type = type;
	}

	public ParanthesisType getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof ParenthesisExpressionToken))
			return false;
		return type.equals(((ParenthesisExpressionToken)obj).type);
	}
	
	@Override
	public boolean isGreedy() {
		return true;
	}
}
