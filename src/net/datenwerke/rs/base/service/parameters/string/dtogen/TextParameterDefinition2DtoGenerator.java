package net.datenwerke.rs.base.service.parameters.string.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto;
import net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition;
import net.datenwerke.rs.base.service.parameters.string.dtogen.TextParameterDefinition2DtoGenerator;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TextParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TextParameterDefinition2DtoGenerator implements Poso2DtoGenerator<TextParameterDefinition,TextParameterDefinitionDto> {

	private final net.datenwerke.rs.base.service.parameters.string.post.TextParameterDefinitionPost postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TextParameterDefinition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.base.service.parameters.string.post.TextParameterDefinitionPost postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public TextParameterDefinitionDto instantiateDto(TextParameterDefinition poso)  {
		TextParameterDefinitionDto dto = new TextParameterDefinitionDto();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public TextParameterDefinitionDto createDto(TextParameterDefinition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TextParameterDefinitionDto dto = new TextParameterDefinitionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set defaultValue */
			dto.setDefaultValue(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDefaultValue(),8192)));

			/*  set dependsOn */
			final List<ParameterDefinitionDto> col_dependsOn = new ArrayList<ParameterDefinitionDto>();
			if( null != poso.getDependsOn()){
				for(ParameterDefinition refPoso : poso.getDependsOn()){
					final Object tmpDtoParameterDefinitionDtogetDependsOn = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
					col_dependsOn.add((ParameterDefinitionDto) tmpDtoParameterDefinitionDtogetDependsOn);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterDefinitionDtogetDependsOn, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (dependsOn)");
							int tmp_index = col_dependsOn.indexOf(tmpDtoParameterDefinitionDtogetDependsOn);
							col_dependsOn.set(tmp_index,(ParameterDefinitionDto) dto);
						}
					});
				}
				dto.setDependsOn(col_dependsOn);
			}

			/*  set displayInline */
			dto.setDisplayInline(poso.isDisplayInline() );

			/*  set editable */
			dto.setEditable(poso.isEditable() );

			/*  set height */
			dto.setHeight(poso.getHeight() );

			/*  set hidden */
			dto.setHidden(poso.isHidden() );

			/*  set labelWidth */
			dto.setLabelWidth(poso.getLabelWidth() );

			/*  set mandatory */
			dto.setMandatory(poso.isMandatory() );

			/*  set n */
			dto.setN(poso.getN() );

			/*  set returnNullWhenEmpty */
			dto.setReturnNullWhenEmpty(poso.isReturnNullWhenEmpty() );

			/*  set returnType */
			Object tmpDtoDatatypeDtogetReturnType = dtoServiceProvider.get().createDto(poso.getReturnType(), referenced, referenced);
			dto.setReturnType((DatatypeDto)tmpDtoDatatypeDtogetReturnType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatatypeDtogetReturnType, poso.getReturnType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setReturnType((DatatypeDto)refDto);
				}
			});

			/*  set validatorRegex */
			dto.setValidatorRegex(StringEscapeUtils.escapeXml(StringUtils.left(poso.getValidatorRegex(),8192)));

			/*  set width */
			dto.setWidth(poso.getWidth() );

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
