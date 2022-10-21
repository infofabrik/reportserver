package net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpaceRole2DtoGenerator;

/**
 * Poso2DtoGenerator for TeamSpaceRole
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpaceRole2DtoGenerator implements Poso2DtoGenerator<TeamSpaceRole,TeamSpaceRoleDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TeamSpaceRole2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TeamSpaceRoleDto instantiateDto(TeamSpaceRole poso)  {
		/* Simply return the first enum! */
		TeamSpaceRoleDto dto = TeamSpaceRoleDto.ADMIN;
		return dto;
	}

	public TeamSpaceRoleDto createDto(TeamSpaceRole poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case ADMIN:
				return TeamSpaceRoleDto.ADMIN;
			case MANAGER:
				return TeamSpaceRoleDto.MANAGER;
			case USER:
				return TeamSpaceRoleDto.USER;
			case GUEST:
				return TeamSpaceRoleDto.GUEST;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
