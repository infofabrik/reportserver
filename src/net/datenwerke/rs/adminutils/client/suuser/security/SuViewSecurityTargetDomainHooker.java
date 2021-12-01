package net.datenwerke.rs.adminutils.client.suuser.security;

import net.datenwerke.rs.adminutils.client.suuser.locale.SuMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class SuViewSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {

	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.USER_MD.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return SuMessages.INSTANCE.suCommandName();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return SuMessages.INSTANCE.suPermissionModuleDescription();
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new SuGenericTargetIdentifier();
	}

}
