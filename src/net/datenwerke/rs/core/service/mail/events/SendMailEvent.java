package net.datenwerke.rs.core.service.mail.events;

import javax.mail.internet.MimeMessage;

import net.datenwerke.rs.utils.eventbus.Event;

public class SendMailEvent implements Event {

	private final MimeMessage message;

	public SendMailEvent(MimeMessage message) {
		super();
		this.message = message;
	}

	public MimeMessage getMessage() {
		return message;
	}
	
	
}
