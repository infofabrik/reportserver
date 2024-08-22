package net.datenwerke.rs.remoteserver.client.remoteservermanager.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class RemoteServerManagerViewSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.ALIGN_CENTER.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return RemoteServerMessages.INSTANCE.remoteServers();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return RemoteServerMessages.INSTANCE.remoteServerPermissionModuleDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new RemoteServerManagerGenericTargetIdentifier();
   }

}
