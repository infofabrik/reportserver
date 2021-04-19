package net.datenwerke.usermanager.ext.client.properties.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;

@RemoteServiceRelativePath("userproperties")
public interface UserPropertiesRpcService extends RemoteService {
	
	public List<String> getPropertyKeys() throws ServerCallFailedException;

	UserDto updateProperties(UserDto user, List<UserPropertyDto> addedProperties,
			List<UserPropertyDto> modifiedProperties, List<UserPropertyDto> removedProperties) throws ServerCallFailedException;
}
