package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.RelationExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.RelationExpressionToken.RelationType;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class RelationHandler implements ExpressionTokenHandlerHook {

	@Override
	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar) {
		boolean greedy = null == lookaheadChar || "".equals(lookaheadChar.trim());
		
		strToken = strToken.toLowerCase().trim();
		
		return "<".equals(strToken) ? new RelationExpressionToken(RelationType.LESS, greedy) :
			   "<=".equals(strToken) ? new RelationExpressionToken(RelationType.LESS_OR_EQUAL, greedy) :
			   ">=".equals(strToken) ? new RelationExpressionToken(RelationType.GREATER_OR_EQUAL, greedy) :
			   ">".equals(strToken) ? new RelationExpressionToken(RelationType.GREATER, greedy) :
			   "=".equals(strToken) ? new RelationExpressionToken(RelationType.EQUAL, greedy) :
			   "!=".equals(strToken) ? new RelationExpressionToken(RelationType.NOTEQUAL, greedy) :
			   "<>".equals(strToken) ? new RelationExpressionToken(RelationType.NOTEQUAL, greedy) :
			   "like".equals(strToken) ? new RelationExpressionToken(RelationType.LIKE, greedy) :
			   "between".equals(strToken) ? new RelationExpressionToken(RelationType.BETWEEN, greedy) :
			   "is null".equals(strToken) ? new RelationExpressionToken(RelationType.IS_NULL, greedy) :
			   "is not null".equals(strToken) ? new RelationExpressionToken(RelationType.IS_NOT_NULL, greedy) :
			   null;
	}

}
