package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.LogicalOperatorToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.LogicalOperatorToken.LogicalOperator;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class LogicalOperatorHandler implements ExpressionTokenHandlerHook {

	@Override
	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar) {
		boolean greedy = null == lookaheadChar || "".equals(lookaheadChar.trim());
		
		strToken = strToken.toLowerCase().trim();
		
		return "and".equals(strToken) ? new LogicalOperatorToken(LogicalOperator.AND, greedy) :
			   "or".equals(strToken) ? new LogicalOperatorToken(LogicalOperator.OR, greedy) :
			   "not".equals(strToken) ? new LogicalOperatorToken(LogicalOperator.NOT, greedy) : 
			   null;
	}

}
