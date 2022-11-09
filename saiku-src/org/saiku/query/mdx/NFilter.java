// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.mdx;

import org.olap4j.mdx.ParseRegion;
import org.olap4j.mdx.LiteralNode;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.olap4j.mdx.ParseTreeNode;
import java.util.List;
import org.olap4j.mdx.parser.MdxParser;

public class NFilter extends AbstractFilterFunction
{
    private String filterExpression;
    private int n;
    private IFilterFunction.MdxFunctionType type;
    
    public NFilter(final IFilterFunction.MdxFunctionType type, final int n, final String filterExpression) {
        if (IFilterFunction.MdxFunctionType.Filter.equals(type)) {
            throw new IllegalArgumentException("Cannot use Filter() as TopN Filter");
        }
        this.filterExpression = filterExpression;
        this.n = n;
        this.type = type;
    }
    
    public int getN() {
        return this.n;
    }
    
    public String getFilterExpression() {
        return this.filterExpression;
    }
    
    @Override
    public List<ParseTreeNode> getArguments(final MdxParser parser) {
        final List<ParseTreeNode> arguments = new ArrayList<ParseTreeNode>();
        final ParseTreeNode nfilter = (ParseTreeNode)LiteralNode.createNumeric((ParseRegion)null, new BigDecimal(this.n), false);
        arguments.add(nfilter);
        if (this.filterExpression != null) {
            final ParseTreeNode topn = parser.parseExpression(this.filterExpression);
            arguments.add(topn);
        }
        return arguments;
    }
    
    @Override
    public IFilterFunction.MdxFunctionType getFunctionType() {
        return this.type;
    }
}
