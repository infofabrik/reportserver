package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import java.util.Collection;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.FunctionExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class FunctionHandler implements ExpressionTokenHandlerHook {

	private Collection<FunctionProviderHook> functions;

	@Override
	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar) {
		for(FunctionProviderHook function : functions)
			if(function.consumes(strToken, expressionTokenizer))
				return new FunctionExpressionToken(strToken, function);
		return null;
	}

	public void initFunctions(Collection<FunctionProviderHook> functions) {
		this.functions = functions;
	}

}
