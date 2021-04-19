/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2006-2011 Pentaho
// All Rights Reserved.
*/
package mondrian3.calc.impl;

import mondrian3.calc.*;
import mondrian3.olap.Evaluator;
import mondrian3.olap.Exp;
import mondrian3.olap.type.SetType;

/**
 * Abstract implementation of the {@link mondrian3.calc.IterCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateIterable(mondrian3.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian3.olap.Evaluator)} method will call it.
 *
 * @see mondrian3.calc.impl.AbstractListCalc
 *
 * @author jhyde
 * @since Oct 24, 2008
 */
public abstract class AbstractIterCalc
    extends AbstractCalc
    implements IterCalc
{
    /**
     * Creates an abstract implementation of a compiled expression which returns
     * a {@link TupleIterable}.
     *
     * @param exp Expression which was compiled
     * @param calcs List of child compiled expressions (for dependency
     *   analysis)
     */
    protected AbstractIterCalc(Exp exp, Calc[] calcs) {
        super(exp, calcs);
    }

    public SetType getType() {
        return (SetType) super.getType();
    }

    public final Object evaluate(Evaluator evaluator) {
        return evaluateIterable(evaluator);
    }

    public ResultStyle getResultStyle() {
        return ResultStyle.ITERABLE;
    }

    public String toString() {
        return "AbstractIterCalc object";
    }
}

// End AbstractIterCalc.java
