/**
 * 
 */
package net.datenwerke.security.ext.client.usermanager.security;

import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.genrights.usermanager.UserManagerAdminViewSecurityTarget;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(UserManagerAdminViewSecurityTarget.class)
public class UserManagerAdminViewGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -8129331353993021497L;

}