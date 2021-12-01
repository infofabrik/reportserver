package net.datenwerke.rs.eximport.client.eximport.security;

import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class ImportSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.DOWNLOAD.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return ExImportMessages.INSTANCE.importSecurityLabel();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return ExImportMessages.INSTANCE.importSecurityDescription();
	}


	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new ImportGenericTargetIdentifier();
	}

}
