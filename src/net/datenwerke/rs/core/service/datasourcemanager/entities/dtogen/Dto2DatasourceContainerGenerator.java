package net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen;

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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.Dto2DatasourceContainerGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasourceContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasourceContainerGenerator implements Dto2PosoGenerator<DatasourceContainerDto,DatasourceContainer> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasourceContainerGenerator(
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

	public DatasourceContainer loadPoso(DatasourceContainerDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasourceContainer poso = entityManager.find(DatasourceContainer.class, id);
		return poso;
	}

	public DatasourceContainer instantiatePoso()  {
		DatasourceContainer poso = new DatasourceContainer();
		return poso;
	}

	public DatasourceContainer createPoso(DatasourceContainerDto dto)  throws ExpectedException {
		DatasourceContainer poso = new DatasourceContainer();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasourceContainer createUnmanagedPoso(DatasourceContainerDto dto)  throws ExpectedException {
		DatasourceContainer poso = new DatasourceContainer();

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

	public void mergePoso(DatasourceContainerDto dto, final DatasourceContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasourceContainerDto dto, final DatasourceContainer poso)  throws ExpectedException {
		/*  set datasource */
		DatasourceDefinitionDto tmpDto_datasource = dto.getDatasource();
		if(null != tmpDto_datasource && null != tmpDto_datasource.getId()){
			if(null != poso.getDatasource() && null != poso.getDatasource().getId() && ! poso.getDatasource().getId().equals(tmpDto_datasource.getId())){
				DatasourceDefinition newPropertyValue = (DatasourceDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasource);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasource(), newPropertyValue, "datasource");
				poso.setDatasource(newPropertyValue);
			} else if(null == poso.getDatasource()){
				poso.setDatasource((DatasourceDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasource));
			}
		} else if(null != tmpDto_datasource && null == tmpDto_datasource.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasource, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (datasource)");
					poso.setDatasource((DatasourceDefinition)refPoso);
				}
			});
		} else if(null == tmpDto_datasource){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasource(), null, "datasource");
			poso.setDatasource(null);
		}

		/*  set datasourceConfig */
		DatasourceDefinitionConfigDto tmpDto_datasourceConfig = dto.getDatasourceConfig();
		if(null != tmpDto_datasourceConfig && null != tmpDto_datasourceConfig.getId()){
			if(null != poso.getDatasourceConfig() && null != poso.getDatasourceConfig().getId() && poso.getDatasourceConfig().getId().equals(tmpDto_datasourceConfig.getId()))
				poso.setDatasourceConfig((DatasourceDefinitionConfig)dtoServiceProvider.get().loadAndMergePoso(tmpDto_datasourceConfig));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (datasourceConfig)");
		} else if(null != poso.getDatasourceConfig()){
			DatasourceDefinitionConfig newPropertyValue = (DatasourceDefinitionConfig)dtoServiceProvider.get().createPoso(tmpDto_datasourceConfig);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDatasourceConfig(), newPropertyValue, "datasourceConfig");
			poso.setDatasourceConfig(newPropertyValue);
		} else {
			poso.setDatasourceConfig((DatasourceDefinitionConfig)dtoServiceProvider.get().createPoso(tmpDto_datasourceConfig));
		}

	}

	protected void mergeProxy2Poso(DatasourceContainerDto dto, final DatasourceContainer poso)  throws ExpectedException {
		/*  set datasource */
		if(dto.isDatasourceModified()){
			DatasourceDefinitionDto tmpDto_datasource = dto.getDatasource();
			if(null != tmpDto_datasource && null != tmpDto_datasource.getId()){
				if(null != poso.getDatasource() && null != poso.getDatasource().getId() && ! poso.getDatasource().getId().equals(tmpDto_datasource.getId())){
					DatasourceDefinition newPropertyValue = (DatasourceDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasource);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasource(), newPropertyValue, "datasource");
					poso.setDatasource(newPropertyValue);
				} else if(null == poso.getDatasource()){
					poso.setDatasource((DatasourceDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasource));
				}
			} else if(null != tmpDto_datasource && null == tmpDto_datasource.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasource, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (datasource)");
						poso.setDatasource((DatasourceDefinition)refPoso);
					}
			});
			} else if(null == tmpDto_datasource){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatasource(), null, "datasource");
				poso.setDatasource(null);
			}
		}

		/*  set datasourceConfig */
		if(dto.isDatasourceConfigModified()){
			DatasourceDefinitionConfigDto tmpDto_datasourceConfig = dto.getDatasourceConfig();
			if(null != tmpDto_datasourceConfig && null != tmpDto_datasourceConfig.getId()){
				if(null != poso.getDatasourceConfig() && null != poso.getDatasourceConfig().getId() && poso.getDatasourceConfig().getId().equals(tmpDto_datasourceConfig.getId()))
					poso.setDatasourceConfig((DatasourceDefinitionConfig)dtoServiceProvider.get().loadAndMergePoso(tmpDto_datasourceConfig));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (datasourceConfig)");
			} else if(null != poso.getDatasourceConfig()){
				DatasourceDefinitionConfig newPropertyValue = (DatasourceDefinitionConfig)dtoServiceProvider.get().createPoso(tmpDto_datasourceConfig);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDatasourceConfig(), newPropertyValue, "datasourceConfig");
				poso.setDatasourceConfig(newPropertyValue);
			} else {
				poso.setDatasourceConfig((DatasourceDefinitionConfig)dtoServiceProvider.get().createPoso(tmpDto_datasourceConfig));
			}
		}

	}

	public void mergeUnmanagedPoso(DatasourceContainerDto dto, final DatasourceContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasourceContainerDto dto, final DatasourceContainer poso)  throws ExpectedException {
		/*  set datasource */
		DatasourceDefinitionDto tmpDto_datasource = dto.getDatasource();
		if(null != tmpDto_datasource && null != tmpDto_datasource.getId()){
			DatasourceDefinition newPropertyValue = (DatasourceDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasource);
			poso.setDatasource(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasource, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDatasource((DatasourceDefinition)refPoso);
				}
			});
		} else if(null != tmpDto_datasource && null == tmpDto_datasource.getId()){
			final DatasourceDefinitionDto tmpDto_datasource_final = tmpDto_datasource;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasource, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDatasource((DatasourceDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasource_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDatasource((DatasourceDefinition)refPoso);
					}
				}
			});
		} else if(null == tmpDto_datasource){
			poso.setDatasource(null);
		}

		/*  set datasourceConfig */
		DatasourceDefinitionConfigDto tmpDto_datasourceConfig = dto.getDatasourceConfig();
		poso.setDatasourceConfig((DatasourceDefinitionConfig)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasourceConfig));

	}

	protected void mergeProxy2UnmanagedPoso(DatasourceContainerDto dto, final DatasourceContainer poso)  throws ExpectedException {
		/*  set datasource */
		if(dto.isDatasourceModified()){
			DatasourceDefinitionDto tmpDto_datasource = dto.getDatasource();
			if(null != tmpDto_datasource && null != tmpDto_datasource.getId()){
				DatasourceDefinition newPropertyValue = (DatasourceDefinition)dtoServiceProvider.get().loadPoso(tmpDto_datasource);
				poso.setDatasource(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasource, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDatasource((DatasourceDefinition)refPoso);
					}
			});
			} else if(null != tmpDto_datasource && null == tmpDto_datasource.getId()){
				final DatasourceDefinitionDto tmpDto_datasource_final = tmpDto_datasource;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_datasource, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDatasource((DatasourceDefinition)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasource_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDatasource((DatasourceDefinition)refPoso);
						}
					}
			});
			} else if(null == tmpDto_datasource){
				poso.setDatasource(null);
			}
		}

		/*  set datasourceConfig */
		if(dto.isDatasourceConfigModified()){
			DatasourceDefinitionConfigDto tmpDto_datasourceConfig = dto.getDatasourceConfig();
			poso.setDatasourceConfig((DatasourceDefinitionConfig)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_datasourceConfig));
		}

	}

	public DatasourceContainer loadAndMergePoso(DatasourceContainerDto dto)  throws ExpectedException {
		DatasourceContainer poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasourceContainerDto dto, DatasourceContainer poso)  {
	}


	public void postProcessCreateUnmanaged(DatasourceContainerDto dto, DatasourceContainer poso)  {
	}


	public void postProcessLoad(DatasourceContainerDto dto, DatasourceContainer poso)  {
	}


	public void postProcessMerge(DatasourceContainerDto dto, DatasourceContainer poso)  {
	}


	public void postProcessInstantiate(DatasourceContainer poso)  {
	}



}
