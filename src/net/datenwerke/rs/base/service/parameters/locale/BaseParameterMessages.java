package net.datenwerke.rs.base.service.parameters.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface BaseParameterMessages extends Messages{

	public final static BaseParameterMessages INSTANCE = LocalizationServiceImpl.getMessages(BaseParameterMessages.class);

	String errorParseDateFormula(String formula);

	String scriptPostProcessingFailed();
	
}

