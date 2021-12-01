package net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers;

import java.util.Iterator;
import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.hooks.ColumnReferenceSqlProvider;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.entities.ComputedColumn;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ComputedColumnSqlProvider implements ColumnReferenceSqlProvider {

	private final Provider<ExpressionTokenizer> tokenizerProvider;
	
	private final HookHandlerService hookHandler;
	
	@Inject
	public ComputedColumnSqlProvider(
		Provider<ExpressionTokenizer> tokenizerProvider,
		HookHandlerService hookHandler
		) {
		
		this.tokenizerProvider = tokenizerProvider;
		this.hookHandler = hookHandler;
	}

	@Override
	public boolean consumes(AdditionalColumnSpec col, QueryBuilder queryBuilder) {
		return col instanceof ComputedColumn;
	}

	@Override
	public String getSelectSnipped(AdditionalColumnSpec col, QueryBuilder queryBuilder) {
		String expression = ((ComputedColumn)col).getExpression();

		if(null == expression || "".equals(expression))
			return "";
		
		ExpressionTokenizer tokenizer = tokenizerProvider.get();
		tokenizer.initFunctions(hookHandler.getHookers(FunctionProviderHook.class));
		tokenizer.initVariables(queryBuilder.getPlainColumnNames());
		
		List<ExpressionToken> tokenlist = tokenizer.tokenize(expression);
		
		StringBuilder snippet = new StringBuilder();
		
		Iterator<ExpressionToken> tokenIt = tokenlist.iterator();
		while(tokenIt.hasNext()){
			ExpressionToken token = tokenIt.next();
			for(ExpressionTokenToSqlHook toSqlHooker : hookHandler.getHookers(ExpressionTokenToSqlHook.class)){
				if(toSqlHooker.consumes(token))
					snippet.append(toSqlHooker.handleToken(token, tokenIt));
			}
		}
		
		return snippet.toString();
	}

}
