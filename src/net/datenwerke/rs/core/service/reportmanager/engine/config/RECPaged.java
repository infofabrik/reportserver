package net.datenwerke.rs.core.service.reportmanager.engine.config;

public interface RECPaged extends ReportExecutionConfig {

	public int getFirstPage();
	
	public int getLastPage();

	public int getPageSize();
}
