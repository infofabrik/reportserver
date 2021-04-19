package net.datenwerke.rs.base.service.parameters.datetime.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.decorator.DateTimeParameterInstanceDtoDec;
import net.datenwerke.rs.base.service.parameters.datetime.DateTimeParameterInstance;
import net.datenwerke.rs.base.service.parameters.datetime.dtogen.DateTimeParameterInstance2DtoGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DateTimeParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DateTimeParameterInstance2DtoGenerator implements Poso2DtoGenerator<DateTimeParameterInstance,DateTimeParameterInstanceDtoDec> {

	private final net.datenwerke.rs.base.service.parameters.datetime.dtogen.DateTimeParameterInstance2DtoPostProcessor postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DateTimeParameterInstance2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.base.service.parameters.datetime.dtogen.DateTimeParameterInstance2DtoPostProcessor postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public DateTimeParameterInstanceDtoDec instantiateDto(DateTimeParameterInstance poso)  {
		DateTimeParameterInstanceDtoDec dto = new DateTimeParameterInstanceDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public DateTimeParameterInstanceDtoDec createDto(DateTimeParameterInstance poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DateTimeParameterInstanceDtoDec dto = new DateTimeParameterInstanceDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set definition */
			Object tmpDtoParameterDefinitionDtogetDefinition = dtoServiceProvider.get().createDto(poso.getDefinition(), referenced, referenced);
			dto.setDefinition((ParameterDefinitionDto)tmpDtoParameterDefinitionDtogetDefinition);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterDefinitionDtogetDefinition, poso.getDefinition(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDefinition((ParameterDefinitionDto)refDto);
				}
			});

			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set formula */
			dto.setFormula(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFormula(),8192)));

			/*  set stillDefault */
			dto.setStillDefault(poso.isStillDefault() );

			/*  set strValue */
			dto.setStrValue(StringEscapeUtils.escapeXml(StringUtils.left(poso.getStrValue(),8192)));

			/*  set value */
			dto.setValue(poso.getValue() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
