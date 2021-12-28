package net.datenwerke.rs.license.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.license.client.dto.LicenseInformationDto;

@RemoteServiceRelativePath("license")
public interface LicenseRpcService extends RemoteService {

   LicenseInformationDto loadLicenseInformation() throws ServerCallFailedException;

   void updateLicense(String license) throws ServerCallFailedException;

}
