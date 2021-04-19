package net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.decorator.TeamSpaceDtoDec;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpace2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TeamSpace
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpace2DtoGenerator implements Poso2DtoGenerator<TeamSpace,TeamSpaceDtoDec> {

	private final net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpace2DtoPostProcessor postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TeamSpace2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpace2DtoPostProcessor postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public TeamSpaceDtoDec instantiateDto(TeamSpace poso)  {
		TeamSpaceDtoDec dto = new TeamSpaceDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public TeamSpaceDtoDec createDto(TeamSpace poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TeamSpaceDtoDec dto = new TeamSpaceDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set owner */
			Object tmpDtoUserDtogetOwner = dtoServiceProvider.get().createDto(poso.getOwner(), referenced, referenced);
			dto.setOwner((UserDto)tmpDtoUserDtogetOwner);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserDtogetOwner, poso.getOwner(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setOwner((UserDto)refDto);
				}
			});

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set apps */
			final Set<TeamSpaceAppDto> col_apps = new HashSet<TeamSpaceAppDto>();
			if( null != poso.getApps()){
				for(TeamSpaceApp refPoso : poso.getApps()){
					final Object tmpDtoTeamSpaceAppDtogetApps = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_apps.add((TeamSpaceAppDto) tmpDtoTeamSpaceAppDtogetApps);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTeamSpaceAppDtogetApps, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (apps)");
							col_apps.remove(tmpDtoTeamSpaceAppDtogetApps);
							col_apps.add((TeamSpaceAppDto) dto);
						}
					});
				}
				dto.setApps(col_apps);
			}

		}
		if(here.compareTo(DtoView.ALL) >= 0){
			/*  set members */
			final Set<TeamSpaceMemberDto> col_members = new HashSet<TeamSpaceMemberDto>();
			if( null != poso.getMembers()){
				for(TeamSpaceMember refPoso : poso.getMembers()){
					final Object tmpDtoTeamSpaceMemberDtogetMembers = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_members.add((TeamSpaceMemberDto) tmpDtoTeamSpaceMemberDtogetMembers);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTeamSpaceMemberDtogetMembers, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (members)");
							col_members.remove(tmpDtoTeamSpaceMemberDtogetMembers);
							col_members.add((TeamSpaceMemberDto) dto);
						}
					});
				}
				dto.setMembers(col_members);
			}

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
