package net.datenwerke.rs.core.service.mail;

/**
 * 
 *
 */
public class SimpleAttachement {

	final private Object attachement;
	final private String mimeType;
	final private String fileName;
	
	public SimpleAttachement(Object attachement, String mimeType, String fileName) {
		if(null == attachement)
			throw new IllegalArgumentException("Cannot send null as attachement");
		this.attachement = attachement;
		this.mimeType = mimeType;
		this.fileName = fileName;
	}

	public Object getAttachement() {
		return attachement;
	}

	public String getMimeType() {
		return mimeType;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	
}
