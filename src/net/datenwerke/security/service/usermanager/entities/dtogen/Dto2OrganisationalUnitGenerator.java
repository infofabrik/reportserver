package net.datenwerke.security.service.usermanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
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
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.dtogen.Dto2OrganisationalUnitGenerator;

/**
 * Dto2PosoGenerator for OrganisationalUnit
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2OrganisationalUnitGenerator implements Dto2PosoGenerator<OrganisationalUnitDto,OrganisationalUnit> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2OrganisationalUnitGenerator(
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

	public OrganisationalUnit loadPoso(OrganisationalUnitDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		OrganisationalUnit poso = entityManager.find(OrganisationalUnit.class, id);
		return poso;
	}

	public OrganisationalUnit instantiatePoso()  {
		OrganisationalUnit poso = new OrganisationalUnit();
		return poso;
	}

	public OrganisationalUnit createPoso(OrganisationalUnitDto dto)  throws ExpectedException {
		OrganisationalUnit poso = new OrganisationalUnit();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public OrganisationalUnit createUnmanagedPoso(OrganisationalUnitDto dto)  throws ExpectedException {
		OrganisationalUnit poso = new OrganisationalUnit();

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

	public void mergePoso(OrganisationalUnitDto dto, final OrganisationalUnit poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(OrganisationalUnitDto dto, final OrganisationalUnit poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set guid */
		poso.setGuid(dto.getGuid() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set origin */
		poso.setOrigin(dto.getOrigin() );

	}

	protected void mergeProxy2Poso(OrganisationalUnitDto dto, final OrganisationalUnit poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set guid */
		if(dto.isGuidModified()){
			poso.setGuid(dto.getGuid() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set origin */
		if(dto.isOriginModified()){
			poso.setOrigin(dto.getOrigin() );
		}

	}

	public void mergeUnmanagedPoso(OrganisationalUnitDto dto, final OrganisationalUnit poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(OrganisationalUnitDto dto, final OrganisationalUnit poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set guid */
		poso.setGuid(dto.getGuid() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set origin */
		poso.setOrigin(dto.getOrigin() );

	}

	protected void mergeProxy2UnmanagedPoso(OrganisationalUnitDto dto, final OrganisationalUnit poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set guid */
		if(dto.isGuidModified()){
			poso.setGuid(dto.getGuid() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set origin */
		if(dto.isOriginModified()){
			poso.setOrigin(dto.getOrigin() );
		}

	}

	public OrganisationalUnit loadAndMergePoso(OrganisationalUnitDto dto)  throws ExpectedException {
		OrganisationalUnit poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(OrganisationalUnitDto dto, OrganisationalUnit poso)  {
	}


	public void postProcessCreateUnmanaged(OrganisationalUnitDto dto, OrganisationalUnit poso)  {
	}


	public void postProcessLoad(OrganisationalUnitDto dto, OrganisationalUnit poso)  {
	}


	public void postProcessMerge(OrganisationalUnitDto dto, OrganisationalUnit poso)  {
	}


	public void postProcessInstantiate(OrganisationalUnit poso)  {
	}



}
