// 
// Decompiled by Procyon v0.5.36
// 

package mondrian9.olap;

import java.util.ArrayList;
import mondrian9.mdx.UnresolvedFunCall;
import mondrian9.olap.Id.NameSegment;
import mondrian9.resource.MondrianResource;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java_cup.runtime.Symbol;
import java.util.Stack;
import java_cup.runtime.lr_parser;

class CUP$Parser$actions
{
    private final Parser parser;
    
    CUP$Parser$actions(final Parser parser) {
        this.parser = parser;
    }
    
    public final Symbol CUP$Parser$do_action(final int CUP$Parser$act_num, final lr_parser CUP$Parser$parser, final Stack CUP$Parser$stack, final int CUP$Parser$top) throws Exception {
        switch (CUP$Parser$act_num) {
            case 146: {
                QueryPart RESULT = null;
                final int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final QueryPart s = (QueryPart)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT = this.parser.factory.makeExplain(s);
                final Symbol CUP$Parser$result = new Symbol(23, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 145: {
                QueryPart RESULT = null;
                RESULT = (QueryPart)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(21, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 144: {
                QueryPart RESULT = null;
                RESULT = (Query)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(21, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 143: {
                Exp RESULT2 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id i = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(14, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 142: {
                List RESULT3 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp j = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int listleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int listright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List list = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                list.add(0, j);
                RESULT3 = list;
                final Symbol CUP$Parser$result = new Symbol(60, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 141: {
                List RESULT3 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp j = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = new LinkedList();
                RESULT3.add(j);
                final Symbol CUP$Parser$result = new Symbol(60, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 140: {
                List RESULT3 = null;
                RESULT3 = null;
                final Symbol CUP$Parser$result = new Symbol(59, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 139: {
                List RESULT3 = null;
                final int rlleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int rlright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List rl = RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(59, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 138: {
                Number RESULT4 = null;
                RESULT4 = null;
                final Symbol CUP$Parser$result = new Symbol(66, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT4);
                return CUP$Parser$result;
            }
            case 137: {
                Number RESULT4 = null;
                final int nleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int nright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final BigDecimal n = (BigDecimal)(RESULT4 = (BigDecimal)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value);
                final Symbol CUP$Parser$result = new Symbol(66, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT4);
                return CUP$Parser$result;
            }
            case 136: {
                Number RESULT4 = null;
                RESULT4 = null;
                final Symbol CUP$Parser$result = new Symbol(65, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT4);
                return CUP$Parser$result;
            }
            case 135: {
                Number RESULT4 = null;
                final int nleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int nright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final BigDecimal n = (BigDecimal)(RESULT4 = (BigDecimal)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value);
                final Symbol CUP$Parser$result = new Symbol(65, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT4);
                return CUP$Parser$result;
            }
            case 134: {
                QueryPart RESULT = null;
                final int mleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int mright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Number m = (Number)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int fleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int fright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Number f = (Number)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int sleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int sright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final Query s2 = (Query)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final int rleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int rright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List r = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT = this.parser.factory.makeDrillThrough(s2, (m == null) ? 0 : m.intValue(), (f == null) ? 0 : f.intValue(), r);
                final Symbol CUP$Parser$result = new Symbol(22, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 133: {
                Id RESULT5 = null;
                RESULT5 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(25, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 132: {
                List RESULT3 = null;
                final int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Id p = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int p1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int p1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List p2 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                p2.add(0, new CellProperty(p.getSegments()));
                RESULT3 = p2;
                final Symbol CUP$Parser$result = new Symbol(58, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 131: {
                List RESULT3 = null;
                final int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id p = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = new LinkedList();
                RESULT3.add(new CellProperty(p.getSegments()));
                final Symbol CUP$Parser$result = new Symbol(58, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 130: {
                Object RESULT6 = null;
                RESULT6 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(42, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, RESULT6);
                return CUP$Parser$result;
            }
            case 129: {
                final Object RESULT6 = null;
                final Symbol CUP$Parser$result = new Symbol(42, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, RESULT6);
                return CUP$Parser$result;
            }
            case 128: {
                List RESULT3 = null;
                final int p1left2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int p1right2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List p3 = RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(48, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 127: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 126: {
                Id RESULT5 = null;
                RESULT5 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(28, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 125: {
                Object RESULT6 = null;
                RESULT6 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(44, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, RESULT6);
                return CUP$Parser$result;
            }
            case 124: {
                List RESULT3 = null;
                final int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Object p4 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int plleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int plright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List pl = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                pl.add(0, p4);
                RESULT3 = pl;
                final Symbol CUP$Parser$result = new Symbol(57, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 123: {
                List RESULT3 = null;
                final int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Object p4 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = new LinkedList();
                RESULT3.add(p4);
                final Symbol CUP$Parser$result = new Symbol(57, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 122: {
                Object RESULT6 = null;
                RESULT6 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(43, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, RESULT6);
                return CUP$Parser$result;
            }
            case 121: {
                final Object RESULT6 = null;
                final Symbol CUP$Parser$result = new Symbol(43, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, RESULT6);
                return CUP$Parser$result;
            }
            case 120: {
                List RESULT3 = null;
                final int plleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int plright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List pl2 = RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(51, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 119: {
                BigDecimal RESULT7 = null;
                final int nleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int nright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final BigDecimal n = RESULT7 = (BigDecimal)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final Symbol CUP$Parser$result = new Symbol(64, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT7);
                return CUP$Parser$result;
            }
            case 118: {
                BigDecimal RESULT7 = null;
                RESULT7 = (BigDecimal)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(64, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT7);
                return CUP$Parser$result;
            }
            case 117: {
                AxisOrdinal.StandardAxisOrdinal RESULT8 = null;
                RESULT8 = AxisOrdinal.StandardAxisOrdinal.CHAPTERS;
                final Symbol CUP$Parser$result = new Symbol(31, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT8);
                return CUP$Parser$result;
            }
            case 116: {
                AxisOrdinal.StandardAxisOrdinal RESULT8 = null;
                RESULT8 = AxisOrdinal.StandardAxisOrdinal.SECTIONS;
                final Symbol CUP$Parser$result = new Symbol(31, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT8);
                return CUP$Parser$result;
            }
            case 115: {
                AxisOrdinal.StandardAxisOrdinal RESULT8 = null;
                RESULT8 = AxisOrdinal.StandardAxisOrdinal.PAGES;
                final Symbol CUP$Parser$result = new Symbol(31, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT8);
                return CUP$Parser$result;
            }
            case 114: {
                AxisOrdinal.StandardAxisOrdinal RESULT8 = null;
                RESULT8 = AxisOrdinal.StandardAxisOrdinal.ROWS;
                final Symbol CUP$Parser$result = new Symbol(31, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT8);
                return CUP$Parser$result;
            }
            case 113: {
                AxisOrdinal.StandardAxisOrdinal RESULT8 = null;
                RESULT8 = AxisOrdinal.StandardAxisOrdinal.COLUMNS;
                final Symbol CUP$Parser$result = new Symbol(31, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT8);
                return CUP$Parser$result;
            }
            case 112: {
                List RESULT3 = null;
                RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(52, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 111: {
                final List RESULT3 = null;
                final Symbol CUP$Parser$result = new Symbol(52, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 110: {
                Boolean RESULT9 = null;
                RESULT9 = Boolean.TRUE;
                final Symbol CUP$Parser$result = new Symbol(45, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT9);
                return CUP$Parser$result;
            }
            case 109: {
                Boolean RESULT9 = null;
                RESULT9 = Boolean.FALSE;
                final Symbol CUP$Parser$result = new Symbol(45, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT9);
                return CUP$Parser$result;
            }
            case 108: {
                QueryAxis RESULT10 = null;
                final int bleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left;
                final int bright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).right;
                final Boolean b = (Boolean)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).value;
                final int sleft3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int sright3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Exp s3 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int dpleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int dpright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final List dp = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int nleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int nright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final BigDecimal n2 = (BigDecimal)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final double d = n2.doubleValue();
                final int index = n2.intValue();
                if (index < 0 || index != d) {
                    throw MondrianResource.instance().InvalidAxis.ex(d);
                }
                final AxisOrdinal axis = AxisOrdinal.StandardAxisOrdinal.forLogicalOrdinal(index);
                RESULT10 = new QueryAxis(b, s3, axis, QueryAxis.SubtotalVisibility.Undefined, Parser.toIdArray(dp));
                final Symbol CUP$Parser$result = new Symbol(1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT10);
                return CUP$Parser$result;
            }
            case 107: {
                QueryAxis RESULT10 = null;
                final int bleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left;
                final int bright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).right;
                final Boolean b = (Boolean)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).value;
                final int sleft3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int sright3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Exp s3 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int dpleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int dpright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final List dp = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final AxisOrdinal.StandardAxisOrdinal a = (AxisOrdinal.StandardAxisOrdinal)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT10 = new QueryAxis(b, s3, a, QueryAxis.SubtotalVisibility.Undefined, Parser.toIdArray(dp));
                final Symbol CUP$Parser$result = new Symbol(1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT10);
                return CUP$Parser$result;
            }
            case 106: {
                Id RESULT5 = null;
                RESULT5 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(30, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 105: {
                Formula RESULT11 = null;
                final int nleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int nright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Id n3 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp e = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT11 = new Formula(n3, e);
                final Symbol CUP$Parser$result = new Symbol(39, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT11);
                return CUP$Parser$result;
            }
            case 104: {
                Formula RESULT11 = null;
                final int nleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int nright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Id n3 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int sleft3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int sright3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final String s4 = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Exp e2 = this.parser.recursivelyParseExp(s4);
                RESULT11 = new Formula(n3, e2);
                final Symbol CUP$Parser$result = new Symbol(39, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT11);
                return CUP$Parser$result;
            }
            case 103: {
                MemberProperty RESULT12 = null;
                final int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Id.NameSegment id = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp e = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT12 = new MemberProperty(id.name, e);
                final Symbol CUP$Parser$result = new Symbol(41, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT12);
                return CUP$Parser$result;
            }
            case 102: {
                Id RESULT5 = null;
                RESULT5 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(29, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 101: {
                List RESULT3 = null;
                final int hdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int hdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final MemberProperty hd = (MemberProperty)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int tlleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int tlright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List tl = RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3.add(0, hd);
                final Symbol CUP$Parser$result = new Symbol(56, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 100: {
                List RESULT3 = null;
                final int mleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int mright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final MemberProperty k = (MemberProperty)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = new LinkedList();
                RESULT3.add(k);
                final Symbol CUP$Parser$result = new Symbol(56, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 99: {
                List RESULT3 = null;
                final int lleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int lright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List l = RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(50, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 98: {
                List RESULT3 = null;
                RESULT3 = new LinkedList();
                final Symbol CUP$Parser$result = new Symbol(50, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 97: {
                Formula RESULT11 = null;
                final int mleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int mright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Id m2 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final Exp e = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final int lleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int lright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List l2 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT11 = new Formula(m2, e, Parser.toMemberPropertyArray(l2));
                final Symbol CUP$Parser$result = new Symbol(38, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT11);
                return CUP$Parser$result;
            }
            case 96: {
                Formula RESULT11 = null;
                final int mleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int mright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Id m2 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int sleft3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int sright3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final String s4 = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final int lleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int lright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List l2 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Exp e3 = this.parser.recursivelyParseExp(s4);
                RESULT11 = new Formula(m2, e3, Parser.toMemberPropertyArray(l2));
                final Symbol CUP$Parser$result = new Symbol(38, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT11);
                return CUP$Parser$result;
            }
            case 95: {
                Formula RESULT11 = null;
                RESULT11 = (Formula)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(40, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT11);
                return CUP$Parser$result;
            }
            case 94: {
                Formula RESULT11 = null;
                RESULT11 = (Formula)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(40, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT11);
                return CUP$Parser$result;
            }
            case 93: {
                List RESULT3 = null;
                final int hdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int hdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final Formula hd2 = (Formula)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final int tlleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int tlright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List tl = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                tl.add(0, hd2);
                RESULT3 = tl;
                final Symbol CUP$Parser$result = new Symbol(55, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 92: {
                List RESULT3 = null;
                final int eleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int eright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Formula e4 = (Formula)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = new LinkedList();
                RESULT3.add(e4);
                final Symbol CUP$Parser$result = new Symbol(55, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 91: {
                List RESULT3 = null;
                RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(49, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 90: {
                List RESULT3 = null;
                RESULT3 = new LinkedList();
                final Symbol CUP$Parser$result = new Symbol(49, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 89: {
                Exp RESULT2 = null;
                final int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp s5 = RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(18, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 88: {
                final Exp RESULT2 = null;
                final Symbol CUP$Parser$result = new Symbol(18, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 87: {
                List RESULT3 = null;
                final int eleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int eright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final QueryAxis e5 = (QueryAxis)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int listleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int listright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List list = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                list.add(0, e5);
                RESULT3 = list;
                final Symbol CUP$Parser$result = new Symbol(46, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 86: {
                List RESULT3 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final QueryAxis i2 = (QueryAxis)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = new LinkedList();
                RESULT3.add(i2);
                final Symbol CUP$Parser$result = new Symbol(46, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 85: {
                List RESULT3 = null;
                RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(47, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 84: {
                List RESULT3 = null;
                RESULT3 = new LinkedList();
                final Symbol CUP$Parser$result = new Symbol(47, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 83: {
                List RESULT3 = null;
                final int fleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int fright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List f2 = RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(62, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 82: {
                List RESULT3 = null;
                RESULT3 = new LinkedList();
                final Symbol CUP$Parser$result = new Symbol(62, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 81: {
                Query RESULT13 = null;
                final int fleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 6)).left;
                final int fright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 6)).right;
                final List f2 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 6)).value;
                final int aleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left;
                final int aright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).right;
                final List a2 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).value;
                final int cleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int cright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Id c = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int wleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int wright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final Exp w = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final int cpleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int cpright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List cp = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Parser parser = (Parser)CUP$Parser$parser;
                final String cubeName = ((Id.NameSegment)c.getElement(0)).name;
                RESULT13 = parser.factory.makeQuery(parser.statement, Parser.toFormulaArray(f2), Parser.toQueryAxisArray(a2), cubeName, w, Parser.toQueryPartArray(cp), parser.strictValidation);
                final Symbol CUP$Parser$result = new Symbol(19, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 6)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT13);
                return CUP$Parser$result;
            }
            case 80: {
                QueryPart RESULT = null;
                final int eleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int eright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp e6 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT = (QueryPart)e6;
                final Symbol CUP$Parser$result = new Symbol(20, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 79: {
                QueryPart RESULT = null;
                RESULT = (QueryPart)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(20, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 78: {
                QueryPart RESULT = null;
                RESULT = (QueryPart)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(20, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 77: {
                QueryPart RESULT = null;
                RESULT = (Query)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(20, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT);
                return CUP$Parser$result;
            }
            case 76: {
                UnresolvedFunCall RESULT14 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int ileft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment i3 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Id id2 = new Id(i3);
                RESULT14 = new UnresolvedFunCall("AS", Syntax.Infix, new Exp[] { x, id2 });
                final Symbol CUP$Parser$result = new Symbol(2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT14);
                return CUP$Parser$result;
            }
            case 75: {
                List RESULT3 = null;
                final int eleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int eright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp e6 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int listleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int listright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final List list = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                list.add(0, e6);
                RESULT3 = list;
                final Symbol CUP$Parser$result = new Symbol(53, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 74: {
                List RESULT3 = null;
                final int eleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int eright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp e6 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = new LinkedList();
                RESULT3.add(e6);
                final Symbol CUP$Parser$result = new Symbol(53, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 73: {
                List RESULT3 = null;
                RESULT3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(54, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 72: {
                List RESULT3 = null;
                RESULT3 = new LinkedList();
                final Symbol CUP$Parser$result = new Symbol(54, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 71: {
                Exp RESULT2 = null;
                RESULT2 = new UnresolvedFunCall("", Syntax.Empty, new Exp[0]);
                final Symbol CUP$Parser$result = new Symbol(6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 70: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 69: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 68: {
                Exp RESULT2 = null;
                RESULT2 = (UnresolvedFunCall)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 67: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall(":", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 66: {
                String RESULT15 = null;
                RESULT15 = ">=";
                final Symbol CUP$Parser$result = new Symbol(32, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 65: {
                String RESULT15 = null;
                RESULT15 = "<=";
                final Symbol CUP$Parser$result = new Symbol(32, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 64: {
                String RESULT15 = null;
                RESULT15 = ">";
                final Symbol CUP$Parser$result = new Symbol(32, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 63: {
                String RESULT15 = null;
                RESULT15 = "<";
                final Symbol CUP$Parser$result = new Symbol(32, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 62: {
                String RESULT15 = null;
                RESULT15 = "<>";
                final Symbol CUP$Parser$result = new Symbol(32, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 61: {
                String RESULT15 = null;
                RESULT15 = "=";
                final Symbol CUP$Parser$result = new Symbol(32, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 60: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp x = RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 59: {
                final Exp RESULT2 = null;
                final Symbol CUP$Parser$result = new Symbol(4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 58: {
                Exp[] RESULT16 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT16 = new Exp[] { x, y };
                final Symbol CUP$Parser$result = new Symbol(63, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT16);
                return CUP$Parser$result;
            }
            case 57: {
                List RESULT3 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final List x2 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp[] y2 = (Exp[])((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT3 = x2;
                x2.add(y2);
                final Symbol CUP$Parser$result = new Symbol(61, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 56: {
                List RESULT3 = null;
                RESULT3 = new ArrayList();
                final Symbol CUP$Parser$result = new Symbol(61, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT3);
                return CUP$Parser$result;
            }
            case 55: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 54: {
                final Exp RESULT2 = null;
                final Symbol CUP$Parser$result = new Symbol(16, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 53: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final List y3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int zleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int zright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final Exp z = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final List v = new ArrayList();
                if (x != null) {
                    v.add(x);
                }
                for (int i4 = 0; i4 < y3.size(); ++i4) {
                    final Exp[] exps = (Exp[]) y3.get(i4);
                    Util.assertTrue(exps.length == 2);
                    v.add(exps[0]);
                    v.add(exps[1]);
                }
                if (z != null) {
                    v.add(z);
                }
                if (x == null) {
                    RESULT2 = new UnresolvedFunCall("_CaseTest", Syntax.Case, Parser.toExpArray(v));
                }
                else {
                    RESULT2 = new UnresolvedFunCall("_CaseMatch", Syntax.Case, Parser.toExpArray(v));
                }
                final Symbol CUP$Parser$result = new Symbol(3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 4)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 52: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 51: {
                Exp RESULT2 = null;
                RESULT2 = Literal.nullValue;
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 50: {
                Exp RESULT2 = null;
                final int lisleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int lisright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final List lis = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                RESULT2 = new UnresolvedFunCall("{}", Syntax.Braces, Parser.toExpArray(lis));
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 49: {
                Exp RESULT2 = null;
                final int lisleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int lisright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final List lis = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                RESULT2 = new UnresolvedFunCall("()", Syntax.Parentheses, Parser.toExpArray(lis));
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 48: {
                Exp RESULT2 = null;
                final int aeleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int aeright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final UnresolvedFunCall ae = (UnresolvedFunCall)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                assert ae.getArgCount() == 2;
                final Exp e7 = ae.getArg(0);
                final Id.NameSegment t = (NameSegment) ((Id)ae.getArg(1)).getSegments().get(0);
                RESULT2 = new UnresolvedFunCall("CAST", Syntax.Cast, new Exp[] { e7, Literal.createSymbol(t.name) });
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 47: {
                Exp RESULT2 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Id i = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int lisleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int lisright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final List lis2 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                RESULT2 = new UnresolvedFunCall(Util.last((List<Id.Segment>)i.getSegments()).toString(), Syntax.Function, Parser.toExpArray(lis2));
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 46: {
                Exp RESULT2 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 5)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 5)).right;
                final Exp j = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 5)).value;
                final int jleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int jright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Id.NameSegment j2 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int lisleft3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int lisright3 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final List lis3 = (List)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                lis3.add(0, j);
                RESULT2 = new UnresolvedFunCall(j2.name, Syntax.Method, Parser.toExpArray(lis3));
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 5)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 45: {
                Exp RESULT2 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp j = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int jleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int jright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment j2 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                if (j instanceof Id) {
                    RESULT2 = ((Id)j).append(j2);
                }
                else {
                    RESULT2 = new UnresolvedFunCall(j2.name, Syntax.AmpersandQuotedProperty, new Exp[] { j });
                }
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 44: {
                Exp RESULT2 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp j = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int jleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int jright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment j2 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                if (j instanceof Id) {
                    RESULT2 = ((Id)j).append(j2);
                }
                else {
                    RESULT2 = new UnresolvedFunCall(j2.name, Syntax.QuotedProperty, new Exp[] { j });
                }
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 43: {
                Exp RESULT2 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp j = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int jleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int jright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment j2 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                if (j instanceof Id && !this.parser.isFunCall(j2.name)) {
                    RESULT2 = ((Id)j).append(j2);
                }
                else {
                    RESULT2 = new UnresolvedFunCall(j2.name, Syntax.Property, new Exp[] { j });
                }
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 42: {
                Exp RESULT2 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment i5 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new Id(i5);
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 41: {
                Exp RESULT2 = null;
                final int dleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int dright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final BigDecimal d2 = (BigDecimal)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = Literal.create(d2);
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 40: {
                Exp RESULT2 = null;
                final int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final String s6 = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = Literal.createString(s6);
                final Symbol CUP$Parser$result = new Symbol(17, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 39: {
                Exp RESULT2 = null;
                final int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp p5 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("-", Syntax.Prefix, new Exp[] { p5 });
                final Symbol CUP$Parser$result = new Symbol(7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 38: {
                Exp RESULT2 = null;
                final int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp p5 = RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                final Symbol CUP$Parser$result = new Symbol(7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 37: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(7, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 36: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("/", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 35: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("*", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 34: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 33: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("||", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 32: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("-", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 31: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("+", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 30: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(10, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 29: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("NOT", Syntax.Prefix, new Exp[] { new UnresolvedFunCall("IN", Syntax.Infix, new Exp[] { x, y }) });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 28: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("IN", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 27: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("NOT", Syntax.Prefix, new Exp[] { new UnresolvedFunCall("MATCHES", Syntax.Infix, new Exp[] { x, y }) });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 3)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 26: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("MATCHES", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 25: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                RESULT2 = new UnresolvedFunCall("IS EMPTY", Syntax.Postfix, new Exp[] { x });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 24: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("IS", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 23: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                RESULT2 = new UnresolvedFunCall("IS NULL", Syntax.Postfix, new Exp[] { x });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 22: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int opleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int opright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final String op = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value;
                final int yleft2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright2 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y4 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall(op, Syntax.Infix, new Exp[] { x, y4 });
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 21: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(11, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 20: {
                Exp RESULT2 = null;
                final int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp p5 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("NOT", Syntax.Prefix, new Exp[] { p5 });
                final Symbol CUP$Parser$result = new Symbol(12, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 19: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(12, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 18: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("AND", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(13, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 17: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(13, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 16: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("XOR", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 15: {
                Exp RESULT2 = null;
                final int xleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int xright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Exp x = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int yleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int yright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Exp y = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT2 = new UnresolvedFunCall("OR", Syntax.Infix, new Exp[] { x, y });
                final Symbol CUP$Parser$result = new Symbol(15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 14: {
                Exp RESULT2 = null;
                RESULT2 = (Exp)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT2);
                return CUP$Parser$result;
            }
            case 13: {
                Id RESULT5 = null;
                RESULT5 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(27, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 12: {
                Id RESULT5 = null;
                final int hdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int hdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Id hd3 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int tlleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int tlright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment tl2 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT5 = hd3.append(tl2);
                final Symbol CUP$Parser$result = new Symbol(24, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 11: {
                Id RESULT5 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment i5 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT5 = new Id(i5);
                final Symbol CUP$Parser$result = new Symbol(24, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 10: {
                Id RESULT5 = null;
                final int hdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left;
                final int hdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).right;
                final Id hd3 = (Id)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).value;
                final int tlleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int tlright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment tl2 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT5 = hd3.append(tl2);
                final Symbol CUP$Parser$result = new Symbol(26, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 2)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 9: {
                Id RESULT5 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final Id.NameSegment i5 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT5 = new Id(i5);
                final Symbol CUP$Parser$result = new Symbol(26, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT5);
                return CUP$Parser$result;
            }
            case 8: {
                String RESULT15 = null;
                RESULT15 = "Properties";
                final Symbol CUP$Parser$result = new Symbol(33, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 7: {
                String RESULT15 = null;
                RESULT15 = "Dimension";
                final Symbol CUP$Parser$result = new Symbol(33, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT15);
                return CUP$Parser$result;
            }
            case 6: {
                Id.NameSegment RESULT17 = null;
                RESULT17 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(34, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT17);
                return CUP$Parser$result;
            }
            case 5: {
                Id.NameSegment RESULT17 = null;
                RESULT17 = (Id.NameSegment)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top)).value;
                final Symbol CUP$Parser$result = new Symbol(34, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT17);
                return CUP$Parser$result;
            }
            case 4: {
                Id.NameSegment RESULT17 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final String i6 = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT17 = new Id.NameSegment(i6, Id.Quoting.UNQUOTED);
                final Symbol CUP$Parser$result = new Symbol(36, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT17);
                return CUP$Parser$result;
            }
            case 3: {
                Id.NameSegment RESULT17 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final String i6 = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT17 = new Id.NameSegment(i6, Id.Quoting.UNQUOTED);
                final Symbol CUP$Parser$result = new Symbol(36, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT17);
                return CUP$Parser$result;
            }
            case 2: {
                Id.NameSegment RESULT17 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final String i6 = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT17 = new Id.NameSegment(i6, Id.Quoting.KEY);
                final Symbol CUP$Parser$result = new Symbol(37, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT17);
                return CUP$Parser$result;
            }
            case 1: {
                Id.NameSegment RESULT17 = null;
                final int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left;
                final int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right;
                final String i6 = (String)((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).value;
                RESULT17 = new Id.NameSegment(i6, Id.Quoting.QUOTED);
                final Symbol CUP$Parser$result = new Symbol(35, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, (Object)RESULT17);
                return CUP$Parser$result;
            }
            case 0: {
                Object RESULT6 = null;
                final int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left;
                final int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).right;
                final QueryPart start_val = (QueryPart)(RESULT6 = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).value);
                final Symbol CUP$Parser$result = new Symbol(0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 1)).left, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top - 0)).right, RESULT6);
                CUP$Parser$parser.done_parsing();
                return CUP$Parser$result;
            }
            default: {
                throw new Exception("Invalid action number found in internal parse table");
            }
        }
    }
}
