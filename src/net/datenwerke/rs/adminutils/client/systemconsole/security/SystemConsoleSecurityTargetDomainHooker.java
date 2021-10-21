package net.datenwerke.rs.adminutils.client.systemconsole.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class SystemConsoleSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.AREA_CHART.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return SystemConsoleMessages.INSTANCE.systemConsole();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return SystemConsoleMessages.INSTANCE.systemConsolePermissionModuleDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new SystemConsoleGenericTargetIdentifier();
   }

}
