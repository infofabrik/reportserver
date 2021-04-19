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
 */package org.saiku.query;

import org.apache.commons.lang.StringUtils;

import org.saiku.query.mdx.IFilterFunction;

import java.util.ArrayList;
import java.util.List;


/**
 * @author pstoellberger
 *
 */
public abstract class AbstractQuerySet implements IQuerySet {

	private String mdxExpression;
	
	private List<IFilterFunction> filters = new ArrayList<IFilterFunction>();

	
	public abstract String getName();
	
	public boolean isSimple() {
		return (mdxExpression == null && filters.isEmpty());
	}
	
	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#setMdxSetExpression(java.lang.String)
	 */
	@Override
	public void setMdxSetExpression(String mdxSetExpression) {
		this.mdxExpression = mdxSetExpression;
		
	}
	
	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#getMdxSetExpression()
	 */
	@Override
	public String getMdxSetExpression() {
      /*StringBuilder buffer = new StringBuilder();
      Iterator it = this.mdxExpression.entrySet().iterator();

      while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        buffer.append((String) pairs.getValue());
        if ( it.hasNext()) {
          buffer.append(" * ");
        }

        it.remove(); // avoids a ConcurrentModificationException
      }

      String mdx = buffer.toString();*/
		return this.mdxExpression;
	}
	
	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#isMdxSetExpression()
	 */
	@Override
	public boolean isMdxSetExpression() {
		return this.mdxExpression != null;
	}
	
	@Override
	public void addFilter(IFilterFunction filter) {
		filters.add(filter);
	}
	
	@Override
	public void setFilter(int index, IFilterFunction filter) {
		filters.set(index, filter);
	}

	@Override
	public List<IFilterFunction> getFilters() {
		return filters;
	}
	@Override
	public void clearFilters() {
		filters.clear();
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mdxExpression == null) ? 0 : mdxExpression.hashCode());
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
		AbstractQuerySet other = (AbstractQuerySet) obj;
		if (mdxExpression == null) {
			if (other.mdxExpression != null)
				return false;
		} else if (!mdxExpression.equals(other.mdxExpression))
			return false;
		if (!StringUtils.equals(getName(), other.getName()))
			return false;
		return true;
	}


}
