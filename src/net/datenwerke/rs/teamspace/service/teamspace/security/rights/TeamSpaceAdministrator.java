package net.datenwerke.rs.teamspace.service.teamspace.security.rights;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.teamspace.service.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.teamspace.service.teamspace.security.TeamSpaceRightsDefinition;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.rights.Right;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.teamspace.client.teamspace.security.rights"
)
public class TeamSpaceAdministrator implements Right {

	private final TeamSpaceMessages messages = LocalizationServiceImpl.getMessages(TeamSpaceMessages.class);
	
	@Override
	public String getDescription() {
		return messages.rightTeamSpaceAdminDescription();
	}

	@Override
	public long getBitField() {
		return TeamSpaceRightsDefinition.RIGHT_ADMINISTRATOR;
	}

	@Override
	public String getAbbreviation() {
		return messages.rightTeamSpaceAdminAbbreviation();
	}
}
