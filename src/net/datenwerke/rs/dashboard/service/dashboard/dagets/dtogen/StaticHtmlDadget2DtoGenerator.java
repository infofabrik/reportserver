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
import net.datenwerke.rs.dashboard.client.dashboard.dto.StaticHtmlDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.StaticHtmlDadgetDtoDec;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.StaticHtmlDadget;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.StaticHtmlDadget2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for StaticHtmlDadget
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class StaticHtmlDadget2DtoGenerator implements Poso2DtoGenerator<StaticHtmlDadget,StaticHtmlDadgetDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public StaticHtmlDadget2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public StaticHtmlDadgetDtoDec instantiateDto(StaticHtmlDadget poso)  {
		StaticHtmlDadgetDtoDec dto = new StaticHtmlDadgetDtoDec();
		return dto;
	}

	public StaticHtmlDadgetDtoDec createDto(StaticHtmlDadget poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final StaticHtmlDadgetDtoDec dto = new StaticHtmlDadgetDtoDec();
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

			/*  set data */
			dto.setData(poso.getData() );

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

			/*  set title */
			dto.setTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTitle(),8192)));

		}

		return dto;
	}


}
