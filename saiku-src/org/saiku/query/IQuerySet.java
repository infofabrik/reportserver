// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import java.util.List;
import org.saiku.query.mdx.IFilterFunction;

public interface IQuerySet
{
    String getName();
    
    boolean isSimple();
    
    void setMdxSetExpression(final String p0);
    
    String getMdxSetExpression();
    
    boolean isMdxSetExpression();
    
    void addFilter(final IFilterFunction p0);
    
    void setFilter(final int p0, final IFilterFunction p1);
    
    List<IFilterFunction> getFilters();
    
    void clearFilters();
}
