package net.datenwerke.rs.configservice.service.manservice.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface ManMessages extends Messages{

	public final static ManMessages INSTANCE = LocalizationServiceImpl.getMessages(ManMessages.class);
	
	String commandMan_description();
	
	String commandDoc_description();
	String commandDoc_sub_reload_description();
	
	String docReloaded();
}

