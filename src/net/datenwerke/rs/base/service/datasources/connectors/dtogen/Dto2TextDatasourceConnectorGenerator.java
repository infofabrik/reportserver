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
import net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto;
import net.datenwerke.rs.base.service.datasources.connectors.TextDatasourceConnector;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.Dto2TextDatasourceConnectorGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TextDatasourceConnector
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TextDatasourceConnectorGenerator implements Dto2PosoGenerator<TextDatasourceConnectorDto,TextDatasourceConnector> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TextDatasourceConnectorGenerator(
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

	public TextDatasourceConnector loadPoso(TextDatasourceConnectorDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TextDatasourceConnector poso = entityManager.find(TextDatasourceConnector.class, id);
		return poso;
	}

	public TextDatasourceConnector instantiatePoso()  {
		TextDatasourceConnector poso = new TextDatasourceConnector();
		return poso;
	}

	public TextDatasourceConnector createPoso(TextDatasourceConnectorDto dto)  throws ExpectedException {
		TextDatasourceConnector poso = new TextDatasourceConnector();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TextDatasourceConnector createUnmanagedPoso(TextDatasourceConnectorDto dto)  throws ExpectedException {
		TextDatasourceConnector poso = new TextDatasourceConnector();

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

	public void mergePoso(TextDatasourceConnectorDto dto, final TextDatasourceConnector poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TextDatasourceConnectorDto dto, final TextDatasourceConnector poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

	}

	protected void mergeProxy2Poso(TextDatasourceConnectorDto dto, final TextDatasourceConnector poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

	}

	public void mergeUnmanagedPoso(TextDatasourceConnectorDto dto, final TextDatasourceConnector poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TextDatasourceConnectorDto dto, final TextDatasourceConnector poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

	}

	protected void mergeProxy2UnmanagedPoso(TextDatasourceConnectorDto dto, final TextDatasourceConnector poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

	}

	public TextDatasourceConnector loadAndMergePoso(TextDatasourceConnectorDto dto)  throws ExpectedException {
		TextDatasourceConnector poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TextDatasourceConnectorDto dto, TextDatasourceConnector poso)  {
	}


	public void postProcessCreateUnmanaged(TextDatasourceConnectorDto dto, TextDatasourceConnector poso)  {
	}


	public void postProcessLoad(TextDatasourceConnectorDto dto, TextDatasourceConnector poso)  {
	}


	public void postProcessMerge(TextDatasourceConnectorDto dto, TextDatasourceConnector poso)  {
	}


	public void postProcessInstantiate(TextDatasourceConnector poso)  {
	}



}
