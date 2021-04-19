/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2006-2009 Pentaho
// All Rights Reserved.
*/
package mondrian3.calc.impl;

import mondrian3.calc.Calc;
import mondrian3.calc.DoubleCalc;
import mondrian3.olap.Evaluator;
import mondrian3.olap.Exp;
import mondrian3.olap.fun.FunUtil;
import mondrian3.olap.type.NumericType;

/**
 * Abstract implementation of the {@link mondrian3.calc.DoubleCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateDouble(mondrian3.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian3.olap.Evaluator)} method will call it.
 *
 * @author jhyde
 * @since Sep 27, 2005
 */
public abstract class AbstractDoubleCalc
    extends AbstractCalc
    implements DoubleCalc
{
    /**
     * Creates an AbstractDoubleCalc.
     *
     * @param exp Source expression
     * @param calcs Child compiled expressions
     */
    protected AbstractDoubleCalc(Exp exp, Calc[] calcs) {
        super(exp, calcs);
        assert getType() instanceof NumericType;
    }

    public Object evaluate(Evaluator evaluator) {
        final double d = evaluateDouble(evaluator);
        if (d == FunUtil.DoubleNull) {
            return null;
        }
        return new Double(d);
    }
}

// End AbstractDoubleCalc.java
