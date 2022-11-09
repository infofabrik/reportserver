// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import java.util.List;
import java.util.Locale;
import org.olap4j.impl.NamedListImpl;
import org.olap4j.Axis;
import org.olap4j.metadata.NamedList;

public class QueryAxis extends AbstractSortableQuerySet
{
    protected final NamedList<QueryHierarchy> hierarchies;
    private final Query query;
    protected Axis location;
    private boolean nonEmpty;
    
    public QueryAxis(final Query query, final Axis location) {
        this.hierarchies = (NamedList<QueryHierarchy>)new NamedListImpl();
        this.location = null;
        this.query = query;
        this.location = location;
    }
    
    public Axis getLocation() {
        return this.location;
    }
    
    public boolean isNonEmpty() {
        return this.nonEmpty;
    }
    
    public void setNonEmpty(final boolean nonEmpty) {
        this.nonEmpty = nonEmpty;
    }
    
    @Override
    public String getName() {
        return this.location.getCaption((Locale)null);
    }
    
    public Query getQuery() {
        return this.query;
    }
    
    public boolean isLowestLevelsOnly() {
        return this.query.isLowestLevelsOnly() | Axis.FILTER.equals((Object)this.location);
    }
    
    public List<QueryHierarchy> getQueryHierarchies() {
        return (List<QueryHierarchy>)this.hierarchies;
    }
    
    public void addHierarchy(final QueryHierarchy hierarchy) {
        this.addHierarchy(-1, hierarchy);
    }
    
    public void addHierarchy(final int index, final QueryHierarchy hierarchy) {
        if (this.getQueryHierarchies().contains(hierarchy)) {
            throw new IllegalStateException("hierarchy already on this axis");
        }
        if (hierarchy.getAxis() != null && hierarchy.getAxis() != this) {
            hierarchy.getAxis().getQueryHierarchies().remove(hierarchy);
        }
        hierarchy.setAxis(this);
        if (index >= this.hierarchies.size() || index < 0) {
            this.hierarchies.add((QueryHierarchy)hierarchy);
        }
        else {
            this.hierarchies.add(index, (QueryHierarchy)hierarchy);
        }
    }
    
    public void removeHierarchy(final QueryHierarchy hierarchy) {
        hierarchy.setAxis(null);
        this.getQueryHierarchies().remove(hierarchy);
    }
}
