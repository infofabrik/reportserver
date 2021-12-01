package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.ParenthesisExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.ParenthesisExpressionToken.ParanthesisType;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class ParenthesisHandler implements ExpressionTokenHandlerHook {

	@Override
	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar) {
		return "(".equals(strToken) ? new ParenthesisExpressionToken(ParanthesisType.L_BRACKET) :
			   ")".equals(strToken) ? new ParenthesisExpressionToken(ParanthesisType.R_BRACKET) :
			   null;
	}

}
