/**
 * 
 */
package net.datenwerke.rs.transport.client.transport.security;

import net.datenwerke.rs.transport.service.transport.genrights.TransportAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(TransportAdminViewSecurityTarget.class)
public class TransportGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -6236104192138664732L;

}