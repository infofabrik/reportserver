package net.datenwerke.security.service.usermanager.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.hooks.ChangePasswordHook;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class ChangePasswordHookAdapter implements ChangePasswordHook {

	@Override
	public void beforePasswordChanged(User user, String newPassword)  {
	}


	@Override
	public void afterPasswordChanged(User user)  {
	}



}
