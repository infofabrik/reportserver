// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.mdx;

import java.math.BigDecimal;
import org.olap4j.mdx.CallNode;
import org.olap4j.mdx.Syntax;
import org.olap4j.mdx.LiteralNode;
import org.olap4j.mdx.ParseRegion;
import org.olap4j.mdx.HierarchyNode;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.mdx.parser.MdxParser;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import org.olap4j.metadata.Hierarchy;
import java.util.List;

public class NameLikeFilter extends AbstractFilterFunction
{
    private String op;
    private List<String> filterExpression;
    private IFilterFunction.MdxFunctionType type;
    private Hierarchy hierarchy;
    private String operator;
    
    public NameLikeFilter(final Hierarchy hierarchy, final String... matchingExpression) {
        this.filterExpression = new ArrayList<String>();
        this.operator = " > ";
        final List<String> expressions = Arrays.asList(matchingExpression);
        this.filterExpression.addAll(expressions);
        this.hierarchy = hierarchy;
        this.type = IFilterFunction.MdxFunctionType.Filter;
    }
    
    public NameLikeFilter(final Hierarchy hierarchy, final List<String> matchingExpression) {
        this.filterExpression = new ArrayList<String>();
        this.operator = " > ";
        this.hierarchy = hierarchy;
        this.filterExpression.addAll(matchingExpression);
        this.type = IFilterFunction.MdxFunctionType.Filter;
    }
    
    public NameLikeFilter(final Hierarchy hierarchy, final List<String> matchingExpression, final String operator) {
        this.filterExpression = new ArrayList<String>();
        this.operator = " > ";
        this.hierarchy = hierarchy;
        this.filterExpression.addAll(matchingExpression);
        this.type = IFilterFunction.MdxFunctionType.Filter;
        this.op = operator;
        if (operator != null && operator.equals("NOTEQUAL")) {
            this.operator = " = ";
        }
    }
    
    @Override
    public List<ParseTreeNode> getArguments(final MdxParser parser) {
        final List<ParseTreeNode> filters = new ArrayList<ParseTreeNode>();
        final List<ParseTreeNode> arguments = new ArrayList<ParseTreeNode>();
        final ParseTreeNode h = (ParseTreeNode)new HierarchyNode((ParseRegion)null, this.hierarchy);
        for (int i = 0; i < this.filterExpression.size(); ++i) {
            final String filter = this.filterExpression.get(i);
            String o = this.operator;
            if (this.filterExpression.size() > 1 && i == 0) {
                o = " > ";
            }
            final ParseTreeNode filterExp = (ParseTreeNode)LiteralNode.createString((ParseRegion)null, filter);
            final CallNode currentMemberNode = new CallNode((ParseRegion)null, "CurrentMember", Syntax.Property, new ParseTreeNode[] { h });
            final CallNode currentMemberNameNode = new CallNode((ParseRegion)null, "Name", Syntax.Property, new ParseTreeNode[] { (ParseTreeNode)currentMemberNode });
            final CallNode instrNode = new CallNode((ParseRegion)null, "Instr", Syntax.Function, new ParseTreeNode[] { (ParseTreeNode)currentMemberNameNode, filterExp });
            final CallNode filterNode = new CallNode((ParseRegion)null, o, Syntax.Infix, new ParseTreeNode[] { (ParseTreeNode)instrNode, (ParseTreeNode)LiteralNode.createNumeric((ParseRegion)null, new BigDecimal(0), true) });
            filters.add((ParseTreeNode)filterNode);
        }
        if (filters.size() == 1) {
            arguments.addAll(filters);
        }
        else if (filters.size() > 1) {
            ParseTreeNode allfilter = filters.get(0);
            for (int j = 1; j < filters.size(); ++j) {
                allfilter = (ParseTreeNode)new CallNode((ParseRegion)null, " OR ", Syntax.Infix, new ParseTreeNode[] { allfilter, filters.get(j) });
            }
            arguments.add(allfilter);
        }
        return arguments;
    }
    
    @Override
    public IFilterFunction.MdxFunctionType getFunctionType() {
        return this.type;
    }
    
    public List<String> getFilterExpression() {
        return this.filterExpression;
    }
    
    public Hierarchy getHierarchy() {
        return this.hierarchy;
    }
    
    public String getOp() {
        return this.op;
    }
}
