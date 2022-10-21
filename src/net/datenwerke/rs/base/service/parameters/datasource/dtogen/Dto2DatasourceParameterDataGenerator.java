package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

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
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2DatasourceParameterDataGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasourceParameterData
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasourceParameterDataGenerator implements Dto2PosoGenerator<DatasourceParameterDataDto,DatasourceParameterData> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasourceParameterDataGenerator(
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

	public DatasourceParameterData loadPoso(DatasourceParameterDataDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasourceParameterData poso = entityManager.find(DatasourceParameterData.class, id);
		return poso;
	}

	public DatasourceParameterData instantiatePoso()  {
		DatasourceParameterData poso = new DatasourceParameterData();
		return poso;
	}

	public DatasourceParameterData createPoso(DatasourceParameterDataDto dto)  throws ExpectedException {
		DatasourceParameterData poso = new DatasourceParameterData();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasourceParameterData createUnmanagedPoso(DatasourceParameterDataDto dto)  throws ExpectedException {
		DatasourceParameterData poso = new DatasourceParameterData();

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

	public void mergePoso(DatasourceParameterDataDto dto, final DatasourceParameterData poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasourceParameterDataDto dto, final DatasourceParameterData poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(DatasourceParameterDataDto dto, final DatasourceParameterData poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(DatasourceParameterDataDto dto, final DatasourceParameterData poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasourceParameterDataDto dto, final DatasourceParameterData poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(DatasourceParameterDataDto dto, final DatasourceParameterData poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public DatasourceParameterData loadAndMergePoso(DatasourceParameterDataDto dto)  throws ExpectedException {
		DatasourceParameterData poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasourceParameterDataDto dto, DatasourceParameterData poso)  {
	}


	public void postProcessCreateUnmanaged(DatasourceParameterDataDto dto, DatasourceParameterData poso)  {
	}


	public void postProcessLoad(DatasourceParameterDataDto dto, DatasourceParameterData poso)  {
	}


	public void postProcessMerge(DatasourceParameterDataDto dto, DatasourceParameterData poso)  {
	}


	public void postProcessInstantiate(DatasourceParameterData poso)  {
	}



}
