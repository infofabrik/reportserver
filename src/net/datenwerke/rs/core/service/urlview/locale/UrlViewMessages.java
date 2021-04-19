package net.datenwerke.rs.core.service.urlview.locale;


import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface UrlViewMessages extends Messages {

	public final static UrlViewMessages INSTANCE = LocalizationServiceImpl.getMessages(UrlViewMessages.class);
	
	String info();
	String history();
	String preview();
	
}
