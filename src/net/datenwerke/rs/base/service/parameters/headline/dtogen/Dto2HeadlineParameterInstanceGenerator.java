package net.datenwerke.rs.base.service.parameters.headline.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto;
import net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterInstance;
import net.datenwerke.rs.base.service.parameters.headline.dtogen.Dto2HeadlineParameterInstanceGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for HeadlineParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2HeadlineParameterInstanceGenerator implements Dto2PosoGenerator<HeadlineParameterInstanceDto,HeadlineParameterInstance> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2HeadlineParameterInstanceGenerator(
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

	public HeadlineParameterInstance loadPoso(HeadlineParameterInstanceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		HeadlineParameterInstance poso = entityManager.find(HeadlineParameterInstance.class, id);
		return poso;
	}

	public HeadlineParameterInstance instantiatePoso()  {
		HeadlineParameterInstance poso = new HeadlineParameterInstance();
		return poso;
	}

	public HeadlineParameterInstance createPoso(HeadlineParameterInstanceDto dto)  throws ExpectedException {
		HeadlineParameterInstance poso = new HeadlineParameterInstance();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public HeadlineParameterInstance createUnmanagedPoso(HeadlineParameterInstanceDto dto)  throws ExpectedException {
		HeadlineParameterInstance poso = new HeadlineParameterInstance();

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

	public void mergePoso(HeadlineParameterInstanceDto dto, final HeadlineParameterInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(HeadlineParameterInstanceDto dto, final HeadlineParameterInstance poso)  throws ExpectedException {
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

		/*  set stillDefault */
		try{
			poso.setStillDefault(dto.isStillDefault() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(HeadlineParameterInstanceDto dto, final HeadlineParameterInstance poso)  throws ExpectedException {
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

		/*  set stillDefault */
		if(dto.isStillDefaultModified()){
			try{
				poso.setStillDefault(dto.isStillDefault() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(HeadlineParameterInstanceDto dto, final HeadlineParameterInstance poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(HeadlineParameterInstanceDto dto, final HeadlineParameterInstance poso)  throws ExpectedException {
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

		/*  set stillDefault */
		try{
			poso.setStillDefault(dto.isStillDefault() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(HeadlineParameterInstanceDto dto, final HeadlineParameterInstance poso)  throws ExpectedException {
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

		/*  set stillDefault */
		if(dto.isStillDefaultModified()){
			try{
				poso.setStillDefault(dto.isStillDefault() );
			} catch(NullPointerException e){
			}
		}

	}

	public HeadlineParameterInstance loadAndMergePoso(HeadlineParameterInstanceDto dto)  throws ExpectedException {
		HeadlineParameterInstance poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(HeadlineParameterInstanceDto dto, HeadlineParameterInstance poso)  {
	}


	public void postProcessCreateUnmanaged(HeadlineParameterInstanceDto dto, HeadlineParameterInstance poso)  {
	}


	public void postProcessLoad(HeadlineParameterInstanceDto dto, HeadlineParameterInstance poso)  {
	}


	public void postProcessMerge(HeadlineParameterInstanceDto dto, HeadlineParameterInstance poso)  {
	}


	public void postProcessInstantiate(HeadlineParameterInstance poso)  {
	}



}
