package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql;

import java.util.Iterator;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ExpressionTokenToSqlHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.RelationExpressionToken;

public class RelationToSql implements ExpressionTokenToSqlHook {

	@Override
	public boolean consumes(ExpressionToken token) {
		return token instanceof RelationExpressionToken;
	}

	@Override
	public String handleToken(ExpressionToken token,
			Iterator<ExpressionToken> tokenIt) {
		switch (((RelationExpressionToken)token).getType()){
		case LESS:
			return "<";
		case LESS_OR_EQUAL:
			return "<=";
		case EQUAL:
			return "=";
		case GREATER:
			return ">";
		case NOTEQUAL:
			return "<>";
		case GREATER_OR_EQUAL:
			return ">=";
		case LIKE:
			return "LIKE";
		case BETWEEN:
			return "BETWEEN";
		case IS_NULL:
			return "IS NULL";
		case IS_NOT_NULL:
			return "IS NOT NULL";
		default:
			throw new IllegalArgumentException();
		}
	}

}
