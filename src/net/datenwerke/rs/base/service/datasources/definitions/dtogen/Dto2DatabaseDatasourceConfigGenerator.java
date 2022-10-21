package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

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
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2DatabaseDatasourceConfigGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatabaseDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatabaseDatasourceConfigGenerator implements Dto2PosoGenerator<DatabaseDatasourceConfigDto,DatabaseDatasourceConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatabaseDatasourceConfigGenerator(
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

	public DatabaseDatasourceConfig loadPoso(DatabaseDatasourceConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatabaseDatasourceConfig poso = entityManager.find(DatabaseDatasourceConfig.class, id);
		return poso;
	}

	public DatabaseDatasourceConfig instantiatePoso()  {
		DatabaseDatasourceConfig poso = new DatabaseDatasourceConfig();
		return poso;
	}

	public DatabaseDatasourceConfig createPoso(DatabaseDatasourceConfigDto dto)  throws ExpectedException {
		DatabaseDatasourceConfig poso = new DatabaseDatasourceConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatabaseDatasourceConfig createUnmanagedPoso(DatabaseDatasourceConfigDto dto)  throws ExpectedException {
		DatabaseDatasourceConfig poso = new DatabaseDatasourceConfig();

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

	public void mergePoso(DatabaseDatasourceConfigDto dto, final DatabaseDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatabaseDatasourceConfigDto dto, final DatabaseDatasourceConfig poso)  throws ExpectedException {
		/*  set query */
		poso.setQuery(dto.getQuery() );

	}

	protected void mergeProxy2Poso(DatabaseDatasourceConfigDto dto, final DatabaseDatasourceConfig poso)  throws ExpectedException {
		/*  set query */
		if(dto.isQueryModified()){
			poso.setQuery(dto.getQuery() );
		}

	}

	public void mergeUnmanagedPoso(DatabaseDatasourceConfigDto dto, final DatabaseDatasourceConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatabaseDatasourceConfigDto dto, final DatabaseDatasourceConfig poso)  throws ExpectedException {
		/*  set query */
		poso.setQuery(dto.getQuery() );

	}

	protected void mergeProxy2UnmanagedPoso(DatabaseDatasourceConfigDto dto, final DatabaseDatasourceConfig poso)  throws ExpectedException {
		/*  set query */
		if(dto.isQueryModified()){
			poso.setQuery(dto.getQuery() );
		}

	}

	public DatabaseDatasourceConfig loadAndMergePoso(DatabaseDatasourceConfigDto dto)  throws ExpectedException {
		DatabaseDatasourceConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatabaseDatasourceConfigDto dto, DatabaseDatasourceConfig poso)  {
	}


	public void postProcessCreateUnmanaged(DatabaseDatasourceConfigDto dto, DatabaseDatasourceConfig poso)  {
	}


	public void postProcessLoad(DatabaseDatasourceConfigDto dto, DatabaseDatasourceConfig poso)  {
	}


	public void postProcessMerge(DatabaseDatasourceConfigDto dto, DatabaseDatasourceConfig poso)  {
	}


	public void postProcessInstantiate(DatabaseDatasourceConfig poso)  {
	}



}
