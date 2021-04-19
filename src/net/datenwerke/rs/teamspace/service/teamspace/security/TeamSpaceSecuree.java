package net.datenwerke.rs.teamspace.service.teamspace.security;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.teamspace.service.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.client.security.dto.SecureeDto;
import net.datenwerke.security.service.security.Securee;
import net.datenwerke.security.service.security.rights.Right;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.teamspace.client.teamspace.security",
	dtoImplementInterfaces=SecureeDto.class
)
public class TeamSpaceSecuree implements Securee{
	
	private final TeamSpaceMessages messages = LocalizationServiceImpl.getMessages(TeamSpaceMessages.class);
	
	public static final String SECUREE_ID = "TeamSpaceService_Securee"; //$NON-NLS-1$
	
	@Override
	public List<Right> getRights() {
		List<Right> rights = new ArrayList<Right>();
		
		rights.add(new TeamSpaceAdministrator());
		
		return rights;
	}

	@Override
	public String getSecureeId() {
		return SECUREE_ID;
	}

	@Override
	public String getName(){
		return messages.teamSpaceSecureeName(); 
	}
	

}
