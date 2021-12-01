package net.datenwerke.rs.core.service.reportmanager.exceptions;

import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;

public class ReportExecutorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3738087665438361986L;

	public ReportExecutorException(String msg){
		super(msg);
	}
	
	public ReportExecutorException(Throwable cause){
		super(ReportManagerMessages.INSTANCE.exceptionReportCouldNotBeExecuted(null != cause ? cause.getMessage() : ""), cause);
	}
	
	public ReportExecutorException(String msg, Throwable cause){
		super(msg, cause);
	}
}
