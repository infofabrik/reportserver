package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutMode;
import net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutPackMode;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datasource.Mode;
import net.datenwerke.rs.base.service.parameters.datasource.MultiSelectionMode;
import net.datenwerke.rs.base.service.parameters.datasource.SingleSelectionMode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2DatasourceParameterDefinitionGenerator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.parameters.dto.DatatypeDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.parameters.entities.Datatype;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasourceParameterDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasourceParameterDefinitionGenerator implements Dto2PosoGenerator<DatasourceParameterDefinitionDto,DatasourceParameterDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasourceParameterDefinitionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public DatasourceParameterDefinition loadPoso(DatasourceParameterDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasourceParameterDefinition poso = entityManager.find(DatasourceParameterDefinition.class, id);
		return poso;
	}

	public DatasourceParameterDefinition instantiatePoso()  {
		DatasourceParameterDefinition poso = new DatasourceParameterDefinition();
		return poso;
	}

	public DatasourceParameterDefinition createPoso(DatasourceParameterDefinitionDto dto)  throws ExpectedException {
		DatasourceParameterDefinition poso = new DatasourceParameterDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasourceParameterDefinition createUnmanagedPoso(DatasourceParameterDefinitionDto dto)  throws ExpectedException {
		DatasourceParameterDefinition poso = new DatasourceParameterDefinition();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(DatasourceParameterDefinitionDto dto, final DatasourceParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasourceParameterDefinitionDto dto, final DatasourceParameterDefinition poso)  throws ExpectedException {
		/*  set boxLayoutMode */
		BoxLayoutModeDto tmpDto_boxLayoutMode = dto.getBoxLayoutMode();
		poso.setBoxLayoutMode((BoxLayoutMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutMode));

		/*  set boxLayoutPackColSize */
		try{
			poso.setBoxLayoutPackColSize(dto.getBoxLayoutPackColSize() );
		} catch(NullPointerException e){
		}

		/*  set boxLayoutPackMode */
		BoxLayoutPackModeDto tmpDto_boxLayoutPackMode = dto.getBoxLayoutPackMode();
		poso.setBoxLayoutPackMode((BoxLayoutPackMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutPackMode));

		/*  set datasourceContainer */
		DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
		if(null != tmpDto_datasourceContainer && null != tmpDto_datasourceContainer.getId()){
			if(null != poso.getDatasourceContainer() && null != poso.getDatasourceContainer().getId() && poso.getDatasourceContainer().getId().equals(tmpDto_datasourceContainer.getId()))
				poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().loadAndMergePoso(tmpDto_datasourceContainer));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (datasourceContainer)");
		} else if(null != poso.getDatasourceContainer()){
			DatasourceContainer newPropertyValue = (DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDatasourceContainer(), newPropertyValue, "datasourceContainer");
			poso.setDatasourceContainer(newPropertyValue);
		} else {
			poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer));
		}

		/*  set dependsOn */
		final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
		/* load new data from dto */
		Collection<ParameterDefinitionDto> tmpCol_dependsOn = dto.getDependsOn();

		for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
			if(null != refDto && null != refDto.getId())
				col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
						col_dependsOn.add((ParameterDefinition) poso);
					}
					});
			}
		}
		poso.setDependsOn(col_dependsOn);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set displayInline */
		poso.setDisplayInline(dto.isDisplayInline() );

		/*  set editable */
		poso.setEditable(dto.isEditable() );

		/*  set format */
		poso.setFormat(dto.getFormat() );

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set hidden */
		poso.setHidden(dto.isHidden() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set labelWidth */
		poso.setLabelWidth(dto.getLabelWidth() );

		/*  set mandatory */
		try{
			poso.setMandatory(dto.isMandatory() );
		} catch(NullPointerException e){
		}

		/*  set mode */
		ModeDto tmpDto_mode = dto.getMode();
		poso.setMode((Mode)dtoServiceProvider.get().createPoso(tmpDto_mode));

		/*  set multiDefaultValueSimpleData */
		final List<DatasourceParameterData> col_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
		/* load new data from dto */
		Collection<DatasourceParameterDataDto> tmpCol_multiDefaultValueSimpleData = dto.getMultiDefaultValueSimpleData();

		/* load current data from poso */
		if(null != poso.getMultiDefaultValueSimpleData())
			col_multiDefaultValueSimpleData.addAll(poso.getMultiDefaultValueSimpleData());

		/* remove non existing data */
		List<DatasourceParameterData> remDto_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
		for(DatasourceParameterData ref : col_multiDefaultValueSimpleData){
			boolean found = false;
			for(DatasourceParameterDataDto refDto : tmpCol_multiDefaultValueSimpleData){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_multiDefaultValueSimpleData.add(ref);
		}
		for(DatasourceParameterData ref : remDto_multiDefaultValueSimpleData)
			col_multiDefaultValueSimpleData.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_multiDefaultValueSimpleData, "multiDefaultValueSimpleData");

		/* merge or add data */
		List<DatasourceParameterData> new_col_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
		for(DatasourceParameterDataDto refDto : tmpCol_multiDefaultValueSimpleData){
			boolean found = false;
			for(DatasourceParameterData ref : col_multiDefaultValueSimpleData){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_multiDefaultValueSimpleData.add((DatasourceParameterData) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_multiDefaultValueSimpleData.add((DatasourceParameterData) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(multiDefaultValueSimpleData) ");
		}
		poso.setMultiDefaultValueSimpleData(new_col_multiDefaultValueSimpleData);

		/*  set multiSelectionMode */
		MultiSelectionModeDto tmpDto_multiSelectionMode = dto.getMultiSelectionMode();
		poso.setMultiSelectionMode((MultiSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_multiSelectionMode));

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set postProcess */
		poso.setPostProcess(dto.getPostProcess() );

		/*  set returnType */
		DatatypeDto tmpDto_returnType = dto.getReturnType();
		poso.setReturnType((Datatype)dtoServiceProvider.get().createPoso(tmpDto_returnType));

		/*  set singleDefaultValueSimpleData */
		DatasourceParameterDataDto tmpDto_singleDefaultValueSimpleData = dto.getSingleDefaultValueSimpleData();
		if(null != tmpDto_singleDefaultValueSimpleData && null != tmpDto_singleDefaultValueSimpleData.getId()){
			if(null != poso.getSingleDefaultValueSimpleData() && null != poso.getSingleDefaultValueSimpleData().getId() && poso.getSingleDefaultValueSimpleData().getId().equals(tmpDto_singleDefaultValueSimpleData.getId()))
				poso.setSingleDefaultValueSimpleData((DatasourceParameterData)dtoServiceProvider.get().loadAndMergePoso(tmpDto_singleDefaultValueSimpleData));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (singleDefaultValueSimpleData)");
		} else if(null != poso.getSingleDefaultValueSimpleData()){
			DatasourceParameterData newPropertyValue = (DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleDefaultValueSimpleData);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getSingleDefaultValueSimpleData(), newPropertyValue, "singleDefaultValueSimpleData");
			poso.setSingleDefaultValueSimpleData(newPropertyValue);
		} else {
			poso.setSingleDefaultValueSimpleData((DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleDefaultValueSimpleData));
		}

		/*  set singleSelectionMode */
		SingleSelectionModeDto tmpDto_singleSelectionMode = dto.getSingleSelectionMode();
		poso.setSingleSelectionMode((SingleSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_singleSelectionMode));

		/*  set width */
		try{
			poso.setWidth(dto.getWidth() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(DatasourceParameterDefinitionDto dto, final DatasourceParameterDefinition poso)  throws ExpectedException {
		/*  set boxLayoutMode */
		if(dto.isBoxLayoutModeModified()){
			BoxLayoutModeDto tmpDto_boxLayoutMode = dto.getBoxLayoutMode();
			poso.setBoxLayoutMode((BoxLayoutMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutMode));
		}

		/*  set boxLayoutPackColSize */
		if(dto.isBoxLayoutPackColSizeModified()){
			try{
				poso.setBoxLayoutPackColSize(dto.getBoxLayoutPackColSize() );
			} catch(NullPointerException e){
			}
		}

		/*  set boxLayoutPackMode */
		if(dto.isBoxLayoutPackModeModified()){
			BoxLayoutPackModeDto tmpDto_boxLayoutPackMode = dto.getBoxLayoutPackMode();
			poso.setBoxLayoutPackMode((BoxLayoutPackMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutPackMode));
		}

		/*  set datasourceContainer */
		if(dto.isDatasourceContainerModified()){
			DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
			if(null != tmpDto_datasourceContainer && null != tmpDto_datasourceContainer.getId()){
				if(null != poso.getDatasourceContainer() && null != poso.getDatasourceContainer().getId() && poso.getDatasourceContainer().getId().equals(tmpDto_datasourceContainer.getId()))
					poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().loadAndMergePoso(tmpDto_datasourceContainer));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (datasourceContainer)");
			} else if(null != poso.getDatasourceContainer()){
				DatasourceContainer newPropertyValue = (DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDatasourceContainer(), newPropertyValue, "datasourceContainer");
				poso.setDatasourceContainer(newPropertyValue);
			} else {
				poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createPoso(tmpDto_datasourceContainer));
			}
		}

		/*  set dependsOn */
		if(dto.isDependsOnModified()){
			final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
			/* load new data from dto */
			Collection<ParameterDefinitionDto> tmpCol_dependsOn = null;
			tmpCol_dependsOn = dto.getDependsOn();

			for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
				if(null != refDto && null != refDto.getId())
					col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
							col_dependsOn.add((ParameterDefinition) poso);
						}
					});
				}
			}
			poso.setDependsOn(col_dependsOn);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set displayInline */
		if(dto.isDisplayInlineModified()){
			poso.setDisplayInline(dto.isDisplayInline() );
		}

		/*  set editable */
		if(dto.isEditableModified()){
			poso.setEditable(dto.isEditable() );
		}

		/*  set format */
		if(dto.isFormatModified()){
			poso.setFormat(dto.getFormat() );
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set hidden */
		if(dto.isHiddenModified()){
			poso.setHidden(dto.isHidden() );
		}

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set labelWidth */
		if(dto.isLabelWidthModified()){
			poso.setLabelWidth(dto.getLabelWidth() );
		}

		/*  set mandatory */
		if(dto.isMandatoryModified()){
			try{
				poso.setMandatory(dto.isMandatory() );
			} catch(NullPointerException e){
			}
		}

		/*  set mode */
		if(dto.isModeModified()){
			ModeDto tmpDto_mode = dto.getMode();
			poso.setMode((Mode)dtoServiceProvider.get().createPoso(tmpDto_mode));
		}

		/*  set multiDefaultValueSimpleData */
		if(dto.isMultiDefaultValueSimpleDataModified()){
			final List<DatasourceParameterData> col_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
			/* load new data from dto */
			Collection<DatasourceParameterDataDto> tmpCol_multiDefaultValueSimpleData = null;
			tmpCol_multiDefaultValueSimpleData = dto.getMultiDefaultValueSimpleData();

			/* load current data from poso */
			if(null != poso.getMultiDefaultValueSimpleData())
				col_multiDefaultValueSimpleData.addAll(poso.getMultiDefaultValueSimpleData());

			/* remove non existing data */
			List<DatasourceParameterData> remDto_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
			for(DatasourceParameterData ref : col_multiDefaultValueSimpleData){
				boolean found = false;
				for(DatasourceParameterDataDto refDto : tmpCol_multiDefaultValueSimpleData){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_multiDefaultValueSimpleData.add(ref);
			}
			for(DatasourceParameterData ref : remDto_multiDefaultValueSimpleData)
				col_multiDefaultValueSimpleData.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_multiDefaultValueSimpleData, "multiDefaultValueSimpleData");

			/* merge or add data */
			List<DatasourceParameterData> new_col_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
			for(DatasourceParameterDataDto refDto : tmpCol_multiDefaultValueSimpleData){
				boolean found = false;
				for(DatasourceParameterData ref : col_multiDefaultValueSimpleData){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_multiDefaultValueSimpleData.add((DatasourceParameterData) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_multiDefaultValueSimpleData.add((DatasourceParameterData) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(multiDefaultValueSimpleData) ");
			}
			poso.setMultiDefaultValueSimpleData(new_col_multiDefaultValueSimpleData);
		}

		/*  set multiSelectionMode */
		if(dto.isMultiSelectionModeModified()){
			MultiSelectionModeDto tmpDto_multiSelectionMode = dto.getMultiSelectionMode();
			poso.setMultiSelectionMode((MultiSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_multiSelectionMode));
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set postProcess */
		if(dto.isPostProcessModified()){
			poso.setPostProcess(dto.getPostProcess() );
		}

		/*  set returnType */
		if(dto.isReturnTypeModified()){
			DatatypeDto tmpDto_returnType = dto.getReturnType();
			poso.setReturnType((Datatype)dtoServiceProvider.get().createPoso(tmpDto_returnType));
		}

		/*  set singleDefaultValueSimpleData */
		if(dto.isSingleDefaultValueSimpleDataModified()){
			DatasourceParameterDataDto tmpDto_singleDefaultValueSimpleData = dto.getSingleDefaultValueSimpleData();
			if(null != tmpDto_singleDefaultValueSimpleData && null != tmpDto_singleDefaultValueSimpleData.getId()){
				if(null != poso.getSingleDefaultValueSimpleData() && null != poso.getSingleDefaultValueSimpleData().getId() && poso.getSingleDefaultValueSimpleData().getId().equals(tmpDto_singleDefaultValueSimpleData.getId()))
					poso.setSingleDefaultValueSimpleData((DatasourceParameterData)dtoServiceProvider.get().loadAndMergePoso(tmpDto_singleDefaultValueSimpleData));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (singleDefaultValueSimpleData)");
			} else if(null != poso.getSingleDefaultValueSimpleData()){
				DatasourceParameterData newPropertyValue = (DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleDefaultValueSimpleData);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getSingleDefaultValueSimpleData(), newPropertyValue, "singleDefaultValueSimpleData");
				poso.setSingleDefaultValueSimpleData(newPropertyValue);
			} else {
				poso.setSingleDefaultValueSimpleData((DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleDefaultValueSimpleData));
			}
		}

		/*  set singleSelectionMode */
		if(dto.isSingleSelectionModeModified()){
			SingleSelectionModeDto tmpDto_singleSelectionMode = dto.getSingleSelectionMode();
			poso.setSingleSelectionMode((SingleSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_singleSelectionMode));
		}

		/*  set width */
		if(dto.isWidthModified()){
			try{
				poso.setWidth(dto.getWidth() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(DatasourceParameterDefinitionDto dto, final DatasourceParameterDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasourceParameterDefinitionDto dto, final DatasourceParameterDefinition poso)  throws ExpectedException {
		/*  set boxLayoutMode */
		BoxLayoutModeDto tmpDto_boxLayoutMode = dto.getBoxLayoutMode();
		poso.setBoxLayoutMode((BoxLayoutMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutMode));

		/*  set boxLayoutPackColSize */
		try{
			poso.setBoxLayoutPackColSize(dto.getBoxLayoutPackColSize() );
		} catch(NullPointerException e){
		}

		/*  set boxLayoutPackMode */
		BoxLayoutPackModeDto tmpDto_boxLayoutPackMode = dto.getBoxLayoutPackMode();
		poso.setBoxLayoutPackMode((BoxLayoutPackMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutPackMode));

		/*  set datasourceContainer */
		DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
		poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasourceContainer));

		/*  set dependsOn */
		final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
		/* load new data from dto */
		Collection<ParameterDefinitionDto> tmpCol_dependsOn = dto.getDependsOn();

		for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
			if(null != refDto && null != refDto.getId())
				col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
			else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object poso){
						if(null == poso)
							throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
						col_dependsOn.add((ParameterDefinition) poso);
					}
					});
			}
		}
		poso.setDependsOn(col_dependsOn);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set displayInline */
		poso.setDisplayInline(dto.isDisplayInline() );

		/*  set editable */
		poso.setEditable(dto.isEditable() );

		/*  set format */
		poso.setFormat(dto.getFormat() );

		/*  set height */
		try{
			poso.setHeight(dto.getHeight() );
		} catch(NullPointerException e){
		}

		/*  set hidden */
		poso.setHidden(dto.isHidden() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set labelWidth */
		poso.setLabelWidth(dto.getLabelWidth() );

		/*  set mandatory */
		try{
			poso.setMandatory(dto.isMandatory() );
		} catch(NullPointerException e){
		}

		/*  set mode */
		ModeDto tmpDto_mode = dto.getMode();
		poso.setMode((Mode)dtoServiceProvider.get().createPoso(tmpDto_mode));

		/*  set multiDefaultValueSimpleData */
		final List<DatasourceParameterData> col_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
		/* load new data from dto */
		Collection<DatasourceParameterDataDto> tmpCol_multiDefaultValueSimpleData = dto.getMultiDefaultValueSimpleData();

		/* merge or add data */
		for(DatasourceParameterDataDto refDto : tmpCol_multiDefaultValueSimpleData){
			col_multiDefaultValueSimpleData.add((DatasourceParameterData) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setMultiDefaultValueSimpleData(col_multiDefaultValueSimpleData);

		/*  set multiSelectionMode */
		MultiSelectionModeDto tmpDto_multiSelectionMode = dto.getMultiSelectionMode();
		poso.setMultiSelectionMode((MultiSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_multiSelectionMode));

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set postProcess */
		poso.setPostProcess(dto.getPostProcess() );

		/*  set returnType */
		DatatypeDto tmpDto_returnType = dto.getReturnType();
		poso.setReturnType((Datatype)dtoServiceProvider.get().createPoso(tmpDto_returnType));

		/*  set singleDefaultValueSimpleData */
		DatasourceParameterDataDto tmpDto_singleDefaultValueSimpleData = dto.getSingleDefaultValueSimpleData();
		poso.setSingleDefaultValueSimpleData((DatasourceParameterData)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_singleDefaultValueSimpleData));

		/*  set singleSelectionMode */
		SingleSelectionModeDto tmpDto_singleSelectionMode = dto.getSingleSelectionMode();
		poso.setSingleSelectionMode((SingleSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_singleSelectionMode));

		/*  set width */
		try{
			poso.setWidth(dto.getWidth() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(DatasourceParameterDefinitionDto dto, final DatasourceParameterDefinition poso)  throws ExpectedException {
		/*  set boxLayoutMode */
		if(dto.isBoxLayoutModeModified()){
			BoxLayoutModeDto tmpDto_boxLayoutMode = dto.getBoxLayoutMode();
			poso.setBoxLayoutMode((BoxLayoutMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutMode));
		}

		/*  set boxLayoutPackColSize */
		if(dto.isBoxLayoutPackColSizeModified()){
			try{
				poso.setBoxLayoutPackColSize(dto.getBoxLayoutPackColSize() );
			} catch(NullPointerException e){
			}
		}

		/*  set boxLayoutPackMode */
		if(dto.isBoxLayoutPackModeModified()){
			BoxLayoutPackModeDto tmpDto_boxLayoutPackMode = dto.getBoxLayoutPackMode();
			poso.setBoxLayoutPackMode((BoxLayoutPackMode)dtoServiceProvider.get().createPoso(tmpDto_boxLayoutPackMode));
		}

		/*  set datasourceContainer */
		if(dto.isDatasourceContainerModified()){
			DatasourceContainerDto tmpDto_datasourceContainer = dto.getDatasourceContainer();
			poso.setDatasourceContainer((DatasourceContainer)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasourceContainer));
		}

		/*  set dependsOn */
		if(dto.isDependsOnModified()){
			final List<ParameterDefinition> col_dependsOn = new ArrayList<ParameterDefinition>();
			/* load new data from dto */
			Collection<ParameterDefinitionDto> tmpCol_dependsOn = null;
			tmpCol_dependsOn = dto.getDependsOn();

			for(ParameterDefinitionDto refDto : tmpCol_dependsOn){
				if(null != refDto && null != refDto.getId())
					col_dependsOn.add((ParameterDefinition) dtoServiceProvider.get().loadPoso(refDto));
				else if(null != refDto && null == refDto.getId()){
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(refDto, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
						public void callback(Object poso){
							if(null == poso)
								throw new IllegalArgumentException("referenced dto should have an id (dependsOn)");
							col_dependsOn.add((ParameterDefinition) poso);
						}
					});
				}
			}
			poso.setDependsOn(col_dependsOn);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set displayInline */
		if(dto.isDisplayInlineModified()){
			poso.setDisplayInline(dto.isDisplayInline() );
		}

		/*  set editable */
		if(dto.isEditableModified()){
			poso.setEditable(dto.isEditable() );
		}

		/*  set format */
		if(dto.isFormatModified()){
			poso.setFormat(dto.getFormat() );
		}

		/*  set height */
		if(dto.isHeightModified()){
			try{
				poso.setHeight(dto.getHeight() );
			} catch(NullPointerException e){
			}
		}

		/*  set hidden */
		if(dto.isHiddenModified()){
			poso.setHidden(dto.isHidden() );
		}

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set labelWidth */
		if(dto.isLabelWidthModified()){
			poso.setLabelWidth(dto.getLabelWidth() );
		}

		/*  set mandatory */
		if(dto.isMandatoryModified()){
			try{
				poso.setMandatory(dto.isMandatory() );
			} catch(NullPointerException e){
			}
		}

		/*  set mode */
		if(dto.isModeModified()){
			ModeDto tmpDto_mode = dto.getMode();
			poso.setMode((Mode)dtoServiceProvider.get().createPoso(tmpDto_mode));
		}

		/*  set multiDefaultValueSimpleData */
		if(dto.isMultiDefaultValueSimpleDataModified()){
			final List<DatasourceParameterData> col_multiDefaultValueSimpleData = new ArrayList<DatasourceParameterData>();
			/* load new data from dto */
			Collection<DatasourceParameterDataDto> tmpCol_multiDefaultValueSimpleData = null;
			tmpCol_multiDefaultValueSimpleData = dto.getMultiDefaultValueSimpleData();

			/* merge or add data */
			for(DatasourceParameterDataDto refDto : tmpCol_multiDefaultValueSimpleData){
				col_multiDefaultValueSimpleData.add((DatasourceParameterData) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setMultiDefaultValueSimpleData(col_multiDefaultValueSimpleData);
		}

		/*  set multiSelectionMode */
		if(dto.isMultiSelectionModeModified()){
			MultiSelectionModeDto tmpDto_multiSelectionMode = dto.getMultiSelectionMode();
			poso.setMultiSelectionMode((MultiSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_multiSelectionMode));
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set postProcess */
		if(dto.isPostProcessModified()){
			poso.setPostProcess(dto.getPostProcess() );
		}

		/*  set returnType */
		if(dto.isReturnTypeModified()){
			DatatypeDto tmpDto_returnType = dto.getReturnType();
			poso.setReturnType((Datatype)dtoServiceProvider.get().createPoso(tmpDto_returnType));
		}

		/*  set singleDefaultValueSimpleData */
		if(dto.isSingleDefaultValueSimpleDataModified()){
			DatasourceParameterDataDto tmpDto_singleDefaultValueSimpleData = dto.getSingleDefaultValueSimpleData();
			poso.setSingleDefaultValueSimpleData((DatasourceParameterData)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_singleDefaultValueSimpleData));
		}

		/*  set singleSelectionMode */
		if(dto.isSingleSelectionModeModified()){
			SingleSelectionModeDto tmpDto_singleSelectionMode = dto.getSingleSelectionMode();
			poso.setSingleSelectionMode((SingleSelectionMode)dtoServiceProvider.get().createPoso(tmpDto_singleSelectionMode));
		}

		/*  set width */
		if(dto.isWidthModified()){
			try{
				poso.setWidth(dto.getWidth() );
			} catch(NullPointerException e){
			}
		}

	}

	public DatasourceParameterDefinition loadAndMergePoso(DatasourceParameterDefinitionDto dto)  throws ExpectedException {
		DatasourceParameterDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasourceParameterDefinitionDto dto, DatasourceParameterDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(DatasourceParameterDefinitionDto dto, DatasourceParameterDefinition poso)  {
	}


	public void postProcessLoad(DatasourceParameterDefinitionDto dto, DatasourceParameterDefinition poso)  {
	}


	public void postProcessMerge(DatasourceParameterDefinitionDto dto, DatasourceParameterDefinition poso)  {
	}


	public void postProcessInstantiate(DatasourceParameterDefinition poso)  {
	}


	public boolean validateKeyProperty(DatasourceParameterDefinitionDto dto, DatasourceParameterDefinition poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]*$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
