// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import java.util.ArrayList;
import org.olap4j.Axis;
import org.olap4j.metadata.Measure;
import java.util.List;
import org.olap4j.impl.Named;

public class QueryDetails implements Named
{
    protected List<Measure> measures;
    private Location location;
    private Axis axis;
    private Query query;
    
    public QueryDetails(final Query query, final Axis axis) {
        this.measures = new ArrayList<Measure>();
        this.location = Location.BOTTOM;
        this.axis = axis;
        this.query = query;
    }
    
    public void add(final Measure measure) {
        if (!this.measures.contains(measure)) {
            this.measures.add(measure);
        }
    }
    
    public void set(final Measure measure, final int position) {
        if (!this.measures.contains(measure)) {
            this.measures.add(position, measure);
        }
        else {
            final int oldindex = this.measures.indexOf(measure);
            if (oldindex <= position) {
                this.measures.add(position, measure);
                this.measures.remove(oldindex);
            }
        }
    }
    
    public void remove(final Measure measure) {
        this.measures.remove(measure);
    }
    
    public List<Measure> getMeasures() {
        return this.measures;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public Axis getAxis() {
        return this.axis;
    }
    
    public void setAxis(final Axis axis) {
        this.axis = axis;
    }
    
    public String getName() {
        return "DETAILS";
    }
    
    public enum Location
    {
        TOP, 
        BOTTOM;
    }
}
