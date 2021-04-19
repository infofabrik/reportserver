package net.datenwerke.security.service.usermanager.entities.dtogen;

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
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.security.service.usermanager.entities.UserProperty;
import net.datenwerke.security.service.usermanager.entities.dtogen.Dto2UserPropertyGenerator;

/**
 * Dto2PosoGenerator for UserProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2UserPropertyGenerator implements Dto2PosoGenerator<UserPropertyDto,UserProperty> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2UserPropertyGenerator(
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

	public UserProperty loadPoso(UserPropertyDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		UserProperty poso = entityManager.find(UserProperty.class, id);
		return poso;
	}

	public UserProperty instantiatePoso()  {
		UserProperty poso = new UserProperty();
		return poso;
	}

	public UserProperty createPoso(UserPropertyDto dto)  throws ExpectedException {
		UserProperty poso = new UserProperty();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public UserProperty createUnmanagedPoso(UserPropertyDto dto)  throws ExpectedException {
		UserProperty poso = new UserProperty();

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

	public void mergePoso(UserPropertyDto dto, final UserProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(UserPropertyDto dto, final UserProperty poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(UserPropertyDto dto, final UserProperty poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(UserPropertyDto dto, final UserProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(UserPropertyDto dto, final UserProperty poso)  throws ExpectedException {
		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(UserPropertyDto dto, final UserProperty poso)  throws ExpectedException {
		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public UserProperty loadAndMergePoso(UserPropertyDto dto)  throws ExpectedException {
		UserProperty poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(UserPropertyDto dto, UserProperty poso)  {
	}


	public void postProcessCreateUnmanaged(UserPropertyDto dto, UserProperty poso)  {
	}


	public void postProcessLoad(UserPropertyDto dto, UserProperty poso)  {
	}


	public void postProcessMerge(UserPropertyDto dto, UserProperty poso)  {
	}


	public void postProcessInstantiate(UserProperty poso)  {
	}



}
