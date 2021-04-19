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
import net.datenwerke.rs.base.client.datasources.dto.ArgumentDatasourceConnectorDto;
import net.datenwerke.rs.base.service.datasources.connectors.ArgumentDatasourceConnector;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2ArgumentDatasourceConnectorGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ArgumentDatasourceConnector
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ArgumentDatasourceConnectorGenerator implements Dto2PosoGenerator<ArgumentDatasourceConnectorDto,ArgumentDatasourceConnector> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ArgumentDatasourceConnectorGenerator(
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

	public ArgumentDatasourceConnector loadPoso(ArgumentDatasourceConnectorDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ArgumentDatasourceConnector poso = entityManager.find(ArgumentDatasourceConnector.class, id);
		return poso;
	}

	public ArgumentDatasourceConnector instantiatePoso()  {
		ArgumentDatasourceConnector poso = new ArgumentDatasourceConnector();
		return poso;
	}

	public ArgumentDatasourceConnector createPoso(ArgumentDatasourceConnectorDto dto)  throws ExpectedException {
		ArgumentDatasourceConnector poso = new ArgumentDatasourceConnector();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ArgumentDatasourceConnector createUnmanagedPoso(ArgumentDatasourceConnectorDto dto)  throws ExpectedException {
		ArgumentDatasourceConnector poso = new ArgumentDatasourceConnector();

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

	public void mergePoso(ArgumentDatasourceConnectorDto dto, final ArgumentDatasourceConnector poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ArgumentDatasourceConnectorDto dto, final ArgumentDatasourceConnector poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(ArgumentDatasourceConnectorDto dto, final ArgumentDatasourceConnector poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(ArgumentDatasourceConnectorDto dto, final ArgumentDatasourceConnector poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ArgumentDatasourceConnectorDto dto, final ArgumentDatasourceConnector poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(ArgumentDatasourceConnectorDto dto, final ArgumentDatasourceConnector poso)  throws ExpectedException {
	}

	public ArgumentDatasourceConnector loadAndMergePoso(ArgumentDatasourceConnectorDto dto)  throws ExpectedException {
		ArgumentDatasourceConnector poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ArgumentDatasourceConnectorDto dto, ArgumentDatasourceConnector poso)  {
	}


	public void postProcessCreateUnmanaged(ArgumentDatasourceConnectorDto dto, ArgumentDatasourceConnector poso)  {
	}


	public void postProcessLoad(ArgumentDatasourceConnectorDto dto, ArgumentDatasourceConnector poso)  {
	}


	public void postProcessMerge(ArgumentDatasourceConnectorDto dto, ArgumentDatasourceConnector poso)  {
	}


	public void postProcessInstantiate(ArgumentDatasourceConnector poso)  {
	}



}
