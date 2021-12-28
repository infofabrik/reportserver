package net.datenwerke.rs.utils.juel;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

/**
 * 
 *
 */
public interface JuelService {

   public static final String JUEL_SANDBOX = "JUEL";

   /**
    * Provides a new {@link ExpressionFactory} with the following properties:
    * <ul>
    * <li>javax.el.methodInvocations: true</li>
    * <li>javax.el.nullProperties: false</li>
    * </ul>
    * 
    * @return A new {@link ExpressionFactory}
    */
   public ExpressionFactory provideBasicExpressionFactory();

   /**
    * Provides a new {@link ELContext} with some default values set. The
    * ContextConfig gets newly created.
    * 
    * @param factory The {@link ExpressionFactory} to create expressions with.
    * @return A new {@link ELContext}
    */
   public ELContext provideBasicContext(ExpressionFactory factory);

   public boolean isValidExpression(String expression);

   public Object evaluate(ExpressionFactory factory, ELContext context, String text);

   public Object evaluate(Map<String, VariableAssignment> replacementMap, String template);

}
