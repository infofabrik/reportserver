package net.datenwerke.rs.incubator.service.outputformatauth.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface OutputFormatAuthMessages extends Messages {

   public final static OutputFormatAuthMessages INSTANCE = LocalizationServiceImpl
         .getMessages(OutputFormatAuthMessages.class);

   String exceptionInvalidFormat(String outputFormat);

}
