package net.datenwerke.rs.license.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.license.client.dto.LicenseInformationDto;
import net.datenwerke.rs.license.client.rpc.LicenseRpcService;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.license.service.exceptions.LicenseValidationFailedException;
import net.datenwerke.rs.license.service.genrights.LicenseSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;

@Singleton
public class LicenseRpcServiceImpl extends SecuredRemoteServiceServlet implements LicenseRpcService {

   private static final long serialVersionUID = 1L;

   private final LicenseService licenseService;

   @Inject
   public LicenseRpcServiceImpl(LicenseService licenseService) {
      this.licenseService = licenseService;
   }

   @Override
   public LicenseInformationDto loadLicenseInformation() {
      LicenseInformationDto info = new LicenseInformationDto();

      info.setRsVersion(licenseService.getRsVersion());
      info.setInstallationDate(licenseService.getInstallationDate());
      info.setServerId(licenseService.getServerId());
      info.setLicenseType(licenseService.getLicenseType());
      if (licenseService.hasNonCommunityLicense()) {
         info.setLicenseIssueDate(licenseService.getLicenseIssueDate());
         info.setUpgradesUntil(licenseService.getUpgradesUntil());
         info.setExpirationDate(licenseService.getLicenseExpirationDate());
         info.setName(licenseService.getLicenseName());
         info.setAdditionalLicenseProperties(licenseService.getAdditionalLicenseProperties());
      }
      return info;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = LicenseSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   public void updateLicense(String license) throws ServerCallFailedException {
      try {
         licenseService.updateLicense(license);
      } catch (LicenseValidationFailedException e) {
         throw new ServerCallFailedException(e);
      }
   }

}
