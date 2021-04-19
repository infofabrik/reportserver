package net.datenwerke.rs.configservice.service.configservice.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface ConfigMessages extends Messages{

	public final static ConfigMessages INSTANCE = LocalizationServiceImpl.getMessages(ConfigMessages.class);
	
	String commandConfig_description();
	String commandConfig_sub_reload_description();
	String commandConfig_sub_echo_description();
	
	String configReloaded();
}

