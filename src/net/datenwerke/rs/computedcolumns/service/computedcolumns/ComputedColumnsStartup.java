package net.datenwerke.rs.computedcolumns.service.computedcolumns;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.hooks.ColumnReferenceSqlProvider;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ComputedColumnSqlProvider;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers.ExpressionTokenToSqlHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionToSqlProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.functions.BaseFunctionProvider;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.functions.sql.BaseFunctionToSqlProvider;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.ArithmeticOperatorHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.CaseExpressionHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.CommaSeparatorHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.ConcattenationExpressionHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.InExpressionHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.LogicalOperatorHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.NullHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.NumberHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.ParameterHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.ParenthesisHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.RelationHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.StringHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.ArithmeticOperatorToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.CaseExpressionToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.CommaSeparatorToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.ConcattentationOperatorToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.FunctionToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.InToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.LogicalOperatorToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.NullToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.NumberToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.ParameterToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.ParenthesisToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.RelationToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.StringToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.VariableToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.sql.WhiteSpaceToSql;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class ComputedColumnsStartup {

	
	@Inject
	public ComputedColumnsStartup(
		HookHandlerService hookHandler,
		
		Provider<BaseFunctionProvider> baseFunctionProvider,
		Provider<BaseFunctionToSqlProvider> baseFunctionToSql,
		
		Provider<ComputedColumnSqlProvider> sqlProvider,
		
		Provider<ArithmeticOperatorHandler> arithmeticHandler,
		Provider<CaseExpressionHandler> caseHandler,
		Provider<CommaSeparatorHandler> commaHandler,
		Provider<ConcattenationExpressionHandler> concattenationHandler,
		Provider<InExpressionHandler> inHandler,
		Provider<LogicalOperatorHandler> logicalOperatorHandler,
		Provider<NullHandler> nullHandler,
		Provider<NumberHandler> numberHandler,
		Provider<ParameterHandler> parameterHandler,
		Provider<ParenthesisHandler> parenthesisHandler,
		Provider<RelationHandler> relationHandler,
		Provider<StringHandler> stringHandler,
		
		
		Provider<ArithmeticOperatorToSql> arithmeticToSql,
		Provider<CaseExpressionToSql> caseExpressionToSql,
		Provider<CommaSeparatorToSql> commaSeparatorToSql,
		Provider<ConcattentationOperatorToSql> concattenationToSql,
		Provider<FunctionToSql> functionToSql,
		Provider<InToSql> inToSql,
		Provider<LogicalOperatorToSql> logicalOperatorToSql,
		Provider<NullToSql> nullToSql,
		Provider<NumberToSql> numberToSql,
		Provider<ParameterToSql> parameterToSql,
		Provider<ParenthesisToSql> parenthesisToSql,
		Provider<RelationToSql> relationToSql,
		Provider<StringToSql> stringToSql,
		Provider<VariableToSql> variableToSql,
		Provider<WhiteSpaceToSql> whiteSpaceToSql
		
		){
		
		hookHandler.attachHooker(FunctionProviderHook.class, baseFunctionProvider);
		hookHandler.attachHooker(FunctionToSqlProviderHook.class, baseFunctionToSql);
		
		hookHandler.attachHooker(ColumnReferenceSqlProvider.class, sqlProvider);
		
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, arithmeticHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, caseHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, commaHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, concattenationHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, inHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, logicalOperatorHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, nullHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, numberHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, parameterHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, parenthesisHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, relationHandler);
		hookHandler.attachHooker(ExpressionTokenHandlerHook.class, stringHandler);
		
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, arithmeticToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, caseExpressionToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, commaSeparatorToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, concattenationToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, functionToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, inToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, logicalOperatorToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, nullToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, numberToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, parameterToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, parenthesisToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, relationToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, stringToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, variableToSql);
		hookHandler.attachHooker(ExpressionTokenToSqlHook.class, whiteSpaceToSql);
	}
}
