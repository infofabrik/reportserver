package net.datenwerke.rs.computedcolumns.service.computedcolumns.hookers;

import java.util.Iterator;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;

public interface ExpressionTokenToSqlHook extends Hook {

   boolean consumes(ExpressionToken token);

   String handleToken(ExpressionToken token, Iterator<ExpressionToken> tokenIt);

}
