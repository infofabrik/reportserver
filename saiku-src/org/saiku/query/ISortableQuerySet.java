// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

public interface ISortableQuerySet extends IQuerySet
{
    void sort(final SortOrder p0);
    
    void sort(final SortOrder p0, final String p1);
    
    SortOrder getSortOrder();
    
    String getSortEvaluationLiteral();
    
    void clearSort();
    
    HierarchizeMode getHierarchizeMode();
    
    void setHierarchizeMode(final HierarchizeMode p0);
    
    void clearHierarchizeMode();
    
    public enum HierarchizeMode
    {
        PRE, 
        POST;
    }
}
