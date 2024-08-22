package net.datenwerke.rs.transport.client.transport.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class TransportManagementViewSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.ARCHIVE.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return TransportMessages.INSTANCE.dialogDescription();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return TransportMessages.INSTANCE.transportManagementPermissionModuleDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new TransportManagementGenericTargetIdentifier();
   }

}