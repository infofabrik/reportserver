/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2006-2009 Pentaho
// All Rights Reserved.
*/
package mondrian3.calc;

import mondrian3.olap.Evaluator;

import java.util.Date;

/**
 * Compiled expression whose result is a {@link Date}, representing an MDX
 * DateTime value.
 *
 * <p>When implementing this interface, it is convenient to extend
 * {@link mondrian3.calc.impl.AbstractDateTimeCalc}, but it is not required.
 *
 * @author jhyde
 * @since Sep 26, 2005
 */
public interface DateTimeCalc extends Calc {
    /**
     * Evaluates this expression to yield a {@link Date} value.
     *
     * @param evaluator Evaluation context
     * @return evaluation result
     */
    Date evaluateDateTime(Evaluator evaluator);
}

// End DateTimeCalc.java
