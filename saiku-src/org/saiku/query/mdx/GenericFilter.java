// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.mdx;

import java.util.ArrayList;
import org.olap4j.mdx.ParseTreeNode;
import java.util.List;
import org.olap4j.mdx.parser.MdxParser;

public class GenericFilter extends AbstractFilterFunction
{
    private String filterExpression;
    private IFilterFunction.MdxFunctionType type;
    
    public GenericFilter(final String filterExpression) {
        this.filterExpression = filterExpression;
        this.type = IFilterFunction.MdxFunctionType.Filter;
    }
    
    public String getFilterExpression() {
        return this.filterExpression;
    }
    
    @Override
    public List<ParseTreeNode> getArguments(final MdxParser parser) {
        final List<ParseTreeNode> arguments = new ArrayList<ParseTreeNode>();
        final ParseTreeNode filterExp = parser.parseExpression(this.filterExpression);
        arguments.add(filterExp);
        return arguments;
    }
    
    @Override
    public IFilterFunction.MdxFunctionType getFunctionType() {
        return this.type;
    }
}
