package net.datenwerke.rs.core.service.reportmanager.exceptions;

public class ReportManagerRuntimeException extends RuntimeException {

	public ReportManagerRuntimeException(){
		super();
	}
	
	public ReportManagerRuntimeException(String msg){
		super(msg);
	}
}
