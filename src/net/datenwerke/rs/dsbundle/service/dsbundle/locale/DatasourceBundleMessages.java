package net.datenwerke.rs.dsbundle.service.dsbundle.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface DatasourceBundleMessages extends Messages{

	public final static DatasourceBundleMessages INSTANCE = LocalizationServiceImpl.getMessages(DatasourceBundleMessages.class);
	
	String databaseBundleTypeName();

	String exceptionNoDatabaseFound(String key);

	String exceptionNoKeysConfigured();
	
}

