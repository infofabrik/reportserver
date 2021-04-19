package net.datenwerke.security.ext.client.usermanager.helper.simpleform;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public interface SFFCUserSelector extends SimpleFormFieldConfiguration {
	
	public UserDto getUser();

}
