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
import net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto;
import net.datenwerke.rs.base.service.datasources.connectors.UrlDatasourceConnector;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2UrlDatasourceConnectorGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for UrlDatasourceConnector
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2UrlDatasourceConnectorGenerator implements Dto2PosoGenerator<UrlDatasourceConnectorDto,UrlDatasourceConnector> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2UrlDatasourceConnectorGenerator(
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

	public UrlDatasourceConnector loadPoso(UrlDatasourceConnectorDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		UrlDatasourceConnector poso = entityManager.find(UrlDatasourceConnector.class, id);
		return poso;
	}

	public UrlDatasourceConnector instantiatePoso()  {
		UrlDatasourceConnector poso = new UrlDatasourceConnector();
		return poso;
	}

	public UrlDatasourceConnector createPoso(UrlDatasourceConnectorDto dto)  throws ExpectedException {
		UrlDatasourceConnector poso = new UrlDatasourceConnector();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public UrlDatasourceConnector createUnmanagedPoso(UrlDatasourceConnectorDto dto)  throws ExpectedException {
		UrlDatasourceConnector poso = new UrlDatasourceConnector();

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

	public void mergePoso(UrlDatasourceConnectorDto dto, final UrlDatasourceConnector poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(UrlDatasourceConnectorDto dto, final UrlDatasourceConnector poso)  throws ExpectedException {
		/*  set url */
		poso.setUrl(dto.getUrl() );

	}

	protected void mergeProxy2Poso(UrlDatasourceConnectorDto dto, final UrlDatasourceConnector poso)  throws ExpectedException {
		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

	}

	public void mergeUnmanagedPoso(UrlDatasourceConnectorDto dto, final UrlDatasourceConnector poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(UrlDatasourceConnectorDto dto, final UrlDatasourceConnector poso)  throws ExpectedException {
		/*  set url */
		poso.setUrl(dto.getUrl() );

	}

	protected void mergeProxy2UnmanagedPoso(UrlDatasourceConnectorDto dto, final UrlDatasourceConnector poso)  throws ExpectedException {
		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

	}

	public UrlDatasourceConnector loadAndMergePoso(UrlDatasourceConnectorDto dto)  throws ExpectedException {
		UrlDatasourceConnector poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(UrlDatasourceConnectorDto dto, UrlDatasourceConnector poso)  {
	}


	public void postProcessCreateUnmanaged(UrlDatasourceConnectorDto dto, UrlDatasourceConnector poso)  {
	}


	public void postProcessLoad(UrlDatasourceConnectorDto dto, UrlDatasourceConnector poso)  {
	}


	public void postProcessMerge(UrlDatasourceConnectorDto dto, UrlDatasourceConnector poso)  {
	}


	public void postProcessInstantiate(UrlDatasourceConnector poso)  {
	}



}
