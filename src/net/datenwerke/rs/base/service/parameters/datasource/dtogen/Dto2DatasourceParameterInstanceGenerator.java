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
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2DatasourceParameterInstanceGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasourceParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasourceParameterInstanceGenerator implements Dto2PosoGenerator<DatasourceParameterInstanceDto,DatasourceParameterInstance> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasourceParameterInstanceGenerator(
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

	public DatasourceParameterInstance loadPoso(DatasourceParameterInstanceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasourceParameterInstance poso = entityManager.find(DatasourceParameterInstance.class, id);
		return poso;
	}

	public DatasourceParameterInstance instantiatePoso()  {
		DatasourceParameterInstance poso = new DatasourceParameterInstance();
		return poso;
	}

	public DatasourceParameterInstance createPoso(DatasourceParameterInstanceDto dto)  throws ExpectedException {
		DatasourceParameterInstance poso = new DatasourceParameterInstance();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasourceParameterInstance createUnmanagedPoso(DatasourceParameterInstanceDto dto)  throws ExpectedException {
		DatasourceParameterInstance poso = new DatasourceParameterInstance();

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

	public void mergePoso(DatasourceParameterInstanceDto dto, final DatasourceParameterInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasourceParameterInstanceDto dto, final DatasourceParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
		if(null != tmpDto_definition && null != tmpDto_definition.getId()){
			if(null != poso.getDefinition() && null != poso.getDefinition().getId() && ! poso.getDefinition().getId().equals(tmpDto_definition.getId())){
				ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), newPropertyValue, "definition");
				poso.setDefinition(newPropertyValue);
			} else if(null == poso.getDefinition()){
				poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition));
			}
		} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (definition)");
					poso.setDefinition((ParameterDefinition)refPoso);
				}
			});
		} else if(null == tmpDto_definition){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), null, "definition");
			poso.setDefinition(null);
		}

		/*  set multiValue */
		final List<DatasourceParameterData> col_multiValue = new ArrayList<DatasourceParameterData>();
		/* load new data from dto */
		Collection<DatasourceParameterDataDto> tmpCol_multiValue = dto.getMultiValue();

		/* load current data from poso */
		if(null != poso.getMultiValue())
			col_multiValue.addAll(poso.getMultiValue());

		/* remove non existing data */
		List<DatasourceParameterData> remDto_multiValue = new ArrayList<DatasourceParameterData>();
		for(DatasourceParameterData ref : col_multiValue){
			boolean found = false;
			for(DatasourceParameterDataDto refDto : tmpCol_multiValue){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_multiValue.add(ref);
		}
		for(DatasourceParameterData ref : remDto_multiValue)
			col_multiValue.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_multiValue, "multiValue");

		/* merge or add data */
		List<DatasourceParameterData> new_col_multiValue = new ArrayList<DatasourceParameterData>();
		for(DatasourceParameterDataDto refDto : tmpCol_multiValue){
			boolean found = false;
			for(DatasourceParameterData ref : col_multiValue){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_multiValue.add((DatasourceParameterData) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_multiValue.add((DatasourceParameterData) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(multiValue) ");
		}
		poso.setMultiValue(new_col_multiValue);

		/*  set singleValue */
		DatasourceParameterDataDto tmpDto_singleValue = dto.getSingleValue();
		if(null != tmpDto_singleValue && null != tmpDto_singleValue.getId()){
			if(null != poso.getSingleValue() && null != poso.getSingleValue().getId() && poso.getSingleValue().getId().equals(tmpDto_singleValue.getId()))
				poso.setSingleValue((DatasourceParameterData)dtoServiceProvider.get().loadAndMergePoso(tmpDto_singleValue));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (singleValue)");
		} else if(null != poso.getSingleValue()){
			DatasourceParameterData newPropertyValue = (DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleValue);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getSingleValue(), newPropertyValue, "singleValue");
			poso.setSingleValue(newPropertyValue);
		} else {
			poso.setSingleValue((DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleValue));
		}

		/*  set stillDefault */
		try{
			poso.setStillDefault(dto.isStillDefault() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(DatasourceParameterInstanceDto dto, final DatasourceParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		if(dto.isDefinitionModified()){
			ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
			if(null != tmpDto_definition && null != tmpDto_definition.getId()){
				if(null != poso.getDefinition() && null != poso.getDefinition().getId() && ! poso.getDefinition().getId().equals(tmpDto_definition.getId())){
					ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), newPropertyValue, "definition");
					poso.setDefinition(newPropertyValue);
				} else if(null == poso.getDefinition()){
					poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition));
				}
			} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (definition)");
						poso.setDefinition((ParameterDefinition)refPoso);
					}
			});
			} else if(null == tmpDto_definition){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDefinition(), null, "definition");
				poso.setDefinition(null);
			}
		}

		/*  set multiValue */
		if(dto.isMultiValueModified()){
			final List<DatasourceParameterData> col_multiValue = new ArrayList<DatasourceParameterData>();
			/* load new data from dto */
			Collection<DatasourceParameterDataDto> tmpCol_multiValue = null;
			tmpCol_multiValue = dto.getMultiValue();

			/* load current data from poso */
			if(null != poso.getMultiValue())
				col_multiValue.addAll(poso.getMultiValue());

			/* remove non existing data */
			List<DatasourceParameterData> remDto_multiValue = new ArrayList<DatasourceParameterData>();
			for(DatasourceParameterData ref : col_multiValue){
				boolean found = false;
				for(DatasourceParameterDataDto refDto : tmpCol_multiValue){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_multiValue.add(ref);
			}
			for(DatasourceParameterData ref : remDto_multiValue)
				col_multiValue.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_multiValue, "multiValue");

			/* merge or add data */
			List<DatasourceParameterData> new_col_multiValue = new ArrayList<DatasourceParameterData>();
			for(DatasourceParameterDataDto refDto : tmpCol_multiValue){
				boolean found = false;
				for(DatasourceParameterData ref : col_multiValue){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_multiValue.add((DatasourceParameterData) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_multiValue.add((DatasourceParameterData) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(multiValue) ");
			}
			poso.setMultiValue(new_col_multiValue);
		}

		/*  set singleValue */
		if(dto.isSingleValueModified()){
			DatasourceParameterDataDto tmpDto_singleValue = dto.getSingleValue();
			if(null != tmpDto_singleValue && null != tmpDto_singleValue.getId()){
				if(null != poso.getSingleValue() && null != poso.getSingleValue().getId() && poso.getSingleValue().getId().equals(tmpDto_singleValue.getId()))
					poso.setSingleValue((DatasourceParameterData)dtoServiceProvider.get().loadAndMergePoso(tmpDto_singleValue));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (singleValue)");
			} else if(null != poso.getSingleValue()){
				DatasourceParameterData newPropertyValue = (DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleValue);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getSingleValue(), newPropertyValue, "singleValue");
				poso.setSingleValue(newPropertyValue);
			} else {
				poso.setSingleValue((DatasourceParameterData)dtoServiceProvider.get().createPoso(tmpDto_singleValue));
			}
		}

		/*  set stillDefault */
		if(dto.isStillDefaultModified()){
			try{
				poso.setStillDefault(dto.isStillDefault() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(DatasourceParameterInstanceDto dto, final DatasourceParameterInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasourceParameterInstanceDto dto, final DatasourceParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
		if(null != tmpDto_definition && null != tmpDto_definition.getId()){
			ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
			poso.setDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDefinition((ParameterDefinition)refPoso);
				}
			});
		} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
			final ParameterDefinitionDto tmpDto_definition_final = tmpDto_definition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_definition_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDefinition((ParameterDefinition)refPoso);
					}
				}
			});
		} else if(null == tmpDto_definition){
			poso.setDefinition(null);
		}

		/*  set multiValue */
		final List<DatasourceParameterData> col_multiValue = new ArrayList<DatasourceParameterData>();
		/* load new data from dto */
		Collection<DatasourceParameterDataDto> tmpCol_multiValue = dto.getMultiValue();

		/* merge or add data */
		for(DatasourceParameterDataDto refDto : tmpCol_multiValue){
			col_multiValue.add((DatasourceParameterData) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setMultiValue(col_multiValue);

		/*  set singleValue */
		DatasourceParameterDataDto tmpDto_singleValue = dto.getSingleValue();
		poso.setSingleValue((DatasourceParameterData)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_singleValue));

		/*  set stillDefault */
		try{
			poso.setStillDefault(dto.isStillDefault() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(DatasourceParameterInstanceDto dto, final DatasourceParameterInstance poso)  throws ExpectedException {
		/*  set definition */
		if(dto.isDefinitionModified()){
			ParameterDefinitionDto tmpDto_definition = dto.getDefinition();
			if(null != tmpDto_definition && null != tmpDto_definition.getId()){
				ParameterDefinition newPropertyValue = (ParameterDefinition)dtoServiceProvider.get().loadPoso(tmpDto_definition);
				poso.setDefinition(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDefinition((ParameterDefinition)refPoso);
					}
			});
			} else if(null != tmpDto_definition && null == tmpDto_definition.getId()){
				final ParameterDefinitionDto tmpDto_definition_final = tmpDto_definition;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_definition, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDefinition((ParameterDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_definition_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDefinition((ParameterDefinition)refPoso);
						}
					}
			});
			} else if(null == tmpDto_definition){
				poso.setDefinition(null);
			}
		}

		/*  set multiValue */
		if(dto.isMultiValueModified()){
			final List<DatasourceParameterData> col_multiValue = new ArrayList<DatasourceParameterData>();
			/* load new data from dto */
			Collection<DatasourceParameterDataDto> tmpCol_multiValue = null;
			tmpCol_multiValue = dto.getMultiValue();

			/* merge or add data */
			for(DatasourceParameterDataDto refDto : tmpCol_multiValue){
				col_multiValue.add((DatasourceParameterData) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setMultiValue(col_multiValue);
		}

		/*  set singleValue */
		if(dto.isSingleValueModified()){
			DatasourceParameterDataDto tmpDto_singleValue = dto.getSingleValue();
			poso.setSingleValue((DatasourceParameterData)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_singleValue));
		}

		/*  set stillDefault */
		if(dto.isStillDefaultModified()){
			try{
				poso.setStillDefault(dto.isStillDefault() );
			} catch(NullPointerException e){
			}
		}

	}

	public DatasourceParameterInstance loadAndMergePoso(DatasourceParameterInstanceDto dto)  throws ExpectedException {
		DatasourceParameterInstance poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasourceParameterInstanceDto dto, DatasourceParameterInstance poso)  {
	}


	public void postProcessCreateUnmanaged(DatasourceParameterInstanceDto dto, DatasourceParameterInstance poso)  {
	}


	public void postProcessLoad(DatasourceParameterInstanceDto dto, DatasourceParameterInstance poso)  {
	}


	public void postProcessMerge(DatasourceParameterInstanceDto dto, DatasourceParameterInstance poso)  {
	}


	public void postProcessInstantiate(DatasourceParameterInstance poso)  {
	}



}
