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
 * Definition of the <code>Var</code> MDX builtin function
 * (and its synonym <code>Variance</code>).
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
class VarFunDef extends AbstractAggregateFunDef {
    static final Resolver VarResolver =
        new ReflectiveMultiResolver(
            "Var",
            "Var(<Set>[, <Numeric Expression>])",
            "Returns the variance of a numeric expression evaluated over a set (unbiased).",
            new String[]{"fnx", "fnxn"},
            VarFunDef.class);

    static final Resolver VarianceResolver =
        new ReflectiveMultiResolver(
            "Variance",
            "Variance(<Set>[, <Numeric Expression>])",
            "Alias for Var.",
            new String[]{"fnx", "fnxn"},
            VarFunDef.class);

    public VarFunDef(FunDef dummyFunDef) {
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
                    final double var =
                        (Double) var(
                            evaluator, list, calc, false);
                    return var;
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

// End VarFunDef.java
