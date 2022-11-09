/*
 * This software is subject to the terms of the Eclipse Public License v1.0
 * Agreement, available at the following URL:
 * http://www.eclipse.org/legal/epl-v10.html.
 * You must accept the terms of that agreement to use this software.
 *
 * Copyright (c) 2002-2020 Hitachi Vantara..  All rights reserved.
 */

package mondrian9.olap.fun;

import java.util.ArrayList;
import java.util.List;

import mondrian9.calc.Calc;
import mondrian9.calc.DummyExp;
import mondrian9.calc.ExpCompiler;
import mondrian9.calc.IntegerCalc;
import mondrian9.calc.LevelCalc;
import mondrian9.calc.ListCalc;
import mondrian9.calc.ResultStyle;
import mondrian9.calc.TupleList;
import mondrian9.calc.impl.AbstractListCalc;
import mondrian9.calc.impl.UnaryTupleList;
import mondrian9.calc.impl.ValueCalc;
import mondrian9.mdx.ResolvedFunCall;
import mondrian9.olap.Category;
import mondrian9.olap.Evaluator;
import mondrian9.olap.Exp;
import mondrian9.olap.FunDef;
import mondrian9.olap.Hierarchy;
import mondrian9.olap.Level;
import mondrian9.olap.Member;
import mondrian9.olap.NativeEvaluator;
import mondrian9.olap.SchemaReader;
import mondrian9.olap.fun.sort.Sorter;
import mondrian9.olap.type.ScalarType;

/**
 * Definition of the <code>DrilldownLevelTop</code> and
 * <code>DrilldownLevelBottom</code> MDX builtin functions.
 *
 * <p>Syntax:
 *
 * <blockquote>
 * DrilldownLevelTop(Set_Expression, Count [, [Level_Expression][, Numeric_Expression]])<br/>
 * DrilldownLevelBottom(Set_Expression, Count [, [Level_Expression][, Numeric_Expression]])
 * </blockquote>
 *
 * @author jhyde
 * @since Oct 18, 2007
 */
class DrilldownLevelTopBottomFunDef extends FunDefBase {
  final boolean top;

  static final MultiResolver DrilldownLevelTopResolver =
    new MultiResolver(
      "DrilldownLevelTop",
      "DrilldownLevelTop(Set_Expression, Count [, [Level_Expression][, Numeric_Expression]])",
      "Drills down the topmost members of a set, at a specified level, to one level below.",
      new String[] { "fxxn", "fxxnl", "fxxnln", "fxxnen" } ) {
      protected FunDef createFunDef( Exp[] args, FunDef dummyFunDef ) {
        return new DrilldownLevelTopBottomFunDef( dummyFunDef, true );
      }
    };

  static final MultiResolver DrilldownLevelBottomResolver =
    new MultiResolver(
      "DrilldownLevelBottom",
      "DrilldownLevelBottom(Set_Expression, Count [, [Level_Expression][, Numeric_Expression]])",
      "Drills down the bottommost members of a set, at a specified level, to one level below.",
      new String[] { "fxxn", "fxxnl", "fxxnln", "fxxnen" } ) {
      protected FunDef createFunDef( Exp[] args, FunDef dummyFunDef ) {
        return new DrilldownLevelTopBottomFunDef( dummyFunDef, false );
      }
    };

  public DrilldownLevelTopBottomFunDef(
    FunDef dummyFunDef,
    final boolean top ) {
    super( dummyFunDef );
    this.top = top;
  }

  public Calc compileCall( final ResolvedFunCall call, ExpCompiler compiler ) {
    // Compile the member list expression. Ask for a mutable list, because
    // we're going to insert members into it later.
    final ListCalc listCalc =
      compiler.compileList( call.getArg( 0 ), true );
    final IntegerCalc integerCalc =
      compiler.compileInteger( call.getArg( 1 ) );
    final LevelCalc levelCalc =
      call.getArgCount() > 2
        && call.getArg( 2 ).getCategory() != Category.Empty
        ? compiler.compileLevel( call.getArg( 2 ) )
        : null;
    final Calc orderCalc =
      call.getArgCount() > 3
        ? compiler.compileScalar( call.getArg( 3 ), true )
        : new ValueCalc(
        new DummyExp(
          new ScalarType() ) );
    return new AbstractListCalc(
      call,
      new Calc[] { listCalc, integerCalc, orderCalc } ) {
      public TupleList evaluateList( Evaluator evaluator ) {
        // Use a native evaluator, if more efficient.
        // TODO: Figure this out at compile time.
        SchemaReader schemaReader = evaluator.getSchemaReader();
        NativeEvaluator nativeEvaluator =
          schemaReader.getNativeSetEvaluator(
            call.getFunDef(), call.getArgs(), evaluator, this );
        if ( nativeEvaluator != null ) {
          return
            (TupleList) nativeEvaluator.execute( ResultStyle.LIST );
        }

        TupleList list = listCalc.evaluateList( evaluator );
        int n = integerCalc.evaluateInteger( evaluator );
        if ( n == FunUtil.IntegerNull || n <= 0 ) {
          return list;
        }
        Level level;
        if ( levelCalc == null ) {
          level = null;
        } else {
          level = levelCalc.evaluateLevel( evaluator );
        }
        List<Member> result = new ArrayList<Member>();
        assert list.getArity() == 1;
        for ( Member member : list.slice( 0 ) ) {
          result.add( member );
          if ( level != null && member.getLevel() != level ) {
            if ( level.getDimension() != member.getDimension() ) {
              throw newEvalException(
                DrilldownLevelTopBottomFunDef.this,
                "Level '"
                  + level.getUniqueName()
                  + "' not compatible with member '"
                  + member.getUniqueName()
                  + "'" );
            }
            continue;
          }
          List<Member> children =
            schemaReader.getMemberChildren( member );
          final int savepoint = evaluator.savepoint();
          List<Member> sortedChildren;
          try {
            evaluator.setNonEmpty( false );
            sortedChildren =
              Sorter.sortMembers(
                evaluator,
                children,
                children,
                orderCalc,
                top,
                true );
          } finally {
            evaluator.restore( savepoint );
          }
          int x = Math.min( n, sortedChildren.size() );
          for ( int i = 0; i < x; i++ ) {
            result.add( sortedChildren.get( i ) );
          }
        }
        return new UnaryTupleList( result );
      }

      public boolean dependsOn( Hierarchy hierarchy ) {
        return anyDependsButFirst( getCalcs(), hierarchy );
      }
    };
  }
}

// End DrilldownLevelTopBottomFunDef.java
