package net.datenwerke.rs.core.service.mail;

/**
 * 
 *
 */
public class SimpleAttachment {

	final private Object attachment;
	final private String mimeType;
	final private String fileName;
	
	public SimpleAttachment(Object attachment, String mimeType, String fileName) {
		if(null == attachment)
			throw new IllegalArgumentException("Cannot send null as attachment");
		this.attachment = attachment;
		this.mimeType = mimeType;
		this.fileName = fileName;
	}

	public Object getAttachment() {
		return attachment;
	}

	public String getMimeType() {
		return mimeType;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	
}
