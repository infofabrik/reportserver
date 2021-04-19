package net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks;

import java.util.Iterator;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.FunctionExpressionToken;

public interface FunctionToSqlProviderHook extends Hook {

	boolean consumes(FunctionExpressionToken token);

	String handleToken(FunctionExpressionToken token, Iterator<ExpressionToken> tokenIt);

}
