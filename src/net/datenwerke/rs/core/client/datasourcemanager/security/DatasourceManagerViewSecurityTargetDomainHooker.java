package net.datenwerke.rs.core.client.datasourcemanager.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class DatasourceManagerViewSecurityTargetDomainHooker implements
		GenericSecurityViewDomainHook {
	
	public ImageResource genericSecurityViewDomainHook_getIcon() {
		return BaseIcon.DATABASE.toImageResource();
	}

	public String genericSecurityViewDomainHook_getName() {
		return DatasourcesMessages.INSTANCE.datasources();
	}
	
	public String genericSecurityViewDomainHook_getDescription() {
		return DatasourcesMessages.INSTANCE.dataSourcePermissionModuleDescription();
	}

	public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
		return new DatasourceManagerGenericTargetIdentifier();
	}

}
