package net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListDadgetDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.FavoriteListDadget2DtoGenerator;

/**
 * Poso2DtoGenerator for FavoriteListDadget
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FavoriteListDadget2DtoGenerator implements Poso2DtoGenerator<FavoriteListDadget,FavoriteListDadgetDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FavoriteListDadget2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FavoriteListDadgetDtoDec instantiateDto(FavoriteListDadget poso)  {
		FavoriteListDadgetDtoDec dto = new FavoriteListDadgetDtoDec();
		return dto;
	}

	public FavoriteListDadgetDtoDec createDto(FavoriteListDadget poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FavoriteListDadgetDtoDec dto = new FavoriteListDadgetDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set col */
			dto.setCol(poso.getCol() );

			/*  set container */
			Object tmpDtoDadgetContainerDtogetContainer = dtoServiceProvider.get().createDto(poso.getContainer(), referenced, referenced);
			dto.setContainer((DadgetContainerDto)tmpDtoDadgetContainerDtogetContainer);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDadgetContainerDtogetContainer, poso.getContainer(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setContainer((DadgetContainerDto)refDto);
				}
			});

			/*  set height */
			dto.setHeight(poso.getHeight() );

			/*  set n */
			dto.setN(poso.getN() );

			/*  set parameterInstances */
			final Set<ParameterInstanceDto> col_parameterInstances = new HashSet<ParameterInstanceDto>();
			if( null != poso.getParameterInstances()){
				for(ParameterInstance refPoso : poso.getParameterInstances()){
					final Object tmpDtoParameterInstanceDtogetParameterInstances = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_parameterInstances.add((ParameterInstanceDto) tmpDtoParameterInstanceDtogetParameterInstances);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterInstanceDtogetParameterInstances, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (parameterInstances)");
							col_parameterInstances.remove(tmpDtoParameterInstanceDtogetParameterInstances);
							col_parameterInstances.add((ParameterInstanceDto) dto);
						}
					});
				}
				dto.setParameterInstances(col_parameterInstances);
			}

			/*  set reloadInterval */
			dto.setReloadInterval(poso.getReloadInterval() );

		}

		return dto;
	}


}
