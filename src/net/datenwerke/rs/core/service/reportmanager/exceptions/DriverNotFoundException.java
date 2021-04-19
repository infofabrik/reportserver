package net.datenwerke.rs.core.service.reportmanager.exceptions;

public class DriverNotFoundException extends ReportManagerRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5998913400806743047L;

	public DriverNotFoundException(String descriptor){
		super("Could not find driver name for: " + descriptor); //$NON-NLS-1$
	}
}
