/*  
 *   Copyright 2014 Paul Stoellberger
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.saiku.query;

import org.saiku.query.metadata.CalculatedMember;

import org.olap4j.OlapException;
import org.olap4j.impl.IdentifierParser;
import org.olap4j.impl.Named;
import org.olap4j.impl.NamedListImpl;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Member;
import org.olap4j.metadata.NamedList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QueryHierarchy extends AbstractSortableQuerySet implements Named {

  private final NamedList<RootMember> rootMembers = new NamedListImpl<RootMember>();
  protected QueryAxis axis;
    private final Query query;
	private final Hierarchy hierarchy;
	
	private NamedList<QueryLevel> queryLevels = new NamedListImpl<QueryLevel>();
	
	private NamedList<QueryLevel> activeLevels = new NamedListImpl<QueryLevel>();
	
	private NamedList<CalculatedMember> calculatedMembers = new NamedListImpl<CalculatedMember>();

	private NamedList<CalculatedMember> activeCalculatedMembers = new NamedListImpl<CalculatedMember>();
	
	private boolean consistent = true;
	
	private boolean visualTotals = false;
	private String visualTotalsPattern;

	public QueryHierarchy(Query query, Hierarchy hierarchy) {
        super();
        this.query = query;
        this.hierarchy = hierarchy;
        for (Level level : hierarchy.getLevels()) {
            QueryLevel queryLevel = new QueryLevel(this, level);
            queryLevels.add(queryLevel);
        }

	  try {
		NamedList<Member> members = hierarchy.getRootMembers();
		for(Member member:members){
		  RootMember rootMember = new RootMember(this, member);
		  rootMembers.add(rootMember);
		}
	  } catch (OlapException e) {
		e.printStackTrace();
	  }

	}

    public Query getQuery() {
        return query;
    }

    public QueryAxis getAxis() {
        return axis;
    }
    
    /**
     * Only internal use!
     * @param axis
     */
    protected void setAxis(QueryAxis axis) {
        this.axis = axis;
    }

    public String getName() {
        return hierarchy.getName();
    }
    
    public String getUniqueName() {
    	return hierarchy.getUniqueName();
    }
    
    public String getCaption() {
    	return hierarchy.getCaption();
    }
    
    public boolean isConsistent() {
    	return consistent;
    }
    
    public void setConsistent(boolean consistent) {
    	this.consistent = consistent;
    }
    
    /**
     * Should the hierarchy return visual totals
	 * @return is visualTotals
	 */
	public boolean isVisualTotals() {
		return (visualTotals | query.isVisualTotals());
	}

	/**
	 * @param visualTotals should the hierarchy use visual totals
	 */
	public void setVisualTotals(boolean visualTotals) {
		this.visualTotals = visualTotals;
		if(!visualTotals) {
			this.visualTotalsPattern = null;
		}
	}
	
	public void setVisualTotalsPattern(String pattern) {
		this.visualTotalsPattern = pattern;
		this.visualTotals = true;
	}
	
	public String getVisualTotalsPattern() {
		return (visualTotalsPattern == null ? query.getVisualTotalsPattern() : visualTotalsPattern);
	}
	
	public boolean needsHierarchize() {
		return ((visualTotals | activeLevels.size() > 1) 
				&& getHierarchizeMode() == null);
	}

    /**
     * Returns the underlying Hierarchy object onto which
     * this query Hierarchy is based.
     * <p>Returns a mutable object so operations on it have
     * unpredictable consequences.
     * @return The underlying Hierarchy representation.
     */
    public Hierarchy getHierarchy() {
        return hierarchy;
    }
    
    public void addCalculatedMember(CalculatedMember cm) {
    	calculatedMembers.add(cm);
    }
    
    public NamedList<CalculatedMember> getCalculatedMembers() {
    	return calculatedMembers;
    }
    
    public List<CalculatedMember> getActiveCalculatedMembers() {
    	return activeCalculatedMembers;
    }
    
    
    public List<QueryLevel> getActiveQueryLevels() {
    	Collections.sort(activeLevels, new SaikuQueryLevelComparator());
    	return activeLevels;
    }
    

    public QueryLevel getActiveLevel(String levelName) {
    	return activeLevels.get(levelName);

    }
    
    public QueryLevel includeLevel(String levelName) {
    	QueryLevel ql = queryLevels.get(levelName);
    	if (ql != null && !activeLevels.contains(ql)) {
    		activeLevels.add(ql);
    	}
    	return ql;
    }
  public QueryLevel includeMemberLevel(String levelName) {
	QueryLevel ql = queryLevels.get(levelName);
	if (ql != null && !activeLevels.contains(ql)) {
	  activeLevels.add(ql);
	}
	return ql;
  }
    public QueryLevel includeLevel(Level l) throws OlapException {
    	if (!l.getHierarchy().equals(hierarchy)) {
    		throw new OlapException(
    				"You cannot include level " + l.getUniqueName() 
    				+ " on hierarchy " + hierarchy.getUniqueName());
    	}
    	QueryLevel ql = queryLevels.get(l.getName());
    	if (ql != null && !activeLevels.contains(l)) {
    		activeLevels.add(ql);
    	}
    	return ql;
    }

    public void excludeLevel(String levelName) {
    	QueryLevel ql = queryLevels.get(levelName);
    	if (ql != null && activeLevels.contains(ql)) {
    		activeLevels.remove(ql);
    	}
    }

    public void excludeLevel(Level l) throws OlapException {
    	QueryLevel ql = queryLevels.get(l.getName());
    	if (ql != null && !activeLevels.contains(l)) {
    		activeLevels.remove(ql);
    	}
    }

    
    public void includeMembers(List<Member> members) throws OlapException {
    	for (Member m : members) {
    		includeMember(m);
    	}
    }

    public void includeMember(String uniqueMemberName) throws OlapException {
    	List<IdentifierSegment> nameParts = IdentifierParser.parseIdentifier(uniqueMemberName);
    	this.includeMember(nameParts);
    }
    
    public void includeMember(List<IdentifierSegment> nameParts) throws OlapException {
        Member member = this.query.getCube().lookupMember(nameParts);
        if (member == null) {
            throw new OlapException(
                "Unable to find a member with name " + nameParts);
        }
        this.includeMember(member);
    }


    public void includeCalculatedMember(CalculatedMember m, boolean include) throws OlapException {
    	Hierarchy h = m.getHierarchy();
    	if (!h.equals(hierarchy)) {
    		throw new OlapException(
    				"You cannot include the calculated member " + m.getUniqueName() 
    				+ " on hierarchy " + hierarchy.getUniqueName());
    	}
    	if(!calculatedMembers.contains(m)) {
    		calculatedMembers.add(m);
    	}
		if(include){
		QueryLevel ql = queryLevels.get(m.getLevel().getName());

		ql.include(m);
		}
		else{
			activeCalculatedMembers.add(m);

		}

    }
    
    public void excludeCalculatedMember(CalculatedMember m) throws OlapException {
    	//calculatedMembers.remove(m);
    	activeCalculatedMembers.remove(m);
    }
    
    public void includeMember(Member m) throws OlapException {
    	Level l = m.getLevel();
    	if (!l.getHierarchy().equals(hierarchy)) {
    		throw new OlapException(
    				"You cannot include member " + m.getUniqueName() 
    				+ " on hierarchy " + hierarchy.getUniqueName());
    	}
    	QueryLevel ql = queryLevels.get(l.getName());
    	if (!activeLevels.contains(ql)) {
    		activeLevels.add(ql);
    	}
    	ql.include(m);
    }
    
    public void includeRange(String uniqueMemberNameStart, String uniqueMemberNameEnd) throws OlapException {
    	List<IdentifierSegment> namePartsStart = IdentifierParser.parseIdentifier(uniqueMemberNameStart);
    	List<IdentifierSegment> namePartsEnd = IdentifierParser.parseIdentifier(uniqueMemberNameEnd);
    	this.includeRange(namePartsStart, namePartsEnd);
    }
    
    public void includeRange(List<IdentifierSegment> namePartsStart, List<IdentifierSegment> namePartsEnd) throws OlapException {
        Member rangeStart = this.query.getCube().lookupMember(namePartsStart);
        Member rangeEnd = this.query.getCube().lookupMember(namePartsEnd);
        if (rangeStart == null) {
            throw new OlapException(
                "Unable to find a member with name " + rangeStart);
        }
        if (rangeEnd == null) {
            throw new OlapException(
                "Unable to find a member with name " + rangeEnd);
        }
        this.includeRange(rangeStart, rangeEnd);
    }

    
    public void includeRange(Member start, Member end) throws OlapException {
    	Level l = start.getLevel();
    	if (!start.getLevel().equals(end.getLevel())) {
    		throw new OlapException(
    				"A range selection must include members from the same level ("
    				+ start.getLevel().getName() + " vs. " + end.getLevel().getName());
    	}
    	if (!l.getHierarchy().equals(hierarchy)) {
    		throw new OlapException(
    				"Hierarchy not matching. You cannot include a range selection for " + start.getUniqueName() 
    				+ " and " + end.getUniqueName() + " on hierarchy " + hierarchy.getUniqueName());
    	}
    	QueryLevel ql = queryLevels.get(l.getName());
    	if (!activeLevels.contains(ql)) {
    		activeLevels.add(ql);
    	}
    	ql.setRange(start, end);
    }
    
    public void excludeMember(String uniqueMemberName) throws OlapException {
    	List<IdentifierSegment> nameParts = IdentifierParser.parseIdentifier(uniqueMemberName);
    	this.excludeMember(nameParts);
    }
    
    public void excludeMember(List<IdentifierSegment> nameParts) throws OlapException {
        Member member = this.query.getCube().lookupMember(nameParts);
        if (member == null) {
            throw new OlapException(
                "Unable to find a member with name " + nameParts);
        }
        this.excludeMember(member);
    }
    
    public void excludeMembers(List<Member> members) {
    	for (Member m : members) {
    		excludeMember(m);
    	}
    }

    public void excludeMember(Member m) {
    	Level l = m.getLevel();
    	if (!l.getHierarchy().equals(hierarchy)) {
    		throw new IllegalArgumentException("You cannot exclude member " + m.getUniqueName() + " on hierarchy " + hierarchy.getUniqueName());
    	}
    	QueryLevel ql = queryLevels.get(l.getName());
    	if (!activeLevels.contains(ql)) {
    		activeLevels.add(ql);
    	}
    	ql.exclude(m);
    }
    
    public void clearSelection() {
    	if (activeLevels != null) {
    		for (QueryLevel ql : activeLevels) {
    			ql.clearSelections();
    		}
    	}
    	activeLevels.clear();
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((hierarchy == null) ? 0 : hierarchy.getUniqueName().hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryHierarchy other = (QueryHierarchy) obj;
		if (hierarchy == null) {
			if (other.hierarchy != null)
				return false;
		} else if (!hierarchy.getUniqueName().equals(other.hierarchy.getUniqueName()))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return hierarchy.getUniqueName();
	}

	
	private class SaikuQueryLevelComparator implements Comparator<QueryLevel> {

		@Override
		public int compare(QueryLevel o1, QueryLevel o2) {
			if (o1 != null && o2 != null) {
				return o1.getLevel().getDepth() - o2.getLevel().getDepth();
			}
			return 0;
		}
		
	}
}








