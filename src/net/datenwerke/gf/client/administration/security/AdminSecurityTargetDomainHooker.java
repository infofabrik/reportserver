package net.datenwerke.gf.client.administration.security;

import net.datenwerke.gf.client.administration.locale.AdministrationMessages;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class AdminSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.COGS.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return AdministrationMessages.INSTANCE.administration(); 
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return AdministrationMessages.INSTANCE.adminSecurityTargetDescription(); 
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new AdminGenericTargetIdentifier();
	}

}
