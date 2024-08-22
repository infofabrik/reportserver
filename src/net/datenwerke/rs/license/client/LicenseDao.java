package net.datenwerke.rs.license.client;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.license.client.dto.LicenseInformationDto;
import net.datenwerke.rs.license.client.rpc.LicenseRpcServiceAsync;

public class LicenseDao extends Dao {

   private final LicenseRpcServiceAsync rpcService;

   @Inject
   public LicenseDao(LicenseRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadLicenseInformation(RsAsyncCallback<LicenseInformationDto> callback) {
      rpcService.loadLicenseInformation(transformAndKeepCallback(callback));
   }

   public void updateLicense(String license, RsAsyncCallback<Void> callback) {
      rpcService.updateLicense(license, transformAndKeepCallback(callback));
   }
}
