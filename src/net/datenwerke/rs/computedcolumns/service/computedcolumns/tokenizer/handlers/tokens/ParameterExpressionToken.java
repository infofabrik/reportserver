package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class ParameterExpressionToken implements ExpressionToken {

	private final String parameter;

	public ParameterExpressionToken(String parameter) {
		super();
		this.parameter = parameter;
	}

	public String getParameter() {
		return parameter;
	}
	
	@Override
	public int hashCode() {
		return parameter.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof ParameterExpressionToken))
			return false;
		return parameter.equals(((ParameterExpressionToken)obj).parameter);
	}
	
	@Override
	public boolean isGreedy() {
		return true;
	}
}