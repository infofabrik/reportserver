package net.datenwerke.rs.core.service.reportmanager.engine.config;

public class RECFirstPage implements RECPaged {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5880814866716333531L;

	private int pageSize = 100;

	public RECFirstPage(){
		// dummy
	}
	
	public RECFirstPage(int pageSize){
		this.pageSize = pageSize;
	}
	
	@Override
	public int getFirstPage() {
		return 1;
	}

	@Override
	public int getLastPage() {
		return 1;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public int hashCode() {
		return pageSize;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj || ! (obj instanceof RECFirstPage))
			return false;
		
		return pageSize == ((RECFirstPage)obj).pageSize;
	}
}
