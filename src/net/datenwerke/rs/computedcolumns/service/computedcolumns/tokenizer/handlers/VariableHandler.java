package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import java.util.Collection;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.VariableExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class VariableHandler implements ExpressionTokenHandlerHook{

	private Collection<String> variables;

	@Override
	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar) {
		boolean greedy = null == lookaheadChar || "".equals(lookaheadChar.trim());
		strToken = strToken.trim();
		
		for(String variable : variables)
			if(strToken.equalsIgnoreCase(variable))
				return new VariableExpressionToken(variable, greedy);
			
		return null;
	}

	public void initVariables(Collection<String> variables) {
		this.variables  = variables;
	}

}
