package net.datenwerke.security.service.security.entities.dtogen;

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
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.service.security.entities.AceAccessMap;
import net.datenwerke.security.service.security.entities.dtogen.Dto2AceAccessMapGenerator;

/**
 * Dto2PosoGenerator for AceAccessMap
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AceAccessMapGenerator implements Dto2PosoGenerator<AceAccessMapDto,AceAccessMap> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2AceAccessMapGenerator(
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

	public AceAccessMap loadPoso(AceAccessMapDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		AceAccessMap poso = entityManager.find(AceAccessMap.class, id);
		return poso;
	}

	public AceAccessMap instantiatePoso()  {
		AceAccessMap poso = new AceAccessMap();
		return poso;
	}

	public AceAccessMap createPoso(AceAccessMapDto dto)  throws ExpectedException {
		AceAccessMap poso = new AceAccessMap();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public AceAccessMap createUnmanagedPoso(AceAccessMapDto dto)  throws ExpectedException {
		AceAccessMap poso = new AceAccessMap();

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

	public void mergePoso(AceAccessMapDto dto, final AceAccessMap poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(AceAccessMapDto dto, final AceAccessMap poso)  throws ExpectedException {
		/*  set access */
		poso.setAccess(dto.getAccess() );

		/*  set securee */
		poso.setSecuree(dto.getSecuree() );

	}

	protected void mergeProxy2Poso(AceAccessMapDto dto, final AceAccessMap poso)  throws ExpectedException {
		/*  set access */
		if(dto.isAccessModified()){
			poso.setAccess(dto.getAccess() );
		}

		/*  set securee */
		if(dto.isSecureeModified()){
			poso.setSecuree(dto.getSecuree() );
		}

	}

	public void mergeUnmanagedPoso(AceAccessMapDto dto, final AceAccessMap poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(AceAccessMapDto dto, final AceAccessMap poso)  throws ExpectedException {
		/*  set access */
		poso.setAccess(dto.getAccess() );

		/*  set securee */
		poso.setSecuree(dto.getSecuree() );

	}

	protected void mergeProxy2UnmanagedPoso(AceAccessMapDto dto, final AceAccessMap poso)  throws ExpectedException {
		/*  set access */
		if(dto.isAccessModified()){
			poso.setAccess(dto.getAccess() );
		}

		/*  set securee */
		if(dto.isSecureeModified()){
			poso.setSecuree(dto.getSecuree() );
		}

	}

	public AceAccessMap loadAndMergePoso(AceAccessMapDto dto)  throws ExpectedException {
		AceAccessMap poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(AceAccessMapDto dto, AceAccessMap poso)  {
	}


	public void postProcessCreateUnmanaged(AceAccessMapDto dto, AceAccessMap poso)  {
	}


	public void postProcessLoad(AceAccessMapDto dto, AceAccessMap poso)  {
	}


	public void postProcessMerge(AceAccessMapDto dto, AceAccessMap poso)  {
	}


	public void postProcessInstantiate(AceAccessMap poso)  {
	}



}
