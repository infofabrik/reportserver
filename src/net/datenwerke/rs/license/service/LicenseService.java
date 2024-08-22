package net.datenwerke.rs.license.service;

import java.util.Date;
import java.util.HashMap;

import net.datenwerke.rs.license.service.exceptions.LicenseValidationFailedException;

public interface LicenseService {

   void checkInit();

   Date getInstallationDate();

   String getServerId();

   String getRsVersion();

   void updateLicense(String license) throws LicenseValidationFailedException;

   String getLicenseType();

   String getLicenseName();

   Date getUpgradesUntil();

   Date getLicenseIssueDate();

   Date getLicenseExpirationDate();

   HashMap<String, String> getAdditionalLicenseProperties();

   boolean isEnterprise();

   boolean isEvaluation();

   boolean hasNonCommunityLicense();

   boolean isInitialized();

   boolean isCustomLicenseType();

}
