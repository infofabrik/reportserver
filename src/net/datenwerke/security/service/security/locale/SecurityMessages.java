package net.datenwerke.security.service.security.locale;

import net.datenwerke.rs.utils.localization.Messages;

public interface SecurityMessages extends Messages {

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

}
