package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class StringExpressionToken implements ExpressionToken {

	private final String str;

	public StringExpressionToken(String str) {
		super();
		this.str = str;
	}

	public String getString() {
		return str;
	}
	
	@Override
	public int hashCode() {
		return str.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof StringExpressionToken))
			return false;
		return str.equals(((StringExpressionToken)obj).str);
	}
	
	@Override
	public boolean isGreedy() {
		return true;
	}
}
