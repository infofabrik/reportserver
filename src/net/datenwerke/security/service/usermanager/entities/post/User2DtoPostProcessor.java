package net.datenwerke.security.service.usermanager.entities.post;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.hooks.UserDtoPostProcessorHook;

public class User2DtoPostProcessor implements Poso2DtoPostProcessor<User, UserDto> {

	private final HookHandlerService hookHandler;
	
	@Inject
	public User2DtoPostProcessor(HookHandlerService hookHandler) {
		this.hookHandler = hookHandler;
	}

	@Override
	public void dtoCreated(User user, UserDto userDto) {
		if(null != user.getPassword() && ! "".equals(user.getPassword().trim()))
			userDto.setHasPassword(true);
		
		for(UserDtoPostProcessorHook hooker : hookHandler.getHookers(UserDtoPostProcessorHook.class))
			hooker.adaptDto(user, userDto);
	}

	@Override
	public void dtoInstantiated(User arg0, UserDto arg1) {
		
	}

}
