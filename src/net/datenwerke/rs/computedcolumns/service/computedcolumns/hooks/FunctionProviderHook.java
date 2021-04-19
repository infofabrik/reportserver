package net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;

public interface FunctionProviderHook extends Hook {

	boolean consumes(String strToken, ExpressionTokenizer expressionTokenizer);

}
