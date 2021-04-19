package net.datenwerke.rs.core.service.mail.exceptions;

public class MailerRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2648883541731172733L;

	public MailerRuntimeException(String msg){
		super(msg);
	}
	
	 public MailerRuntimeException(String message, Throwable cause) {
		 super(message, cause);
	 }
}
