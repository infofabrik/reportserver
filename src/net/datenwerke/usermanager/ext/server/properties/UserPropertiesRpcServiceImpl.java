package net.datenwerke.usermanager.ext.server.properties;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;
import net.datenwerke.usermanager.ext.client.properties.rpc.UserPropertiesRpcService;

/**
 * 
 *
 */
@Singleton
public class UserPropertiesRpcServiceImpl extends SecuredRemoteServiceServlet
		implements UserPropertiesRpcService {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final DtoService dtoService;
	private final UserManagerService userService;
	private final UserPropertiesService userPropertiesService;
	
	
	@Inject
	public UserPropertiesRpcServiceImpl(
		DtoService dtoService,
		UserManagerService userService,
		UserPropertiesService userPropertiesService
		) {
		
		/* store objects */
		this.dtoService = dtoService;
		this.userService = userService;
		this.userPropertiesService = userPropertiesService;
	}

	@SecurityChecked(
		argumentVerification = {
			@ArgumentVerification(
				name = "user",
				isDto = true,
				verify = @RightsVerification(rights=Write.class)
			)
		}
	)
	@Override
	@Transactional(rollbackOn={Exception.class})
	public UserDto updateProperties(@Named("user") UserDto userDto,
			List<UserPropertyDto> addedProperties,
			List<UserPropertyDto> modifiedProperties, List<UserPropertyDto> removedProperties) throws ServerCallFailedException {
		User user = (User) dtoService.loadPoso(userDto);
		
		/* remove */
		for(UserPropertyDto propDto : removedProperties){
			UserProperty property = (UserProperty) dtoService.loadPoso(propDto);
			
			if(null == userPropertiesService.getProperty(user, property.getKey()))
				throw new ServerCallFailedException("User does not have property");
			
			userPropertiesService.removeProperty(user, property);
		}
		
		/* add */
		for(UserPropertyDto propertyDto : addedProperties){
			UserProperty property = new UserProperty();
			if ( null != propertyDto.getValue() ) 
				property.setValue(propertyDto.getValue().trim());
			if(null == propertyDto.getKey())
				property.setKey(getUniqueKey(user));
			if( null != propertyDto.getKey())
				property.setKey(propertyDto.getKey().trim());
			userPropertiesService.setProperty(user, property);
		}
		
		modifiedProperties.removeAll(removedProperties);
		modifiedProperties.removeAll(addedProperties);
		
		/* modified */
		
		for(UserPropertyDto propertyDto : modifiedProperties){
			UserProperty property = (UserProperty) dtoService.loadPoso(propertyDto);
				
			if(null == userPropertiesService.getProperty(user, property.getKey()))
				throw new ServerCallFailedException("User does not have property");
			
			if( null != propertyDto.getKey())
				propertyDto.setKey(propertyDto.getKey().trim());
			if ( null != propertyDto.getValue() ) 
				propertyDto.setValue(propertyDto.getValue().trim());
			if(null == propertyDto.getKey())
				propertyDto.setKey(getUniqueKey(user));
			/* merge data and store user */
			dtoService.mergePoso(propertyDto, property);
		}
		
		userService.merge(user);
		
		return (UserDto) dtoService.createDtoFullAccess(user);
	}
	
	protected String getUniqueKey(User user) {
		String name = "unnamed";
		int i = 0;
		while(hasProperty(user, name))
			name = "unnamed_" + (++i);
		
		return name;
	}

	protected boolean hasProperty(User user, String name) {
		for(UserProperty p : user.getProperties())
			if(name.equals(p.getKey()))
				return true;
		return false;
	}
	

	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<String> getPropertyKeys() throws ServerCallFailedException {
		return userPropertiesService.getPropertyKeys();
	}

}
