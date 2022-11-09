// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.mdx;

import org.olap4j.mdx.ParseTreeNode;
import java.util.List;
import org.olap4j.mdx.parser.MdxParser;

public interface IFilterFunction
{
    MdxFunctionType getFunctionType();
    
    List<ParseTreeNode> getArguments(final MdxParser p0);
    
    ParseTreeNode visit(final MdxParser p0, final ParseTreeNode p1);
    
    public enum MdxFunctionType
    {
        Filter, 
        TopCount, 
        TopPercent, 
        TopSum, 
        BottomCount, 
        BottomPercent, 
        BottomSum;
    }
}
