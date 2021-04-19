package net.datenwerke.rs.uservariables.client.uservariables.genrights;

import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class UserVariableAdminViewSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.USER_VARIABLE.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return UserVariablesMessages.INSTANCE.userVariablesegnericAdminHeading();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return UserVariablesMessages.INSTANCE.userVariablesGenericAdmindescription();
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new UserVariableAdminViewGenericTargetIdentifier();
	}

}
