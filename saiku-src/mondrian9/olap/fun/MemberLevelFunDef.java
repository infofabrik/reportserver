/*
* This software is subject to the terms of the Eclipse Public License v1.0
* Agreement, available at the following URL:
* http://www.eclipse.org/legal/epl-v10.html.
* You must accept the terms of that agreement to use this software.
*
* Copyright (c) 2002-2017 Hitachi Vantara..  All rights reserved.
*/

package mondrian9.olap.fun;

import mondrian9.calc.*;
import mondrian9.calc.impl.AbstractLevelCalc;
import mondrian9.mdx.ResolvedFunCall;
import mondrian9.olap.*;
import mondrian9.olap.type.LevelType;
import mondrian9.olap.type.Type;

/**
 * Definition of the <code>&lt;Member&gt;.Level</code> MDX builtin function.
 *
 * @author jhyde
 * @since Mar 23, 2006
 */
public class MemberLevelFunDef extends FunDefBase {
    static final MemberLevelFunDef instance = new MemberLevelFunDef();

    private MemberLevelFunDef() {
        super("Level", "Returns a member's level.", "plm");
    }

    public Type getResultType(Validator validator, Exp[] args) {
        final Type argType = args[0].getType();
        return LevelType.forType(argType);
    }

    public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler) {
        final MemberCalc memberCalc =
                compiler.compileMember(call.getArg(0));
        return new CalcImpl(call, memberCalc);
    }

    public static class CalcImpl extends AbstractLevelCalc {
        private final MemberCalc memberCalc;

        public CalcImpl(Exp exp, MemberCalc memberCalc) {
            super(exp, new Calc[] {memberCalc});
            this.memberCalc = memberCalc;
        }

        public Level evaluateLevel(Evaluator evaluator) {
            Member member = memberCalc.evaluateMember(evaluator);
            return member.getLevel();
        }
    }
}

// End MemberLevelFunDef.java
