package net.datenwerke.rs.uservariables.service.uservariables.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface UserVariableMessages extends Messages {

   public final static UserVariableMessages INSTANCE = LocalizationServiceImpl
         .getMessages(UserVariableMessages.class);

   String userVariableStatistics();
   
   String userVariableDefinitions();
   
   String userVariableInstances();
}
