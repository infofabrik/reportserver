package net.datenwerke.rs.teamspace.service.teamspace.security.rights.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.security.rights.TeamSpaceAdministratorDto;
import net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator;
import net.datenwerke.rs.teamspace.service.teamspace.security.rights.dtogen.TeamSpaceAdministrator2DtoGenerator;

/**
 * Poso2DtoGenerator for TeamSpaceAdministrator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpaceAdministrator2DtoGenerator implements Poso2DtoGenerator<TeamSpaceAdministrator,TeamSpaceAdministratorDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TeamSpaceAdministrator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TeamSpaceAdministratorDto instantiateDto(TeamSpaceAdministrator poso)  {
		TeamSpaceAdministratorDto dto = new TeamSpaceAdministratorDto();
		return dto;
	}

	public TeamSpaceAdministratorDto createDto(TeamSpaceAdministrator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TeamSpaceAdministratorDto dto = new TeamSpaceAdministratorDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set Abbreviation */
			dto.setAbbreviation(poso.getAbbreviation() );

			/*  set BitField */
			dto.setBitField(poso.getBitField() );

			/*  set Description */
			dto.setDescription(poso.getDescription() );

		}

		return dto;
	}


}
