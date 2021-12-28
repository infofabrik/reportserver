package net.datenwerke.rs.core.client.genrights;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.locale.ReportServerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class AccessRsSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.SIGN_IN.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return ReportServerMessages.INSTANCE.accessRsPermission();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return ReportServerMessages.INSTANCE.accessRsPermissionPermissionModuleDescription();
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new AccessRsGenericTargetIdentifier();
	}

}
