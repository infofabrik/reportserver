/**
 * 
 */
package net.datenwerke.rs.scheduler.client.scheduler.security;

import net.datenwerke.rs.scheduler.service.scheduler.genrights.SchedulingAdminSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(SchedulingAdminSecurityTarget.class)
public class SchedulingAdminViewGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = 2245411445759565802L;

}