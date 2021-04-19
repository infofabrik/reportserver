package net.datenwerke.rs.teamspace.service.teamspace.entities;

import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;


/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.teamspace.client.teamspace.dto"
)
public enum TeamSpaceRole {
	
	@EnumLabel(msg=TeamSpaceMessages.class,key="memberRoleAdmin")
	ADMIN,
	
	@EnumLabel(msg=TeamSpaceMessages.class,key="memberRoleManager")
	MANAGER,
	
	@EnumLabel(msg=TeamSpaceMessages.class,key="memberRoleUser")
	USER,
	
	@EnumLabel(msg=TeamSpaceMessages.class,key="memberRoleGuest")
	GUEST
}
