/**
 * 
 */
package net.datenwerke.security.ext.client.security.ui.genericview.targets;

import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.genrights.security.GenericSecurityTargetAdminViewSecurityTarget;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target.
 *
 */
@GenericTargetIdentifierMapper(GenericSecurityTargetAdminViewSecurityTarget.class)
public class GenericSecurityTargetAdminViewGenericTargetIdentifier implements GenericTargetIdentifier {

   /**
   	 * 
   	 */
   private static final long serialVersionUID = 8647141676124462249L;

}