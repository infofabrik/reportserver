package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql;

import java.util.Iterator;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ExpressionTokenToSqlHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.LogicalOperatorToken;

public class LogicalOperatorToSql implements ExpressionTokenToSqlHook {


	@Override
	public boolean consumes(ExpressionToken token) {
		return token instanceof LogicalOperatorToken;
	}

	@Override
	public String handleToken(ExpressionToken token,
			Iterator<ExpressionToken> tokenIt) {
		switch (((LogicalOperatorToken)token).getType()){
		case AND:
			return "AND";
		case OR:
			return "OR";
		case NOT:
			return "NOT";
		default:
			throw new IllegalArgumentException();
		}
	}

}
