package net.datenwerke.security.ext.server.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface DwSecurityMessages extends Messages{

	public final static DwSecurityMessages INSTANCE = LocalizationServiceImpl.getMessages(DwSecurityMessages.class);
	
	String unnamed();
	
	String firstname();
	String lastname();
	
	String username();
	
}

