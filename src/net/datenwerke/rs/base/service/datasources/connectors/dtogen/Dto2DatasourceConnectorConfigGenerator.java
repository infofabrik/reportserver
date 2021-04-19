package net.datenwerke.rs.base.service.datasources.connectors.dtogen;

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
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2DatasourceConnectorConfigGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasourceConnectorConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasourceConnectorConfigGenerator implements Dto2PosoGenerator<DatasourceConnectorConfigDto,DatasourceConnectorConfig> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasourceConnectorConfigGenerator(
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

	public DatasourceConnectorConfig loadPoso(DatasourceConnectorConfigDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasourceConnectorConfig poso = entityManager.find(DatasourceConnectorConfig.class, id);
		return poso;
	}

	public DatasourceConnectorConfig instantiatePoso()  {
		DatasourceConnectorConfig poso = new DatasourceConnectorConfig();
		return poso;
	}

	public DatasourceConnectorConfig createPoso(DatasourceConnectorConfigDto dto)  throws ExpectedException {
		DatasourceConnectorConfig poso = new DatasourceConnectorConfig();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasourceConnectorConfig createUnmanagedPoso(DatasourceConnectorConfigDto dto)  throws ExpectedException {
		DatasourceConnectorConfig poso = new DatasourceConnectorConfig();

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

	public void mergePoso(DatasourceConnectorConfigDto dto, final DatasourceConnectorConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasourceConnectorConfigDto dto, final DatasourceConnectorConfig poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(DatasourceConnectorConfigDto dto, final DatasourceConnectorConfig poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(DatasourceConnectorConfigDto dto, final DatasourceConnectorConfig poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasourceConnectorConfigDto dto, final DatasourceConnectorConfig poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(DatasourceConnectorConfigDto dto, final DatasourceConnectorConfig poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public DatasourceConnectorConfig loadAndMergePoso(DatasourceConnectorConfigDto dto)  throws ExpectedException {
		DatasourceConnectorConfig poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasourceConnectorConfigDto dto, DatasourceConnectorConfig poso)  {
	}


	public void postProcessCreateUnmanaged(DatasourceConnectorConfigDto dto, DatasourceConnectorConfig poso)  {
	}


	public void postProcessLoad(DatasourceConnectorConfigDto dto, DatasourceConnectorConfig poso)  {
	}


	public void postProcessMerge(DatasourceConnectorConfigDto dto, DatasourceConnectorConfig poso)  {
	}


	public void postProcessInstantiate(DatasourceConnectorConfig poso)  {
	}



}
