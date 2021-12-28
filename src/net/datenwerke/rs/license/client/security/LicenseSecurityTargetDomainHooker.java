package net.datenwerke.rs.license.client.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.license.client.locale.LicenseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class LicenseSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.CERTIFICATE.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return LicenseMessages.INSTANCE.viewNavigationTitle();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return LicenseMessages.INSTANCE.permissionModuleDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new LicenseGenericTargetIdentifier();
   }

}
