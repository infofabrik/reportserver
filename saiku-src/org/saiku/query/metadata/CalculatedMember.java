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
package org.saiku.query.metadata;

import org.olap4j.OlapException;
import org.olap4j.impl.Named;
import org.olap4j.impl.NamedListImpl;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.mdx.ParseTreeNode;
import org.olap4j.metadata.*;
import org.olap4j.metadata.Measure.Aggregator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatedMember implements Member, Named, Calculated {



	private final Member parentMember;
	private Dimension dimension;
	private Hierarchy hierarchy;
	private String name;
	private String uniqueName;
	private Type memberType;
	private String formula;
	
	private Map<String, String> properties = new HashMap<String, String>();
	private String description;
	private Level level;

	public CalculatedMember(
			Dimension dimension,
			Hierarchy hierarchy,
			String name,
			String description,
			Member parentMember,
			Type memberType,
			String formula,
			Map<String, String> properties, String l, boolean mondrian3)
	{
		this(dimension,hierarchy,name,description,parentMember,memberType,formula,properties,mondrian3);
		if(l!=null && !l.equals("")) {
			for(Level level:hierarchy.getLevels()){
				if(level.getUniqueName().equals(l)){
					this.level = level;
				}
			}

		}
		else{
			this.level = hierarchy.getLevels().get(0);

		}
	}

	public CalculatedMember(
			Dimension dimension,
			Hierarchy hierarchy,
			String name,
			String description,
			Member parentMember,
			Type memberType,
			String formula,
			Map<String, String> properties, boolean mondrian3)
	{
		this.dimension = dimension;
		this.hierarchy = hierarchy;
		this.level = hierarchy.getLevels().get(0);
		this.name = name;
		this.description = description;
		this.memberType = memberType;
		this.formula = formula;
		if (parentMember == null) {
			if(mondrian3){
				this.uniqueName = IdentifierNode.ofNames(hierarchy.getName(), name).toString();

			}
			else {
				this.uniqueName = IdentifierNode.ofNames(hierarchy.getDimension().getName(), hierarchy.getName(), name)
												.toString();
			}
		} else {
			IdentifierNode parent = IdentifierNode.parseIdentifier(parentMember.getUniqueName());
			IdentifierNode cm = IdentifierNode.ofNames(name);
			List<IdentifierSegment> segmentList = new ArrayList<IdentifierSegment>();
			segmentList.addAll(parent.getSegmentList());
			segmentList.addAll(cm.getSegmentList());
	        StringBuilder buf = new StringBuilder();
	        for (IdentifierSegment segment : segmentList) {
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
		return dimension;
	}


	public Hierarchy getHierarchy() {
		return hierarchy;
	}
	
	/* (non-Javadoc)
	 * @see org.saiku.query.metadata.Calculated#getFormula()
	 */
	@Override
	public String getFormula() {
		return formula;
	}

	public Type getMemberType() {
		return memberType;
	}

	public Map<String, String> getFormatProperties() {
		return properties;
	}
	
	public String getFormatPropertyValue(String key) throws OlapException {
		if (properties.containsKey(key)) {
			return properties.get(key);
		}
		return null;
	}

	public void setFormatProperty(String key, String value) throws OlapException {
		properties.put(key, value);
	}


	public String getCaption() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public String getName() {
		return name;
	}


	/* (non-Javadoc)
	 * @see org.saiku.query.metadata.Calculated#getUniqueName()
	 */
	@Override
	public String getUniqueName() {
		return uniqueName;
	}


	public Aggregator getAggregator() {
		return Aggregator.CALCULATED;
	}


	public boolean isVisible() {
		return true;
	}



	@Override
	public List<Member> getAncestorMembers() {
		throw new UnsupportedOperationException();
	}



	@Override
	public int getChildMemberCount() throws OlapException {
		return 0;
	}



	@Override
	public NamedList<? extends Member> getChildMembers() throws OlapException {
		throw new UnsupportedOperationException();
	}



	@Override
	public Member getDataMember() {
		throw new UnsupportedOperationException();
	}



	@Override
	public int getDepth() {
		return 0;
	}



	@Override
	public ParseTreeNode getExpression() {
		throw new UnsupportedOperationException();
	}



	@Override
	public Level getLevel() {
		return level;
	}



	@Override
	public int getOrdinal() {
		throw new UnsupportedOperationException();
	}



	@Override
	public Member getParentMember() {
		return parentMember;
	}



	@Override
	public String getPropertyFormattedValue(Property property) throws OlapException {
		return String.valueOf(getPropertyValue(property));
	}



	@Override
	public int getSolveOrder() {
		throw new UnsupportedOperationException();
	}



	@Override
	public boolean isAll() {
		return false;
	}



	@Override
	public boolean isCalculated() {
		return true;
	}



	@Override
	public boolean isCalculatedInQuery() {
		return true;
	}



	@Override
	public boolean isChildOrEqualTo(Member arg0) {
		return false;
	}



	@Override
	public boolean isHidden() {
		return false;
	}



	/**
	 * DO NOT USE THIS
	 */
	@Deprecated
	public NamedList<Property> getProperties() {
	  NamedList l = new NamedListImpl(properties.entrySet());
	  return l;

	}



	/**
	 * DO NOT USE THIS
	 */
	@Deprecated
	public Object getPropertyValue(Property p) throws OlapException {
		if (properties.containsKey(p.getName())) {
			return properties.get(p.getName());
		}
		return null;
	}



	/**
	 * DO NOT USE THIS
	 */
	@Deprecated
	public void setProperty(Property arg0, Object arg1) throws OlapException {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((uniqueName == null) ? 0 : uniqueName.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculatedMember other = (CalculatedMember) obj;
		if (uniqueName == null) {
			if (other.uniqueName != null)
				return false;
		} else if (!uniqueName.equals(other.uniqueName))
			return false;
		return true;
	}

	

}
