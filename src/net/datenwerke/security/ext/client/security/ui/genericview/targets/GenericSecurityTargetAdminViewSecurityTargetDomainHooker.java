package net.datenwerke.security.ext.client.security.ui.genericview.targets;

import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.ext.client.security.locale.SecurityMessages;

import com.google.gwt.resources.client.ImageResource;

public class GenericSecurityTargetAdminViewSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.LOCK.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return SecurityMessages.INSTANCE.genericSecurityAdminViewHeading(); 
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return SecurityMessages.INSTANCE.genericSecurityAdminViewDescription(); 
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new GenericSecurityTargetAdminViewGenericTargetIdentifier();
	}

}
