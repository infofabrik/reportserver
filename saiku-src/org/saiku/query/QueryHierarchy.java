// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.impl.IdentifierParser;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import org.olap4j.OlapException;
import org.olap4j.metadata.Member;
import org.olap4j.metadata.Level;
import org.olap4j.impl.NamedListImpl;
import org.saiku.query.metadata.CalculatedMember;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.NamedList;
import org.olap4j.impl.Named;

public class QueryHierarchy extends AbstractSortableQuerySet implements Named
{
    private final NamedList<RootMember> rootMembers;
    protected QueryAxis axis;
    private final Query query;
    private final Hierarchy hierarchy;
    private NamedList<QueryLevel> queryLevels;
    private NamedList<QueryLevel> activeLevels;
    private NamedList<CalculatedMember> calculatedMembers;
    private NamedList<CalculatedMember> activeCalculatedMembers;
    private boolean consistent;
    private boolean visualTotals;
    private String visualTotalsPattern;
    
    public QueryHierarchy(final Query query, final Hierarchy hierarchy) {
        this.rootMembers = (NamedList<RootMember>)new NamedListImpl();
        this.queryLevels = (NamedList<QueryLevel>)new NamedListImpl();
        this.activeLevels = (NamedList<QueryLevel>)new NamedListImpl();
        this.calculatedMembers = (NamedList<CalculatedMember>)new NamedListImpl();
        this.activeCalculatedMembers = (NamedList<CalculatedMember>)new NamedListImpl();
        this.consistent = true;
        this.visualTotals = false;
        this.query = query;
        this.hierarchy = hierarchy;
        for (final Level level : hierarchy.getLevels()) {
            final QueryLevel queryLevel = new QueryLevel(this, level);
            this.queryLevels.add((QueryLevel)queryLevel);
        }
        try {
            final NamedList<Member> members = (NamedList<Member>)hierarchy.getRootMembers();
            for (final Member member : members) {
                final RootMember rootMember = new RootMember(this, member);
                this.rootMembers.add((RootMember)rootMember);
            }
        }
        catch (OlapException e) {
            e.printStackTrace();
        }
    }
    
    public Query getQuery() {
        return this.query;
    }
    
    public QueryAxis getAxis() {
        return this.axis;
    }
    
    protected void setAxis(final QueryAxis axis) {
        this.axis = axis;
    }
    
    public String getName() {
        return this.hierarchy.getName();
    }
    
    public String getUniqueName() {
        return this.hierarchy.getUniqueName();
    }
    
    public String getCaption() {
        return this.hierarchy.getCaption();
    }
    
    public boolean isConsistent() {
        return this.consistent;
    }
    
    public void setConsistent(final boolean consistent) {
        this.consistent = consistent;
    }
    
    public boolean isVisualTotals() {
        return this.visualTotals | this.query.isVisualTotals();
    }
    
    public void setVisualTotals(final boolean visualTotals) {
        if (!(this.visualTotals = visualTotals)) {
            this.visualTotalsPattern = null;
        }
    }
    
    public void setVisualTotalsPattern(final String pattern) {
        this.visualTotalsPattern = pattern;
        this.visualTotals = true;
    }
    
    public String getVisualTotalsPattern() {
        return (this.visualTotalsPattern == null) ? this.query.getVisualTotalsPattern() : this.visualTotalsPattern;
    }
    
    public boolean needsHierarchize() {
        return (this.visualTotals | this.activeLevels.size() > 1) && this.getHierarchizeMode() == null;
    }
    
    public Hierarchy getHierarchy() {
        return this.hierarchy;
    }
    
    public void addCalculatedMember(final CalculatedMember cm) {
        this.calculatedMembers.add((CalculatedMember)cm);
    }
    
    public NamedList<CalculatedMember> getCalculatedMembers() {
        return this.calculatedMembers;
    }
    
    public List<CalculatedMember> getActiveCalculatedMembers() {
        return (List<CalculatedMember>)this.activeCalculatedMembers;
    }
    
    public List<QueryLevel> getActiveQueryLevels() {
        Collections.sort((NamedList<QueryLevel>)this.activeLevels, new SaikuQueryLevelComparator());
        return (List<QueryLevel>)this.activeLevels;
    }
    
    public QueryLevel getActiveLevel(final String levelName) {
        return (QueryLevel)this.activeLevels.get(levelName);
    }
    
    public QueryLevel includeLevel(final String levelName) {
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(levelName);
        if (ql != null && !this.activeLevels.contains((Object)ql)) {
            this.activeLevels.add((QueryLevel)ql);
        }
        return ql;
    }
    
    public QueryLevel includeMemberLevel(final String levelName) {
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(levelName);
        if (ql != null && !this.activeLevels.contains((Object)ql)) {
            this.activeLevels.add((QueryLevel)ql);
        }
        return ql;
    }
    
    public QueryLevel includeLevel(final Level l) throws OlapException {
        if (!l.getHierarchy().equals(this.hierarchy)) {
            throw new OlapException("You cannot include level " + l.getUniqueName() + " on hierarchy " + this.hierarchy.getUniqueName());
        }
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(l.getName());
        if (ql != null && !this.activeLevels.contains((Object)l)) {
            this.activeLevels.add((QueryLevel)ql);
        }
        return ql;
    }
    
    public void excludeLevel(final String levelName) {
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(levelName);
        if (ql != null && this.activeLevels.contains((Object)ql)) {
            this.activeLevels.remove((Object)ql);
        }
    }
    
    public void excludeLevel(final Level l) throws OlapException {
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(l.getName());
        if (ql != null && !this.activeLevels.contains((Object)l)) {
            this.activeLevels.remove((Object)ql);
        }
    }
    
    public void includeMembers(final List<Member> members) throws OlapException {
        for (final Member m : members) {
            this.includeMember(m);
        }
    }
    
    public void includeMember(final String uniqueMemberName) throws OlapException {
        final List<IdentifierSegment> nameParts = (List<IdentifierSegment>)IdentifierParser.parseIdentifier(uniqueMemberName);
        this.includeMember(nameParts);
    }
    
    public void includeMember(final List<IdentifierSegment> nameParts) throws OlapException {
        final Member member = this.query.getCube().lookupMember((List)nameParts);
        if (member == null) {
            throw new OlapException("Unable to find a member with name " + nameParts);
        }
        this.includeMember(member);
    }
    
    public void includeCalculatedMember(final CalculatedMember m, final boolean include) throws OlapException {
        final Hierarchy h = m.getHierarchy();
        if (!h.equals(this.hierarchy)) {
            throw new OlapException("You cannot include the calculated member " + m.getUniqueName() + " on hierarchy " + this.hierarchy.getUniqueName());
        }
        if (!this.calculatedMembers.contains((Object)m)) {
            this.calculatedMembers.add((CalculatedMember)m);
        }
        if (include) {
            final QueryLevel ql = (QueryLevel)this.queryLevels.get(m.getLevel().getName());
            ql.include((Member)m);
        }
        else {
            this.activeCalculatedMembers.add((CalculatedMember)m);
        }
    }
    
    public void excludeCalculatedMember(final CalculatedMember m) throws OlapException {
        this.activeCalculatedMembers.remove((Object)m);
    }
    
    public void includeMember(final Member m) throws OlapException {
        final Level l = m.getLevel();
        if (!l.getHierarchy().equals(this.hierarchy)) {
            throw new OlapException("You cannot include member " + m.getUniqueName() + " on hierarchy " + this.hierarchy.getUniqueName());
        }
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(l.getName());
        if (!this.activeLevels.contains((Object)ql)) {
            this.activeLevels.add((QueryLevel)ql);
        }
        ql.include(m);
    }
    
    public void includeRange(final String uniqueMemberNameStart, final String uniqueMemberNameEnd) throws OlapException {
        final List<IdentifierSegment> namePartsStart = (List<IdentifierSegment>)IdentifierParser.parseIdentifier(uniqueMemberNameStart);
        final List<IdentifierSegment> namePartsEnd = (List<IdentifierSegment>)IdentifierParser.parseIdentifier(uniqueMemberNameEnd);
        this.includeRange(namePartsStart, namePartsEnd);
    }
    
    public void includeRange(final List<IdentifierSegment> namePartsStart, final List<IdentifierSegment> namePartsEnd) throws OlapException {
        final Member rangeStart = this.query.getCube().lookupMember((List)namePartsStart);
        final Member rangeEnd = this.query.getCube().lookupMember((List)namePartsEnd);
        if (rangeStart == null) {
            throw new OlapException("Unable to find a member with name " + rangeStart);
        }
        if (rangeEnd == null) {
            throw new OlapException("Unable to find a member with name " + rangeEnd);
        }
        this.includeRange(rangeStart, rangeEnd);
    }
    
    public void includeRange(final Member start, final Member end) throws OlapException {
        final Level l = start.getLevel();
        if (!start.getLevel().equals(end.getLevel())) {
            throw new OlapException("A range selection must include members from the same level (" + start.getLevel().getName() + " vs. " + end.getLevel().getName());
        }
        if (!l.getHierarchy().equals(this.hierarchy)) {
            throw new OlapException("Hierarchy not matching. You cannot include a range selection for " + start.getUniqueName() + " and " + end.getUniqueName() + " on hierarchy " + this.hierarchy.getUniqueName());
        }
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(l.getName());
        if (!this.activeLevels.contains((Object)ql)) {
            this.activeLevels.add((QueryLevel)ql);
        }
        ql.setRange(start, end);
    }
    
    public void excludeMember(final String uniqueMemberName) throws OlapException {
        final List<IdentifierSegment> nameParts = (List<IdentifierSegment>)IdentifierParser.parseIdentifier(uniqueMemberName);
        this.excludeMember(nameParts);
    }
    
    public void excludeMember(final List<IdentifierSegment> nameParts) throws OlapException {
        final Member member = this.query.getCube().lookupMember((List)nameParts);
        if (member == null) {
            throw new OlapException("Unable to find a member with name " + nameParts);
        }
        this.excludeMember(member);
    }
    
    public void excludeMembers(final List<Member> members) {
        for (final Member m : members) {
            this.excludeMember(m);
        }
    }
    
    public void excludeMember(final Member m) {
        final Level l = m.getLevel();
        if (!l.getHierarchy().equals(this.hierarchy)) {
            throw new IllegalArgumentException("You cannot exclude member " + m.getUniqueName() + " on hierarchy " + this.hierarchy.getUniqueName());
        }
        final QueryLevel ql = (QueryLevel)this.queryLevels.get(l.getName());
        if (!this.activeLevels.contains((Object)ql)) {
            this.activeLevels.add((QueryLevel)ql);
        }
        ql.exclude(m);
    }
    
    public void clearSelection() {
        if (this.activeLevels != null) {
            for (final QueryLevel ql : this.activeLevels) {
                ql.clearSelections();
            }
        }
        this.activeLevels.clear();
    }
    
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = 31 * result + ((this.hierarchy == null) ? 0 : this.hierarchy.getUniqueName().hashCode());
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final QueryHierarchy other = (QueryHierarchy)obj;
        if (this.hierarchy == null) {
            if (other.hierarchy != null) {
                return false;
            }
        }
        else if (!this.hierarchy.getUniqueName().equals(other.hierarchy.getUniqueName())) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        return this.hierarchy.getUniqueName();
    }
    
    private class SaikuQueryLevelComparator implements Comparator<QueryLevel>
    {
        @Override
        public int compare(final QueryLevel o1, final QueryLevel o2) {
            if (o1 != null && o2 != null) {
                return o1.getLevel().getDepth() - o2.getLevel().getDepth();
            }
            return 0;
        }
    }
}
