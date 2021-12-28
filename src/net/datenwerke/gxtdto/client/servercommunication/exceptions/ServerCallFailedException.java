package net.datenwerke.gxtdto.client.servercommunication.exceptions;

import com.google.gwt.core.shared.GWT;

import net.datenwerke.gxtdto.client.locale.BaseMessages;

/**
 * 
 *
 */
public class ServerCallFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402471906508984817L;
	private String stacktrace;
	
	public ServerCallFailedException() {
		super();
	}
	
	public ServerCallFailedException(String msg) {
		super(msg);
	}
	
	public ServerCallFailedException(String msg, Throwable e) {
		super(msg, e);
	}

	public ServerCallFailedException(Throwable e) {
		super(e.getMessage());
		initCause(e);
	}

	public void setStackTraceAsString(String stacktrace){
		this.stacktrace = stacktrace;
	}
	
	public String getStackTraceAsString(){
		return stacktrace;
	}

	public String getTitle() {
		if(GWT.isClient())
			return BaseMessages.INSTANCE.encounteredError();
		return "Encountered an error";
	}
	
}
