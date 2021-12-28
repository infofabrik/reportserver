package net.datenwerke.rs.search.service.search.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SearchMessages extends Messages {

   public final static SearchMessages INSTANCE = LocalizationServiceImpl.getMessages(SearchMessages.class);

   String objectTypeTagDisplay();

   String commandSearch_description();

   String commandSearch_tArgument();

   String commandSearch_queryArgument();
}
