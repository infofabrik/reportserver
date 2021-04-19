package net.datenwerke.usermanager.ext.client.properties.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;

public interface UserPropertiesRpcServiceAsync {

	void getPropertyKeys(AsyncCallback<List<String>> callback);

	void updateProperties(UserDto user, List<UserPropertyDto> addedProperties,
			List<UserPropertyDto> modifiedProperties, List<UserPropertyDto> removedProperties,
			AsyncCallback<UserDto> callback);

}
