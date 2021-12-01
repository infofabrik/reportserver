package net.datenwerke.rs.remoteaccess.client.sftp.genrights;

import javax.inject.Inject;

import net.datenwerke.rs.remoteaccess.client.locale.RemoteAccessMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class SftpSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {
	
	@Inject
	public SftpSecurityTargetDomainHooker() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String genericSecurityViewDomainHook_getName() {
		return RemoteAccessMessages.INSTANCE.sftpPermission();
	}

	@Override
	public String genericSecurityViewDomainHook_getDescription() {
		return RemoteAccessMessages.INSTANCE.sftpPermissionPermissionModuleDescription();
	}

	@Override
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.SITEMAP.toImageResource();
	}

	@Override
	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new SftpGenericTargetIdentifier();
	}

}
