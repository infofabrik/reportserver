package net.datenwerke.rs.license.service.exceptions;

import net.datenwerke.rs.license.service.locale.LicenseMessages;

public class LicenseValidationFailedException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public LicenseValidationFailedException(String msg, Throwable e) {
      super(LicenseMessages.INSTANCE.licenseCouldNotBeValidated(msg), e);
   }

   public LicenseValidationFailedException(String validateionError) {
      super(LicenseMessages.INSTANCE.licenseCouldNotBeValidated(validateionError));
   }

   public LicenseValidationFailedException(Throwable e) {
      super(LicenseMessages.INSTANCE.licenseCouldNotBeValidated(e.getMessage()), e);
   }
}
