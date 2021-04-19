/**
 * 
 */
package net.datenwerke.rs.core.client.datasourcemanager.security;

import net.datenwerke.rs.core.service.genrights.datasources.DatasourceManagerAdminViewSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(DatasourceManagerAdminViewSecurityTarget.class) 
public class DatasourceManagerGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6236104192138664732L;


	
}