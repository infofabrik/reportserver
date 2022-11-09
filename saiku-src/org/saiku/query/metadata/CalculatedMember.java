// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query.metadata;

import org.olap4j.impl.NamedListImpl;
import org.olap4j.metadata.Property;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.metadata.NamedList;
import org.olap4j.metadata.Measure;
import org.olap4j.OlapException;
import java.util.List;
import java.util.Collection;
import org.olap4j.mdx.IdentifierSegment;
import java.util.ArrayList;
import org.olap4j.mdx.IdentifierNode;
import java.util.HashMap;
import java.util.Iterator;
import org.olap4j.metadata.Level;
import java.util.Map;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Dimension;
import org.olap4j.impl.Named;
import org.olap4j.metadata.Member;

public class CalculatedMember implements Member, Named, Calculated
{
    private final Member parentMember;
    private Dimension dimension;
    private Hierarchy hierarchy;
    private String name;
    private String uniqueName;
    private Member.Type memberType;
    private String formula;
    private Map<String, String> properties;
    private String description;
    private Level level;
    
    public CalculatedMember(final Dimension dimension, final Hierarchy hierarchy, final String name, final String description, final Member parentMember, final Member.Type memberType, final String formula, final Map<String, String> properties, final String l, final boolean mondrian3) {
        this(dimension, hierarchy, name, description, parentMember, memberType, formula, properties, mondrian3);
        if (l != null && !l.equals("")) {
            for (final Level level : hierarchy.getLevels()) {
                if (level.getUniqueName().equals(l)) {
                    this.level = level;
                }
            }
        }
        else {
            this.level = (Level)hierarchy.getLevels().get(0);
        }
    }
    
    public CalculatedMember(final Dimension dimension, final Hierarchy hierarchy, final String name, final String description, final Member parentMember, final Member.Type memberType, final String formula, final Map<String, String> properties, final boolean mondrian3) {
        this.properties = new HashMap<String, String>();
        this.dimension = dimension;
        this.hierarchy = hierarchy;
        this.level = (Level)hierarchy.getLevels().get(0);
        this.name = name;
        this.description = description;
        this.memberType = memberType;
        this.formula = formula;
        if (parentMember == null) {
            if (mondrian3) {
                this.uniqueName = IdentifierNode.ofNames(new String[] { hierarchy.getName(), name }).toString();
            }
            else {
                this.uniqueName = IdentifierNode.ofNames(new String[] { hierarchy.getDimension().getName(), hierarchy.getName(), name }).toString();
            }
        }
        else {
            final IdentifierNode parent = IdentifierNode.parseIdentifier(parentMember.getUniqueName());
            final IdentifierNode cm = IdentifierNode.ofNames(new String[] { name });
            final List<IdentifierSegment> segmentList = new ArrayList<IdentifierSegment>();
            segmentList.addAll(parent.getSegmentList());
            segmentList.addAll(cm.getSegmentList());
            final StringBuilder buf = new StringBuilder();
            for (final IdentifierSegment segment : segmentList) {
                if (buf.length() > 0) {
                    buf.append('.');
                }
                buf.append(segment.toString());
            }
            this.uniqueName = buf.toString();
        }
        this.parentMember = parentMember;
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
        return this.memberType;
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
        return 0;
    }
    
    public NamedList<? extends Member> getChildMembers() throws OlapException {
        throw new UnsupportedOperationException();
    }
    
    public Member getDataMember() {
        throw new UnsupportedOperationException();
    }
    
    public int getDepth() {
        return 0;
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
        return this.parentMember;
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
    
    @Deprecated
    public NamedList<Property> getProperties() {
        final NamedList l = (NamedList)new NamedListImpl((Collection)this.properties.entrySet());
        return (NamedList<Property>)l;
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
        final CalculatedMember other = (CalculatedMember)obj;
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
}
