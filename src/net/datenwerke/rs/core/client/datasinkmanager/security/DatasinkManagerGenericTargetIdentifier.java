/**
 * 
 */
package net.datenwerke.rs.core.client.datasinkmanager.security;

import net.datenwerke.rs.core.service.genrights.datasinks.DatasinkManagerAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(DatasinkManagerAdminViewSecurityTarget.class)
public class DatasinkManagerGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
    * 
    */
   private static final long serialVersionUID = -6236104192138664732L;

}