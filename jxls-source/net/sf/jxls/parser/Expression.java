package net.sf.jxls.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.Configuration;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;


/**
 * Represents JEXL expression
 * @author Leonid Vysochyn
 */
public class Expression {


    public static final String aggregateSeparator = "[a-zA-Z()]+[0-9]*:";
     private static final JexlEngine jexlEngine = new JexlEngine();

    static {
        jexlEngine.setDebug(false);
    }
    String expression;
    String rawExpression;
    String aggregateFunction;
    String aggregateField;
    Map beans;
    List properties = new ArrayList();
    org.apache.commons.jexl2.Expression jexlExpresssion;

    Configuration config;

    Property collectionProperty;

    public Property getCollectionProperty() {
        return collectionProperty;
    }

    public List getProperties() {
        return properties;
    }

    public String getExpression() {
        return expression;

    }

    public Expression(String expression, Configuration config) {
        this.expression = expression;
        this.config = config;
    }

    public Expression(String expression, Map beans, Configuration config) throws Exception {
        this.config = config;
        this.expression = expression;
        this.rawExpression = parseAggregate(expression);
        this.beans = beans;
//        jexlExpresssion = new JexlEngine().createExpression(rawExpression);
        jexlExpresssion = ExpressionCollectionParser.expressionCache.get(rawExpression);
        if (jexlExpresssion == null) {
            jexlExpresssion = jexlEngine.createExpression(rawExpression);
            ExpressionCollectionParser.expressionCache.put(rawExpression, jexlExpresssion);
        }
        parse();
    }

    public Object evaluate() throws Exception {
        if (beans != null && !beans.isEmpty()) {
            JexlContext context = new MapContext(beans);
            Object ret = jexlExpresssion.evaluate(context);
            if (aggregateFunction != null) {
                return calculateAggregate(aggregateFunction, aggregateField, ret);
            }
            return ret;
        }
        return expression;
    }

    private String parseAggregate(String expr) {
        String[] aggregateParts = expr.split(aggregateSeparator, 2);
        int i = expr.indexOf(":");
        if (aggregateParts.length >= 2 && i >= 0) {
            String aggregate = expr.substring(0, i);
            if (aggregate.length() == 0) {
                aggregateFunction = null;
                aggregateField = null;
            } else {
                int f1 = aggregate.indexOf("(");
                int f2 = aggregate.indexOf(")");
                if (f1 != -1 && f2 != -1 && f2 > f1) {
                    aggregateFunction = aggregate.substring(0, f1);
                    aggregateField = aggregate.substring(f1 + 1, f2);
                } else {
                    aggregateFunction = aggregate;
                    aggregateField = "c1";
                }
            }
            return expr.substring(i + 1);
        }
        aggregateFunction = null;
        aggregateField = null;
        return expr;
    }

    private void parse() {
        Property prop = new Property(rawExpression, beans, config);
        this.properties = new ArrayList();
        this.properties.add(prop);
        if (prop.isCollection() && aggregateFunction == null && collectionProperty == null) {
            this.collectionProperty = prop;
        }
    }

    private Object calculateAggregate(String function, String field, Object list) {
        Aggregator agg = Aggregator.getInstance(function);
        if (agg != null) {
            if (list instanceof Collection) {
                Collection coll = (Collection) list;
                for (Iterator iterator = coll.iterator(); iterator.hasNext();) {
                    Object o = iterator.next();
                    try {
                        Object f = PropertyUtils.getProperty(o, field);
                        agg.add(f);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    Object f = PropertyUtils.getProperty(list, field);
                    agg.add(f);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return agg.getResult();
        }
        return list;
    }

    public String toString() {
        return expression;
    }
}
