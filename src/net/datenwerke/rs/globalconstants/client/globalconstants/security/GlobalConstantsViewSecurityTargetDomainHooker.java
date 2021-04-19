package net.datenwerke.rs.globalconstants.client.globalconstants.security;

import net.datenwerke.rs.globalconstants.client.globalconstants.locale.GlobalConstantsMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 *
 */
public class GlobalConstantsViewSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.EDIT.toImageResource(); 
	}

	public String genericSecurityViewDomainHook_getName() {
		return GlobalConstantsMessages.INSTANCE.securityTitle(); 
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return GlobalConstantsMessages.INSTANCE.securityDescription();
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new GlobalConstantsGenericTargetIdentifier();
	}

}
