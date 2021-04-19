package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import com.sencha.gxt.data.shared.loader.PagingLoadConfigBean;

public class SelectorPanelLoadConfig<M> extends PagingLoadConfigBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8167590942653000577L;
	
	private String filter;

	private boolean caseSensitive;
	
	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilter() {
		return filter;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
}