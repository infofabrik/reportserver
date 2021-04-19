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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.olap4j.impl.Named;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Level.Type;
import org.olap4j.metadata.Member;
import org.saiku.query.Parameter.SelectionType;

public class QueryLevel extends AbstractQuerySet implements Named {
    private final QueryHierarchy hierarchy;
	private final Level level;
	
	private List<Member> inclusions = new ArrayList<Member>();
	private List<Member> exclusions = new ArrayList<Member>();
	private Member rangeStart = null;
	private Member rangeEnd = null;
	private String rangeStartExpr = null;
	private String rangeEndExpr = null;
	private String rangeStartSyn;
	private String rangeEndSyn;
	private String parameterName = null;
	private SelectionType parameterSelectionType = Parameter.SelectionType.INCLUSION;
	
    public QueryLevel(QueryHierarchy hierarchy, Level level) {
        super();
        this.hierarchy = hierarchy;
        this.level = level;
    }

    public QueryHierarchy getQueryHierarchy() {
        return hierarchy;
    }

    public String getName() {
        return level.getName();
    }
    
    public String getUniqueName() {
    	return level.getUniqueName();
    }
    
    public String getCaption() {
    	return level.getCaption();
    }

    
    @Override
    public boolean isSimple() {
    	return (super.isSimple() 
    			&& (level.getLevelType().equals(Type.ALL) 
    					|| (inclusions.isEmpty() && exclusions.isEmpty() && rangeStart == null && rangeEnd == null && rangeStartExpr == null && rangeEndExpr == null) )
    			&& (!hasParameter() || hierarchy.getQuery().getParameter(getParameterName()) == null));
    }
    
    public boolean isRange() {
    	return ((rangeStart != null && rangeEnd != null) || (rangeStartExpr != null || rangeEndExpr != null));
    }

    /**
     * Returns the underlying Level object onto which
     * this query Level is based.
     * <p>Returns a mutable object so operations on it have
     * unpredictable consequences.
     * @return The underlying  representation.
     */
    public Level getLevel() {
        return level;
    }

    public List<Member> getInclusions() {
    	return inclusions;
    }
    
    public List<Member> getExclusions() {
    	return exclusions;
    }
    
	public Member getRangeStart() {
		return rangeStart;
	}
	
	public Member getRangeEnd() {
		return rangeEnd;
	}
	
	public String getRangeStartExpr() {
		return rangeStartExpr;
	}
	
	public String getRangeEndExpr() {
		return rangeEndExpr;
	}
	
	public String getRangeStartSyn() {
		return rangeStartSyn;
	}
	
	public String getRangeEndSyn() {
		return rangeEndSyn;
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

    protected void include(Member m) {
    	if(!inclusions.contains(m)) {
    		inclusions.add(m);
    	}
    }
    
    protected void exclude(Member m) {
    	if(inclusions.contains(m)) {
    		inclusions.remove(m);
    	}
    	if(!exclusions.contains(m)) {
    		exclusions.add(m);
    	}
    }
    
    protected void setRange(Member start, Member end) {
    	rangeStart = start;
    	rangeEnd = end;
    }
    
    public void setRangeSynonyms(String startSynonym, String endSynonym) {
    	rangeStartSyn = startSynonym;
    	rangeEndSyn = endSynonym;
    }
    
    public void setRangeStartSynonym(String startSyn) {
    	rangeStartSyn = startSyn;
    }
    public void setRangeEndSynonym(String endSyn) {
    	rangeEndSyn = endSyn;
    }

    public void setRangeStartExpr(String startExp) {
    	rangeStart = null;
    	rangeStartExpr = startExp;
    }
    public void setRangeEndExpr(String endExp) {
    	rangeEnd = null;
    	rangeEndExpr = endExp;
    }

    public void setRangeExpressions(String startExpr, String endExpr) {
    	rangeStart = null;
    	rangeEnd = null;
    	rangeStartExpr = startExpr;
    	rangeEndExpr = endExpr;
    }    

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((level == null) ? 0 : level.getUniqueName().hashCode());
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
		QueryLevel other = (QueryLevel) obj;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.getUniqueName().equals(other.getLevel().getUniqueName()))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return level.getUniqueName();
	}

	public void setParameterName(String parameter) {
		this.parameterName  = parameter;
		
	}

	public void setParameterSelectionType(SelectionType selectionType) {
		this.parameterSelectionType = selectionType;
		
	}

	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * @return the parameterSelectionType
	 */
	public SelectionType getParameterSelectionType() {
		return parameterSelectionType;
	}
	
	public boolean hasParameter() {
		return (StringUtils.isNotBlank(parameterName));
	}
}








