package net.datenwerke.rs.teamspace.service.teamspace.security.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.security.TeamSpaceSecureeDto;
import net.datenwerke.rs.teamspace.service.teamspace.security.TeamSpaceSecuree;
import net.datenwerke.rs.teamspace.service.teamspace.security.dtogen.TeamSpaceSecuree2DtoGenerator;
import net.datenwerke.security.client.security.dto.RightDto;
import net.datenwerke.security.service.security.rights.Right;

/**
 * Poso2DtoGenerator for TeamSpaceSecuree
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpaceSecuree2DtoGenerator implements Poso2DtoGenerator<TeamSpaceSecuree,TeamSpaceSecureeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TeamSpaceSecuree2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TeamSpaceSecureeDto instantiateDto(TeamSpaceSecuree poso)  {
		TeamSpaceSecureeDto dto = new TeamSpaceSecureeDto();
		return dto;
	}

	public TeamSpaceSecureeDto createDto(TeamSpaceSecuree poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TeamSpaceSecureeDto dto = new TeamSpaceSecureeDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set Name */
			dto.setName(poso.getName() );

			/*  set Rights */
			final List<RightDto> col_Rights = new ArrayList<RightDto>();
			if( null != poso.getRights()){
				for(Right refPoso : poso.getRights()){
					final Object tmpDtoRightDtogetRights = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_Rights.add((RightDto) tmpDtoRightDtogetRights);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoRightDtogetRights, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (Rights)");
							int tmp_index = col_Rights.indexOf(tmpDtoRightDtogetRights);
							col_Rights.set(tmp_index,(RightDto) dto);
						}
					});
				}
				dto.setRights(col_Rights);
			}

			/*  set SecureeId */
			dto.setSecureeId(poso.getSecureeId() );

		}

		return dto;
	}


}
