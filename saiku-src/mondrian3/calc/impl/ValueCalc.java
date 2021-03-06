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
import mondrian3.olap.*;

/**
 * Expression which yields the value of the current member in the current
 * dimensional context.
 *
 * @see mondrian3.calc.impl.MemberValueCalc
 *
 * @author jhyde
 * @since Sep 27, 2005
 */
public class ValueCalc extends GenericCalc {
    /**
     * Creates a ValueCalc.
     *
     * @param exp Source expression
     */
    public ValueCalc(Exp exp) {
        super(exp, new Calc[0]);
    }

    public Object evaluate(Evaluator evaluator) {
        return evaluator.evaluateCurrent();
    }

    public boolean dependsOn(Hierarchy hierarchy) {
        return true;
    }
}

// End ValueCalc.java
