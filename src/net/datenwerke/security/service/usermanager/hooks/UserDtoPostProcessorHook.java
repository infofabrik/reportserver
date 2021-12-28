package net.datenwerke.security.service.usermanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.usermanager.entities.User;

public interface UserDtoPostProcessorHook extends Hook {

   void adaptDto(User user, UserDto userDto);

}
