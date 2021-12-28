package net.datenwerke.rs.legacysaiku.service.saiku.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SaikuEngineMessages extends Messages {

   public final static SaikuEngineMessages INSTANCE = LocalizationServiceImpl.getMessages(SaikuEngineMessages.class);

   String reportTypeName();

   String errorUnableToFindMember(String member);

}
