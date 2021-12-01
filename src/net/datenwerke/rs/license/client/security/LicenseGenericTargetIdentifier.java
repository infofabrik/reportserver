/**
 * 
 */
package net.datenwerke.rs.license.client.security;

import net.datenwerke.rs.license.service.genrights.LicenseSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(LicenseSecurityTarget.class) 
public class LicenseGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	
}