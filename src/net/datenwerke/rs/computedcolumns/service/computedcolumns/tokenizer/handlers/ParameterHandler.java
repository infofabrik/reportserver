package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers;

import javax.el.ExpressionFactory;

import com.google.inject.Inject;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import de.odysseus.el.util.SimpleResolver;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.ExpressionTokenizer;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.ParameterExpressionToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;
import net.datenwerke.rs.utils.juel.JuelService;

public class ParameterHandler implements ExpressionTokenHandlerHook {

   @Inject
   private JuelService juelService;

   ExpressionFactory factory = new ExpressionFactoryImpl();
   SimpleContext context = new SimpleContext(new SimpleResolver());

   @Override
   public ExpressionToken generateToken(String strToken, ExpressionTokenizer expressionTokenizer,
         String lookaheadChar) {
      if (strToken.matches("\\$\\{.*\\}") && juelService.isValidExpression(strToken)) {
         try {
            factory.createValueExpression(context, strToken, Object.class);
            return new ParameterExpressionToken(strToken.substring(2, strToken.length() - 1));
         } catch (Exception e) {
            return null;
         }
      }

      return null;
   }

}