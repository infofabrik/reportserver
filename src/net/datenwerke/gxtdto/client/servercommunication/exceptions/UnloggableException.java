package net.datenwerke.gxtdto.client.servercommunication.exceptions;


public class UnloggableException extends ServerCallFailedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1605677970715844281L;

	public UnloggableException(){
		super("unloggableException"); //$NON-NLS-1$
	}
}
