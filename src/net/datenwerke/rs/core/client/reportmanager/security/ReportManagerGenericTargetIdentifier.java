/**
 * 
 */
package net.datenwerke.rs.core.client.reportmanager.security;

import net.datenwerke.rs.core.service.genrights.reportmanager.ReportManagerAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(ReportManagerAdminViewSecurityTarget.class) 
public class ReportManagerGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2682911959806911761L;

	
}