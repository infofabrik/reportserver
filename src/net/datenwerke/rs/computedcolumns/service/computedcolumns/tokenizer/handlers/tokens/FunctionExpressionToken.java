package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens;

import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public class FunctionExpressionToken implements ExpressionToken {

	private final String name;
	private final FunctionProviderHook function;

	public FunctionExpressionToken(String name, FunctionProviderHook function) {
		super();
		this.name = name;
		this.function = function;
	}

	public String getName() {
		return name;
	}
	
	public FunctionProviderHook getFunction() {
		return function;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof FunctionExpressionToken))
			return false;
		return name.equals(((FunctionExpressionToken)obj).name);
	}
	
	@Override
	public boolean isGreedy() {
		return false;
	}
}
