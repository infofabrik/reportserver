/**
 * 
 */
package net.datenwerke.rs.adminutils.client.suuser.security;

import net.datenwerke.rs.adminutils.service.su.genrights.SuSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(SuSecurityTarget.class)
public class SuGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -2538043428243902673L;

}