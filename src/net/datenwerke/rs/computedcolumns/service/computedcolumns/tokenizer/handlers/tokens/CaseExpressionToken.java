package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;


public class CaseExpressionToken implements ExpressionToken {

	public enum CaseType {
		CASE,
		WHEN,
		THEN,
		ELSE,
		END
	}
	
	private final CaseType type;
	private final boolean greedy;

	public CaseExpressionToken(CaseType type, boolean greedy) {
		super();
		this.type = type;
		this.greedy = greedy;
	}

	public CaseType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof CaseExpressionToken))
			return false;
		return type.equals(((CaseExpressionToken)obj).type);
	}
	
	@Override
	public boolean isGreedy() {
		return greedy;
	}
}
