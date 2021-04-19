package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.functions.sql;

import java.util.Iterator;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionToSqlProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.functions.BaseFunctionProvider;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.FunctionExpressionToken;

public class BaseFunctionToSqlProvider implements FunctionToSqlProviderHook {

	@Override
	public boolean consumes(FunctionExpressionToken token) {
		return token.getFunction() instanceof BaseFunctionProvider;
	}

	@Override
	public String handleToken(FunctionExpressionToken token,
			Iterator<ExpressionToken> tokenIt) {
		return token.getName().toUpperCase();
	}

}
