package net.datenwerke.rs.utils.juel;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.inject.Provider;
import javax.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.juel.annotations.JuelExpressionBlacklist;
import net.datenwerke.rs.utils.juel.hooks.JuelServiceHook;
import net.datenwerke.rs.utils.juel.wrapper.ServerInfoWrapper;
import net.datenwerke.rs.utils.juel.wrapper.SimpleDateWrapper;
import net.datenwerke.rs.utils.juel.wrapper.TodayWrapper;
import net.datenwerke.rs.utils.misc.DateUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import de.odysseus.el.util.SimpleResolver;

/**
 * 
 *
 */
@Singleton
public class JuelServiceImpl implements JuelService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public static final String VARIABLE_CURRENT_DATE = "RS_CURRENT_DATE";
	public static final String TODAY = "today";

	private static final String S_UTILS = "StringUtils";
	private static final String S_ESCAPE_UTILS = "StringEscapeUtils";
	private static final String SERVER_INFO = "ServerInfo";

	private final Provider<Collection<String>> expressionBlacklist;
	private final HookHandlerService hookHandler;

	
	@Inject
	public JuelServiceImpl(
		@JuelExpressionBlacklist Provider<Collection<String>> expressionBlacklist,
		HookHandlerService hookHandler
		) {
		this.expressionBlacklist = expressionBlacklist;
		this.hookHandler = hookHandler;
	}
	
	@Override
	public ELContext provideBasicContext(ExpressionFactory factory) {
		
		ELResolver resolver = new SimpleResolver();
		SimpleContext context = new SimpleContext(resolver);

		/* add functions */
		try {
			context.setFunction("math", "random", Math.class.getMethod("random")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			context.setFunction("math", "sin", Math.class.getMethod("sin", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "cos", Math.class.getMethod("cos", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "tan", Math.class.getMethod("tan", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			context.setFunction("math", "abs", Math.class.getMethod("abs", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "ceil", Math.class.getMethod("ceil", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "floor", Math.class.getMethod("floor", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "round", Math.class.getMethod("round", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			context.setFunction("math", "max", Math.class.getMethod("max", double.class, double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "min", Math.class.getMethod("min", double.class, double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			context.setFunction("math", "pow", Math.class.getMethod("pow", double.class, double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "log", Math.class.getMethod("log", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "exp", Math.class.getMethod("exp", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			context.setFunction("math", "sqrt", Math.class.getMethod("sqrt", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			context.setFunction("math", "signum", Math.class.getMethod("signum", double.class)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			context.setFunction("sutils", "left", StringUtils.class.getMethod("left", String.class, int.class));
			context.setFunction("sutils", "right", StringUtils.class.getMethod("right", String.class, int.class));
			
			
			context.setFunction("sutils", "right", StringUtils.class.getMethod("right", String.class, int.class));
			
			context.setFunction("dutils", "format", DateUtils.class.getMethod("formatDate", Date.class, String.class));
			
			context.setVariable("pi", factory.createValueExpression(Math.PI, double.class)); //$NON-NLS-1$
			context.setVariable("e", factory.createValueExpression(Math.E, double.class)); //$NON-NLS-1$
		} catch (SecurityException e) {
			logger.warn( e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			logger.warn( e.getMessage(), e);
		}
		
		/* add special objects */
		context.setVariable(VARIABLE_CURRENT_DATE, factory.createValueExpression(new SimpleDateWrapper(), SimpleDateWrapper.class));
		context.setVariable(TODAY, factory.createValueExpression(new TodayWrapper(), TodayWrapper.class));
		
		context.setVariable(SERVER_INFO, factory.createValueExpression(new ServerInfoWrapper(), ServerInfoWrapper.class));
		context.setVariable(S_UTILS, factory.createValueExpression(new StringUtils(), StringUtils.class));
		context.setVariable(S_ESCAPE_UTILS, factory.createValueExpression(new StringEscapeUtils(), StringEscapeUtils.class));
				
		for(JuelServiceHook hook : hookHandler.getHookers(JuelServiceHook.class))
			hook.adaptContext(context);
		
		return context;
	}

	@Override
	public ExpressionFactory provideBasicExpressionFactory() {
		Properties properties = new Properties();
		
		/* enable method invocations */
		properties.setProperty("javax.el.methodInvocations", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		properties.setProperty("javax.el.nullProperties", "false"); //$NON-NLS-1$ //$NON-NLS-2$
		
		for(JuelServiceHook hook : hookHandler.getHookers(JuelServiceHook.class))
			hook.propertiesForFactoryCreation(properties);
		
		/* build factory */
		ExpressionFactory factory = new ExpressionFactoryImpl(properties);
		
		for(JuelServiceHook hook : hookHandler.getHookers(JuelServiceHook.class))
			hook.adaptFactory(factory);
		
		return factory;
	}

	@Override
	public boolean isValidExpression(String expression) {
		if(null == expression)
			return true;
		if(null == expressionBlacklist)
			return true;
		for(String ble : expressionBlacklist.get())
			if(expression.contains(ble))
				return false;
		return true;
	}

	@Override
	public Object evaluate(Map<String, VariableAssignment> replacementMap, String template) {
		ExpressionFactory factory = provideBasicExpressionFactory();
		ELContext context = provideBasicContext(factory);
		
		for(Map.Entry<String, VariableAssignment> e : replacementMap.entrySet()){
			String key = e.getKey();
			VariableAssignment arg = e.getValue();
			if(null != arg && null != arg.getValue())
				context.getVariableMapper().setVariable(key, factory.createValueExpression(arg.getValue(), arg.getType()));
			else
				context.getVariableMapper().setVariable(key, factory.createValueExpression(null, Object.class));
		}	
		
		return evaluate(factory, context, template);
	}
	
	@Override
	public Object evaluate(ExpressionFactory factory, ELContext context, String template) {
		if(null == template)
			return null;
		
		/* validate */
		if(! isValidExpression(template))
			throw new IllegalArgumentException("Expression '" + template + "' is not a valid Juel expression.");
		
		notifyHookers(factory, context, template);
		
		return  factory.createValueExpression(context, template, Object.class).getValue(context);
	}

	protected void notifyHookers(ExpressionFactory factory, ELContext context, String template) {
		for(JuelServiceHook hook : hookHandler.getHookers(JuelServiceHook.class))
			hook.aboutToEvaluate(factory, context, template);
	}


}
