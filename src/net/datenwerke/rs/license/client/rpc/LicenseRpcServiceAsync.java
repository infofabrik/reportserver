package net.datenwerke.rs.license.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.license.client.dto.LicenseInformationDto;

public interface LicenseRpcServiceAsync {

   void loadLicenseInformation(AsyncCallback<LicenseInformationDto> transformAndKeepCallback);

   void updateLicense(String license, AsyncCallback<Void> transformAndKeepCallback);

}
