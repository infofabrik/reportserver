package net.datenwerke.rs.core.client.reportmanager.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class ReportManagerViewSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.FILE.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return ReportmanagerMessages.INSTANCE.reportmanager();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return ReportmanagerMessages.INSTANCE.reportSecurityDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new ReportManagerGenericTargetIdentifier();
   }

}
