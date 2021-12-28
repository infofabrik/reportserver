package net.datenwerke.rs.dashboard.client.dashboard.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class DashboardAdminSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.TACHOMETER.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return DashboardMessages.INSTANCE.dashboardAdminRightsLabel(); 
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return DashboardMessages.INSTANCE.securityTargetDescription(); 
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new DashboardAdminGenericTargetIdentifier();
	}

}
