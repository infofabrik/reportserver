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
 * Abstract implementation of the {@link mondrian3.calc.ListCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateList(mondrian3.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian3.olap.Evaluator)} method will call it.
 *
 * @author jhyde
 * @since Sep 27, 2005
 */
public abstract class AbstractListCalc
    extends AbstractCalc
    implements ListCalc
{
    private final boolean mutable;

    /**
     * Creates an abstract implementation of a compiled expression which
     * returns a mutable list of tuples.
     *
     * @param exp Expression which was compiled
     * @param calcs List of child compiled expressions (for dependency
     *   analysis)
     */
    protected AbstractListCalc(Exp exp, Calc[] calcs) {
        this(exp, calcs, true);
    }

    /**
     * Creates an abstract implementation of a compiled expression which
     * returns a list.
     *
     * @param exp Expression which was compiled
     * @param calcs List of child compiled expressions (for dependency
     *   analysis)
     * @param mutable Whether the list is mutable
     */
    protected AbstractListCalc(Exp exp, Calc[] calcs, boolean mutable) {
        super(exp, calcs);
        this.mutable = mutable;
        assert type instanceof SetType : "expecting a set: " + getType();
    }

    public SetType getType() {
        return (SetType) super.getType();
    }

    public final Object evaluate(Evaluator evaluator) {
        final TupleList tupleList = evaluateList(evaluator);
        assert tupleList != null : "null as empty tuple list is deprecated";
        return tupleList;
    }

    public TupleIterable evaluateIterable(Evaluator evaluator) {
        return evaluateList(evaluator);
    }

    public ResultStyle getResultStyle() {
        return mutable
            ? ResultStyle.MUTABLE_LIST
            : ResultStyle.LIST;
    }

    public String toString() {
        return "AbstractListCalc object";
    }
}

// End AbstractListCalc.java
