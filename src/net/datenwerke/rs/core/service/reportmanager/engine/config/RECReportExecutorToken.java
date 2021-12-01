package net.datenwerke.rs.core.service.reportmanager.engine.config;

public class RECReportExecutorToken implements ReportExecutionConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8161367363243107979L;
	
	private final String token;

	public RECReportExecutorToken(){
		token = null;
	}
	
	public RECReportExecutorToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
}
