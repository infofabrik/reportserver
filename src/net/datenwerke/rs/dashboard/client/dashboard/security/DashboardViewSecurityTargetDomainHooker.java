package net.datenwerke.rs.dashboard.client.dashboard.security;

import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class DashboardViewSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.TACHOMETER.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return DashboardMessages.INSTANCE.clientModuleName(); 
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return DashboardMessages.INSTANCE.securityTargetDescription(); 
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new DashboardViewGenericTargetIdentifier();
	}

}
