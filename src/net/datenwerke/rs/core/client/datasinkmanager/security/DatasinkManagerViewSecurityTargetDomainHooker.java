package net.datenwerke.rs.core.client.datasinkmanager.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class DatasinkManagerViewSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.SERVER.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return DatasinksMessages.INSTANCE.datasinks();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return DatasinksMessages.INSTANCE.datasinkPermissionModuleDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new DatasinkManagerGenericTargetIdentifier();
   }

}
