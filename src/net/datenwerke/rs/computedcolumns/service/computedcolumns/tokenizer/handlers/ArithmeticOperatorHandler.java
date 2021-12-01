package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.ArithmeticOperatorExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.ArithmeticOperatorExpressionToken.OperatorType;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class ArithmeticOperatorHandler implements ExpressionTokenHandlerHook {

	@Override
	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar) {
		return "+".equals(strToken) ? new ArithmeticOperatorExpressionToken(OperatorType.PLUS) :
			   "-".equals(strToken) ? new ArithmeticOperatorExpressionToken(OperatorType.MINUS) :
			   "*".equals(strToken) ? new ArithmeticOperatorExpressionToken(OperatorType.TIMES) :
			   "/".equals(strToken) ? new ArithmeticOperatorExpressionToken(OperatorType.DIVIDES) :
			   "^".equals(strToken) ? new ArithmeticOperatorExpressionToken(OperatorType.POWER) :
			   null;
	}

}
