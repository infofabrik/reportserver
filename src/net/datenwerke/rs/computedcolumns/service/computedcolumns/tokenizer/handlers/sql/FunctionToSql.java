package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql;

import java.util.Iterator;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ExpressionTokenToSqlHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionToSqlProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.FunctionExpressionToken;

public class FunctionToSql implements ExpressionTokenToSqlHook {

	private final HookHandlerService hookHandler;
	
	@Inject
	public FunctionToSql(HookHandlerService hookHandler) {
		super();
		this.hookHandler = hookHandler;
	}

	@Override
	public boolean consumes(ExpressionToken token) {
		return token instanceof FunctionExpressionToken;
	}

	@Override
	public String handleToken(ExpressionToken token,
			Iterator<ExpressionToken> tokenIt) {
		for(FunctionToSqlProviderHook sqlProvider : hookHandler.getHookers(FunctionToSqlProviderHook.class))
			if(sqlProvider.consumes((FunctionExpressionToken) token))
				return sqlProvider.handleToken((FunctionExpressionToken) token, tokenIt);
		throw new IllegalArgumentException();
	}

}
