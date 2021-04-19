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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.LibraryDadgetDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.LibraryDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.LibraryDadget2DtoGenerator;

/**
 * Poso2DtoGenerator for LibraryDadget
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class LibraryDadget2DtoGenerator implements Poso2DtoGenerator<LibraryDadget,LibraryDadgetDtoDec> {

	private final net.datenwerke.rs.dashboard.service.dashboard.dagets.post.LibraryDadget2DtoPost postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public LibraryDadget2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.dashboard.service.dashboard.dagets.post.LibraryDadget2DtoPost postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public LibraryDadgetDtoDec instantiateDto(LibraryDadget poso)  {
		LibraryDadgetDtoDec dto = new LibraryDadgetDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public LibraryDadgetDtoDec createDto(LibraryDadget poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final LibraryDadgetDtoDec dto = new LibraryDadgetDtoDec();
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

			/*  set dadgetNode */
			Object tmpDtoDadgetNodeDtogetDadgetNode = dtoServiceProvider.get().createDto(poso.getDadgetNode(), here, referenced);
			dto.setDadgetNode((DadgetNodeDto)tmpDtoDadgetNodeDtogetDadgetNode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDadgetNodeDtogetDadgetNode, poso.getDadgetNode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDadgetNode((DadgetNodeDto)refDto);
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

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
