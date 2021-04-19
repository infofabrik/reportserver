package net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen;

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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DashboardContainerGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DashboardContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DashboardContainerGenerator implements Dto2PosoGenerator<DashboardContainerDto,DashboardContainer> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DashboardContainerGenerator(
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

	public DashboardContainer loadPoso(DashboardContainerDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DashboardContainer poso = entityManager.find(DashboardContainer.class, id);
		return poso;
	}

	public DashboardContainer instantiatePoso()  {
		DashboardContainer poso = new DashboardContainer();
		return poso;
	}

	public DashboardContainer createPoso(DashboardContainerDto dto)  throws ExpectedException {
		DashboardContainer poso = new DashboardContainer();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DashboardContainer createUnmanagedPoso(DashboardContainerDto dto)  throws ExpectedException {
		DashboardContainer poso = new DashboardContainer();

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

	public void mergePoso(DashboardContainerDto dto, final DashboardContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DashboardContainerDto dto, final DashboardContainer poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(DashboardContainerDto dto, final DashboardContainer poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(DashboardContainerDto dto, final DashboardContainer poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DashboardContainerDto dto, final DashboardContainer poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(DashboardContainerDto dto, final DashboardContainer poso)  throws ExpectedException {
	}

	public DashboardContainer loadAndMergePoso(DashboardContainerDto dto)  throws ExpectedException {
		DashboardContainer poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DashboardContainerDto dto, DashboardContainer poso)  {
	}


	public void postProcessCreateUnmanaged(DashboardContainerDto dto, DashboardContainer poso)  {
	}


	public void postProcessLoad(DashboardContainerDto dto, DashboardContainer poso)  {
	}


	public void postProcessMerge(DashboardContainerDto dto, DashboardContainer poso)  {
	}


	public void postProcessInstantiate(DashboardContainer poso)  {
	}



}
