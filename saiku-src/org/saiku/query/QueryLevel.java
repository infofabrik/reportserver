// 
// Decompiled by Procyon v0.5.36
// 

package org.saiku.query;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import org.olap4j.metadata.Member;
import java.util.List;
import org.olap4j.metadata.Level;
import org.olap4j.impl.Named;

public class QueryLevel extends AbstractQuerySet implements Named
{
    private final QueryHierarchy hierarchy;
    private final Level level;
    private List<Member> inclusions;
    private List<Member> exclusions;
    private Member rangeStart;
    private Member rangeEnd;
    private String rangeStartExpr;
    private String rangeEndExpr;
    private String rangeStartSyn;
    private String rangeEndSyn;
    private String parameterName;
    private Parameter.SelectionType parameterSelectionType;
    
    public QueryLevel(final QueryHierarchy hierarchy, final Level level) {
        this.inclusions = new ArrayList<Member>();
        this.exclusions = new ArrayList<Member>();
        this.rangeStart = null;
        this.rangeEnd = null;
        this.rangeStartExpr = null;
        this.rangeEndExpr = null;
        this.parameterName = null;
        this.parameterSelectionType = Parameter.SelectionType.INCLUSION;
        this.hierarchy = hierarchy;
        this.level = level;
    }
    
    public QueryHierarchy getQueryHierarchy() {
        return this.hierarchy;
    }
    
    @Override
    public String getName() {
        return this.level.getName();
    }
    
    public String getUniqueName() {
        return this.level.getUniqueName();
    }
    
    public String getCaption() {
        return this.level.getCaption();
    }
    
    @Override
    public boolean isSimple() {
        return super.isSimple() && (this.level.getLevelType().equals((Object)Level.Type.ALL) || (this.inclusions.isEmpty() && this.exclusions.isEmpty() && this.rangeStart == null && this.rangeEnd == null && this.rangeStartExpr == null && this.rangeEndExpr == null)) && (!this.hasParameter() || this.hierarchy.getQuery().getParameter(this.getParameterName()) == null);
    }
    
    public boolean isRange() {
        return (this.rangeStart != null && this.rangeEnd != null) || this.rangeStartExpr != null || this.rangeEndExpr != null;
    }
    
    public Level getLevel() {
        return this.level;
    }
    
    public List<Member> getInclusions() {
        return this.inclusions;
    }
    
    public List<Member> getExclusions() {
        return this.exclusions;
    }
    
    public Member getRangeStart() {
        return this.rangeStart;
    }
    
    public Member getRangeEnd() {
        return this.rangeEnd;
    }
    
    public String getRangeStartExpr() {
        return this.rangeStartExpr;
    }
    
    public String getRangeEndExpr() {
        return this.rangeEndExpr;
    }
    
    public String getRangeStartSyn() {
        return this.rangeStartSyn;
    }
    
    public String getRangeEndSyn() {
        return this.rangeEndSyn;
    }
    
    protected void clearSelections() {
        this.inclusions.clear();
        this.exclusions.clear();
        this.rangeStart = null;
        this.rangeStartExpr = null;
        this.rangeStartSyn = null;
        this.rangeEnd = null;
        this.rangeEndExpr = null;
        this.rangeEndSyn = null;
    }
    
    protected void include(final Member m) {
        if (!this.inclusions.contains(m)) {
            this.inclusions.add(m);
        }
    }
    
    protected void exclude(final Member m) {
        if (this.inclusions.contains(m)) {
            this.inclusions.remove(m);
        }
        if (!this.exclusions.contains(m)) {
            this.exclusions.add(m);
        }
    }
    
    protected void setRange(final Member start, final Member end) {
        this.rangeStart = start;
        this.rangeEnd = end;
    }
    
    public void setRangeSynonyms(final String startSynonym, final String endSynonym) {
        this.rangeStartSyn = startSynonym;
        this.rangeEndSyn = endSynonym;
    }
    
    public void setRangeStartSynonym(final String startSyn) {
        this.rangeStartSyn = startSyn;
    }
    
    public void setRangeEndSynonym(final String endSyn) {
        this.rangeEndSyn = endSyn;
    }
    
    public void setRangeStartExpr(final String startExp) {
        this.rangeStart = null;
        this.rangeStartExpr = startExp;
    }
    
    public void setRangeEndExpr(final String endExp) {
        this.rangeEnd = null;
        this.rangeEndExpr = endExp;
    }
    
    public void setRangeExpressions(final String startExpr, final String endExpr) {
        this.rangeStart = null;
        this.rangeEnd = null;
        this.rangeStartExpr = startExpr;
        this.rangeEndExpr = endExpr;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = 31 * result + ((this.level == null) ? 0 : this.level.getUniqueName().hashCode());
        return result;
    }
    
    @Override
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
        final QueryLevel other = (QueryLevel)obj;
        if (this.level == null) {
            if (other.level != null) {
                return false;
            }
        }
        else if (!this.level.getUniqueName().equals(other.getLevel().getUniqueName())) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        return this.level.getUniqueName();
    }
    
    public void setParameterName(final String parameter) {
        this.parameterName = parameter;
    }
    
    public void setParameterSelectionType(final Parameter.SelectionType selectionType) {
        this.parameterSelectionType = selectionType;
    }
    
    public String getParameterName() {
        return this.parameterName;
    }
    
    public Parameter.SelectionType getParameterSelectionType() {
        return this.parameterSelectionType;
    }
    
    public boolean hasParameter() {
        return StringUtils.isNotBlank(this.parameterName);
    }
}
