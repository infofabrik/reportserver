/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2006-2011 Pentaho
// All Rights Reserved.
*/
package mondrian3.olap.fun;

import mondrian3.calc.*;
import mondrian3.calc.impl.AbstractDoubleCalc;
import mondrian3.calc.impl.ValueCalc;
import mondrian3.mdx.ResolvedFunCall;
import mondrian3.olap.*;

/**
 * Definition of the <code>StdevP</code> builtin MDX function, and its alias
 * <code>StddevP</code>.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class StdevPFunDef extends AbstractAggregateFunDef {

    static final Resolver StdevpResolver =
        new ReflectiveMultiResolver(
            "StdevP",
            "StdevP(<Set>[, <Numeric Expression>])",
            "Returns the standard deviation of a numeric expression evaluated over a set (biased).",
            new String[]{"fnx", "fnxn"},
            StdevPFunDef.class);

    static final Resolver StddevpResolver =
        new ReflectiveMultiResolver(
            "StddevP",
            "StddevP(<Set>[, <Numeric Expression>])",
            "Alias for StdevP.",
            new String[]{"fnx", "fnxn"},
            StdevPFunDef.class);

    public StdevPFunDef(FunDef dummyFunDef) {
        super(dummyFunDef);
    }

    public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler) {
        final ListCalc listCalc =
            compiler.compileList(call.getArg(0));
        final Calc calc =
            call.getArgCount() > 1
            ? compiler.compileScalar(call.getArg(1), true)
            : new ValueCalc(call);
        return new AbstractDoubleCalc(call, new Calc[] {listCalc, calc}) {
            public double evaluateDouble(Evaluator evaluator) {
                final int savepoint = evaluator.savepoint();
                try {
                    evaluator.setNonEmpty(false);
                    TupleList list = evaluateCurrentList(listCalc, evaluator);
                    final double stdev =
                        (Double) stdev(
                            evaluator, list, calc, true);
                    return stdev;
                } finally {
                    evaluator.restore(savepoint);
                }
            }

            public boolean dependsOn(Hierarchy hierarchy) {
                return anyDependsButFirst(getCalcs(), hierarchy);
            }
        };
    }
}

// End StdevPFunDef.java
