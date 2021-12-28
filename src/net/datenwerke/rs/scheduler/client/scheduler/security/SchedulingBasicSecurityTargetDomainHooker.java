package net.datenwerke.rs.scheduler.client.scheduler.security;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;

public class SchedulingBasicSecurityTargetDomainHooker implements GenericSecurityViewDomainHook {

   public ImageResource genericSecurityViewDomainHook_getIcon() {
      return BaseIcon.CLOCK_O.toImageResource();
   }

   public String genericSecurityViewDomainHook_getName() {
      return SchedulerMessages.INSTANCE.schedulerBasicHeading();
   }

   public String genericSecurityViewDomainHook_getDescription() {
      return SchedulerMessages.INSTANCE.schedulerBasicDescription();
   }

   public GenericTargetIdentifier genericSecurityViewDomainHook_getTargetId() {
      return new SchedulingBasicGenericTargetIdentifier();
   }

}
