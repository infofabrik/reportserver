package net.datenwerke.rs.terminal.service.terminal.exceptions;

public class MaxAutocompleteResultsExceededException extends TerminalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8760792941178195214L;

	private int numberOfResults;
	
	public MaxAutocompleteResultsExceededException(int numberOfResults){
		super();
		this.numberOfResults = numberOfResults;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}
}
