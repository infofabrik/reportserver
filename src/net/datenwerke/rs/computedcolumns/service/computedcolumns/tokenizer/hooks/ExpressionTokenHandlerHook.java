package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;

public interface ExpressionTokenHandlerHook extends Hook {

	public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer, String lookaheadChar);

}
