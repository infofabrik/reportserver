/**
 * 
 */
package net.datenwerke.rs.core.client.genrights;

import net.datenwerke.rs.core.service.genrights.access.AccessRsSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(AccessRsSecurityTarget.class) 
public class AccessRsGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4459944343694544921L;



	
}