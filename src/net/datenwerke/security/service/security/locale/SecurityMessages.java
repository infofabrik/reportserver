package net.datenwerke.security.service.security.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SecurityMessages extends Messages {

   public final static SecurityMessages INSTANCE = LocalizationServiceImpl
         .getMessages(SecurityMessages.class);
   
   String changePasswordComplexityFail(String join);

   String changePasswordHistoryFail(int historySize);

   String changePasswordOnceInDays(int passwordMinAge);

   String deleteRightAbbreviation();

   String deleteRightName();

   String executeRightAbbreviation();

   String executeRightName();

   String grantAccessRightAbbreviation();

   String grantAccessRightName();

   String lostPasswordMessageSubject();

   String lostPasswordMessageTemplate();

   String passwordCharacterClassRequirement(int minOccurrences, String string, int sumOccurencesOfClassmembers);

   String passwordLengthSpec(int minLength, int length);

   String readRightAbbreviation();

   String readRightName();

   String securitySecureeName();

   String writeRightAbbreviation();

   String writeRightName();
   
   String commandHaspermission_description();
   
   String commandHaspermission_user();
   
   String commandHaspermission_target();
   
   String commandHaspermission_right();
   
   String commandHaspermission_flagG();
   
   String knownHostsFile();

   String supportedSslProtocols();

   String defaultSslProtocols();

   String enabledSslProtocols();

   String security();

}
