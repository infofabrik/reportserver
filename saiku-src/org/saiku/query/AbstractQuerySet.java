// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import org.saiku.query.mdx.IFilterFunction;
import java.util.List;

public abstract class AbstractQuerySet implements IQuerySet
{
    private String mdxExpression;
    private List<IFilterFunction> filters;
    
    public AbstractQuerySet() {
        this.filters = new ArrayList<IFilterFunction>();
    }
    
    @Override
    public abstract String getName();
    
    @Override
    public boolean isSimple() {
        return this.mdxExpression == null && this.filters.isEmpty();
    }
    
    @Override
    public void setMdxSetExpression(final String mdxSetExpression) {
        this.mdxExpression = mdxSetExpression;
    }
    
    @Override
    public String getMdxSetExpression() {
        return this.mdxExpression;
    }
    
    @Override
    public boolean isMdxSetExpression() {
        return this.mdxExpression != null;
    }
    
    @Override
    public void addFilter(final IFilterFunction filter) {
        this.filters.add(filter);
    }
    
    @Override
    public void setFilter(final int index, final IFilterFunction filter) {
        this.filters.set(index, filter);
    }
    
    @Override
    public List<IFilterFunction> getFilters() {
        return this.filters;
    }
    
    @Override
    public void clearFilters() {
        this.filters.clear();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.mdxExpression == null) ? 0 : this.mdxExpression.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final AbstractQuerySet other = (AbstractQuerySet)obj;
        if (this.mdxExpression == null) {
            if (other.mdxExpression != null) {
                return false;
            }
        }
        else if (!this.mdxExpression.equals(other.mdxExpression)) {
            return false;
        }
        return StringUtils.equals(this.getName(), other.getName());
    }
}
