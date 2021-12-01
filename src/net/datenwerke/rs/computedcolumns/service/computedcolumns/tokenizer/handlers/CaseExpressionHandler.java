package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.CaseExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.CaseExpressionToken.CaseType;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class CaseExpressionHandler implements ExpressionTokenHandlerHook {

	@Override
	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar) {
		boolean greedy = null == lookaheadChar || "".equals(lookaheadChar.trim());
		
		strToken = strToken.toLowerCase().trim();
		return "case".equals(strToken) ? new CaseExpressionToken(CaseType.CASE, greedy) :
			   "when".equals(strToken) ? new CaseExpressionToken(CaseType.WHEN, greedy) :
			   "then".equals(strToken) ? new CaseExpressionToken(CaseType.THEN, greedy) :
	   		   "else".equals(strToken) ? new CaseExpressionToken(CaseType.ELSE, greedy) :
		   	   "end".equals(strToken) ? new CaseExpressionToken(CaseType.END, greedy) :
			   null;
	}

}
