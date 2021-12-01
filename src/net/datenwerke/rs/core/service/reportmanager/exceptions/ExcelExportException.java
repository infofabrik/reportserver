package net.datenwerke.rs.core.service.reportmanager.exceptions;



public class ExcelExportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7561358910878360046L;

	public ExcelExportException(){
		super("Excel export failed."); //$NON-NLS-1$
	}
	
	public ExcelExportException(String message){
		super(message);
	}
}
