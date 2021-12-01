package net.datenwerke.treedb.service.treedb.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;


public interface TreeDbMessages extends Messages{

	public final static TreeDbMessages INSTANCE = LocalizationServiceImpl.getMessages(TreeDbMessages.class);

	String exceptionNodeIsWriteProtected();

	String exceptionUnsupportedChild(String simpleName, String simpleName2);

}

