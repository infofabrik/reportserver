// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.mdx;

import java.util.List;
import org.olap4j.mdx.ParseRegion;
import org.olap4j.mdx.CallNode;
import org.olap4j.mdx.Syntax;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.mdx.parser.MdxParser;

public abstract class AbstractFilterFunction implements IFilterFunction
{
    @Override
    public ParseTreeNode visit(final MdxParser parser, final ParseTreeNode parent) {
        final List<ParseTreeNode> arguments = this.getArguments(parser);
        arguments.add(0, parent);
        return (ParseTreeNode)new CallNode((ParseRegion)null, this.getFunctionType().toString(), Syntax.Function, (List)arguments);
    }
}
