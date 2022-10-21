package net.datenwerke.rs.scp.service.scp.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ScpMessages extends Messages {

   public final static ScpMessages INSTANCE = LocalizationServiceImpl.getMessages(ScpMessages.class);

   String scpDatasinkTypeName();

}
