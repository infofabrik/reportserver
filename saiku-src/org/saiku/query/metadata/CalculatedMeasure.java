// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.metadata;

import org.olap4j.impl.NamedListImpl;
import org.olap4j.metadata.Property;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.metadata.NamedList;
import java.util.List;
import org.olap4j.OlapException;
import org.olap4j.metadata.Member;
import org.olap4j.mdx.IdentifierNode;
import java.util.HashMap;
import org.olap4j.metadata.Datatype;
import org.olap4j.metadata.Level;
import java.util.Map;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Dimension;
import org.olap4j.impl.Named;
import org.olap4j.metadata.Measure;

public class CalculatedMeasure implements Measure, Named, Calculated
{
    private Dimension dimension;
    private Hierarchy hierarchy;
    private String name;
    private String uniqueName;
    private String formula;
    private Map<String, String> properties;
    private String description;
    private Level level;
    private Datatype datatype;
    
    public CalculatedMeasure(final Hierarchy hierarchy, final String name, final String description, final String formula, final Map<String, String> properties) {
        this.properties = new HashMap<String, String>();
        this.dimension = hierarchy.getDimension();
        this.hierarchy = hierarchy;
        this.level = (Level)hierarchy.getLevels().get(0);
        this.name = name;
        this.description = description;
        this.formula = formula;
        this.uniqueName = IdentifierNode.ofNames(new String[] { hierarchy.getName(), name }).toString();
        if (properties != null) {
            this.properties.putAll(properties);
        }
    }
    
    public Dimension getDimension() {
        return this.dimension;
    }
    
    public Hierarchy getHierarchy() {
        return this.hierarchy;
    }
    
    public String getFormula() {
        return this.formula;
    }
    
    public Member.Type getMemberType() {
        return Member.Type.FORMULA;
    }
    
    public Map<String, String> getFormatProperties() {
        return this.properties;
    }
    
    public String getFormatPropertyValue(final String key) throws OlapException {
        if (this.properties.containsKey(key)) {
            return this.properties.get(key);
        }
        return null;
    }
    
    public void setFormatProperty(final String key, final String value) throws OlapException {
        this.properties.put(key, value);
    }
    
    public String getCaption() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUniqueName() {
        return this.uniqueName;
    }
    
    public Measure.Aggregator getAggregator() {
        return Measure.Aggregator.CALCULATED;
    }
    
    public boolean isVisible() {
        return true;
    }
    
    public List<Member> getAncestorMembers() {
        throw new UnsupportedOperationException();
    }
    
    public int getChildMemberCount() throws OlapException {
        throw new UnsupportedOperationException();
    }
    
    public NamedList<? extends Member> getChildMembers() throws OlapException {
        throw new UnsupportedOperationException();
    }
    
    public Member getDataMember() {
        throw new UnsupportedOperationException();
    }
    
    public int getDepth() {
        throw new UnsupportedOperationException();
    }
    
    public ParseTreeNode getExpression() {
        throw new UnsupportedOperationException();
    }
    
    public Level getLevel() {
        return this.level;
    }
    
    public int getOrdinal() {
        throw new UnsupportedOperationException();
    }
    
    public Member getParentMember() {
        throw new UnsupportedOperationException();
    }
    
    public String getPropertyFormattedValue(final Property property) throws OlapException {
        return String.valueOf(this.getPropertyValue(property));
    }
    
    public int getSolveOrder() {
        throw new UnsupportedOperationException();
    }
    
    public boolean isAll() {
        return false;
    }
    
    public boolean isCalculated() {
        return true;
    }
    
    public boolean isCalculatedInQuery() {
        return true;
    }
    
    public boolean isChildOrEqualTo(final Member arg0) {
        return false;
    }
    
    public boolean isHidden() {
        return false;
    }
    
    public Datatype getDatatype() {
        if (this.datatype != null) {
            return this.datatype;
        }
        return Datatype.STRING;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.uniqueName == null) ? 0 : this.uniqueName.hashCode());
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
        final CalculatedMeasure other = (CalculatedMeasure)obj;
        if (this.uniqueName == null) {
            if (other.uniqueName != null) {
                return false;
            }
        }
        else if (!this.uniqueName.equals(other.uniqueName)) {
            return false;
        }
        return true;
    }
    
    @Deprecated
    public NamedList<Property> getProperties() {
        final NamedList<Property> l = (NamedList<Property>)new NamedListImpl();
        return l;
    }
    
    @Deprecated
    public Object getPropertyValue(final Property p) throws OlapException {
        if (this.properties.containsKey(p.getName())) {
            return this.properties.get(p.getName());
        }
        return null;
    }
    
    @Deprecated
    public void setProperty(final Property arg0, final Object arg1) throws OlapException {
    }
}
