package net.datenwerke.rs.eximport.service.eximport.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface ExImportMessages extends Messages{
	
	public final static ExImportMessages INSTANCE = LocalizationServiceImpl.getMessages(ExImportMessages.class);

	String commandExport_description();
	
	String commandExport_sub_all_description();

	String commandImport_description();
	
	String expectedXmlOrZip();

	String fileSeemsEmpty();

	String zipShouldContainOnlyOne();

	String parseException(String message);
}

