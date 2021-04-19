package net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
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
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen.Dto2DatasinkContainerGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasinkContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasinkContainerGenerator implements Dto2PosoGenerator<DatasinkContainerDto,DatasinkContainer> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasinkContainerGenerator(
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

	public DatasinkContainer loadPoso(DatasinkContainerDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasinkContainer poso = entityManager.find(DatasinkContainer.class, id);
		return poso;
	}

	public DatasinkContainer instantiatePoso()  {
		DatasinkContainer poso = new DatasinkContainer();
		return poso;
	}

	public DatasinkContainer createPoso(DatasinkContainerDto dto)  throws ExpectedException {
		DatasinkContainer poso = new DatasinkContainer();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasinkContainer createUnmanagedPoso(DatasinkContainerDto dto)  throws ExpectedException {
		DatasinkContainer poso = new DatasinkContainer();

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

	public void mergePoso(DatasinkContainerDto dto, final DatasinkContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasinkContainerDto dto, final DatasinkContainer poso)  throws ExpectedException {
		/*  set datasink */
		DatasinkDefinitionDto tmpDto_datasink = dto.getDatasink();
		if(null != tmpDto_datasink && null != tmpDto_datasink.getId()){
			if(null != poso.getDatasink() && null != poso.getDatasink().getId() && ! poso.getDatasink().getId().equals(tmpDto_datasink.getId())){
				DatasinkDefinition newPropertyValue = (DatasinkDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasink);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasink(), newPropertyValue, "datasink");
				poso.setDatasink(newPropertyValue);
			} else if(null == poso.getDatasink()){
				poso.setDatasink((DatasinkDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasink));
			}
		} else if(null != tmpDto_datasink && null == tmpDto_datasink.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasink, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (datasink)");
					poso.setDatasink((DatasinkDefinition)refPoso);
				}
			});
		} else if(null == tmpDto_datasink){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasink(), null, "datasink");
			poso.setDatasink(null);
		}

	}

	protected void mergeProxy2Poso(DatasinkContainerDto dto, final DatasinkContainer poso)  throws ExpectedException {
		/*  set datasink */
		if(dto.isDatasinkModified()){
			DatasinkDefinitionDto tmpDto_datasink = dto.getDatasink();
			if(null != tmpDto_datasink && null != tmpDto_datasink.getId()){
				if(null != poso.getDatasink() && null != poso.getDatasink().getId() && ! poso.getDatasink().getId().equals(tmpDto_datasink.getId())){
					DatasinkDefinition newPropertyValue = (DatasinkDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasink);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasink(), newPropertyValue, "datasink");
					poso.setDatasink(newPropertyValue);
				} else if(null == poso.getDatasink()){
					poso.setDatasink((DatasinkDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasink));
				}
			} else if(null != tmpDto_datasink && null == tmpDto_datasink.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasink, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (datasink)");
						poso.setDatasink((DatasinkDefinition)refPoso);
					}
			});
			} else if(null == tmpDto_datasink){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasink(), null, "datasink");
				poso.setDatasink(null);
			}
		}

	}

	public void mergeUnmanagedPoso(DatasinkContainerDto dto, final DatasinkContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasinkContainerDto dto, final DatasinkContainer poso)  throws ExpectedException {
		/*  set datasink */
		DatasinkDefinitionDto tmpDto_datasink = dto.getDatasink();
		if(null != tmpDto_datasink && null != tmpDto_datasink.getId()){
			DatasinkDefinition newPropertyValue = (DatasinkDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasink);
			poso.setDatasink(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasink, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDatasink((DatasinkDefinition)refPoso);
				}
			});
		} else if(null != tmpDto_datasink && null == tmpDto_datasink.getId()){
			final DatasinkDefinitionDto tmpDto_datasink_final = tmpDto_datasink;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasink, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDatasink((DatasinkDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasink_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDatasink((DatasinkDefinition)refPoso);
					}
				}
			});
		} else if(null == tmpDto_datasink){
			poso.setDatasink(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(DatasinkContainerDto dto, final DatasinkContainer poso)  throws ExpectedException {
		/*  set datasink */
		if(dto.isDatasinkModified()){
			DatasinkDefinitionDto tmpDto_datasink = dto.getDatasink();
			if(null != tmpDto_datasink && null != tmpDto_datasink.getId()){
				DatasinkDefinition newPropertyValue = (DatasinkDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasink);
				poso.setDatasink(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasink, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDatasink((DatasinkDefinition)refPoso);
					}
			});
			} else if(null != tmpDto_datasink && null == tmpDto_datasink.getId()){
				final DatasinkDefinitionDto tmpDto_datasink_final = tmpDto_datasink;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasink, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDatasink((DatasinkDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasink_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDatasink((DatasinkDefinition)refPoso);
						}
					}
			});
			} else if(null == tmpDto_datasink){
				poso.setDatasink(null);
			}
		}

	}

	public DatasinkContainer loadAndMergePoso(DatasinkContainerDto dto)  throws ExpectedException {
		DatasinkContainer poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasinkContainerDto dto, DatasinkContainer poso)  {
	}


	public void postProcessCreateUnmanaged(DatasinkContainerDto dto, DatasinkContainer poso)  {
	}


	public void postProcessLoad(DatasinkContainerDto dto, DatasinkContainer poso)  {
	}


	public void postProcessMerge(DatasinkContainerDto dto, DatasinkContainer poso)  {
	}


	public void postProcessInstantiate(DatasinkContainer poso)  {
	}



}
