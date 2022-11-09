// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import org.olap4j.mdx.parser.MdxParser;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.LiteralNode;
import java.util.Map;
import org.olap4j.mdx.PropertyValueNode;
import org.olap4j.mdx.parser.impl.DefaultMdxParserImpl;
import org.olap4j.mdx.WithMemberNode;
import org.saiku.query.metadata.Calculated;
import org.olap4j.metadata.Measure;
import java.util.Iterator;
import java.util.ArrayList;
import org.olap4j.metadata.Member;
import org.olap4j.mdx.MemberNode;
import java.util.List;
import org.olap4j.mdx.ParseRegion;
import org.olap4j.mdx.Syntax;
import org.olap4j.mdx.CallNode;
import org.olap4j.mdx.ParseTreeNode;

public class NodeConverter
{
    protected static CallNode generateSetCall(final ParseTreeNode... args) {
        return new CallNode((ParseRegion)null, "{}", Syntax.Braces, args);
    }
    
    protected static CallNode generateListSetCall(final List<ParseTreeNode> cnodes) {
        return new CallNode((ParseRegion)null, "{}", Syntax.Braces, (List)cnodes);
    }
    
    protected static CallNode generateListTupleCall(final List<ParseTreeNode> cnodes) {
        return new CallNode((ParseRegion)null, "()", Syntax.Parentheses, (List)cnodes);
    }
    
    protected static CallNode generateCrossJoin(final List<ParseTreeNode> selections) {
        return generateCrossJoin(selections, false, true);
    }
    
    protected static CallNode generateCrossJoin(final List<ParseTreeNode> selections, final boolean nonEmpty, final boolean asterisk) {
        final String crossJoinFun = nonEmpty ? "NonEmptyCrossJoin" : "CrossJoin";
        if (selections == null || selections.size() == 0) {
            return null;
        }
        ParseTreeNode sel1 = selections.get(0);
        if (sel1 instanceof MemberNode) {
            sel1 = (ParseTreeNode)generateSetCall(sel1);
        }
        if (selections.size() == 2) {
            ParseTreeNode sel2 = selections.get(1);
            if (sel2 instanceof MemberNode) {
                sel2 = (ParseTreeNode)generateSetCall(sel2);
            }
            return new CallNode((ParseRegion)null, crossJoinFun, Syntax.Function, new ParseTreeNode[] { sel1, sel2 });
        }
        if (asterisk) {
            return new CallNode((ParseRegion)null, " * ", Syntax.Infix, (List)selections);
        }
        selections.remove(0);
        return new CallNode((ParseRegion)null, crossJoinFun, Syntax.Function, new ParseTreeNode[] { sel1, (ParseTreeNode)generateCrossJoin(selections, nonEmpty, asterisk) });
    }
    
    protected static CallNode generateUnion(final List<List<ParseTreeNode>> unions) {
        if (unions.size() > 2) {
            final List<ParseTreeNode> first = unions.remove(0);
            return new CallNode((ParseRegion)null, "Union", Syntax.Function, new ParseTreeNode[] { (ParseTreeNode)generateCrossJoin(first), (ParseTreeNode)generateUnion(unions) });
        }
        return new CallNode((ParseRegion)null, "Union", Syntax.Function, new ParseTreeNode[] { (ParseTreeNode)generateCrossJoin(unions.get(0)), (ParseTreeNode)generateCrossJoin(unions.get(1)) });
    }
    
    protected static CallNode generateHierarchizeUnion(final List<List<ParseTreeNode>> unions) {
        return new CallNode((ParseRegion)null, "Hierarchize", Syntax.Function, new ParseTreeNode[] { (ParseTreeNode)generateUnion(unions) });
    }
    
    protected static ParseTreeNode toOlap4jMemberSet(final List<Member> members) {
        final List<ParseTreeNode> membernodes = new ArrayList<ParseTreeNode>();
        for (final Member m : members) {
            membernodes.add((ParseTreeNode)new MemberNode((ParseRegion)null, m));
        }
        return (ParseTreeNode)generateListSetCall(membernodes);
    }
    
    protected static ParseTreeNode toOlap4jMeasureSet(final List<Measure> measures) {
        final List<ParseTreeNode> membernodes = new ArrayList<ParseTreeNode>();
        for (final Measure m : measures) {
            membernodes.add((ParseTreeNode)new MemberNode((ParseRegion)null, (Member)m));
        }
        return (ParseTreeNode)generateListSetCall(membernodes);
    }
    
    protected static WithMemberNode toOlap4jCalculatedMember(final Calculated cm) {
        final MdxParser parser = (MdxParser)new DefaultMdxParserImpl();
        final ParseTreeNode formula = parser.parseExpression(cm.getFormula());
        final List<PropertyValueNode> propertyList = new ArrayList<PropertyValueNode>();
        for (final Map.Entry<String, String> entry : cm.getFormatProperties().entrySet()) {
            final ParseTreeNode exp = (ParseTreeNode)LiteralNode.createString((ParseRegion)null, (String)entry.getValue());
            final String name = entry.getKey();
            final PropertyValueNode prop = new PropertyValueNode((ParseRegion)null, name, exp);
            propertyList.add(prop);
        }
        final WithMemberNode wm = new WithMemberNode((ParseRegion)null, IdentifierNode.parseIdentifier(cm.getUniqueName()), formula, (List)propertyList);
        return wm;
    }
    
    protected static IdentifierNode getIdentifier(final String... identifiers) {
        String identifier = "";
        for (int i = 0; i < identifiers.length; ++i) {
            if (i == 0) {
                identifier = "~" + identifiers[0];
            }
            else {
                identifier = identifier + "_" + identifiers[i];
            }
        }
        return IdentifierNode.ofNames(new String[] { identifier });
    }
}
