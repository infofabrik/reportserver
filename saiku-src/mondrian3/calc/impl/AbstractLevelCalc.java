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
import mondrian3.calc.LevelCalc;
import mondrian3.olap.Evaluator;
import mondrian3.olap.Exp;
import mondrian3.olap.type.LevelType;

/**
 * Abstract implementation of the {@link mondrian3.calc.LevelCalc} interface.
 *
 * <p>The derived class must
 * implement the {@link #evaluateLevel(mondrian3.olap.Evaluator)} method,
 * and the {@link #evaluate(mondrian3.olap.Evaluator)} method will call it.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public abstract class AbstractLevelCalc
    extends AbstractCalc
    implements LevelCalc
{
    /**
     * Creates an AbstractLevelCalc.
     *
     * @param exp Source expression
     * @param calcs Child compiled expressions
     */
    protected AbstractLevelCalc(Exp exp, Calc[] calcs) {
        super(exp, calcs);
        assert getType() instanceof LevelType;
    }

    public Object evaluate(Evaluator evaluator) {
        return evaluateLevel(evaluator);
    }
}

// End AbstractLevelCalc.java
