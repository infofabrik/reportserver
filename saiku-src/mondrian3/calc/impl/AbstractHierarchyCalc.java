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
import mondrian3.calc.HierarchyCalc;
import mondrian3.olap.Evaluator;
import mondrian3.olap.Exp;
import mondrian3.olap.type.HierarchyType;

/**
 * Abstract implementation of the {@link mondrian3.calc.HierarchyCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateHierarchy(mondrian3.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian3.olap.Evaluator)} method will call it.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public abstract class AbstractHierarchyCalc
    extends AbstractCalc
    implements HierarchyCalc
{
    /**
     * Creates an AbstractHierarchyCalc.
     *
     * @param exp Source expression
     * @param calcs Child compiled expressions
     */
    protected AbstractHierarchyCalc(Exp exp, Calc[] calcs) {
        super(exp, calcs);
        assert getType() instanceof HierarchyType;
    }

    public Object evaluate(Evaluator evaluator) {
        return evaluateHierarchy(evaluator);
    }
}

// End AbstractHierarchyCalc.java
