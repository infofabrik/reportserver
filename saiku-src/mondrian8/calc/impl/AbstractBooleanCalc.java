/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian8.calc.impl;

import mondrian8.calc.BooleanCalc;
import mondrian8.calc.Calc;
import mondrian8.olap.Evaluator;
import mondrian8.olap.Exp;

/**
 * Abstract implementation of the {@link mondrian8.calc.BooleanCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateBoolean(mondrian8.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian8.olap.Evaluator)} method will call it.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public abstract class AbstractBooleanCalc
    extends AbstractCalc
    implements BooleanCalc
{
    /**
     * Creates an AbstractBooleanCalc.
     *
     * @param exp Source expression
     * @param calcs Child compiled expressions
     */
    public AbstractBooleanCalc(Exp exp, Calc[] calcs) {
        super(exp, calcs);
        // now supports int and double conversion (see
        // AbstractExpCompiler.compileBoolean():
        // assert getType() instanceof BooleanType;
    }

    public Object evaluate(Evaluator evaluator) {
        return Boolean.valueOf(evaluateBoolean(evaluator));
    }
}

// End AbstractBooleanCalc.java
