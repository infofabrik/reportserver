package net.datenwerke.rs.uservariables.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface UserVarMessages extends Messages {

   public final static UserVarMessages INSTANCE = LocalizationServiceImpl.getMessages(UserVarMessages.class);

   String userVarHasInstances();

   String userVarHasParameters();

}
