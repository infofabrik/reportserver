/**
 * 
 */
package net.datenwerke.rs.uservariables.client.uservariables.genrights;

import net.datenwerke.rs.uservariables.service.genrights.UserVariableAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(UserVariableAdminViewSecurityTarget.class)
public class UserVariableAdminViewGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -8129331353993021497L;

}