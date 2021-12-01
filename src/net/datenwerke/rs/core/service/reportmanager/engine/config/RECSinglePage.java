package net.datenwerke.rs.core.service.reportmanager.engine.config;



public class RECSinglePage implements RECPaged {

	/**
	 * 
	 */
	private static final long serialVersionUID = 29621752085956289L;
	
	private int page;
	private int pageSize = 100;

	public RECSinglePage(){
		// dummy
	}

	public RECSinglePage(int page){
		this.page = page;
	}
	
	public RECSinglePage(int page, int pagesize){
		this.page = page;
		this.pageSize = pagesize;
	}
	
	@Override
	public int getFirstPage() {
		return page;
	}

	@Override
	public int getLastPage() {
		return page;
	}
	
	@Override
	public int getPageSize() {
		return this.pageSize;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public int hashCode() {
		return pageSize;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj || ! (obj instanceof RECSinglePage))
			return false;
		
		return page == ((RECSinglePage)obj).page && pageSize == ((RECSinglePage)obj).pageSize;
	}
}
