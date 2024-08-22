/**
 * 
 */
package net.datenwerke.rs.transport.client.transport.security;

import net.datenwerke.rs.transport.service.transport.genrights.TransportManagementAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(TransportManagementAdminViewSecurityTarget.class)
public class TransportManagementGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -6236104192138664732L;

}