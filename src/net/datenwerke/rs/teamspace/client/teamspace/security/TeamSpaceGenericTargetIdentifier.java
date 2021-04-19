/**
 * 
 */
package net.datenwerke.rs.teamspace.client.teamspace.security;

import net.datenwerke.rs.teamspace.service.teamspace.genrights.TeamSpaceSecurityTarget;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.service.security.annotation.GenericTargetIdentifierMapper;

/**
 * Transport object to identify the corresponding generic security target. 
 *
 */
@GenericTargetIdentifierMapper(TeamSpaceSecurityTarget.class) 
public class TeamSpaceGenericTargetIdentifier implements GenericTargetIdentifier{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6320512532161207064L;


	
}