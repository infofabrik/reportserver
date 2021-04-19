package net.datenwerke.rs.saiku.service.datasource.dtogen;

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
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig;
import net.datenwerke.rs.saiku.service.datasource.dtogen.Dto2MondrianDatasourceConfigGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for MondrianDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2MondrianDatasourceConfigGenerator implements Dto2PosoGenerator<MondrianDatasourceConfigDto,MondrianDatasourceConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2MondrianDatasourceConfigGenerator(
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

	public MondrianDatasourceConfig loadPoso(MondrianDatasourceConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		MondrianDatasourceConfig poso = entityManager.find(MondrianDatasourceConfig.class, id);
		return poso;
	}

	public MondrianDatasourceConfig instantiatePoso()  {
		MondrianDatasourceConfig poso = new MondrianDatasourceConfig();
		return poso;
	}

	public MondrianDatasourceConfig createPoso(MondrianDatasourceConfigDto dto)  throws ExpectedException {
		MondrianDatasourceConfig poso = new MondrianDatasourceConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public MondrianDatasourceConfig createUnmanagedPoso(MondrianDatasourceConfigDto dto)  throws ExpectedException {
		MondrianDatasourceConfig poso = new MondrianDatasourceConfig();

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

	public void mergePoso(MondrianDatasourceConfigDto dto, final MondrianDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(MondrianDatasourceConfigDto dto, final MondrianDatasourceConfig poso)  throws ExpectedException {
		/*  set cubeName */
		poso.setCubeName(dto.getCubeName() );

	}

	protected void mergeProxy2Poso(MondrianDatasourceConfigDto dto, final MondrianDatasourceConfig poso)  throws ExpectedException {
		/*  set cubeName */
		if(dto.isCubeNameModified()){
			poso.setCubeName(dto.getCubeName() );
		}

	}

	public void mergeUnmanagedPoso(MondrianDatasourceConfigDto dto, final MondrianDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(MondrianDatasourceConfigDto dto, final MondrianDatasourceConfig poso)  throws ExpectedException {
		/*  set cubeName */
		poso.setCubeName(dto.getCubeName() );

	}

	protected void mergeProxy2UnmanagedPoso(MondrianDatasourceConfigDto dto, final MondrianDatasourceConfig poso)  throws ExpectedException {
		/*  set cubeName */
		if(dto.isCubeNameModified()){
			poso.setCubeName(dto.getCubeName() );
		}

	}

	public MondrianDatasourceConfig loadAndMergePoso(MondrianDatasourceConfigDto dto)  throws ExpectedException {
		MondrianDatasourceConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(MondrianDatasourceConfigDto dto, MondrianDatasourceConfig poso)  {
	}


	public void postProcessCreateUnmanaged(MondrianDatasourceConfigDto dto, MondrianDatasourceConfig poso)  {
	}


	public void postProcessLoad(MondrianDatasourceConfigDto dto, MondrianDatasourceConfig poso)  {
	}


	public void postProcessMerge(MondrianDatasourceConfigDto dto, MondrianDatasourceConfig poso)  {
	}


	public void postProcessInstantiate(MondrianDatasourceConfig poso)  {
	}



}
