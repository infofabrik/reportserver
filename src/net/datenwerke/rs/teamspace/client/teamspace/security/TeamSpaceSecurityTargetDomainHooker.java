package net.datenwerke.rs.teamspace.client.teamspace.security;

import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class TeamSpaceSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.USERS.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return TeamSpaceMessages.INSTANCE.teamSpaceSecurityTitle();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return TeamSpaceMessages.INSTANCE.teamSpaceSeucirtyDescription();
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new TeamSpaceGenericTargetIdentifier();
	}

}
