/**
 * 
 */
package net.datenwerke.rs.transport.client.transport.security;

import net.datenwerke.rs.core.service.genrights.transport.TransportManagerAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(TransportManagerAdminViewSecurityTarget.class)
public class TransportManagerGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -6236104192138664732L;

}