// 
// Decompiled by Procyon v0.5.36
// 

package mondrian8.parser;

import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import mondrian8.resource.MondrianResource;
import mondrian8.olap.AxisOrdinal;
import mondrian8.olap.CellProperty;
import mondrian8.olap.Query;
import java.util.LinkedList;
import java.math.BigDecimal;
import mondrian8.olap.Literal;
import java.util.Collections;
import java.util.ArrayList;
import mondrian8.mdx.UnresolvedFunCall;
import mondrian8.olap.Syntax;
import mondrian8.olap.Util;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import mondrian8.olap.QueryAxis;
import mondrian8.olap.QueryPart;
import mondrian8.olap.Id;
import mondrian8.olap.Formula;
import mondrian8.olap.Exp;
import mondrian8.olap.MemberProperty;
import mondrian8.olap.FunTable;
import mondrian8.server.Statement;

public class MdxParserImpl implements MdxParserImplConstants
{
    private MdxParserValidator.QueryPartFactory factory;
    private Statement statement;
    private FunTable funTable;
    private boolean strictValidation;
    private static final MemberProperty[] EmptyMemberPropertyArray;
    private static final Exp[] EmptyExpArray;
    private static final Formula[] EmptyFormulaArray;
    private static final Id[] EmptyIdArray;
    private static final QueryPart[] EmptyQueryPartArray;
    private static final QueryAxis[] EmptyQueryAxisArray;
    private static final String DQ = "\"";
    private static final String DQDQ = "\"\"";
    public MdxParserImplTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private Token jj_scanpos;
    private Token jj_lastpos;
    private int jj_la;
    private int jj_gen;
    private final int[] jj_la1;
    private static int[] jj_la1_0;
    private static int[] jj_la1_1;
    private static int[] jj_la1_2;
    private final JJCalls[] jj_2_rtns;
    private boolean jj_rescan;
    private int jj_gc;
    private final LookaheadSuccess jj_ls;
    private List<int[]> jj_expentries;
    private int[] jj_expentry;
    private int jj_kind;
    private int[] jj_lasttokens;
    private int jj_endpos;
    
    public MdxParserImpl(final MdxParserValidator.QueryPartFactory factory, final Statement statement, final String queryString, final boolean debug, final FunTable funTable, final boolean strictValidation) {
        this(new StringReader(term(queryString)));
        this.factory = factory;
        this.statement = statement;
        this.funTable = funTable;
        this.strictValidation = strictValidation;
    }
    
    private static String term(final String s) {
        return s.endsWith("\n") ? s : (s + "\n");
    }
    
    public void setTabSize(final int tabSize) {
        this.jj_input_stream.setTabSize(tabSize);
    }
    
    Exp recursivelyParseExp(final String s) throws ParseException {
        final MdxParserImpl parser = new MdxParserImpl(this.factory, this.statement, s, false, this.funTable, this.strictValidation);
        return parser.expression();
    }
    
    static Id[] toIdArray(final List<Id> idList) {
        if (idList == null || idList.size() == 0) {
            return MdxParserImpl.EmptyIdArray;
        }
        return idList.toArray(new Id[idList.size()]);
    }
    
    static Exp[] toExpArray(final List<Exp> expList) {
        if (expList == null || expList.size() == 0) {
            return MdxParserImpl.EmptyExpArray;
        }
        return expList.toArray(new Exp[expList.size()]);
    }
    
    static Formula[] toFormulaArray(final List<Formula> formulaList) {
        if (formulaList == null || formulaList.size() == 0) {
            return MdxParserImpl.EmptyFormulaArray;
        }
        return formulaList.toArray(new Formula[formulaList.size()]);
    }
    
    static MemberProperty[] toMemberPropertyArray(final List<MemberProperty> mpList) {
        if (mpList == null || mpList.size() == 0) {
            return MdxParserImpl.EmptyMemberPropertyArray;
        }
        return mpList.toArray(new MemberProperty[mpList.size()]);
    }
    
    static QueryPart[] toQueryPartArray(final List<QueryPart> qpList) {
        if (qpList == null || qpList.size() == 0) {
            return MdxParserImpl.EmptyQueryPartArray;
        }
        return qpList.toArray(new QueryPart[qpList.size()]);
    }
    
    static QueryAxis[] toQueryAxisArray(final List<QueryAxis> qpList) {
        if (qpList == null || qpList.size() == 0) {
            return MdxParserImpl.EmptyQueryAxisArray;
        }
        return qpList.toArray(new QueryAxis[qpList.size()]);
    }
    
    private static String stripQuotes(String s, final String prefix, final String suffix, final String quoted) {
        assert s.startsWith(prefix) && s.endsWith(suffix);
        s = s.substring(prefix.length(), s.length() - suffix.length());
        s = Util.replace(s, quoted, suffix);
        return s;
    }
    
    private Exp createCall(final Exp left, final Id.Segment segment, final List<Exp> argList) {
        final String name = (segment instanceof Id.NameSegment) ? ((Id.NameSegment)segment).name : null;
        if (argList != null) {
            if (left != null) {
                argList.add(0, left);
                return new UnresolvedFunCall(name, Syntax.Method, toExpArray(argList));
            }
            return new UnresolvedFunCall(name, Syntax.Function, toExpArray(argList));
        }
        else {
            boolean call = false;
            Syntax syntax = null;
            switch (segment.quoting) {
                case UNQUOTED: {
                    syntax = Syntax.Property;
                    call = this.funTable.isProperty(name);
                    break;
                }
                case QUOTED: {
                    syntax = Syntax.QuotedProperty;
                    break;
                }
                default: {
                    syntax = Syntax.AmpersandQuotedProperty;
                    break;
                }
            }
            if (left instanceof Id && !call) {
                return ((Id)left).append(segment);
            }
            if (left == null) {
                return new Id(segment);
            }
            return new UnresolvedFunCall(name, syntax, new Exp[] { left });
        }
    }
    
    public final QueryPart statementEof() throws ParseException {
        final QueryPart qp = this.statement();
        this.jj_consume_token(0);
        return qp;
    }
    
    public final Exp expressionEof() throws ParseException {
        final Exp e = this.expression();
        this.jj_consume_token(0);
        return e;
    }
    
    public final Id.Segment identifier() throws ParseException {
        Id.Segment segment = null;
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 9:
            case 30:
            case 81:
            case 82: {
                segment = this.nameSegment();
                break;
            }
            case 83:
            case 84: {
                segment = this.keyIdentifier();
                break;
            }
            default: {
                this.jj_la1[0] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        return segment;
    }
    
    public final Id.NameSegment nameSegment() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 9:
            case 30: {
                final String id = this.keyword();
                return new Id.NameSegment(id, Id.Quoting.UNQUOTED);
            }
            case 81: {
                this.jj_consume_token(81);
                return new Id.NameSegment(this.token.image, Id.Quoting.UNQUOTED);
            }
            case 82: {
                this.jj_consume_token(82);
                return new Id.NameSegment(stripQuotes(this.token.image, "[", "]", "]]"), Id.Quoting.QUOTED);
            }
            default: {
                this.jj_la1[1] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final Id.KeySegment keyIdentifier() throws ParseException {
        final List<Id.NameSegment> list = new ArrayList<Id.NameSegment>();
        while (true) {
            final Id.NameSegment key = this.ampId();
            list.add(key);
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 83:
                case 84: {
                    continue;
                }
                default: {
                    this.jj_la1[2] = this.jj_gen;
                    return new Id.KeySegment(list);
                }
            }
        }
    }
    
    public final Id.NameSegment ampId() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 83: {
                this.jj_consume_token(83);
                return new Id.NameSegment(stripQuotes(this.token.image, "&[", "]", "]]"), Id.Quoting.QUOTED);
            }
            case 84: {
                this.jj_consume_token(84);
                return new Id.NameSegment(this.token.image.substring(1), Id.Quoting.UNQUOTED);
            }
            default: {
                this.jj_la1[3] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final String keyword() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 9: {
                this.jj_consume_token(9);
                return "Dimension";
            }
            case 30: {
                this.jj_consume_token(30);
                return "Properties";
            }
            default: {
                this.jj_la1[4] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final Id compoundId() throws ParseException {
        final List<Id.Segment> list = new ArrayList<Id.Segment>();
        Id.Segment i = this.identifier();
        list.add(i);
        while (this.jj_2_1(Integer.MAX_VALUE)) {
            this.jj_consume_token(60);
            i = this.identifier();
            list.add(i);
        }
        return new Id(list);
    }
    
    public final Exp unaliasedExpression() throws ParseException {
        Exp x = this.term5();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 27:
                case 39:
                case 57: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 27: {
                            this.jj_consume_token(27);
                            final Exp y = this.term5();
                            x = new UnresolvedFunCall("OR", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        case 39: {
                            this.jj_consume_token(39);
                            final Exp y = this.term5();
                            x = new UnresolvedFunCall("XOR", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        case 57: {
                            this.jj_consume_token(57);
                            final Exp y = this.term5();
                            x = new UnresolvedFunCall(":", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        default: {
                            this.jj_la1[6] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                default: {
                    this.jj_la1[5] = this.jj_gen;
                    return x;
                }
            }
        }
    }
    
    public final Exp term5() throws ParseException {
        Exp x = this.term4();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 1: {
                    this.jj_consume_token(1);
                    final Exp y = this.term4();
                    x = new UnresolvedFunCall("AND", Syntax.Infix, new Exp[] { x, y });
                    continue;
                }
                default: {
                    this.jj_la1[7] = this.jj_gen;
                    return x;
                }
            }
        }
    }
    
    public final Exp term4() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 4:
            case 5:
            case 9:
            case 25:
            case 30:
            case 41:
            case 64:
            case 66:
            case 68:
            case 70:
            case 74:
            case 75:
            case 76:
            case 78:
            case 79:
            case 81:
            case 82: {
                final Exp x = this.term3();
                return x;
            }
            case 24: {
                this.jj_consume_token(24);
                final Exp x = this.term4();
                return new UnresolvedFunCall("NOT", Syntax.Prefix, new Exp[] { x });
            }
            default: {
                this.jj_la1[8] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final Exp term3() throws ParseException {
        Exp x = this.term2();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 18:
                case 19:
                case 20:
                case 24:
                case 61:
                case 62:
                case 63:
                case 65:
                case 67:
                case 69: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 61:
                        case 62:
                        case 63:
                        case 65:
                        case 67:
                        case 69: {
                            Token op = null;
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 61: {
                                    this.jj_consume_token(61);
                                    op = this.token;
                                    break;
                                }
                                case 69: {
                                    this.jj_consume_token(69);
                                    op = this.token;
                                    break;
                                }
                                case 67: {
                                    this.jj_consume_token(67);
                                    op = this.token;
                                    break;
                                }
                                case 63: {
                                    this.jj_consume_token(63);
                                    op = this.token;
                                    break;
                                }
                                case 65: {
                                    this.jj_consume_token(65);
                                    op = this.token;
                                    break;
                                }
                                case 62: {
                                    this.jj_consume_token(62);
                                    op = this.token;
                                    break;
                                }
                                default: {
                                    this.jj_la1[10] = this.jj_gen;
                                    this.jj_consume_token(-1);
                                    throw new ParseException();
                                }
                            }
                            final Exp y = this.term2();
                            x = new UnresolvedFunCall(op.image, Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        default: {
                            this.jj_la1[11] = this.jj_gen;
                            if (this.jj_2_2(2)) {
                                this.jj_consume_token(19);
                                this.jj_consume_token(25);
                                x = new UnresolvedFunCall("IS NULL", Syntax.Postfix, new Exp[] { x });
                                continue;
                            }
                            if (this.jj_2_3(2)) {
                                this.jj_consume_token(19);
                                final Exp y = this.term2();
                                x = new UnresolvedFunCall("IS", Syntax.Infix, new Exp[] { x, y });
                                continue;
                            }
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 19: {
                                    this.jj_consume_token(19);
                                    this.jj_consume_token(12);
                                    x = new UnresolvedFunCall("IS EMPTY", Syntax.Postfix, new Exp[] { x });
                                    continue;
                                }
                                case 20: {
                                    this.jj_consume_token(20);
                                    final Exp y = this.term2();
                                    x = new UnresolvedFunCall("MATCHES", Syntax.Infix, new Exp[] { x, y });
                                    continue;
                                }
                                default: {
                                    this.jj_la1[12] = this.jj_gen;
                                    if (this.jj_2_4(2)) {
                                        this.jj_consume_token(24);
                                        this.jj_consume_token(20);
                                        final Exp y = this.term2();
                                        x = new UnresolvedFunCall("NOT", Syntax.Prefix, new Exp[] { new UnresolvedFunCall("MATCHES", Syntax.Infix, new Exp[] { x, y }) });
                                        continue;
                                    }
                                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                        case 18: {
                                            this.jj_consume_token(18);
                                            final Exp y = this.term2();
                                            x = new UnresolvedFunCall("IN", Syntax.Infix, new Exp[] { x, y });
                                            continue;
                                        }
                                        case 24: {
                                            this.jj_consume_token(24);
                                            this.jj_consume_token(18);
                                            final Exp y = this.term2();
                                            x = new UnresolvedFunCall("NOT", Syntax.Prefix, new Exp[] { new UnresolvedFunCall("IN", Syntax.Infix, new Exp[] { x, y }) });
                                            continue;
                                        }
                                        default: {
                                            this.jj_la1[13] = this.jj_gen;
                                            this.jj_consume_token(-1);
                                            throw new ParseException();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                default: {
                    this.jj_la1[9] = this.jj_gen;
                    return x;
                }
            }
        }
    }
    
    public final Exp term2() throws ParseException {
        Exp x = this.term();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 59:
                case 68:
                case 70: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 70: {
                            this.jj_consume_token(70);
                            final Exp y = this.term();
                            x = new UnresolvedFunCall("+", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        case 68: {
                            this.jj_consume_token(68);
                            final Exp y = this.term();
                            x = new UnresolvedFunCall("-", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        case 59: {
                            this.jj_consume_token(59);
                            final Exp y = this.term();
                            x = new UnresolvedFunCall("||", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        default: {
                            this.jj_la1[15] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                default: {
                    this.jj_la1[14] = this.jj_gen;
                    return x;
                }
            }
        }
    }
    
    public final Exp term() throws ParseException {
        Exp x = this.factor();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 55:
                case 73: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 55: {
                            this.jj_consume_token(55);
                            final Exp y = this.factor();
                            x = new UnresolvedFunCall("*", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        case 73: {
                            this.jj_consume_token(73);
                            final Exp y = this.factor();
                            x = new UnresolvedFunCall("/", Syntax.Infix, new Exp[] { x, y });
                            continue;
                        }
                        default: {
                            this.jj_la1[17] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                default: {
                    this.jj_la1[16] = this.jj_gen;
                    return x;
                }
            }
        }
    }
    
    public final Exp factor() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 4:
            case 5:
            case 9:
            case 25:
            case 30:
            case 64:
            case 66:
            case 74:
            case 75:
            case 76:
            case 78:
            case 79:
            case 81:
            case 82: {
                final Exp p = this.primary();
                return p;
            }
            case 70: {
                this.jj_consume_token(70);
                final Exp p = this.primary();
                return p;
            }
            case 68: {
                this.jj_consume_token(68);
                final Exp p = this.primary();
                return new UnresolvedFunCall("-", Syntax.Prefix, new Exp[] { p });
            }
            case 41: {
                this.jj_consume_token(41);
                final Exp p = this.primary();
                return new UnresolvedFunCall("Existing", Syntax.Prefix, new Exp[] { p });
            }
            default: {
                this.jj_la1[18] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final Exp primary() throws ParseException {
        Exp e = this.atom();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 60: {
                    this.jj_consume_token(60);
                    e = this.segmentOrFuncall(e);
                    continue;
                }
                default: {
                    this.jj_la1[19] = this.jj_gen;
                    return e;
                }
            }
        }
    }
    
    public final Exp segmentOrFuncall(final Exp left) throws ParseException {
        List<Exp> argList = null;
        final Id.Segment segment = this.identifier();
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 66: {
                this.jj_consume_token(66);
                if (this.jj_2_5(Integer.MAX_VALUE)) {
                    argList = Collections.emptyList();
                }
                else {
                    argList = this.expOrEmptyList();
                }
                this.jj_consume_token(72);
                break;
            }
            default: {
                this.jj_la1[20] = this.jj_gen;
                break;
            }
        }
        return this.createCall(left, segment, argList);
    }
    
    public final Literal numericLiteral() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 76: {
                this.jj_consume_token(76);
                return Literal.create(new BigDecimal(this.token.image));
            }
            case 74: {
                this.jj_consume_token(74);
                return Literal.create(new BigDecimal(this.token.image));
            }
            case 75: {
                this.jj_consume_token(75);
                return Literal.create(new BigDecimal(this.token.image));
            }
            default: {
                this.jj_la1[21] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final Exp atom() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 78: {
                this.jj_consume_token(78);
                return Literal.createString(stripQuotes(this.token.image, "'", "'", "''"));
            }
            case 79: {
                this.jj_consume_token(79);
                return Literal.createString(stripQuotes(this.token.image, "\"", "\"", "\"\""));
            }
            case 74:
            case 75:
            case 76: {
                final Exp e = this.numericLiteral();
                return e;
            }
            case 25: {
                this.jj_consume_token(25);
                return Literal.nullValue;
            }
            case 5: {
                this.jj_consume_token(5);
                this.jj_consume_token(66);
                final Exp e = this.unaliasedExpression();
                this.jj_consume_token(2);
                final Id.NameSegment segment = this.nameSegment();
                this.jj_consume_token(72);
                return new UnresolvedFunCall("CAST", Syntax.Cast, new Exp[] { e, Literal.createSymbol(segment.name) });
            }
            case 66: {
                this.jj_consume_token(66);
                final List<Exp> lis = this.expList();
                this.jj_consume_token(72);
                return new UnresolvedFunCall("()", Syntax.Parentheses, toExpArray(lis));
            }
            case 64: {
                this.jj_consume_token(64);
                List<Exp> lis = null;
                if (this.jj_2_6(Integer.MAX_VALUE)) {
                    lis = Collections.emptyList();
                }
                else {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 4:
                        case 5:
                        case 9:
                        case 24:
                        case 25:
                        case 30:
                        case 41:
                        case 64:
                        case 66:
                        case 68:
                        case 70:
                        case 74:
                        case 75:
                        case 76:
                        case 78:
                        case 79:
                        case 81:
                        case 82: {
                            lis = this.expList();
                            break;
                        }
                        default: {
                            this.jj_la1[22] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                }
                this.jj_consume_token(71);
                return new UnresolvedFunCall("{}", Syntax.Braces, toExpArray(lis));
            }
            case 4: {
                final Exp e = this.caseExpression();
                return e;
            }
            case 9:
            case 30:
            case 81:
            case 82: {
                Id.NameSegment segment = this.nameSegment();
                while (true) {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 56: {
                            this.jj_consume_token(56);
                            segment = this.nameSegment();
                            continue;
                        }
                        default: {
                            this.jj_la1[23] = this.jj_gen;
                            List<Exp> lis = null;
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 66: {
                                    this.jj_consume_token(66);
                                    if (this.jj_2_7(Integer.MAX_VALUE)) {
                                        lis = Collections.emptyList();
                                    }
                                    else {
                                        lis = this.expOrEmptyList();
                                    }
                                    this.jj_consume_token(72);
                                    break;
                                }
                                default: {
                                    this.jj_la1[24] = this.jj_gen;
                                    lis = null;
                                    break;
                                }
                            }
                            return this.createCall(null, segment, lis);
                        }
                    }
                }
            }
            default: {
                this.jj_la1[25] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final Exp caseExpression() throws ParseException {
        final List<Exp> list = new ArrayList<Exp>();
        boolean match = false;
        this.jj_consume_token(4);
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 4:
            case 5:
            case 9:
            case 24:
            case 25:
            case 30:
            case 41:
            case 64:
            case 66:
            case 68:
            case 70:
            case 74:
            case 75:
            case 76:
            case 78:
            case 79:
            case 81:
            case 82: {
                final Exp e = this.expression();
                match = true;
                list.add(e);
                break;
            }
            default: {
                this.jj_la1[26] = this.jj_gen;
                break;
            }
        }
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 37: {
                    this.jj_consume_token(37);
                    final Exp e = this.expression();
                    this.jj_consume_token(36);
                    final Exp e2 = this.expression();
                    list.add(e);
                    list.add(e2);
                    continue;
                }
                default: {
                    this.jj_la1[27] = this.jj_gen;
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 11: {
                            this.jj_consume_token(11);
                            final Exp e = this.expression();
                            list.add(e);
                            break;
                        }
                        default: {
                            this.jj_la1[28] = this.jj_gen;
                            break;
                        }
                    }
                    this.jj_consume_token(13);
                    if (match) {
                        return new UnresolvedFunCall("_CaseMatch", Syntax.Case, toExpArray(list));
                    }
                    return new UnresolvedFunCall("_CaseTest", Syntax.Case, toExpArray(list));
                }
            }
        }
    }
    
    public final Exp expression() throws ParseException {
        Exp e = this.unaliasedExpression();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 2: {
                    this.jj_consume_token(2);
                    final Id.Segment i = this.identifier();
                    final Id id = new Id(i);
                    e = new UnresolvedFunCall("AS", Syntax.Infix, new Exp[] { e, id });
                    continue;
                }
                default: {
                    this.jj_la1[29] = this.jj_gen;
                    return e;
                }
            }
        }
    }
    
    public final Exp expressionOrEmpty() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 4:
            case 5:
            case 9:
            case 24:
            case 25:
            case 30:
            case 41:
            case 64:
            case 66:
            case 68:
            case 70:
            case 74:
            case 75:
            case 76:
            case 78:
            case 79:
            case 81:
            case 82: {
                final Exp e = this.expression();
                return e;
            }
            default: {
                this.jj_la1[30] = this.jj_gen;
                return new UnresolvedFunCall("", Syntax.Empty, new Exp[0]);
            }
        }
    }
    
    public final List<Exp> expOrEmptyList() throws ParseException {
        final List<Exp> list = new LinkedList<Exp>();
        Exp e = this.expressionOrEmpty();
        list.add(e);
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 58: {
                    this.jj_consume_token(58);
                    e = this.expressionOrEmpty();
                    list.add(e);
                    continue;
                }
                default: {
                    this.jj_la1[31] = this.jj_gen;
                    return list;
                }
            }
        }
    }
    
    public final List<Exp> expList() throws ParseException {
        final List<Exp> list = new LinkedList<Exp>();
        Exp e = this.expression();
        list.add(e);
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 58: {
                    this.jj_consume_token(58);
                    e = this.expression();
                    list.add(e);
                    continue;
                }
                default: {
                    this.jj_la1[32] = this.jj_gen;
                    return list;
                }
            }
        }
    }
    
    public final QueryPart statement() throws ParseException {
        QueryPart qp = null;
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 34:
            case 40: {
                qp = this.selectStatement();
                break;
            }
            case 10: {
                qp = this.drillthroughStatement();
                break;
            }
            case 14: {
                qp = this.explainStatement();
                break;
            }
            default: {
                this.jj_la1[33] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        return qp;
    }
    
    public final QueryPart selectOrDrillthroughStatement() throws ParseException {
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 34:
            case 40: {
                final QueryPart qp = this.selectStatement();
                return qp;
            }
            case 10: {
                final QueryPart qp = this.drillthroughStatement();
                return qp;
            }
            default: {
                this.jj_la1[34] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final Query selectStatement() throws ParseException {
        final List<Formula> f = new ArrayList<Formula>();
        Exp w = null;
        final List<QueryAxis> a = new ArrayList<QueryAxis>();
        final List<QueryPart> cellPropList = new ArrayList<QueryPart>();
        Label_0248: {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 40: {
                    this.jj_consume_token(40);
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 22: {
                                final Formula e = this.memberSpecification();
                                f.add(e);
                                break;
                            }
                            case 35: {
                                final Formula e = this.setSpecification();
                                f.add(e);
                                break;
                            }
                            default: {
                                this.jj_la1[35] = this.jj_gen;
                                this.jj_consume_token(-1);
                                throw new ParseException();
                            }
                        }
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 22:
                            case 35: {
                                continue;
                            }
                            default: {
                                this.jj_la1[36] = this.jj_gen;
                                break Label_0248;
                            }
                        }
                    }
                }
                default: {
                    this.jj_la1[37] = this.jj_gen;
                    break;
                }
            }
        }
        this.jj_consume_token(34);
        Label_0710: {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 4:
                case 5:
                case 9:
                case 23:
                case 24:
                case 25:
                case 30:
                case 41:
                case 64:
                case 66:
                case 68:
                case 70:
                case 74:
                case 75:
                case 76:
                case 78:
                case 79:
                case 81:
                case 82: {
                    QueryAxis i = this.axisSpecification();
                    a.add(i);
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 58: {
                                this.jj_consume_token(58);
                                i = this.axisSpecification();
                                a.add(i);
                                continue;
                            }
                            default: {
                                this.jj_la1[38] = this.jj_gen;
                                break Label_0710;
                            }
                        }
                    }
                }
                default: {
                    this.jj_la1[39] = this.jj_gen;
                    break;
                }
            }
        }
        this.jj_consume_token(17);
        final Id c = this.compoundId();
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 38: {
                this.jj_consume_token(38);
                w = this.expression();
                break;
            }
            default: {
                this.jj_la1[40] = this.jj_gen;
                break;
            }
        }
        Label_1024: {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 6:
                case 30: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 6: {
                            this.jj_consume_token(6);
                            break;
                        }
                        default: {
                            this.jj_la1[41] = this.jj_gen;
                            break;
                        }
                    }
                    this.jj_consume_token(30);
                    Id p = this.compoundId();
                    cellPropList.add(new CellProperty(p.getSegments()));
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 58: {
                                this.jj_consume_token(58);
                                p = this.compoundId();
                                cellPropList.add(new CellProperty(p.getSegments()));
                                continue;
                            }
                            default: {
                                this.jj_la1[42] = this.jj_gen;
                                break Label_1024;
                            }
                        }
                    }
                }
                default: {
                    this.jj_la1[43] = this.jj_gen;
                    break;
                }
            }
        }
        final String cubeName = ((Id.NameSegment)c.getElement(0)).name;
        return this.factory.makeQuery(this.statement, toFormulaArray(f), toQueryAxisArray(a), cubeName, w, toQueryPartArray(cellPropList), this.strictValidation);
    }
    
    public final Formula memberSpecification() throws ParseException {
        final List<MemberProperty> l = new ArrayList<MemberProperty>();
        this.jj_consume_token(22);
        final Id m = this.compoundId();
        this.jj_consume_token(2);
        final Exp e = this.formulaExpression();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 58: {
                    this.jj_consume_token(58);
                    final MemberProperty mp = this.memberPropertyDefinition();
                    l.add(mp);
                    continue;
                }
                default: {
                    this.jj_la1[44] = this.jj_gen;
                    return new Formula(m, e, toMemberPropertyArray(l));
                }
            }
        }
    }
    
    public final Exp formulaExpression() throws ParseException {
        if (this.jj_2_8(Integer.MAX_VALUE)) {
            this.jj_consume_token(78);
            return this.recursivelyParseExp(stripQuotes(this.token.image, "'", "'", "''"));
        }
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 4:
            case 5:
            case 9:
            case 24:
            case 25:
            case 30:
            case 41:
            case 64:
            case 66:
            case 68:
            case 70:
            case 74:
            case 75:
            case 76:
            case 78:
            case 79:
            case 81:
            case 82: {
                final Exp e = this.unaliasedExpression();
                return e;
            }
            default: {
                this.jj_la1[45] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }
    
    public final MemberProperty memberPropertyDefinition() throws ParseException {
        final Id.NameSegment id = this.nameSegment();
        this.jj_consume_token(61);
        final Exp e = this.expression();
        return new MemberProperty(id.name, e);
    }
    
    public final Formula setSpecification() throws ParseException {
        this.jj_consume_token(35);
        final Id n = this.compoundId();
        this.jj_consume_token(2);
        final Exp e = this.formulaExpression();
        return new Formula(n, e);
    }
    
    public final QueryAxis axisSpecification() throws ParseException {
        boolean nonEmpty = false;
        final List<Id> dp = new ArrayList<Id>();
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 23: {
                this.jj_consume_token(23);
                this.jj_consume_token(12);
                nonEmpty = true;
                break;
            }
            default: {
                this.jj_la1[46] = this.jj_gen;
                break;
            }
        }
        final Exp e = this.expression();
        Label_0297: {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 9:
                case 30: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 9: {
                            this.jj_consume_token(9);
                            break;
                        }
                        default: {
                            this.jj_la1[47] = this.jj_gen;
                            break;
                        }
                    }
                    this.jj_consume_token(30);
                    Id p = this.compoundId();
                    dp.add(p);
                    while (true) {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 58: {
                                this.jj_consume_token(58);
                                p = this.compoundId();
                                dp.add(p);
                                continue;
                            }
                            default: {
                                this.jj_la1[48] = this.jj_gen;
                                break Label_0297;
                            }
                        }
                    }
                }
                default: {
                    this.jj_la1[49] = this.jj_gen;
                    break;
                }
            }
        }
        this.jj_consume_token(26);
        AxisOrdinal axis = null;
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 8: {
                this.jj_consume_token(8);
                axis = AxisOrdinal.StandardAxisOrdinal.COLUMNS;
                break;
            }
            case 32: {
                this.jj_consume_token(32);
                axis = AxisOrdinal.StandardAxisOrdinal.ROWS;
                break;
            }
            case 28: {
                this.jj_consume_token(28);
                axis = AxisOrdinal.StandardAxisOrdinal.PAGES;
                break;
            }
            case 33: {
                this.jj_consume_token(33);
                axis = AxisOrdinal.StandardAxisOrdinal.SECTIONS;
                break;
            }
            case 7: {
                this.jj_consume_token(7);
                axis = AxisOrdinal.StandardAxisOrdinal.CHAPTERS;
                break;
            }
            case 3:
            case 74:
            case 75:
            case 76: {
                Literal n = null;
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 74:
                    case 75:
                    case 76: {
                        n = this.numericLiteral();
                        break;
                    }
                    case 3: {
                        this.jj_consume_token(3);
                        this.jj_consume_token(66);
                        n = this.numericLiteral();
                        this.jj_consume_token(72);
                        break;
                    }
                    default: {
                        this.jj_la1[50] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                    }
                }
                final Number number = (Number)n.getValue();
                if (number.doubleValue() < 0.0 || number.doubleValue() != number.intValue()) {
                    throw MondrianResource.instance().InvalidAxis.ex(number.doubleValue());
                }
                axis = AxisOrdinal.StandardAxisOrdinal.forLogicalOrdinal(number.intValue());
                break;
            }
            default: {
                this.jj_la1[51] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        return new QueryAxis(nonEmpty, e, axis, QueryAxis.SubtotalVisibility.Undefined, toIdArray(dp));
    }
    
    public final QueryPart drillthroughStatement() throws ParseException {
        int m = 0;
        int f = 0;
        List<Exp> rl = null;
        this.jj_consume_token(10);
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 21: {
                this.jj_consume_token(21);
                this.jj_consume_token(74);
                m = Integer.valueOf(this.token.image);
                break;
            }
            default: {
                this.jj_la1[52] = this.jj_gen;
                break;
            }
        }
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 15: {
                this.jj_consume_token(15);
                this.jj_consume_token(74);
                f = Integer.valueOf(this.token.image);
                break;
            }
            default: {
                this.jj_la1[53] = this.jj_gen;
                break;
            }
        }
        final Query s = this.selectStatement();
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 31: {
                this.jj_consume_token(31);
                rl = this.returnItemList();
                break;
            }
            default: {
                this.jj_la1[54] = this.jj_gen;
                break;
            }
        }
        return this.factory.makeDrillThrough(s, m, f, rl);
    }
    
    public final List<Exp> returnItemList() throws ParseException {
        final List<Exp> list = new ArrayList<Exp>();
        Id i = this.returnItem();
        list.add(i);
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 58: {
                    this.jj_consume_token(58);
                    i = this.returnItem();
                    list.add(i);
                    continue;
                }
                default: {
                    this.jj_la1[55] = this.jj_gen;
                    return list;
                }
            }
        }
    }
    
    public final Id returnItem() throws ParseException {
        final Id i = this.compoundId();
        return i;
    }
    
    public final QueryPart explainStatement() throws ParseException {
        this.jj_consume_token(14);
        this.jj_consume_token(29);
        this.jj_consume_token(16);
        QueryPart qp = null;
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 34:
            case 40: {
                qp = this.selectStatement();
                break;
            }
            case 10: {
                qp = this.drillthroughStatement();
                break;
            }
            default: {
                this.jj_la1[56] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        return this.factory.makeExplain(qp);
    }
    
    private boolean jj_2_1(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_1();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(0, xla);
        }
    }
    
    private boolean jj_2_2(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_2();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(1, xla);
        }
    }
    
    private boolean jj_2_3(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_3();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(2, xla);
        }
    }
    
    private boolean jj_2_4(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_4();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(3, xla);
        }
    }
    
    private boolean jj_2_5(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_5();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(4, xla);
        }
    }
    
    private boolean jj_2_6(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_6();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(5, xla);
        }
    }
    
    private boolean jj_2_7(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_7();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(6, xla);
        }
    }
    
    private boolean jj_2_8(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_8();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(7, xla);
        }
    }
    
    private boolean jj_3R_46() {
        return this.jj_scan_token(82);
    }
    
    private boolean jj_3R_45() {
        return this.jj_scan_token(81);
    }
    
    private boolean jj_3_8() {
        return this.jj_scan_token(78);
    }
    
    private boolean jj_3R_40() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_44()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_45()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_46()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean jj_3R_44() {
        return this.jj_3R_47();
    }
    
    private boolean jj_3_1() {
        return this.jj_scan_token(60);
    }
    
    private boolean jj_3_4() {
        return this.jj_scan_token(24) || this.jj_scan_token(20);
    }
    
    private boolean jj_3R_37() {
        return this.jj_3R_40();
    }
    
    private boolean jj_3_6() {
        return this.jj_scan_token(71);
    }
    
    private boolean jj_3_3() {
        return this.jj_scan_token(19) || this.jj_3R_20();
    }
    
    private boolean jj_3R_27() {
        return this.jj_3R_28();
    }
    
    private boolean jj_3R_36() {
        return this.jj_3R_39();
    }
    
    private boolean jj_3_2() {
        return this.jj_scan_token(19) || this.jj_scan_token(25);
    }
    
    private boolean jj_3R_26() {
        return this.jj_scan_token(41);
    }
    
    private boolean jj_3R_25() {
        return this.jj_scan_token(68);
    }
    
    private boolean jj_3R_35() {
        return this.jj_scan_token(64);
    }
    
    private boolean jj_3R_24() {
        return this.jj_scan_token(70);
    }
    
    private boolean jj_3R_49() {
        return this.jj_scan_token(30);
    }
    
    private boolean jj_3R_23() {
        return this.jj_3R_27();
    }
    
    private boolean jj_3R_22() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_23()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_24()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_25()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_26()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean jj_3R_48() {
        return this.jj_scan_token(9);
    }
    
    private boolean jj_3R_47() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_48()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_49()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean jj_3R_34() {
        return this.jj_scan_token(66);
    }
    
    private boolean jj_3R_33() {
        return this.jj_scan_token(5);
    }
    
    private boolean jj_3R_32() {
        return this.jj_scan_token(25);
    }
    
    private boolean jj_3R_31() {
        return this.jj_3R_38();
    }
    
    private boolean jj_3R_21() {
        return this.jj_3R_22();
    }
    
    private boolean jj_3R_30() {
        return this.jj_scan_token(79);
    }
    
    private boolean jj_3R_28() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_29()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_30()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_31()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_32()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_33()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_34()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_35()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_36()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_37()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean jj_3R_29() {
        return this.jj_scan_token(78);
    }
    
    private boolean jj_3R_43() {
        return this.jj_scan_token(75);
    }
    
    private boolean jj_3R_42() {
        return this.jj_scan_token(74);
    }
    
    private boolean jj_3R_39() {
        return this.jj_scan_token(4);
    }
    
    private boolean jj_3_5() {
        return this.jj_scan_token(72);
    }
    
    private boolean jj_3R_38() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_41()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_42()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_43()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean jj_3R_41() {
        return this.jj_scan_token(76);
    }
    
    private boolean jj_3R_20() {
        return this.jj_3R_21();
    }
    
    private boolean jj_3_7() {
        return this.jj_scan_token(72);
    }
    
    private static void jj_la1_init_0() {
        MdxParserImpl.jj_la1_0 = new int[] { 1073742336, 1073742336, 0, 0, 1073742336, 134217728, 134217728, 2, 1124074032, 18612224, 0, 0, 1572864, 17039360, 0, 0, 0, 0, 1107296816, 0, 0, 0, 1124074032, 0, 0, 1107296816, 1124074032, 0, 2048, 4, 1124074032, 0, 0, 17408, 1024, 4194304, 4194304, 0, 0, 1132462640, 0, 64, 0, 1073741888, 0, 1124074032, 8388608, 512, 0, 1073742336, 8, 268435848, 2097152, 32768, Integer.MIN_VALUE, 0, 1024 };
    }
    
    private static void jj_la1_init_1() {
        MdxParserImpl.jj_la1_1 = new int[] { 0, 0, 0, 0, 0, 33554560, 33554560, 0, 512, -536870912, -536870912, -536870912, 0, 0, 134217728, 134217728, 8388608, 8388608, 512, 268435456, 0, 0, 512, 16777216, 0, 0, 512, 32, 0, 0, 512, 67108864, 67108864, 260, 260, 8, 8, 256, 67108864, 512, 64, 0, 67108864, 0, 67108864, 512, 0, 0, 67108864, 0, 0, 3, 0, 0, 0, 67108864, 260 };
    }
    
    private static void jj_la1_init_2() {
        MdxParserImpl.jj_la1_2 = new int[] { 1966080, 393216, 1572864, 1572864, 0, 0, 0, 0, 449621, 42, 42, 42, 0, 0, 80, 80, 512, 512, 449621, 0, 4, 7168, 449621, 0, 4, 449541, 449621, 0, 0, 0, 449621, 0, 0, 0, 0, 0, 0, 0, 0, 449621, 0, 0, 0, 0, 0, 449621, 0, 0, 0, 0, 7168, 7168, 0, 0, 0, 0, 0 };
    }
    
    public MdxParserImpl(final InputStream stream) {
        this(stream, null);
    }
    
    public MdxParserImpl(final InputStream stream, final String encoding) {
        this.jj_la1 = new int[57];
        this.jj_2_rtns = new JJCalls[8];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new ArrayList<int[]>();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        try {
            this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.token_source = new MdxParserImplTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 57; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final InputStream stream) {
        this.ReInit(stream, null);
    }
    
    public void ReInit(final InputStream stream, final String encoding) {
        try {
            this.jj_input_stream.ReInit(stream, encoding, 1, 1);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 57; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public MdxParserImpl(final Reader stream) {
        this.jj_la1 = new int[57];
        this.jj_2_rtns = new JJCalls[8];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new ArrayList<int[]>();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        this.token_source = new MdxParserImplTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 57; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final Reader stream) {
        this.jj_input_stream.ReInit(stream, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 57; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public MdxParserImpl(final MdxParserImplTokenManager tm) {
        this.jj_la1 = new int[57];
        this.jj_2_rtns = new JJCalls[8];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess();
        this.jj_expentries = new ArrayList<int[]>();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 57; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final MdxParserImplTokenManager tm) {
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 57; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    private Token jj_consume_token(final int kind) throws ParseException {
        final Token oldToken;
        if ((oldToken = this.token).next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        if (this.token.kind == kind) {
            ++this.jj_gen;
            if (++this.jj_gc > 100) {
                this.jj_gc = 0;
                for (int i = 0; i < this.jj_2_rtns.length; ++i) {
                    for (JJCalls c = this.jj_2_rtns[i]; c != null; c = c.next) {
                        if (c.gen < this.jj_gen) {
                            c.first = null;
                        }
                    }
                }
            }
            return this.token;
        }
        this.token = oldToken;
        this.jj_kind = kind;
        throw this.generateParseException();
    }
    
    private boolean jj_scan_token(final int kind) {
        if (this.jj_scanpos == this.jj_lastpos) {
            --this.jj_la;
            if (this.jj_scanpos.next == null) {
                final Token jj_scanpos = this.jj_scanpos;
                final Token nextToken = this.token_source.getNextToken();
                jj_scanpos.next = nextToken;
                this.jj_scanpos = nextToken;
                this.jj_lastpos = nextToken;
            }
            else {
                final Token next = this.jj_scanpos.next;
                this.jj_scanpos = next;
                this.jj_lastpos = next;
            }
        }
        else {
            this.jj_scanpos = this.jj_scanpos.next;
        }
        if (this.jj_rescan) {
            int i = 0;
            Token tok;
            for (tok = this.token; tok != null && tok != this.jj_scanpos; tok = tok.next) {
                ++i;
            }
            if (tok != null) {
                this.jj_add_error_token(kind, i);
            }
        }
        if (this.jj_scanpos.kind != kind) {
            return true;
        }
        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
            throw this.jj_ls;
        }
        return false;
    }
    
    public final Token getNextToken() {
        if (this.token.next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        ++this.jj_gen;
        return this.token;
    }
    
    public final Token getToken(final int index) {
        Token t = this.token;
        for (int i = 0; i < index; ++i) {
            if (t.next != null) {
                t = t.next;
            }
            else {
                final Token token = t;
                final Token nextToken = this.token_source.getNextToken();
                token.next = nextToken;
                t = nextToken;
            }
        }
        return t;
    }
    
    private int jj_ntk() {
        final Token next = this.token.next;
        this.jj_nt = next;
        if (next == null) {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            return this.jj_ntk = nextToken.kind;
        }
        return this.jj_ntk = this.jj_nt.kind;
    }
    
    private void jj_add_error_token(final int kind, final int pos) {
        if (pos >= 100) {
            return;
        }
        if (pos == this.jj_endpos + 1) {
            this.jj_lasttokens[this.jj_endpos++] = kind;
        }
        else if (this.jj_endpos != 0) {
            this.jj_expentry = new int[this.jj_endpos];
            for (int i = 0; i < this.jj_endpos; ++i) {
                this.jj_expentry[i] = this.jj_lasttokens[i];
            }
        Label_0092:
            for (final int[] oldentry : this.jj_expentries) {
                if (oldentry.length == this.jj_expentry.length) {
                    for (int j = 0; j < this.jj_expentry.length; ++j) {
                        if (oldentry[j] != this.jj_expentry[j]) {
                            continue Label_0092;
                        }
                    }
                    this.jj_expentries.add(this.jj_expentry);
                    break;
                }
            }
            if (pos != 0) {
                this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind;
            }
        }
    }
    
    public ParseException generateParseException() {
        this.jj_expentries.clear();
        final boolean[] la1tokens = new boolean[87];
        if (this.jj_kind >= 0) {
            la1tokens[this.jj_kind] = true;
            this.jj_kind = -1;
        }
        for (int i = 0; i < 57; ++i) {
            if (this.jj_la1[i] == this.jj_gen) {
                for (int j = 0; j < 32; ++j) {
                    if ((MdxParserImpl.jj_la1_0[i] & 1 << j) != 0x0) {
                        la1tokens[j] = true;
                    }
                    if ((MdxParserImpl.jj_la1_1[i] & 1 << j) != 0x0) {
                        la1tokens[32 + j] = true;
                    }
                    if ((MdxParserImpl.jj_la1_2[i] & 1 << j) != 0x0) {
                        la1tokens[64 + j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 87; ++i) {
            if (la1tokens[i]) {
                (this.jj_expentry = new int[1])[0] = i;
                this.jj_expentries.add(this.jj_expentry);
            }
        }
        this.jj_endpos = 0;
        this.jj_rescan_token();
        this.jj_add_error_token(0, 0);
        final int[][] exptokseq = new int[this.jj_expentries.size()][];
        for (int k = 0; k < this.jj_expentries.size(); ++k) {
            exptokseq[k] = this.jj_expentries.get(k);
        }
        return new ParseException(this.token, exptokseq, MdxParserImpl.tokenImage);
    }
    
    public final void enable_tracing() {
    }
    
    public final void disable_tracing() {
    }
    
    private void jj_rescan_token() {
        this.jj_rescan = true;
        for (int i = 0; i < 8; ++i) {
            try {
                JJCalls p = this.jj_2_rtns[i];
                do {
                    if (p.gen > this.jj_gen) {
                        this.jj_la = p.arg;
                        final Token first = p.first;
                        this.jj_scanpos = first;
                        this.jj_lastpos = first;
                        switch (i) {
                            case 0: {
                                this.jj_3_1();
                                break;
                            }
                            case 1: {
                                this.jj_3_2();
                                break;
                            }
                            case 2: {
                                this.jj_3_3();
                                break;
                            }
                            case 3: {
                                this.jj_3_4();
                                break;
                            }
                            case 4: {
                                this.jj_3_5();
                                break;
                            }
                            case 5: {
                                this.jj_3_6();
                                break;
                            }
                            case 6: {
                                this.jj_3_7();
                                break;
                            }
                            case 7: {
                                this.jj_3_8();
                                break;
                            }
                        }
                    }
                    p = p.next;
                } while (p != null);
            }
            catch (LookaheadSuccess lookaheadSuccess) {}
        }
        this.jj_rescan = false;
    }
    
    private void jj_save(final int index, final int xla) {
        JJCalls p;
        for (p = this.jj_2_rtns[index]; p.gen > this.jj_gen; p = p.next) {
            if (p.next == null) {
                final JJCalls jjCalls = p;
                final JJCalls next = new JJCalls();
                jjCalls.next = next;
                p = next;
                break;
            }
        }
        p.gen = this.jj_gen + xla - this.jj_la;
        p.first = this.token;
        p.arg = xla;
    }
    
    static {
        EmptyMemberPropertyArray = new MemberProperty[0];
        EmptyExpArray = new Exp[0];
        EmptyFormulaArray = new Formula[0];
        EmptyIdArray = new Id[0];
        EmptyQueryPartArray = new QueryPart[0];
        EmptyQueryAxisArray = new QueryAxis[0];
        jj_la1_init_0();
        jj_la1_init_1();
        jj_la1_init_2();
    }
    
    private static final class LookaheadSuccess extends Error
    {
    }
    
    static final class JJCalls
    {
        int gen;
        Token first;
        int arg;
        JJCalls next;
    }
}
