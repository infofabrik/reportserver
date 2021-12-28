package net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.hooks.FunctionProviderHook;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.FunctionHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.VariableHandler;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.handlers.tokens.WhiteSpaceToken;
import net.datenwerke.rs.computedcolumns.service.computedcolumns.tokenizer.hooks.ExpressionTokenHandlerHook;

public class ExpressionTokenizer {

   public interface TokenizeCallback {

      void foundToken(String strToken, ExpressionToken token);

   }

   private final HookHandlerService hookHandler;
   private final Provider<VariableHandler> variableHandlerProvider;
   private final Provider<FunctionHandler> functionHandlerProvider;

   private Collection<String> variables = new HashSet<String>();
   private Collection<FunctionProviderHook> functions = new HashSet<FunctionProviderHook>();

   @Inject
   public ExpressionTokenizer(HookHandlerService hookHandler, Provider<VariableHandler> variableHandlerProvider,
         Provider<FunctionHandler> functionHandlerProvider) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.variableHandlerProvider = variableHandlerProvider;
      this.functionHandlerProvider = functionHandlerProvider;
   }

   public void initVariables(Collection<String> variables) {
      this.variables = variables;
   }

   public void initFunctions(Collection<FunctionProviderHook> functions) {
      this.functions = functions;
   }

   public List<ExpressionToken> tokenize(String input) {
      return tokenize(input, new TokenizeCallback() {
         @Override
         public void foundToken(String strToken, ExpressionToken token) {
         }
      });
   }

   public List<ExpressionToken> tokenize(String input, TokenizeCallback callback) {
      List<ExpressionToken> tokenList = new ArrayList<ExpressionToken>();

      /* no need to process leading/trailing whitespace */
      input = input.trim();

      /* gather token handlers */
      List<ExpressionTokenHandlerHook> handlers = hookHandler.getHookers(ExpressionTokenHandlerHook.class);

      /* add variable and function handler */
      VariableHandler vHandler = variableHandlerProvider.get();
      vHandler.initVariables(variables);
      handlers.add(vHandler);

      FunctionHandler fHandler = functionHandlerProvider.get();
      fHandler.initFunctions(functions);
      handlers.add(fHandler);

      /* go one character at a time */
      int lastStart = 0;
      String[] breaker = new String[] { "(", ")", ",", "||" };
      while (lastStart < input.length()) {
         int newStart = lastStart;
         ExpressionToken currentToken = null;
         String currentTokenStr = "";

         /* get rid of whitespace */
         for (int i = lastStart; i < input.length(); i++) {
            char c = input.charAt(i);
            if ("".equals(String.valueOf(c).trim())) {
               lastStart++;
               newStart++;
               tokenList.add(new WhiteSpaceToken());
            } else
               break;
         }

         for (int i = lastStart; i < input.length(); i++) {
            char c = input.charAt(i);
            String lookaheadChar = i + 1 < input.length() ? String.valueOf(input.charAt(i + 1)) : null;
            currentTokenStr += c;

            if (null != currentToken) {
               boolean doBreak = false;
               for (String br : breaker) {
                  if (currentTokenStr.endsWith(br)) {
                     doBreak = true;
                     break;
                  }
               }
               if (doBreak)
                  break;
            }

            ExpressionToken newToken = findToken(handlers, currentTokenStr, lookaheadChar);
            if (null != newToken) {
               currentToken = newToken;
               newStart = i + 1;

               if (currentToken.isGreedy()) {
                  break;
               }
            }
         }

         if (null == currentTokenStr || null == currentToken)
            throw new IllegalArgumentException(
                  "Could not tokenize: " + input + ". Problem occured at " + input.substring(lastStart));

         tokenList.add(currentToken);
         callback.foundToken(currentTokenStr, currentToken);

         if (newStart == lastStart)
            throw new IllegalArgumentException(
                  "Could not tokenize: " + input + ". Problem occured at " + input.substring(lastStart));
         lastStart = newStart;
      }

      return tokenList;
   }

   private ExpressionToken findToken(List<ExpressionTokenHandlerHook> handlers, String strToken, String lookaheadChar) {
      for (ExpressionTokenHandlerHook handler : handlers) {
         ExpressionToken token = handler.generateToken(strToken, this, lookaheadChar);
         if (null != token) {
            return token;
         }
      }
      return null;
   }

}
