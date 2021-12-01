package net.datenwerke.rs.core.service.reportmanager.exceptions;


public class UnsupportedDriverException extends ReportManagerRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6533103824939865401L;

	public UnsupportedDriverException(String driver){
		super("Could not load driver: " + driver + ". Have you added it to the classpath?"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
