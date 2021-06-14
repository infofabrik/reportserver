package net.datenwerke.rs.terminal.client.terminal.security;

import net.datenwerke.rs.terminal.client.terminal.locale.TerminalMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

import com.google.gwt.resources.client.ImageResource;

public class TerminalSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.TERMINAL.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return TerminalMessages.INSTANCE.adminLabel();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return TerminalMessages.INSTANCE.permissionModuleDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new TerminalGenericTargetIdentifier();
   }

}
