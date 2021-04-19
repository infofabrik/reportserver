package net.datenwerke.rs.scheduler.client.scheduler.dto;


public class EmailInformation implements AdditionalScheduleInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5477596705986988354L;
	
	private String subject;
	private String message;
	private boolean compressed;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public boolean isCompressed() {
		return compressed;
	}
	
	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}
}
