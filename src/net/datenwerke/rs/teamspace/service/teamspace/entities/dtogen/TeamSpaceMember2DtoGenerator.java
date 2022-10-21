package net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceRoleDto;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpaceMember2DtoGenerator;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

/**
 * Poso2DtoGenerator for TeamSpaceMember
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpaceMember2DtoGenerator implements Poso2DtoGenerator<TeamSpaceMember,TeamSpaceMemberDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TeamSpaceMember2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TeamSpaceMemberDto instantiateDto(TeamSpaceMember poso)  {
		TeamSpaceMemberDto dto = new TeamSpaceMemberDto();
		return dto;
	}

	public TeamSpaceMemberDto createDto(TeamSpaceMember poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TeamSpaceMemberDto dto = new TeamSpaceMemberDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set folk */
			Object tmpDtoAbstractUserManagerNodeDtogetFolk = dtoServiceProvider.get().createDto(poso.getFolk(), referenced, referenced);
			dto.setFolk((AbstractUserManagerNodeDto)tmpDtoAbstractUserManagerNodeDtogetFolk);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAbstractUserManagerNodeDtogetFolk, poso.getFolk(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFolk((AbstractUserManagerNodeDto)refDto);
				}
			});

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set role */
			Object tmpDtoTeamSpaceRoleDtogetRole = dtoServiceProvider.get().createDto(poso.getRole(), referenced, referenced);
			dto.setRole((TeamSpaceRoleDto)tmpDtoTeamSpaceRoleDtogetRole);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTeamSpaceRoleDtogetRole, poso.getRole(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setRole((TeamSpaceRoleDto)refDto);
				}
			});

		}

		return dto;
	}


}
