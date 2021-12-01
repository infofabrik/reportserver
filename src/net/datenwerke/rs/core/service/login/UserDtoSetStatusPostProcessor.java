package net.datenwerke.rs.core.service.login;

import net.datenwerke.rs.core.service.reportserver.ReportServerModule;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;
import net.datenwerke.security.service.usermanager.hooks.UserDtoPostProcessorHook;

public class UserDtoSetStatusPostProcessor implements UserDtoPostProcessorHook {


	@Override
	public void adaptDto(User user, UserDto userDto) {
		if(! userDto.isActive())
			return;

		UserProperty property = user.getProperty(ReportServerModule.USER_PROPERTY_ACCOUNT_INHIBITED);
		if(null != property && property.getValueAsBoolean())
			userDto.setActive(false);
	}

}
