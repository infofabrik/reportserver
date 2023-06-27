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

public abstract class AbstractSortableQuerySet extends AbstractQuerySet implements ISortableQuerySet {
	
	private SortOrder sortOrder;
	private String sortEvaluationLiteral;
	private HierarchizeMode hierarchizeMode;
	
	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#sort(org.saiku.query.SortOrder)
	 */
	@Override
	public void sort(SortOrder order) {
		this.sortOrder = order;

	}
	
	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#sort(org.saiku.query.SortOrder, java.lang.String)
	 */
	@Override
	public void sort(SortOrder order, String sortEvaluationLiteral) {
		this.sortOrder = order;
		this.sortEvaluationLiteral = sortEvaluationLiteral;
	}

	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#getSortOrder()
	 */
	@Override
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	@Override
	public String getSortEvaluationLiteral() {
		return sortEvaluationLiteral;
	}

	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#clearSort()
	 */
	@Override
	public void clearSort() {
		this.sortOrder = null;
		this.sortEvaluationLiteral = null;
	}

	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#getHierarchizeMode()
	 */
	@Override
	public HierarchizeMode getHierarchizeMode() {
		return this.hierarchizeMode;
	}

	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#setHierarchizeMode(org.saiku.query.IQuerySet.HierarchizeMode)
	 */
	@Override
	public void setHierarchizeMode(HierarchizeMode hierarchizeMode) {
		this.hierarchizeMode = hierarchizeMode;

	}

	/* (non-Javadoc)
	 * @see org.saiku.query.IQuerySet#clearHierarchizeMode()
	 */
	@Override
	public void clearHierarchizeMode() {
		this.hierarchizeMode = null;

	}


}
