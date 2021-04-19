package net.datenwerke.rs.fileserver.client.fileserver.security;

import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class FileServerManagerViewSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.FLOPPY_O.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return FileServerMessages.INSTANCE.adminLabel();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return FileServerMessages.INSTANCE.permissionModuleDescription();
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new FileServerManagerGenericTargetIdentifier();
	}

}
