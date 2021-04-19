package net.datenwerke.rs.globalconstants.client.globalconstants.security;

import net.datenwerke.rs.globalconstants.service.globalconstants.genrights.GlobalConstantsAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(GlobalConstantsAdminViewSecurityTarget.class) 
public class GlobalConstantsGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5572753226889248753L;
	
}