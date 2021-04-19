package net.datenwerke.rs.globalconstants.service.globalconstants.entities.dtogen;

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
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.dtogen.Dto2GlobalConstantGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for GlobalConstant
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2GlobalConstantGenerator implements Dto2PosoGenerator<GlobalConstantDto,GlobalConstant> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2GlobalConstantGenerator(
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

	public GlobalConstant loadPoso(GlobalConstantDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		GlobalConstant poso = entityManager.find(GlobalConstant.class, id);
		return poso;
	}

	public GlobalConstant instantiatePoso()  {
		GlobalConstant poso = new GlobalConstant();
		return poso;
	}

	public GlobalConstant createPoso(GlobalConstantDto dto)  throws ExpectedException {
		GlobalConstant poso = new GlobalConstant();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public GlobalConstant createUnmanagedPoso(GlobalConstantDto dto)  throws ExpectedException {
		GlobalConstant poso = new GlobalConstant();

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

	public void mergePoso(GlobalConstantDto dto, final GlobalConstant poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(GlobalConstantDto dto, final GlobalConstant poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(GlobalConstantDto dto, final GlobalConstant poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(GlobalConstantDto dto, final GlobalConstant poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(GlobalConstantDto dto, final GlobalConstant poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(GlobalConstantDto dto, final GlobalConstant poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public GlobalConstant loadAndMergePoso(GlobalConstantDto dto)  throws ExpectedException {
		GlobalConstant poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(GlobalConstantDto dto, GlobalConstant poso)  {
	}


	public void postProcessCreateUnmanaged(GlobalConstantDto dto, GlobalConstant poso)  {
	}


	public void postProcessLoad(GlobalConstantDto dto, GlobalConstant poso)  {
	}


	public void postProcessMerge(GlobalConstantDto dto, GlobalConstant poso)  {
	}


	public void postProcessInstantiate(GlobalConstant poso)  {
	}



}
