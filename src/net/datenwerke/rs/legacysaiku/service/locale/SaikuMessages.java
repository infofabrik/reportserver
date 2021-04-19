package net.datenwerke.rs.legacysaiku.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SaikuMessages extends Messages {

	public final static SaikuMessages INSTANCE = LocalizationServiceImpl.getMessages(SaikuMessages.class);
	
	String reportTypeName();


}
