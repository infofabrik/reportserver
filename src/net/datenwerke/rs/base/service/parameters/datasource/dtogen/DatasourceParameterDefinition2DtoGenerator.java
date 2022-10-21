package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.DatasourceParameterDefinition2DtoGenerator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DatasourceParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceParameterDefinition2DtoGenerator implements Poso2DtoGenerator<DatasourceParameterDefinition,DatasourceParameterDefinitionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasourceParameterDefinition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasourceParameterDefinitionDto instantiateDto(DatasourceParameterDefinition poso)  {
		DatasourceParameterDefinitionDto dto = new DatasourceParameterDefinitionDto();
		return dto;
	}

	public DatasourceParameterDefinitionDto createDto(DatasourceParameterDefinition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasourceParameterDefinitionDto dto = new DatasourceParameterDefinitionDto();
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
			/*  set boxLayoutMode */
			Object tmpDtoBoxLayoutModeDtogetBoxLayoutMode = dtoServiceProvider.get().createDto(poso.getBoxLayoutMode(), referenced, referenced);
			dto.setBoxLayoutMode((BoxLayoutModeDto)tmpDtoBoxLayoutModeDtogetBoxLayoutMode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoBoxLayoutModeDtogetBoxLayoutMode, poso.getBoxLayoutMode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setBoxLayoutMode((BoxLayoutModeDto)refDto);
				}
			});

			/*  set boxLayoutPackColSize */
			dto.setBoxLayoutPackColSize(poso.getBoxLayoutPackColSize() );

			/*  set boxLayoutPackMode */
			Object tmpDtoBoxLayoutPackModeDtogetBoxLayoutPackMode = dtoServiceProvider.get().createDto(poso.getBoxLayoutPackMode(), referenced, referenced);
			dto.setBoxLayoutPackMode((BoxLayoutPackModeDto)tmpDtoBoxLayoutPackModeDtogetBoxLayoutPackMode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoBoxLayoutPackModeDtogetBoxLayoutPackMode, poso.getBoxLayoutPackMode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setBoxLayoutPackMode((BoxLayoutPackModeDto)refDto);
				}
			});

			/*  set datasourceContainer */
			Object tmpDtoDatasourceContainerDtogetDatasourceContainer = dtoServiceProvider.get().createDto(poso.getDatasourceContainer(), here, referenced);
			dto.setDatasourceContainer((DatasourceContainerDto)tmpDtoDatasourceContainerDtogetDatasourceContainer);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceContainerDtogetDatasourceContainer, poso.getDatasourceContainer(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDatasourceContainer((DatasourceContainerDto)refDto);
				}
			});

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

			/*  set format */
			dto.setFormat(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFormat(),8192)));

			/*  set height */
			dto.setHeight(poso.getHeight() );

			/*  set hidden */
			dto.setHidden(poso.isHidden() );

			/*  set labelWidth */
			dto.setLabelWidth(poso.getLabelWidth() );

			/*  set mandatory */
			dto.setMandatory(poso.isMandatory() );

			/*  set mode */
			Object tmpDtoModeDtogetMode = dtoServiceProvider.get().createDto(poso.getMode(), referenced, referenced);
			dto.setMode((ModeDto)tmpDtoModeDtogetMode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoModeDtogetMode, poso.getMode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setMode((ModeDto)refDto);
				}
			});

			/*  set multiDefaultValueSimpleData */
			final List<DatasourceParameterDataDto> col_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterDataDto>();
			if( null != poso.getMultiDefaultValueSimpleData()){
				for(DatasourceParameterData refPoso : poso.getMultiDefaultValueSimpleData()){
					final Object tmpDtoDatasourceParameterDataDtogetMultiDefaultValueSimpleData = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_multiDefaultValueSimpleData.add((DatasourceParameterDataDto) tmpDtoDatasourceParameterDataDtogetMultiDefaultValueSimpleData);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceParameterDataDtogetMultiDefaultValueSimpleData, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (multiDefaultValueSimpleData)");
							int tmp_index = col_multiDefaultValueSimpleData.indexOf(tmpDtoDatasourceParameterDataDtogetMultiDefaultValueSimpleData);
							col_multiDefaultValueSimpleData.set(tmp_index,(DatasourceParameterDataDto) dto);
						}
					});
				}
				dto.setMultiDefaultValueSimpleData(col_multiDefaultValueSimpleData);
			}

			/*  set multiSelectionMode */
			Object tmpDtoMultiSelectionModeDtogetMultiSelectionMode = dtoServiceProvider.get().createDto(poso.getMultiSelectionMode(), referenced, referenced);
			dto.setMultiSelectionMode((MultiSelectionModeDto)tmpDtoMultiSelectionModeDtogetMultiSelectionMode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoMultiSelectionModeDtogetMultiSelectionMode, poso.getMultiSelectionMode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setMultiSelectionMode((MultiSelectionModeDto)refDto);
				}
			});

			/*  set n */
			dto.setN(poso.getN() );

			/*  set postProcess */
			dto.setPostProcess(StringEscapeUtils.escapeXml(StringUtils.left(poso.getPostProcess(),8192)));

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

			/*  set singleDefaultValueSimpleData */
			Object tmpDtoDatasourceParameterDataDtogetSingleDefaultValueSimpleData = dtoServiceProvider.get().createDto(poso.getSingleDefaultValueSimpleData(), here, referenced);
			dto.setSingleDefaultValueSimpleData((DatasourceParameterDataDto)tmpDtoDatasourceParameterDataDtogetSingleDefaultValueSimpleData);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceParameterDataDtogetSingleDefaultValueSimpleData, poso.getSingleDefaultValueSimpleData(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setSingleDefaultValueSimpleData((DatasourceParameterDataDto)refDto);
				}
			});

			/*  set singleSelectionMode */
			Object tmpDtoSingleSelectionModeDtogetSingleSelectionMode = dtoServiceProvider.get().createDto(poso.getSingleSelectionMode(), referenced, referenced);
			dto.setSingleSelectionMode((SingleSelectionModeDto)tmpDtoSingleSelectionModeDtogetSingleSelectionMode);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoSingleSelectionModeDtogetSingleSelectionMode, poso.getSingleSelectionMode(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setSingleSelectionMode((SingleSelectionModeDto)refDto);
				}
			});

			/*  set width */
			dto.setWidth(poso.getWidth() );

		}

		return dto;
	}


}
