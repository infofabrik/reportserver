package net.datenwerke.rs.samba.service.samba.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SambaMessages extends Messages {

   public final static SambaMessages INSTANCE = LocalizationServiceImpl.getMessages(SambaMessages.class);

   String sambaDatasinkTypeName();

   String sambaDatasinks();

}
