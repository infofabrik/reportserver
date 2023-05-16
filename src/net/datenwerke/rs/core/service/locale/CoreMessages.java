package net.datenwerke.rs.core.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface CoreMessages extends Messages {

   public final static CoreMessages INSTANCE = LocalizationServiceImpl.getMessages(CoreMessages.class);

   String clearInternalDbCache_description();

   String folders();
}
