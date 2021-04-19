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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.Dto2DatasourceDefinitionConfigGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasourceDefinitionConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasourceDefinitionConfigGenerator implements Dto2PosoGenerator<DatasourceDefinitionConfigDto,DatasourceDefinitionConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasourceDefinitionConfigGenerator(
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

	public DatasourceDefinitionConfig loadPoso(DatasourceDefinitionConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasourceDefinitionConfig poso = entityManager.find(DatasourceDefinitionConfig.class, id);
		return poso;
	}

	public DatasourceDefinitionConfig instantiatePoso()  {
		DatasourceDefinitionConfig poso = new DatasourceDefinitionConfig();
		return poso;
	}

	public DatasourceDefinitionConfig createPoso(DatasourceDefinitionConfigDto dto)  throws ExpectedException {
		DatasourceDefinitionConfig poso = new DatasourceDefinitionConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasourceDefinitionConfig createUnmanagedPoso(DatasourceDefinitionConfigDto dto)  throws ExpectedException {
		DatasourceDefinitionConfig poso = new DatasourceDefinitionConfig();

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

	public void mergePoso(DatasourceDefinitionConfigDto dto, final DatasourceDefinitionConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasourceDefinitionConfigDto dto, final DatasourceDefinitionConfig poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(DatasourceDefinitionConfigDto dto, final DatasourceDefinitionConfig poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(DatasourceDefinitionConfigDto dto, final DatasourceDefinitionConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasourceDefinitionConfigDto dto, final DatasourceDefinitionConfig poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(DatasourceDefinitionConfigDto dto, final DatasourceDefinitionConfig poso)  throws ExpectedException {
	}

	public DatasourceDefinitionConfig loadAndMergePoso(DatasourceDefinitionConfigDto dto)  throws ExpectedException {
		DatasourceDefinitionConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasourceDefinitionConfigDto dto, DatasourceDefinitionConfig poso)  {
	}


	public void postProcessCreateUnmanaged(DatasourceDefinitionConfigDto dto, DatasourceDefinitionConfig poso)  {
	}


	public void postProcessLoad(DatasourceDefinitionConfigDto dto, DatasourceDefinitionConfig poso)  {
	}


	public void postProcessMerge(DatasourceDefinitionConfigDto dto, DatasourceDefinitionConfig poso)  {
	}


	public void postProcessInstantiate(DatasourceDefinitionConfig poso)  {
	}



}
