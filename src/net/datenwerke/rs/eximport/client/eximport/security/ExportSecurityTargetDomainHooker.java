package net.datenwerke.rs.eximport.client.eximport.security;

import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class ExportSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.EXPORT.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return ExImportMessages.INSTANCE.exportSecurityLabel();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return ExImportMessages.INSTANCE.exportSecurityDescription();
	}


	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new ExportGenericTargetIdentifier();
	}

}
