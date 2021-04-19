package net.datenwerke.rs.grideditor.service.grideditor.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface GridEditorMessages extends Messages{

	public final static GridEditorMessages INSTANCE = LocalizationServiceImpl.getMessages(GridEditorMessages.class);
	
	String reportTypeName();

	String validationFailedDefaultMessage();

	String validationFailedExceptionMessage(String casted, String valueOf,
			String name, String error);

	String foreignKeyException(String name);
}
