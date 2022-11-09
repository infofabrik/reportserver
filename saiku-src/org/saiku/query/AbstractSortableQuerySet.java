// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

public abstract class AbstractSortableQuerySet extends AbstractQuerySet implements ISortableQuerySet
{
    private SortOrder sortOrder;
    private String sortEvaluationLiteral;
    private HierarchizeMode hierarchizeMode;
    
    @Override
    public void sort(final SortOrder order) {
        this.sortOrder = order;
    }
    
    @Override
    public void sort(final SortOrder order, final String sortEvaluationLiteral) {
        this.sortOrder = order;
        this.sortEvaluationLiteral = sortEvaluationLiteral;
    }
    
    @Override
    public SortOrder getSortOrder() {
        return this.sortOrder;
    }
    
    @Override
    public String getSortEvaluationLiteral() {
        return this.sortEvaluationLiteral;
    }
    
    @Override
    public void clearSort() {
        this.sortOrder = null;
        this.sortEvaluationLiteral = null;
    }
    
    @Override
    public HierarchizeMode getHierarchizeMode() {
        return this.hierarchizeMode;
    }
    
    @Override
    public void setHierarchizeMode(final HierarchizeMode hierarchizeMode) {
        this.hierarchizeMode = hierarchizeMode;
    }
    
    @Override
    public void clearHierarchizeMode() {
        this.hierarchizeMode = null;
    }
}
