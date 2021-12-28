package net.datenwerke.rs.license.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface LicenseMessages extends Messages {

   public final static LicenseMessages INSTANCE = LocalizationServiceImpl.getMessages(LicenseMessages.class);

   String licenseCouldNotBeValidated(String error);

}
