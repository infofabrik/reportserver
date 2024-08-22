/**
 * 
 */
package net.datenwerke.rs.remoteserver.client.remoteservermanager.security;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.genrights.RemoteServerManagerAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(RemoteServerManagerAdminViewSecurityTarget.class)
public class RemoteServerManagerGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -6236104192138664732L;

}