package net.datenwerke.rs.core.service.reportmanager.exceptions;


public class ReportExecutorRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2254153379814412033L;

	public ReportExecutorRuntimeException(){
		super();
	}
	
	public ReportExecutorRuntimeException(String msg){
		super(msg);
	}

	public ReportExecutorRuntimeException(Throwable e) {
		super(e);
	}
	
	public ReportExecutorRuntimeException(String msg, Throwable e) {
		super(msg, e);
	}
}
