package net.datenwerke.security.client.usermanager.dto.decorator;

import java.util.Set;

import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;

/**
 * Dto Decorator for {@link UserDto}
 *
 */
public class UserDtoDec extends UserDto {


	private static final long serialVersionUID = 1L;

	public UserDtoDec() {
	}

	public boolean hasUserProperty(String key){
		if(null == key)
			return false;
		for(UserPropertyDto prop : getProperties())
			if(key.equals(prop.getKey()))
				return true;
		return false;
	}
	
	public String getUserPropertyValue(String key){
		if(null == key)
			return null;
		for(UserPropertyDto prop : getProperties())
			if(key.equals(prop.getKey()))
				return prop.getValue();
		return null;
	}
	
	public void setUserPropertyValue(String key, String value){
		if(null == key)
			return;
		for(UserPropertyDto prop : getProperties())
			if(key.equals(prop.getKey())){
				prop.setValue(value);
				return;
			}
		
		UserPropertyDto prop = new UserPropertyDto();
		prop.setKey(key);
		prop.setValue(value);
		
		Set<UserPropertyDto> properties = getProperties();
		properties.add(prop);
		setProperties(properties);
	}

}
