package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql;

import java.util.Iterator;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ExpressionTokenToSqlHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.ArithmeticOperatorExpressionToken;

public class ArithmeticOperatorToSql implements ExpressionTokenToSqlHook {

	@Override
	public boolean consumes(ExpressionToken token) {
		return token instanceof ArithmeticOperatorExpressionToken;
	}

	@Override
	public String handleToken(ExpressionToken token,
			Iterator<ExpressionToken> tokenIt) {
		switch (((ArithmeticOperatorExpressionToken)token).getType()){
		case PLUS:
			return "+";
		case MINUS:
			return "-";
		case TIMES:
			return "*";
		case DIVIDES:
			return "/";
		case POWER:
			return "**";
		default:
			throw new IllegalArgumentException();
		}
	}

}
