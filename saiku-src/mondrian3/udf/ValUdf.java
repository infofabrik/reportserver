/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2006-2006 Pentaho and others
// All Rights Reserved.
*/
package mondrian3.udf;

import mondrian3.olap.Evaluator;
import mondrian3.olap.Syntax;
import mondrian3.olap.type.NumericType;
import mondrian3.olap.type.Type;
import mondrian3.spi.UserDefinedFunction;

/**
 * VB function <code>Val</code>
 *
 * @author Gang Chen
 */
public class ValUdf implements UserDefinedFunction {

    public Object execute(Evaluator evaluator, Argument[] arguments) {
        Object arg = arguments[0].evaluateScalar(evaluator);

        if (arg instanceof Number) {
            return new Double(((Number) arg).doubleValue());
        } else {
            return new Double(0.0);
        }
    }

    public String getDescription() {
        return "VB function Val";
    }

    public String getName() {
        return "Val";
    }

    public Type[] getParameterTypes() {
        return new Type[] { new NumericType() };
    }

    public String[] getReservedWords() {
        return null;
    }

    public Type getReturnType(Type[] parameterTypes) {
        return new NumericType();
    }

    public Syntax getSyntax() {
        return Syntax.Function;
    }

}

// End ValUdf.java
