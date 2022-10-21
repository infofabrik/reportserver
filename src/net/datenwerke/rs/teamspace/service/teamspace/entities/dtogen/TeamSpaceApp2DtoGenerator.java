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
import net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;
import net.datenwerke.rs.teamspace.service.teamspace.entities.AppProperty;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.TeamSpaceApp2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TeamSpaceApp
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpaceApp2DtoGenerator implements Poso2DtoGenerator<TeamSpaceApp,TeamSpaceAppDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TeamSpaceApp2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TeamSpaceAppDto instantiateDto(TeamSpaceApp poso)  {
		TeamSpaceAppDto dto = new TeamSpaceAppDto();
		return dto;
	}

	public TeamSpaceAppDto createDto(TeamSpaceApp poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TeamSpaceAppDto dto = new TeamSpaceAppDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set appProperties */
			final Set<AppPropertyDto> col_appProperties = new HashSet<AppPropertyDto>();
			if( null != poso.getAppProperties()){
				for(AppProperty refPoso : poso.getAppProperties()){
					final Object tmpDtoAppPropertyDtogetAppProperties = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_appProperties.add((AppPropertyDto) tmpDtoAppPropertyDtogetAppProperties);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAppPropertyDtogetAppProperties, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (appProperties)");
							col_appProperties.remove(tmpDtoAppPropertyDtogetAppProperties);
							col_appProperties.add((AppPropertyDto) dto);
						}
					});
				}
				dto.setAppProperties(col_appProperties);
			}

			/*  set installed */
			dto.setInstalled(poso.isInstalled() );

			/*  set type */
			dto.setType(StringEscapeUtils.escapeXml(StringUtils.left(poso.getType(),8192)));

		}

		return dto;
	}


}
