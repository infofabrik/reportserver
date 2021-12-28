package net.datenwerke.security.ext.client.usermanager.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;

public class UserManagerAdminViewSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.USER.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return UsermanagerMessages.INSTANCE.usermanagement();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return UsermanagerMessages.INSTANCE.usermanagementDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new UserManagerAdminViewGenericTargetIdentifier();
   }

}
